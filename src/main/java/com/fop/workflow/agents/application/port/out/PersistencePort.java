package com.fop.workflow.agents.application.port.out;
import com.fop.workflow.agents.application.model.AgentParameterContainerModel;
import com.fop.workflow.agents.application.port.in.AgentIntern;
public interface PersistencePort {
    
    Long save(AgentParameterContainerModel agentParameterContainerModel);
    Long save(AgentIntern agentIntern);
   

}
