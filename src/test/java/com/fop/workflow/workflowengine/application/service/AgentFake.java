package com.fop.workflow.workflowengine.application.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fop.workflow.agents.application.port.in.Agent;
import com.fop.workflow.agents.application.port.in.AgentOutput;
import com.fop.workflow.agents.application.port.in.ExecutionResult;

public class AgentFake implements Agent {

    private final String name;
    private final String type;
    private final Map<String, Object> properties;
    private final Long id;
    private final Random random;
    private ExecutionResultFake executionResultFake;
    private SharedExecutionMemory sharedExecMem;

    public AgentFake(String name, String type, Map<String, Object> properties,
            Long id, SharedExecutionMemory sharedExecutionMemory) {
        this.name = name;
        this.type = type;
        this.properties = properties;
        this.id = id;
        this.sharedExecMem = sharedExecutionMemory;
        random = new Random();
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
        return properties;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ExecutionResult execute() {
        sharedExecMem.addAgent(this);
        int randomValue = random.nextInt();
        executionResultFake = new ExecutionResultFake("int", randomValue);
        return executionResultFake;
    }

    @Override
    public ExecutionResult getLastResult() {
        return executionResultFake;
    }

    @Override
    public ExecutionResult execute(List<AgentOutput> inputs) {
        sharedExecMem.addAgent(this);
        StringBuilder sb = new StringBuilder();
        for (AgentOutput input : inputs) {
            sb.append(input.getValue()).append(" ");
        }
        executionResultFake = new ExecutionResultFake("string", sb.toString());
        return executionResultFake;
    }

}
