package com.fop.workflow.workflowengine.application.port.out;

public interface AgentDefinition {

    String getName();

    void setName(String name);

    String getType();

    void setType(String type);

    String getDescription();

    void setDescription(String description);

    String getSystemMessage();

    void setSystemMessage(String systemMessage);

    String getPrompt();

    void setPrompt(String prompt);

}