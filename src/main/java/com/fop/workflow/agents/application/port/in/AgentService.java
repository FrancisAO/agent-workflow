package com.fop.workflow.agents.application.port.in;

import java.util.Map;

public interface AgentService {

    boolean isKnownAgentType(String type);

    AgentIntern createAgent(String type, String name, Map<String, Object> properties);

}