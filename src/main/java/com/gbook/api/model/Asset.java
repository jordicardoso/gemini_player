package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {
    private String id;
    private String name;
    private String category;
    private String type;
    private String filename;
    private String creationDate;

    public Asset() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
}