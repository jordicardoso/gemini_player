package com.gbook.api;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.gbook.api.model.JsonGamebook;

@ApplicationScoped
public class Routes extends RouteBuilder {

        @Override
        public void configure() throws Exception {

                // ------------------------------------------------------------------------------------------
                // Gbook API REST
                // ------------------------------------------------------------------------------------------

                restConfiguration().bindingMode(RestBindingMode.json)
                        .component("platform-http")
                        .bindingMode(RestBindingMode.json)
                        .clientRequestValidation(true)
                        .apiContextPath("/api-doc")
                        .apiProperty("api.title", "Gemini Player")
                        .apiProperty("api.version", "1.0.0");

                rest().openApi().specification("openapi.json").missingOperation("ignore");
                
                from("direct:getHealth")
                        .routeId("getHealthRoute")
                        .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(200))
                        .setBody().constant("Ok");

                from("direct:postBook")
                        .routeId("postBookRoute")
                        .marshal().json()
                        .unmarshal().json(JsonGamebook.class)
                        .log("Received a processed JsonGamebook object. Title: ${body.meta.title}")
                        .to("bean:gameManager?method=loadGame")
                        .log("Game loaded. Initiating the game loop asynchronously...")
                        // Usamos toD para crear un endpoint dinámico y evitar que se bloquee el hilo
                        // principal
                        .toD("seda:startGameLoop")
                        .setBody(constant("Gamebook processing initiated. Check logs for progress."))
                        .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(202));

                // ------------------------------------------------------------------------------------------
                // Rutas del Bucle del Juego
                // ------------------------------------------------------------------------------------------

                from("seda:startGameLoop")
                        .routeId("startGameLoopRoute")
                        .log("Starting game loop after a short delay...")
                        .delay(1000)
                        .to("direct:gameLoop");

                from("direct:gameLoop")
                        .routeId("gameLoopRoute")
                        .log("--- NEW GAME TURN ---")
                        .to("bean:gameManager?method=getCurrentTurnContext")
                        .to("bean:promptBuilder?method=buildPrompt")
                        .log("Prompt sent to Gemini: ${body}")
                        .to("bean:geminiService?method=generateContent")
                        .log("Response from Gemini: ${body}")
                        .to("bean:responseParser?method=parse")
                        .log("Parsed decision from Gemini: ${body}")
                        .to("bean:gameManager?method=processDecision")
                        .log("Decision processed. Checking if game is over...")
                        .choice()
                        .when(method("gameManager", "isGameOver"))
                        .log("Game is over. Transitioning to end game route.")
                        .to("direct:endGame")
                        .otherwise()
                        .log("Game continues. Looping for next turn.")
                        .to("seda:startGameLoop")
                        .end();

                from("direct:endGame")
                        .routeId("endGameRoute")
                        .log("====== GAME OVER ======")
                        .to("bean:gameManager?method=getCurrentTurnContext")
                        .log("Final game result description: ${body.currentNode.description}");
}
}