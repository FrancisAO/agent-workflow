package com.fop.workflow.agents.application.type;

public enum AgentParameterType {

    STRING("string");

    private String type;

    AgentParameterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
