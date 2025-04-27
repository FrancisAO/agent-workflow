package com.fop.workflow.agents.adapter.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fop.workflow.agents.adapter.jpa.PersistenceJpaAdapter;
import com.fop.workflow.agents.adapter.jpa.entity.AgentEntityRepository;
import com.fop.workflow.agents.adapter.jpa.entity.AgentParameterContainerRepository;
import com.fop.workflow.agents.application.port.in.AgentPort;
import com.fop.workflow.agents.application.port.in.AgentService;
import com.fop.workflow.agents.application.port.in.AgentServiceFactory;
import com.fop.workflow.agents.application.port.out.PersistencePort;
import com.fop.workflow.agents.application.service.AgentServiceImpl;
import com.fop.workflow.agents.application.usecase.CreateAgent;

@Configuration
public class AgentConfiguration {

    @Bean
    public AgentPort agentPort(PersistencePort persistencePort) {
        AgentService agentService = new AgentServiceFactory().createService();

        CreateAgent createAgent = new CreateAgent(agentService, persistencePort);
        return createAgent;
    }

    @Bean
    public PersistencePort persistencePort(AgentEntityRepository agentEntityRepository, 
    AgentParameterContainerRepository agentParameterContainerRepository) {
        return new PersistenceJpaAdapter(agentParameterContainerRepository, agentEntityRepository);
    }

}
