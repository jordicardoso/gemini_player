// Fichero: src/main/java/com/gbook/api/beans/GameManager.java
package com.gbook.api.beans;

import com.gbook.api.model.CharacterSheet;
import com.gbook.api.model.GameContext;
import com.gbook.api.model.JsonGamebook;
import com.gbook.api.model.Node;
import com.gbook.api.model.PlayerState;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RegisterForReflection
@ApplicationScoped
@Named("gameManager")
public class GameManager {

    private static final Logger LOG = LoggerFactory.getLogger(GameManager.class);

    private JsonGamebook currentGamebook;
    private CharacterSheet playerState;
    private String currentPosition; // ID del nodo actual

    // Usamos un mapa para un acceso súper rápido a los nodos por su ID
    private Map<String, Node> nodeMap;

    public void loadGame(JsonGamebook gamebook) {
        LOG.info("Loading new gamebook: {}", gamebook.getMeta().getTitle());
        this.currentGamebook = gamebook;

        // Obtener el ID del nodo inicial
        String startNodeId = gamebook.getStartNodeId();
        if (startNodeId == null) {
            LOG.error("No start node found in gamebook: {}", gamebook.getMeta().getTitle());
            this.currentPosition = null;
        } else {
            this.currentPosition = startNodeId;
        }

        // --- ¡AQUÍ ESTÁ LA CORRECCIÓN CLAVE! ---
        // Inicializa el mapa de nodos para un acceso rápido.
        if (gamebook.getNodes() != null) {
            this.nodeMap = gamebook.getNodes().stream()
                    .collect(Collectors.toMap(Node::getId, Function.identity()));
        }

        // Inicializa el estado del jugador (podrías cargarlo desde el JSON también)
        this.playerState = gamebook.getCharacterSheet();
        if (this.playerState == null) {
            LOG.warn("No characterSheet found in the gamebook. Player state will be empty.");
            this.playerState = new CharacterSheet(); // Creamos uno vacío para evitar NullPointerExceptions
        }
        // Aquí podrías cargar el estado inicial desde gamebook.getCharacterSheet() si lo deseas.

        LOG.info("Game loaded. Starting at node: {}. Node map initialized with {} nodes.",
                this.currentPosition, this.nodeMap != null ? this.nodeMap.size() : 0);
    }

    /**
     * Construye y devuelve el contexto completo del turno actual.
     * Este objeto se enviará al PromptBuilder.
     */
    public GameContext getCurrentTurnContext() {
        if (currentPosition == null || nodeMap == null || !nodeMap.containsKey(currentPosition)) {
            LOG.error("Error: Current position '{}' is invalid or nodeMap is not initialized. Game cannot continue.", currentPosition);
            return new GameContext(null, playerState, currentGamebook);
        }
        Node currentNode = nodeMap.get(currentPosition);
        return new GameContext(currentNode, playerState, currentGamebook);
    }

    /**
     * Procesa la decisión tomada por Gemini.
     * La 'decision' debería ser el ID del siguiente nodo.
     */
    public void processDecision(String decisionNodeId) {
        if (currentGamebook == null || currentPosition == null || nodeMap == null || !nodeMap.containsKey(currentPosition)) {
            LOG.warn("Cannot process decision. Game not loaded or current position is invalid.");
            return;
        }

        Node currentNode = nodeMap.get(currentPosition);

        boolean isValidChoice = currentGamebook.getEdges().stream()
                .filter(edge -> currentNode.getId().equals(edge.getSource()))
                .anyMatch(edge -> edge.getTarget().equals(decisionNodeId));

        if (isValidChoice) {
            LOG.info("Player chose a valid path. Moving from node '{}' to '{}'", currentPosition, decisionNodeId);
            this.currentPosition = decisionNodeId;
        } else {
            LOG.warn("Gemini returned an invalid decision '{}' for node '{}'. Staying in the same node.", decisionNodeId, currentPosition);
        }
    }

    /**
     * Comprueba si el juego ha terminado.
     */
    public boolean isGameOver() {
        if (currentPosition == null || nodeMap == null || !nodeMap.containsKey(currentPosition)) {
            return true;
        }
        Node currentNode = nodeMap.get(currentPosition);

        if ("end".equalsIgnoreCase(currentNode.getType())) {
            LOG.info("Game over: Reached an 'end' node (ID: {}).", currentNode.getId());
            return true;
        }

        boolean hasOutgoingEdges = currentGamebook.getEdges().stream()
                .anyMatch(edge -> currentNode.getId().equals(edge.getSource()));

        if (!hasOutgoingEdges) {
            LOG.info("Game over: Current node (ID: {}) has no outgoing edges.", currentNode.getId());
        }
        return !hasOutgoingEdges;
    }

    // Getters
    public JsonGamebook getCurrentGamebook() {
        return currentGamebook;
    }

    public CharacterSheet getPlayerState() {
        return playerState;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }
}