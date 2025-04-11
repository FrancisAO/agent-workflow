package com.fop.workflow.agents.adapter.jpa.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Konvertiert eine Map<String, Object> in einen JSON-String und zurück für die Speicherung in der Datenbank.
 */
@Converter(autoApply = false)
public class AgentPropertiesConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String convertToDatabaseColumn(Map<String, Object> properties) {
        try {
            return properties == null ? null : objectMapper.writeValueAsString(properties);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Fehler beim Konvertieren der Properties zu JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String json) {
        try {
            if (json == null) {
                return new HashMap<>();
            }
            return objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Fehler beim Konvertieren von JSON zu Properties", e);
        }
    }
}