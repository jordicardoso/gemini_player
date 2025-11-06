// Fichero: src/main/java/com/gbook/api/model/PlayerState.java
package com.gbook.api.model;

import java.util.HashMap;
import java.util.Map;

public class PlayerState {

    // La clave es el nombre de la estadística (ej. "fatiga")
    // El valor es el objeto Stat completo.
    private Map<String, Stat> stats = new HashMap<>();

    // Podrías añadir aquí otros campos como inventario, eventos, etc.
    // private List<Item> inventory = new ArrayList<>();

    public PlayerState() {
    }

    public Map<String, Stat> getStats() {
        return stats;
    }

    public void setStats(Map<String, Stat> stats) {
        this.stats = stats;
    }
}