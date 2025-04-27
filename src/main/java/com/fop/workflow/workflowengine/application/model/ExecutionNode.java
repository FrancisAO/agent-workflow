package com.fop.workflow.workflowengine.application.model;

import com.fop.workflow.agents.application.port.in.Agent;

public record ExecutionNode(Agent from, Agent to) {

    public ExecutionNode {
        if (from == null) {
            throw new IllegalArgumentException("From agent cannot be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("To agent cannot be null");
        }
    }

}
