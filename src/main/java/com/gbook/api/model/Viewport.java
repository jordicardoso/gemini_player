package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Viewport {
    private ViewportEvent event;
    private FlowTransform flowTransform;

    public Viewport() {}

    public ViewportEvent getEvent() { return event; }
    public void setEvent(ViewportEvent event) { this.event = event; }
    public FlowTransform getFlowTransform() { return flowTransform; }
    public void setFlowTransform(FlowTransform flowTransform) { this.flowTransform = flowTransform; }
}