package com.fop.workflow.agents.adapter.jpa;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fop.workflow.agents.adapter.jpa.entity.AgentEntity;
import com.fop.workflow.agents.adapter.jpa.entity.AgentEntityRepository;
import com.fop.workflow.agents.adapter.jpa.entity.AgentParameter;
import com.fop.workflow.agents.adapter.jpa.entity.AgentParameterContainer;
import com.fop.workflow.agents.adapter.jpa.entity.AgentParameterContainerRepository;
import com.fop.workflow.agents.application.model.AgentParameterContainerModel;
import com.fop.workflow.agents.application.port.in.AgentIntern;
import com.fop.workflow.agents.application.port.out.PersistencePort;

@Service
public class PersistenceJpaAdapter implements PersistencePort {

    private final AgentParameterContainerRepository agentParameterContainerRepo;
    private final AgentEntityRepository agentEntityRepo;

    public PersistenceJpaAdapter(
            AgentParameterContainerRepository agentParameterContainerRepo,
            AgentEntityRepository agentEntityRepo) {
        this.agentParameterContainerRepo = agentParameterContainerRepo;
        this.agentEntityRepo = agentEntityRepo;
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

    /**
     * Speichert ein AgentIntern-Objekt in der Datenbank.
     *
     * @param agentIntern das zu speichernde AgentIntern-Objekt
     * @return die ID des gespeicherten Agents
     */
    @Transactional
    @Override
    public Long save(AgentIntern agentIntern) {
        AgentEntity agentEntity = new AgentEntity(
                agentIntern.getName(),
                agentIntern.getType(),
                agentIntern.getProperties());
        
        // Wenn die ID bereits gesetzt ist, verwenden wir sie
        if (agentIntern.getId() != null && agentIntern.getId() > 0) {
            agentEntity.setId(agentIntern.getId());
        }
        
        // Speichern der Entity und Rückgabe der ID
        Long id = agentEntityRepo.save(agentEntity).getId();
        
        // Setzen der ID im AgentIntern-Objekt
        agentIntern.setId(id);
        
        return id;
    }


}
