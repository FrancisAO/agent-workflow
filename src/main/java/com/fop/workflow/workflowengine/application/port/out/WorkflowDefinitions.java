package com.fop.workflow.workflowengine.application.port.out;

import java.util.List;

public interface WorkflowDefinitions {

    String getVersion();

    void setVersion(String version);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<AgentDefinition> getAgents();

    void setAgents(List<AgentDefinition> agents);

    List<WorkflowDefinition> getWorkflow();

    void setWorkflow(List<WorkflowDefinition> workflow);

}