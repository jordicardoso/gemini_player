package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Choice {
    private String id;
    private String type;
    private String label;
    private String targetNodeId;
    private String sourceHandle;

    public Choice() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getTargetNodeId() { return targetNodeId; }
    public void setTargetNodeId(String targetNodeId) { this.targetNodeId = targetNodeId; }

    public String getSourceHandle() { return sourceHandle; }
    public void setSourceHandle(String sourceHandle) { this.sourceHandle = sourceHandle; }
}
