package com.gbook.api.beans;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Named("geminiService")
public class GeminiService {

    private static final Logger LOG = LoggerFactory.getLogger(GeminiService.class);

    @ConfigProperty(name = "gemini.api.key")
    String apiKey;

    @ConfigProperty(name = "gemini.model", defaultValue = "gemini-2.5-flash")
    String modelName;

    public String generateContent(String prompt) {
        try {
            Client client = Client.builder()
                    .apiKey(apiKey)
                    .vertexAI(false)
                    .build();

            GenerateContentResponse response = client.models.generateContent(
                    modelName,
                    prompt,
                    null);
            return response.text();
        } catch (Exception e) {
            LOG.error("Error calling Gemini SDK", e);
            return "Error: " + e.getMessage();
        }
    }
}
