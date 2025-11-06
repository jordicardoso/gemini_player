package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiRequest {
    private List<Content> contents;

    public GeminiRequest() {
    }

    public GeminiRequest(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    // Clases anidadas para la estructura JSON
    public static class Content {
        private List<Part> parts;

        public Content() {
        }

        public Content(List<Part> parts) {
            this.parts = parts;
        }

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    public static class Part {
        private String text;

        public Part() {
        }

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * Método de utilidad para crear una GeminiRequest a partir de un String de prompt.
     */
    public static GeminiRequest fromPromptText(String promptText) {
        Part part = new Part(promptText);
        Content content = new Content(Collections.singletonList(part));
        return new GeminiRequest(Collections.singletonList(content));
    }
}