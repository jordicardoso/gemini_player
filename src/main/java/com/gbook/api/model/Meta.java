package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private String title;
    private String description;
    private String author;
    private String imageId;

    public Meta() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }
}