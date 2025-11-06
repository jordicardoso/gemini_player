package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewportEvent {
    private String type;
    private SourceEvent sourceEvent;
    private Transform transform;

    public ViewportEvent() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public SourceEvent getSourceEvent() { return sourceEvent; }
    public void setSourceEvent(SourceEvent sourceEvent) { this.sourceEvent = sourceEvent; }
    public Transform getTransform() { return transform; }
    public void setTransform(Transform transform) { this.transform = transform; }
}