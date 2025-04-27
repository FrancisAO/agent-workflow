package com.fop.workflow.agents.application.service;

import java.util.Map;

import com.fop.workflow.agents.application.model.TextAgent;
import com.fop.workflow.agents.application.port.in.AgentIntern;
import com.fop.workflow.agents.application.port.in.AgentService;
import com.fop.workflow.agents.application.port.in.AgentServiceFactory;
import com.fop.workflow.agents.application.type.AgentType;

public class AgentServiceImpl implements AgentService {

    @Override
    public boolean isKnownAgentType(String type) {
        return AgentType.fromString(type).isPresent();
    }

    @Override
    public AgentIntern createAgent(String type, String name, Map<String, Object> properties) {
        if(!isKnownAgentType(type)) {
            throw new IllegalArgumentException("Agent type is not supported: " + type);
        }
        
        AgentType agentType = AgentType.fromString(type).get();
        switch (agentType) {
            case TEXT:
                return new TextAgent(name, type, properties);
            default:
                throw new IllegalArgumentException("Unsupported agent type: " + type);
        }
        

    }



}
