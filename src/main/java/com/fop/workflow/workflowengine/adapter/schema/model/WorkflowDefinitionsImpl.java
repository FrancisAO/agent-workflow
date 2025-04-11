package com.fop.workflow.workflowengine.adapter.schema.model;

import java.util.ArrayList;
import java.util.List;

import com.fop.workflow.workflowengine.application.port.out.AgentDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;

/**
 * Implementation of the WorkflowDefinitions interface.
 */
public class WorkflowDefinitionsImpl implements WorkflowDefinitions {

    private String version;
    private String name;
    private String description;
    private List<AgentDefinition> agents = new ArrayList<>();
    private List<WorkflowDefinition> workflow = new ArrayList<>();

    /**
     * Default constructor.
     */
    public WorkflowDefinitionsImpl() {
    }

    /**
     * Constructor with all fields.
     *
     * @param version     the version of the workflow
     * @param name        the name of the workflow
     * @param description the description of the workflow
     * @param agents      the list of agent definitions
     * @param workflow    the list of workflow definitions
     */
    public WorkflowDefinitionsImpl(String version, String name, String description,
                                  List<AgentDefinition> agents, List<WorkflowDefinition> workflow) {
        this.version = version;
        this.name = name;
        this.description = description;
        this.agents = agents;
        this.workflow = workflow;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<AgentDefinition> getAgents() {
        return agents;
    }

    @Override
    public void setAgents(List<AgentDefinition> agents) {
        this.agents = agents;
    }

    @Override
    public List<WorkflowDefinition> getWorkflow() {
        return workflow;
    }

    @Override
    public void setWorkflow(List<WorkflowDefinition> workflow) {
        this.workflow = workflow;
    }
}