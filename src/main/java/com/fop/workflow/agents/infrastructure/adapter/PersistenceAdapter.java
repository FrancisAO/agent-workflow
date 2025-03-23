package com.fop.workflow.agents.infrastructure.adapter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fop.workflow.agents.application.entity.AgentParameterContainerModel;
import com.fop.workflow.agents.application.port.out.PersistencePort;
import com.fop.workflow.agents.infrastructure.repo.AgentParameter;
import com.fop.workflow.agents.infrastructure.repo.AgentParameterContainer;
import com.fop.workflow.agents.infrastructure.repo.AgentParameterContainerRepository;

@Service
public class PersistenceAdapter implements PersistencePort {

    private AgentParameterContainerRepository agentParameterContainerRepo;

    public PersistenceAdapter(AgentParameterContainerRepository agentParameterContainerRepo) {
        this.agentParameterContainerRepo = agentParameterContainerRepo;
    }

    @Transactional
    @Override
    public Long save(AgentParameterContainerModel agentParameterContainerModel) {
        AgentParameterContainer agentParameterContainer = new AgentParameterContainer();
        agentParameterContainer.setId(agentParameterContainerModel.getId());
        agentParameterContainer.setAgentId(agentParameterContainerModel.getAgentId());
        agentParameterContainer.setParameters(agentParameterContainerModel.getParameters().stream()
                .map(paramModel -> new AgentParameter(paramModel.getType(), paramModel.getSemantic(),
                        paramModel.getValue(), paramModel.getCreatedAt()))
                .collect(Collectors.toList()));

        return agentParameterContainerRepo.save(agentParameterContainer).getId();
    }



}
