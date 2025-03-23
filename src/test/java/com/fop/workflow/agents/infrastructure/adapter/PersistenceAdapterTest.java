package com.fop.workflow.agents.infrastructure.adapter;

import com.fop.workflow.agents.application.entity.AgentParameterContainerModel;
import com.fop.workflow.agents.infrastructure.repo.AgentParameterContainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import com.fop.workflow.agents.application.parameter.AgentParameterSemantic;
import com.fop.workflow.agents.application.parameter.AgentParameterType;
import com.fop.workflow.agents.application.entity.AgentParameterModel;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersistenceAdapterTest {

    @Autowired
    private AgentParameterContainerRepository agentParameterContainerRepo;

    private PersistenceAdapter persistenceAdapter;

    @Autowired
    public PersistenceAdapterTest(AgentParameterContainerRepository agentParameterContainerRepo) {
        this.agentParameterContainerRepo = agentParameterContainerRepo;
        this.persistenceAdapter = new PersistenceAdapter(agentParameterContainerRepo);
    }

    @Test
    public void testSaveAgentParameterContainerModel() {
        // Given
        AgentParameterModel param1 = new AgentParameterModel(AgentParameterType.STRING,
                AgentParameterSemantic.BODY_TEXT, "value1", LocalDateTime.now());
        AgentParameterModel param2 = new AgentParameterModel(AgentParameterType.STRING,
                AgentParameterSemantic.IMAGE_BASE64, "value2", LocalDateTime.now());
        List<AgentParameterModel> parameters = Arrays.asList(param1, param2);

        AgentParameterContainerModel model = new AgentParameterContainerModel();
        model.setId(1L);
        model.setAgentId("agent-123");
        model.setParameters(parameters);

        // When
        persistenceAdapter.save(model);

        // Then
        assertThat(agentParameterContainerRepo.findById(1L)).isPresent();
        assertThat(agentParameterContainerRepo.findById(1L).get().getParameters()).hasSize(2);
        assertThat(agentParameterContainerRepo.findById(1L).get().getParameters().get(0).getSemantic().getSemantic())
                .isEqualTo(param1.getSemantic().getSemantic());
        assertThat(agentParameterContainerRepo.findById(1L).get().getParameters().get(1).getSemantic().getSemantic())
                .isEqualTo(param2.getSemantic().getSemantic());
    }
}