package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {
    private String id;
    private String type;
    private Dimensions dimensions;
    private ComputedPosition computedPosition;
    private HandleBounds handleBounds;
    private boolean selected;
    private boolean dragging;
    private boolean resizing;
    private boolean initialized;
    private boolean isParent;
    private Position position;
    private NodeData data;
    private Map<String, Object> events; // O una clase específica si la estructura es conocida
    private String label;

    public Node() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public ComputedPosition getComputedPosition() {
        return computedPosition;
    }

    public void setComputedPosition(ComputedPosition computedPosition) {
        this.computedPosition = computedPosition;
    }

    public HandleBounds getHandleBounds() {
        return handleBounds;
    }

    public void setHandleBounds(HandleBounds handleBounds) {
        this.handleBounds = handleBounds;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public boolean isResizing() {
        return resizing;
    }

    public void setResizing(boolean resizing) {
        this.resizing = resizing;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public NodeData getData() {
        return data;
    }

    public void setData(NodeData data) {
        this.data = data;
    }

    public Map<String, Object> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Object> events) {
        this.events = events;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}