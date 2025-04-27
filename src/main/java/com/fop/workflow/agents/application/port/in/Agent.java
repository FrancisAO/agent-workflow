package com.fop.workflow.agents.application.port.in;

import java.util.List;
import java.util.Map;

public interface Agent {

    String getName();

    String getType();

    Map<String, Object> getProperties();

    Long getId();

    ExecutionResult execute();

    ExecutionResult getLastResult();

    /**
     * Executes the agent with the given inputs.
     * @param input
     * @return
     */
    ExecutionResult execute(List<AgentOutput> inputs);

}
