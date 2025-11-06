package com.gbook.api.beans;

import com.gbook.api.model.GeminiRequest; // Importa la clase GeminiRequest desde su nueva ubicación
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@RegisterForReflection
@ApplicationScoped
@Named("geminiRequestProcessor")
public class GeminiRequestProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(GeminiRequestProcessor.class);

    /**
     * Toma el prompt de texto del cuerpo del mensaje de Camel y lo envuelve en la estructura JSON
     * esperada por la API de Gemini.
     *
     * @param exchange El Exchange de Camel que contiene el prompt en el cuerpo (como String).
     * @return Un objeto GeminiRequest listo para ser serializado a JSON.
     */
    public GeminiRequest buildGeminiRequestBody(Exchange exchange) {
        // Obtener el prompt de texto del cuerpo del mensaje de entrada
        String promptText = exchange.getIn().getBody(String.class);
        LOG.debug("Building Gemini request body for prompt: {}", promptText);

        // Construir la estructura GeminiRequest utilizando el método de utilidad
        return GeminiRequest.fromPromptText(promptText);
    }
}