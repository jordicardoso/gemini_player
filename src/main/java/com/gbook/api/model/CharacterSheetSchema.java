package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterSheetSchema {
    private List<LayoutItem> layout;

    public CharacterSheetSchema() {}

    public List<LayoutItem> getLayout() { return layout; }
    public void setLayout(List<LayoutItem> layout) { this.layout = layout; }
}