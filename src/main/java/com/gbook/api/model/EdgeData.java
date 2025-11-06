package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EdgeData {
    private String description;

    public EdgeData() {}

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}