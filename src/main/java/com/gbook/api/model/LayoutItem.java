package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LayoutItem {
    private String type;
    private String title;
    private String icon;
    private String dataKey;
    private String mode; // Puede ser null para algunos tipos

    public LayoutItem() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getDataKey() { return dataKey; }
    public void setDataKey(String dataKey) { this.dataKey = dataKey; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
}