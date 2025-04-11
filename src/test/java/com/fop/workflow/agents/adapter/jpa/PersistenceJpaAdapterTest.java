package com.fop.workflow.agents.adapter.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fop.workflow.agents.adapter.jpa.entity.AgentEntityRepository;
import com.fop.workflow.agents.adapter.jpa.entity.AgentParameterContainerRepository;
import com.fop.workflow.agents.application.model.AgentParameterContainerModel;
import com.fop.workflow.agents.application.model.AgentParameterModel;
import com.fop.workflow.agents.application.model.TextAgent;
import com.fop.workflow.agents.application.port.in.AgentIntern;
import com.fop.workflow.agents.application.type.AgentParameterSemantic;
import com.fop.workflow.agents.application.type.AgentParameterType;

@DataJpaTest
public class PersistenceJpaAdapterTest {

    @Autowired
    private AgentParameterContainerRepository agentParameterContainerRepo;
    
    @Autowired
    private AgentEntityRepository agentEntityRepo;

    private PersistenceJpaAdapter persistenceAdapter;

    @Autowired
    public PersistenceJpaAdapterTest(
            AgentParameterContainerRepository agentParameterContainerRepo,
            AgentEntityRepository agentEntityRepo) {
        this.agentParameterContainerRepo = agentParameterContainerRepo;
        this.agentEntityRepo = agentEntityRepo;
        this.persistenceAdapter = new PersistenceJpaAdapter(agentParameterContainerRepo, agentEntityRepo);
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
        model.setAgentId("agent-123");
        model.setParameters(parameters);

        // Clear the repository before saving
        agentParameterContainerRepo.deleteAll();

        // When
        Long id = persistenceAdapter.save(model);

        // Then
        var savedContainer = agentParameterContainerRepo.findById(id);
        assertThat(savedContainer).isPresent();
        assertThat(savedContainer.get().getParameters()).hasSize(2);
        assertThat(savedContainer.get().getParameters().get(0).getSemantic()).isEqualTo(param1.getSemantic());
        assertThat(savedContainer.get().getParameters().get(1).getSemantic()).isEqualTo(param2.getSemantic());
    }
    
    @Test
    public void testSaveAgentIntern() {
        // Given
        Map<String, Object> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", 42);
        properties.put("key3", true);
        
        AgentIntern agent = new TextAgent("TestAgent", "TextAgent", properties);
        
        // Clear the repository before saving
        agentEntityRepo.deleteAll();
        
        // When
        Long id = persistenceAdapter.save(agent);
        
        // Then
        assertThat(id).isNotNull();
        assertThat(agent.getId()).isEqualTo(id);
        
        var savedAgent = agentEntityRepo.findById(id);
        assertThat(savedAgent).isPresent();
        assertThat(savedAgent.get().getName()).isEqualTo("TestAgent");
        assertThat(savedAgent.get().getType()).isEqualTo("TextAgent");
        assertThat(savedAgent.get().getProperties()).containsEntry("key1", "value1");
        assertThat(savedAgent.get().getProperties()).containsEntry("key2", 42);
        assertThat(savedAgent.get().getProperties()).containsEntry("key3", true);
    }
}