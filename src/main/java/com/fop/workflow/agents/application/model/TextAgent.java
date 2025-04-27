package com.fop.workflow.agents.application.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fop.workflow.agents.application.port.in.AgentIntern;
import com.fop.workflow.agents.application.port.in.AgentOutput;
import com.fop.workflow.agents.application.port.in.ExecutionResult;

public class TextAgent implements AgentIntern {

    private String name;
    private String type;
    private Map<String, Object> properties;
    private Long id;

    public TextAgent(String name, String type, Map<String, Object> properties) {
        this.name = name;
        this.type = type;
        this.properties = properties;
        id = -1L;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ExecutionResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ExecutionResult getLastResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastResult'");
    }

    @Override
    public ExecutionResult execute(List<AgentOutput> inputs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
