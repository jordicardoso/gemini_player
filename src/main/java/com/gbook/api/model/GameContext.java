// Ejemplo de GameContext.java
package com.gbook.api.model;

public class GameContext {
    private Node currentNode;
    private PlayerState playerState;
    private JsonGamebook gamebook; // Añadir el gamebook completo

    public GameContext(Node currentNode, PlayerState playerState, JsonGamebook gamebook) {
        this.currentNode = currentNode;
        this.playerState = playerState;
        this.gamebook = gamebook;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public JsonGamebook getGamebook() { // Getter para el gamebook
        return gamebook;
    }
    // ... otros getters si son necesarios
}
    