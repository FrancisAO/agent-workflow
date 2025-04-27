package com.fop.workflow.workflowengine.application.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fop.workflow.agents.application.port.in.Agent;

public class SharedExecutionMemory {

    private final List<Agent> agents = new ArrayList<>();
    
    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public List<Agent> getAgents() {
        return Collections.unmodifiableList(agents);
    }

}
