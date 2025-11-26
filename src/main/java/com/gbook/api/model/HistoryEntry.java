package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryEntry {
    private String nodeId;
    private String nodeDescription;
    private String choiceId;
    private String choiceLabel;

    public HistoryEntry() {
    }

    public HistoryEntry(String nodeId, String nodeDescription, String choiceId, String choiceLabel) {
        this.nodeId = nodeId;
        this.nodeDescription = nodeDescription;
        this.choiceId = choiceId;
        this.choiceLabel = choiceLabel;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeDescription() {
        return nodeDescription;
    }

    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceLabel() {
        return choiceLabel;
    }

    public void setChoiceLabel(String choiceLabel) {
        this.choiceLabel = choiceLabel;
    }
}
