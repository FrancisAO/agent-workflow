package com.fop.workflow.agents.application.entity;

import java.util.Collections;
import java.util.List;

import com.fop.workflow.agents.application.parameter.AgentParameterSemantic;
import com.fop.workflow.agents.infrastructure.repo.AgentParameter;

public class AgentParameterContainerModel {
    private Long id;
    private String agentId;
    private List<AgentParameterModel> parameters;

    public AgentParameterContainerModel() {
    }

    public AgentParameterContainerModel(Long id, String agentId, List<AgentParameterModel> parameters) {
        this.id = id;
        this.agentId = agentId;
        this.parameters = parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public List<AgentParameterModel> getParameters() {
        return parameters;
    }

    public void setParameters(List<AgentParameterModel> parameters) {
        this.parameters = parameters;
    }

    /**
     * Returns a list of parameters that match the given semantic
     * 
     * @param semantic
     * @return list of parameters
     */
    public List<AgentParameterModel> getParameters(AgentParameterSemantic semantic) {
        return parameters.stream().filter(p -> p.getSemantic().equals(semantic)).toList();
    }

    /**
     * Returns a list of parameters that match the given regex
     * 
     * @param regex
     * @return list of parameters
     */
    public List<AgentParameterModel> getParametersMatching(String regex) {
        return parameters.stream().filter(p -> p.getSemantic().getSemantic().matches(regex)).toList();
    }

    /**
     * Returns true if the container has a parameter with the given semantic
     * 
     * @param semantic
     * @return true if the container has a parameter with the given semantic
     */
    public boolean hasParameter(AgentParameterSemantic semantic) {
        return parameters.stream().anyMatch(p -> p.getSemantic().equals(semantic));
    }

    /**
     * Returns the first parameter with the given semantic
     * 
     * @param semantic
     * @return parameter
     */
    public AgentParameterModel getFirstParameter(AgentParameterSemantic semantic) {
        return parameters.stream().filter(p -> p.getSemantic().equals(semantic)).findFirst().get();
    }

    public AgentParameterModel getMostActualParameter(AgentParameterSemantic semantic) {
        return parameters.stream().filter(p -> p.getSemantic().equals(semantic))
                .max((p1, p2) -> p1.getCreatedAt().compareTo(p2.getCreatedAt())).get();
    }

}