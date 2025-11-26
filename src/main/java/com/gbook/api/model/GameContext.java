// Fichero: src/main/java/com/gbook/api/model/GameContext.java
package com.gbook.api.model;

import java.util.List;

public class GameContext {
    private final Node currentNode;
    private final CharacterSheet playerState; // <-- CORRECCIÓN: Usamos CharacterSheet
    private final JsonGamebook gamebook;
    private final List<HistoryEntry> history;

    public GameContext(Node currentNode, CharacterSheet playerState, JsonGamebook gamebook,
            List<HistoryEntry> history) {
        this.currentNode = currentNode;
        this.playerState = playerState;
        this.gamebook = gamebook;
        this.history = history;
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

    public List<HistoryEntry> getHistory() {
        return history;
    }
}
