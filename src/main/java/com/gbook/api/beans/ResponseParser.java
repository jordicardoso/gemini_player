package com.gbook.api.beans;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@RegisterForReflection
@ApplicationScoped
@Named("responseParser")
public class ResponseParser {
    private static final java.util.regex.Pattern UUID_PATTERN = java.util.regex.Pattern.compile(
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");

    public String parse(String geminiResponse) {
        if (geminiResponse == null) {
            return null;
        }
        java.util.regex.Matcher matcher = UUID_PATTERN.matcher(geminiResponse);
        if (matcher.find()) {
            return matcher.group();
        }
        // Si no se encuentra un UUID, devolvemos la respuesta original limpia
        // o podríamos devolver null para indicar fallo.
        // Por ahora, devolvemos la respuesta original trimmeada por si acaso.
        return geminiResponse.trim();
    }
}
