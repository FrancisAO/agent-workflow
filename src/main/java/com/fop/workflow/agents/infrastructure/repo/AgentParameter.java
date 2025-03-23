package com.fop.workflow.agents.infrastructure.repo;

import java.time.LocalDateTime;

import com.fop.workflow.agents.application.parameter.AgentParameterSemantic;
import com.fop.workflow.agents.application.parameter.AgentParameterType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class AgentParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT") // Verhindert automatische CHECK-Constraint
    private AgentParameterType type;
    @Lob
    @Convert(converter = AgentParameterConverter.class)
    @Column(name = "parameter_value")
    private Object value;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT") // Verhindert automatische CHECK-Constraint
    private AgentParameterSemantic semantic;
    @Column
	private LocalDateTime createdAt;

    public AgentParameter() {
    }
    
    public AgentParameter(AgentParameterType type, AgentParameterSemantic semantic, Object value, LocalDateTime createdAt) {
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
