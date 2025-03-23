package com.fop.workflow.agents.application.port.out;
import com.fop.workflow.agents.application.entity.AgentParameterContainerModel;
public interface PersistencePort {
    Long save(AgentParameterContainerModel agentParameterContainerModel);
   

}
