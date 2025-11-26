// Fichero: src/main/java/com/gbook/api/beans/PromptBuilder.java
package com.gbook.api.beans;

import com.gbook.api.model.CharacterEvent;
import com.gbook.api.model.CharacterSheet;
import com.gbook.api.model.GameContext;
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
        prompt.append(
                "Eres un jugador de rol que debe tomar la mejor decisión para sobrevivir y progresar en la historia.\n\n");

        // --- ¡AQUÍ ESTÁ LA CORRECCIÓN CLAVE! ---
        // 1. Estado del Personaje (ahora mucho más completo)
        prompt.append("== ESTADO ACTUAL ==\n");
        CharacterSheet playerState = context.getPlayerState();
        if (playerState != null) {
            // Estadísticas
            if (playerState.getStats() != null && !playerState.getStats().isEmpty()) {
                String statsString = playerState.getStats().entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue().getCurrent() + "/"
                                + entry.getValue().getMax())
                        .collect(Collectors.joining(", "));
                prompt.append("Estadísticas: ").append(statsString).append("\n");
            }

            // Inventario/Items
            if (playerState.getItemSectionSlots() != null && !playerState.getItemSectionSlots().isEmpty()) {
                String itemsString = playerState.getItemSectionSlots().entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue().getName())
                        .collect(Collectors.joining(", "));
                prompt.append("Equipamiento: ").append(itemsString).append("\n");
            }

            // Eventos Activos
            if (playerState.getEventsDefault() != null && !playerState.getEventsDefault().isEmpty()) {
                String eventsString = playerState.getEventsDefault().stream()
                        .filter(CharacterEvent::isHappened) // Mostramos solo los eventos que han ocurrido
                        .map(CharacterEvent::getName)
                        .collect(Collectors.joining(", "));
                if (!eventsString.isEmpty()) {
                    prompt.append("Eventos: ").append(eventsString).append("\n");
                }
            }
        }
        prompt.append("\n");

        // 1.5. Historia (NUEVO)
        if (context.getHistory() != null && !context.getHistory().isEmpty()) {
            prompt.append("== HISTORIA PREVIA ==\n");
            for (com.gbook.api.model.HistoryEntry entry : context.getHistory()) {
                prompt.append("Narración previa: ").append(entry.getNodeDescription()).append("\n");
                prompt.append("Decisión tomada: ").append(entry.getChoiceLabel()).append("\n\n");
            }
        }

        prompt.append("== SITUACIÓN ==\n");
        prompt.append(context.getCurrentNode().getData().getDescription()).append("\n\n");

        // 3. Opciones Disponibles
        prompt.append("== OPCIONES ==\n");
        String options = "";
        if (context.getCurrentNode().getData().getChoices() != null) {
            options = context.getCurrentNode().getData().getChoices().stream()
                    .map(choice -> {
                        Integer targetParagraph = context.getGamebook().getNodes().stream()
                                .filter(n -> n.getId().equals(choice.getTargetNodeId()))
                                .findFirst()
                                .map(n -> n.getData().getParagraphNumber())
                                .orElse(null);
                        return "Opción con ID '" + choice.getTargetNodeId() + "': " + choice.getLabel() + " "
                                + (targetParagraph != null ? targetParagraph : "");
                    })
                    .collect(Collectors.joining("\n"));
        }

        if (options.isEmpty()) {
            options = "No hay más opciones. La historia ha terminado.";
        }
        prompt.append(options).append("\n\n");

        // 4. Instrucción clara para Gemini
        prompt.append("== INSTRUCCIÓN ==\n");
        prompt.append(
                "Analiza tu estado y la situación. Elige la opción que consideres más inteligente o apropiada. Responde únicamente con el ID de la opción que elijas (por ejemplo, si eliges la opción con ID 'b5e2f5f7-cade-4d61-88f0-cc1bb6c5592c', tu respuesta debe ser solo 'b5e2f5f7-cade-4d61-88f0-cc1bb6c5592c').");

        return prompt.toString();
    }
}