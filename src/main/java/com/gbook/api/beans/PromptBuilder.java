// Fichero: src/main/java/com/gbook/api/beans/PromptBuilder.java
package com.gbook.api.beans;

import com.gbook.api.model.GameContext;
import com.gbook.api.model.Edge; // Importamos la clase Edge
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.stream.Collectors;

@RegisterForReflection
@ApplicationScoped
@Named("promptBuilder")
public class PromptBuilder {

    public String buildPrompt(GameContext context) {
        if (context == null || context.getCurrentNode() == null || context.getGamebook() == null) {
            return "Error: No game context available.";
        }

        StringBuilder prompt = new StringBuilder();
        prompt.append("Eres un jugador de rol que debe tomar la mejor decisión para sobrevivir y progresar en la historia.\n\n");

        // 1. Estado del Personaje (simplificado para mayor claridad)
        prompt.append("== ESTADO ACTUAL ==\n");
        if (context.getPlayerState() != null && context.getPlayerState().getStats() != null) {
            String statsString = context.getPlayerState().getStats().entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue().getCurrent())
                    .collect(Collectors.joining(", "));
            prompt.append("Estadísticas: ").append(statsString).append("\n");
        }
        // Podríamos añadir el inventario de forma similar si fuera necesario
        prompt.append("\n");


        // 2. Situación Actual (la descripción del nodo actual)
        prompt.append("== SITUACIÓN ==\n");
        prompt.append(context.getCurrentNode().getData().getDescription()).append("\n\n");

        // 3. Opciones Disponibles (obtenidas de los 'edges' que salen del nodo actual)
        prompt.append("== OPCIONES ==\n");
        String currentNodeId = context.getCurrentNode().getId();
        String options = context.getGamebook().getEdges().stream()
                .filter(edge -> currentNodeId.equals(edge.getSource())) // Filtramos los edges que salen del nodo actual
                .map(edge -> "Opción con ID '" + edge.getTarget() + "': " + edge.getLabel()) // Usamos el target del edge como ID de la opción
                .collect(Collectors.joining("\n"));

        if (options.isEmpty()) {
            options = "No hay más opciones. La historia ha terminado.";
        }
        prompt.append(options).append("\n\n");

        // 4. Instrucción clara para Gemini
        prompt.append("== INSTRUCCIÓN ==\n");
        prompt.append("Analiza tu estado y la situación. Elige la opción que consideres más inteligente o apropiada. Responde únicamente con el ID de la opción que elijas (por ejemplo, si eliges la opción con ID 'b5e2f5f7-cade-4d61-88f0-cc1bb6c5592c', tu respuesta debe ser solo 'b5e2f5f7-cade-4d61-88f0-cc1bb6c5592c').");

        return prompt.toString();
    }
}