package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterSheet {
    private Map<String, Stat> stats;
    @JsonProperty("itemSection_slots_1762268755185") // Usar @JsonProperty para nombres de campo con caracteres especiales
    private Map<String, Item> itemSectionSlots;
    @JsonProperty("events_default_1762284496085") // Usar @JsonProperty
    private List<CharacterEvent> eventsDefault;

    public CharacterSheet() {}

    public Map<String, Stat> getStats() { return stats; }
    public void setStats(Map<String, Stat> stats) { this.stats = stats; }

    public Map<String, Item> getItemSectionSlots() { return itemSectionSlots; }
    public void setItemSectionSlots(Map<String, Item> itemSectionSlots) { this.itemSectionSlots = itemSectionSlots; }

    public List<CharacterEvent> getEventsDefault() { return eventsDefault; }
    public void setEventsDefault(List<CharacterEvent> eventsDefault) { this.eventsDefault = eventsDefault; }
}