// Fichero: src/main/java/com/gbook/api/model/Stat.java
package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stat {
    private int current;
    private int max;
    private int min;

    // Constructor por defecto para Jackson
    public Stat() {}

    // Constructor para facilitar la creación en GameManager
    public Stat(int current, int max, int min) {
        this.current = current;
        this.max = max;
        this.min = min;
    }

    public int getCurrent() { return current; }
    public void setCurrent(int current) { this.current = current; }

    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }

    public int getMin() { return min; }
    public void setMin(int min) { this.min = min; }
}