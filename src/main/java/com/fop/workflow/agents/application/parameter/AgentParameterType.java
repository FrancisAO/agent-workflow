package com.fop.workflow.agents.application.parameter;

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
