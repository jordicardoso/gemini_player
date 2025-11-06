package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String name;
    private String description;
    private List<Effect> effects;
    private String id;

    public Item() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Effect> getEffects() { return effects; }
    public void setEffects(List<Effect> effects) { this.effects = effects; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}