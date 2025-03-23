package com.fop.workflow.agents.application.port.in;

import java.util.Map;

public interface AgentPort {

    WorkflowAgent createAgent(String agentName, Map<String, Object> agentProperties);
   
}