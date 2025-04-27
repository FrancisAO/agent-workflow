package com.fop.workflow.agents.application.port.in;

import com.fop.workflow.agents.application.service.AgentServiceImpl;

public class AgentServiceFactory {

    public AgentService createService() {
        return new AgentServiceImpl();
    }

}
