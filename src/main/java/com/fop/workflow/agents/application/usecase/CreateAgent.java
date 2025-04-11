package com.fop.workflow.agents.application.usecase;

import java.util.Map;

import com.fop.workflow.agents.application.port.in.Agent;
import com.fop.workflow.agents.application.port.in.AgentIntern;
import com.fop.workflow.agents.application.port.in.AgentPort;
import com.fop.workflow.agents.application.port.in.AgentService;
import com.fop.workflow.agents.application.port.out.PersistencePort;

public class CreateAgent implements AgentPort {

    private final AgentService agentService;
    private final PersistencePort persistencePort;
    private static final String SYS_MSG_PROP_KEY = "sysMsgPropKey";

    public CreateAgent(AgentService agentService, PersistencePort persistencePort) {
        this.agentService = agentService;
        this.persistencePort = persistencePort;
    }

    public Agent createAgent(String name, String type, Map<String, Object> properties) {
        // Validate input
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Agent name cannot be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Agent type cannot be null or empty");
        }
        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("Agent properties cannot be null or empty");
        }
        if(agentService.isKnownAgentType(type) == false) {
            throw new IllegalArgumentException("Agent type is not supported: " + type);
        }

        AgentIntern agent = agentService.createAgent(type, name, properties);
        persistencePort.save(agent);

        return agent;

    }

    @Override
    public String getSysMsgPropKey() {
       return SYS_MSG_PROP_KEY;
    }

}
