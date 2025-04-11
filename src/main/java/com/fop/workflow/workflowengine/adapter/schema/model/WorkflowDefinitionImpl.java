package com.fop.workflow.workflowengine.adapter.schema.model;

import java.util.ArrayList;
import java.util.List;

import com.fop.workflow.workflowengine.application.port.out.LoopDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinition;

/**
 * Implementation of the WorkflowDefinition interface.
 */
public class WorkflowDefinitionImpl implements WorkflowDefinition {

    private String from;
    private String to;
    private List<LoopDefinition> loop = new ArrayList<>();

    /**
     * Default constructor.
     */
    public WorkflowDefinitionImpl() {
    }

    /**
     * Constructor with all fields.
     *
     * @param from the source agent
     * @param to   the target agent
     * @param loop the list of loop definitions
     */
    public WorkflowDefinitionImpl(String from, String to, List<LoopDefinition> loop) {
        this.from = from;
        this.to = to;
        this.loop = loop;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public List<LoopDefinition> getLoop() {
        return loop;
    }

    @Override
    public void setLoop(List<LoopDefinition> loop) {
        this.loop = loop;
    }
}