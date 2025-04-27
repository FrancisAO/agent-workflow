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
    private AgentPort agentPort;

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

            for (WorkflowDefinition workflowDef : workflowDefs.getWorkflow()) {
                Agent fromAgent = findAgent(agents, workflowDef.getFrom());
                Agent toAgent = findAgent(agents, workflowDef.getTo());
                addExecutionStep(executionSteps, fromAgent, toAgent);
                if (isRegularFlow(workflowDef)) {
                    if (!isTransitiveExecutionStep(executionSteps)) {
                        ExecutionResult fromResult = fromAgent.execute();
                        if (fromResult.isSuccess()) {
                            List<AgentOutput> results = fromResult.getResult();
                            toAgent.execute(results);
                        } else {
                            // todo: retry-strategy ausführen wenn nicht erfolgreich
                        }
                    } else {
                        ExecutionResult lastResult = fromAgent.getLastResult();
                        if (lastResult.isSuccess()) {
                            List<AgentOutput> results = lastResult.getResult();
                            toAgent.execute(results);
                        } else {
                            // todo: abbruch wenn Ausführung von vorherigem Agent nicht erfolgreich
                        }
                    }
                    // todo: retry-strategy ausführen

                } else if (isLoopFlow(workflowDef)) {
                    List<LoopDefinition> loop = workflowDef.getLoop();
                    for (LoopDefinition loopDef : loop) {
                        Integer maxIterations = loopDef.getMaxIterations();
                        for (int i = maxIterations; i > 0; i--) {
                            ExecutionResult fromResult = fromAgent.execute();
                            if (fromResult.isSuccess()) {
                                List<AgentOutput> results = fromResult.getResult();
                                toAgent.execute(results);
                            } else {
                                // todo: retry-strategy ausführen
                            }
                        }
                    }
                }
            }
        } catch (AgentCreateException e) {
            throw new RuntimeException("Error creating agent: " + e.getMessage(), e);
        }
    }

    private void addExecutionStep(LinkedList<ExecutionStep> executionSteps, Agent fromAgent, Agent toAgent) {
        ExecutionStep step = new ExecutionStep(fromAgent, toAgent);
        executionSteps.add(step);
    }

    private List<Agent> createAgents(WorkflowDefinitions workflowDefs) throws AgentCreateException {
        List<Agent> agents = new ArrayList<>();
        for (AgentDefinition agentDef : workflowDefs.getAgents()) {
            String type = agentDef.getType();
            String name = agentDef.getName();
            String systemMessage = agentDef.getSystemMessage();
            Map<String, Object> properties = new HashMap<String, Object>();
            String sysMsgPropKey = agentPort.getSysMsgPropKey();
            properties.put(sysMsgPropKey, systemMessage);
            agents.add(agentPort.createAgent(type, name, properties));
        }
        return agents;
    }

    /**
     * checks if the last two execution steps are transitive realize transitivity, i.e.
     * the current step is from agent B to agent C and the second last step was from agent A to agent B.
     * A and C can be the same agent.
     * @param executionSteps
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