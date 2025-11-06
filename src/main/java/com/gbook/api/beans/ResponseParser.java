package com.gbook.api.beans;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@RegisterForReflection
@ApplicationScoped
@Named("responseParser")
public class ResponseParser {
    public String parse(String geminiResponse) {
        // Lógica para parsear la respuesta de Gemini y extraer la decisión
        // Esto dependerá del formato de salida que configures en Gemini.
        return "decision_extraida_de_gemini";
    }
}
