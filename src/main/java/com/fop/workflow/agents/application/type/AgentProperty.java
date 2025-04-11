package com.fop.workflow.agents.application.type;

public class AgentProperty {
    private String key;
    private String type;
    private Object value;

    public AgentProperty(String key, String type, Object value) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public static  AgentProperty of(String key, String type, String value) {
        switch (type.toLowerCase()) {
            case "string":
                return new AgentProperty(key, type, value);
            case "integer":
                return new AgentProperty(key, type,  Integer.valueOf(value));
            case "boolean":
                return new AgentProperty(key, type, Boolean.valueOf(value));
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
