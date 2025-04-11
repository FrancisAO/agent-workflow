package com.fop.workflow.agents.application.model;

import java.time.LocalDateTime;

import com.fop.workflow.agents.application.type.AgentParameterSemantic;
import com.fop.workflow.agents.application.type.AgentParameterType;

public class AgentParameterModel {

    private Long id;
    private AgentParameterType type;
    private Object value;
    private AgentParameterSemantic semantic;
    private LocalDateTime createdAt;

    public AgentParameterModel() {
    }

    public AgentParameterModel(AgentParameterType type, AgentParameterSemantic semantic, Object value, LocalDateTime createdAt) {
        this.type = type;
        this.semantic = semantic;
        this.value = value;
        this.createdAt = createdAt;
    }

    public AgentParameterType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public AgentParameterSemantic getSemantic() {
        return semantic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(AgentParameterType type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setSemantic(AgentParameterSemantic semantic) {
        this.semantic = semantic;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}