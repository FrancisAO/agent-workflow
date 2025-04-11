package com.fop.workflow.agents.application.type;

import java.util.Optional;

public enum AgentType {

    TEXT("text");

    private final String type;

    AgentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Optional<AgentType> fromString(String type) {
        for (AgentType agentType : AgentType.values()) {
            if (agentType.type.equalsIgnoreCase(type)) {
                return Optional.of(agentType);
            }
        }
        return Optional.empty();
    }

}
