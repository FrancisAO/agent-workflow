package com.fop.workflow.agents.infrastructure.repo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class AgentParameterContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String agentId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agent_parameter_container_id")
    
    private List<AgentParameter> parameters;
    
    public AgentParameterContainer() {
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setParameters(List<AgentParameter> parameters) {
        this.parameters = parameters;
    }

    public AgentParameterContainer(String agentId) {
        Objects.requireNonNull(agentId, "agentId cannot be null");
        this.agentId = agentId;
        parameters = new ArrayList<>();
    }

    public String getAgentId() {
        return agentId;
    }

    public Long getId() {
        return id;
    }

    /**
     * Returns an unmodifiable list of parameters
     * 
     * @return list of parameters
     */
    public List<AgentParameter> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

}
