// Fichero: src/main/java/com/gbook/api/model/GameContext.java
package com.gbook.api.model;

public class GameContext {
    private final Node currentNode;
    private final CharacterSheet playerState; // <-- CORRECCIÓN: Usamos CharacterSheet
    private final JsonGamebook gamebook;

    public GameContext(Node currentNode, CharacterSheet playerState, JsonGamebook gamebook) {
        this.currentNode = currentNode;
        this.playerState = playerState;
        this.gamebook = gamebook;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public CharacterSheet getPlayerState() { // <-- El tipo de retorno ahora es CharacterSheet
        return playerState;
    }

    public JsonGamebook getGamebook() {
        return gamebook;
    }
}
