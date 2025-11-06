package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeData {
    private String description;
    private String imageId;
    private List<String> tags;
    private String color;
    private String size;

    public NodeData() {}

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}