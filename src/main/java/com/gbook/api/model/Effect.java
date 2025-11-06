package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Effect {
    private String target;
    private int value;

    public Effect() {}

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}