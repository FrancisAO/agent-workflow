package com.fop.workflow.agents.application.port.out;
import com.fop.workflow.agents.application.entity.AgentParameterContainerModel;
import com.fop.workflow.agents.application.entity.AgentParameterModel;
public interface PersistencePort {
    void save(AgentParameterContainerModel agentParameterContainerModel);
   

}
