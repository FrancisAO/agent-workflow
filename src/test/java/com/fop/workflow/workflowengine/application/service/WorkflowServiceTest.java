package com.fop.workflow.workflowengine.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fop.workflow.agents.application.port.in.Agent;
import com.fop.workflow.agents.application.port.in.AgentCreateException;
import com.fop.workflow.agents.application.port.in.AgentPort;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;

@SpringBootTest
@ActiveProfiles("test")
public class WorkflowServiceTest {

    @Mock
    private AgentPort agentPort;
    @Autowired
    private WorkflowSpecReaderPort workflowSpecReaderPort;
    private SharedExecutionMemory sharedExecMem;
    
    private WorkflowService sut;
    
    private static final String WORKFLOW_FILE = "src/test/java/com/fop/workflow/workflowengine/application/service/workflow-example.yaml";
    private static final String SYS_MSG_PROP_KEY = "system_message";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       
        sut = new WorkflowService(workflowSpecReaderPort, agentPort);
        sharedExecMem = new SharedExecutionMemory();
        
        // Mock AgentPort.getSysMsgPropKey() to return the system message property key
        when(agentPort.getSysMsgPropKey()).thenReturn(SYS_MSG_PROP_KEY);
    }
    
    @Test
    public void testExecuteWorkflow() throws IOException, AgentCreateException {
        // given
        AgentFake agentA = new AgentFake("Agent A", "Type A", new HashMap<>(), 1L, sharedExecMem);
        AgentFake agentB = new AgentFake("Agent B", "Type B", new HashMap<>(), 2L, sharedExecMem);
        AgentFake agentC = new AgentFake("Agent C", "Type C", new HashMap<>(), 3L, sharedExecMem);
        
        // Mock AgentPort.createAgent() to return AgentFake instances
        when(agentPort.createAgent(eq("Type A"), eq("Agent A"), anyMap())).thenReturn(agentA);
        when(agentPort.createAgent(eq("Type B"), eq("Agent B"), anyMap())).thenReturn(agentB);
        when(agentPort.createAgent(eq("Type C"), eq("Agent C"), anyMap())).thenReturn(agentC);
        
        // when
        sut.executeWorkflow(WORKFLOW_FILE);
        
        // then
        // Verify that createAgent was called for each agent in the workflow
        verify(agentPort, times(1)).createAgent(eq("Type A"), eq("Agent A"), anyMap());
        verify(agentPort, times(1)).createAgent(eq("Type B"), eq("Agent B"), anyMap());
        verify(agentPort, times(1)).createAgent(eq("Type C"), eq("Agent C"), anyMap());
        
        List<Agent> agents = sharedExecMem.getAgents();
        assertEquals(agents.size(), 3);
        assertEquals(agents.get(0).getName(), agentA.getName());
        assertEquals(agents.get(1).getName(), agentB.getName());
        assertEquals(agents.get(2).getName(), agentC.getName());

        
    }
    
    @Test
    public void testExecuteWorkflow_WithSystemMessage() throws IOException, AgentCreateException {
        // Arrange
        when(agentPort.createAgent(anyString(), anyString(), anyMap())).thenAnswer(invocation -> {
            String type = invocation.getArgument(0);
            String name = invocation.getArgument(1);
            Map<String, Object> properties = invocation.getArgument(2);
            return new AgentFake(name, type, properties, 1L, sharedExecMem);
        });
        
        // Act
        sut.executeWorkflow(WORKFLOW_FILE);
        
        // Assert
        // Verify that the system message was passed to the agent
        verify(agentPort, times(1)).createAgent(eq("Type A"), eq("Agent A"),
                eq(Map.of(SYS_MSG_PROP_KEY, "System message for Agent A.")));
        verify(agentPort, times(1)).createAgent(eq("Type B"), eq("Agent B"),
                eq(Map.of(SYS_MSG_PROP_KEY, "System message for Agent B.")));
        verify(agentPort, times(1)).createAgent(eq("Type C"), eq("Agent C"),
                eq(Map.of(SYS_MSG_PROP_KEY, "System message for Agent C.")));
    }
}