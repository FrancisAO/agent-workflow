package com.fop.workflow.agents.application.port.in;

import java.util.List;

public interface ExecutionResult {

    /**
     * Returns the result of the agent execution. Never null.
     * @return a list of AgentOutput objects representing the result of the agent execution.
     */
    List<AgentOutput> getResult();
    
    String getErrorMessage();

    boolean isSuccess();
}
