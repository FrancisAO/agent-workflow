package com.fop.workflow.workflowengine.application.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fop.workflow.agents.application.port.in.Agent;
import com.fop.workflow.agents.application.port.in.AgentCreateException;
import com.fop.workflow.agents.application.port.in.AgentOutput;
import com.fop.workflow.agents.application.port.in.AgentPort;
import com.fop.workflow.agents.application.port.in.ExecutionResult;
import com.fop.workflow.workflowengine.application.model.ExecutionStep;
import com.fop.workflow.workflowengine.application.port.in.WorkflowExecutionUseCase;
import com.fop.workflow.workflowengine.application.port.out.AgentDefinition;
import com.fop.workflow.workflowengine.application.port.out.LoopDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;

@Service
public class WorkflowService implements WorkflowExecutionUseCase {

    private final WorkflowSpecReaderPort workflowSpecReaderPort;
    private final AgentPort agentPort;

    public WorkflowService(WorkflowSpecReaderPort workflowSpecReaderPort, AgentPort agentPort) {
        this.agentPort = agentPort;
        this.workflowSpecReaderPort = workflowSpecReaderPort;
    }

    private WorkflowDefinitions readWorkflow(String workflowPath) throws IOException {
        return workflowSpecReaderPort.readWorkflowSpec(workflowPath);
    }

    @Override
    public void executeWorkflow(String workflowPath) throws IOException {
        WorkflowDefinitions workflowDefs = readWorkflow(workflowPath);
        try {
            List<Agent> agents = createAgents(workflowDefs);
            LinkedList<ExecutionStep> executionSteps = new LinkedList<>();
            
            executeWorkflowSteps(workflowDefs, agents, executionSteps);
        } catch (AgentCreateException e) {
            throw new RuntimeException("Error creating agent: " + e.getMessage(), e);
        }
    }
    
    private void executeWorkflowSteps(WorkflowDefinitions workflowDefs, List<Agent> agents, LinkedList<ExecutionStep> executionSteps) {
        for (WorkflowDefinition workflowDef : workflowDefs.getWorkflow()) {
            Agent fromAgent = findAgent(agents, workflowDef.getFrom());
            Agent toAgent = findAgent(agents, workflowDef.getTo());
            addExecutionStep(executionSteps, fromAgent, toAgent);
            
            if (isRegularFlow(workflowDef)) {
                executeRegularFlow(fromAgent, toAgent, executionSteps);
            } else if (isLoopFlow(workflowDef)) {
                executeLoopFlow(workflowDef, fromAgent, toAgent);
            }
        }
    }
    
    private void executeRegularFlow(Agent fromAgent, Agent toAgent, LinkedList<ExecutionStep> executionSteps) {
        if (!isTransitiveExecutionStep(executionSteps)) {
            ExecutionResult fromResult = fromAgent.execute();
            processExecutionResult(fromResult, toAgent);
        } else {
            ExecutionResult lastResult = fromAgent.getLastResult();
            processExecutionResult(lastResult, toAgent);
        }
        // TODO: Implement retry strategy
    }
    
    private void executeLoopFlow(WorkflowDefinition workflowDef, Agent fromAgent, Agent toAgent) {
        List<LoopDefinition> loopDefinitions = workflowDef.getLoop();
        for (LoopDefinition loopDef : loopDefinitions) {
            int maxIterations = loopDef.getMaxIterations();
            executeLoop(fromAgent, toAgent, maxIterations);
        }
    }
    
    private void executeLoop(Agent fromAgent, Agent toAgent, int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            ExecutionResult fromResult = fromAgent.execute();
            processExecutionResult(fromResult, toAgent);
        }
    }
    
    private void processExecutionResult(ExecutionResult result, Agent targetAgent) {
        if (result.isSuccess()) {
            List<AgentOutput> outputs = result.getResult();
            targetAgent.execute(outputs);
        } else {
            // TODO: Implement retry strategy or error handling
        }
    }

    private void addExecutionStep(LinkedList<ExecutionStep> executionSteps, Agent fromAgent, Agent toAgent) {
        ExecutionStep step = new ExecutionStep(fromAgent, toAgent);
        executionSteps.add(step);
    }

    private List<Agent> createAgents(WorkflowDefinitions workflowDefs) throws AgentCreateException {
        List<Agent> agents = new ArrayList<>();
        for (AgentDefinition agentDef : workflowDefs.getAgents()) {
            agents.add(createSingleAgent(agentDef));
        }
        return agents;
    }
    
    private Agent createSingleAgent(AgentDefinition agentDef) throws AgentCreateException {
        String type = agentDef.getType();
        String name = agentDef.getName();
        String systemMessage = agentDef.getSystemMessage();
        
        Map<String, Object> properties = new HashMap<>();
        String sysMsgPropKey = agentPort.getSysMsgPropKey();
        properties.put(sysMsgPropKey, systemMessage);
        
        return agentPort.createAgent(type, name, properties);
    }

    /**
     * Checks if the last two execution steps are transitive:
     * The current step is from agent B to agent C and the second last step was from agent A to agent B.
     * A and C can be the same agent.
     * 
     * @param executionSteps List of execution steps
     * @return true if the last two execution steps are transitive, false otherwise
     */
    private boolean isTransitiveExecutionStep(LinkedList<ExecutionStep> executionSteps) {
        Iterator<ExecutionStep> descendingIterator = executionSteps.descendingIterator();
        if (descendingIterator.hasNext()) {
            ExecutionStep currentStep = descendingIterator.next();
            if (descendingIterator.hasNext()) {
                ExecutionStep previousStep = descendingIterator.next();
                return previousStep.getNode().to().getName().equals(currentStep.getNode().from().getName());
            }
        }
        return false;
    }

    private Agent findAgent(List<Agent> agents, String declaration) {
        return agents.stream()
                .filter(agent -> agent.getName().equals(declaration))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Agent not found: " + declaration));
    }

    private boolean isLoopFlow(WorkflowDefinition workflowDef) {
        return workflowDef.getLoop() != null && !workflowDef.getLoop().isEmpty();
    }

    private boolean isRegularFlow(WorkflowDefinition workflowDef) {
        return workflowDef.getFrom() != null && workflowDef.getTo() != null
                && !workflowDef.getFrom().isBlank()
                && !workflowDef.getTo().isBlank();
    }
}