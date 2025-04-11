package com.fop.workflow.workflowengine.adapter.schema.model;

import com.fop.workflow.workflowengine.application.port.out.AgentDefinition;

/**
 * Implementation of the AgentDefinition interface.
 */
public class AgentDefinitionImpl implements AgentDefinition {

    private String name;
    private String type;
    private String description;
    private String systemMessage;
    private String prompt;

    /**
     * Default constructor.
     */
    public AgentDefinitionImpl() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name          the name of the agent
     * @param type          the type of the agent
     * @param description   the description of the agent
     * @param systemMessage the system message of the agent
     * @param prompt        the prompt of the agent
     */
    public AgentDefinitionImpl(String name, String type, String description, String systemMessage, String prompt) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.systemMessage = systemMessage;
        this.prompt = prompt;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSystemMessage() {
        return systemMessage;
    }

    @Override
    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}