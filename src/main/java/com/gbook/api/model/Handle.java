package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Handle {
    private String id;
    private String type;
    private String nodeId;
    private String position;
    private double x;
    private double y;
    private int width;
    private int height;

    public Handle() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getNodeId() { return nodeId; }
    public void setNodeId(String nodeId) { this.nodeId = nodeId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}