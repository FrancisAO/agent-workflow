package com.fop.workflow.agents.infrastructure.repo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class AgentParameterConverter implements AttributeConverter<Object, String>  {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String convertToDatabaseColumn(Object object) {
        try {
            return object == null ? null : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Object to JSON", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String json) {
        try {
            return json == null ? null : objectMapper.readValue(json, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Object", e);
        }
    }

}
