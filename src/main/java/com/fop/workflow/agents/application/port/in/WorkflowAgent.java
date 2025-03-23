package com.fop.workflow.agents.application.port.in;

import java.util.Map;

import com.fop.workflow.agents.infrastructure.repo.AgentParameterContainer;

public interface WorkflowAgent {

    AgentParameterContainer solveTask();

    AgentParameterContainer continueAgentWithState(AgentParameterContainer stateContainer);

    AgentParameterContainer continueAgentWithInput(AgentParameterContainer input);

    AgentParameterContainer continueAgentWithSateAndInput(AgentParameterContainer state, AgentParameterContainer input);

    String getType();

    Map<String, Object> getProperties();

}
