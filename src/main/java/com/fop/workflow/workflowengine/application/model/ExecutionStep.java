package com.fop.workflow.workflowengine.application.model;

import com.fop.workflow.agents.application.port.in.Agent;

public class ExecutionStep {

    private final ExecutionNode node;

    public ExecutionStep(Agent from, Agent to) {
        this.node = new ExecutionNode(from, to);
    }

    public ExecutionNode getNode() {
        return node;
    }



}
