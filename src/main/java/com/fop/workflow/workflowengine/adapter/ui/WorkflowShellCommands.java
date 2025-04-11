package com.fop.workflow.workflowengine.adapter.ui;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.fop.workflow.agents.application.port.in.Agent;
import com.fop.workflow.agents.application.port.in.AgentCreateException;
import com.fop.workflow.agents.application.port.in.AgentPort;
import com.fop.workflow.workflowengine.application.port.in.WorkflowExecutionUseCase;
import com.fop.workflow.workflowengine.application.port.out.AgentDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

@ShellComponent
public class WorkflowShellCommands {

    private final WorkflowExecutionUseCase workflowExecutionUseCase;
    private AgentPort agentPort;

    public WorkflowShellCommands(WorkflowExecutionUseCase workflowExecutionUseCase, AgentPort agentPort) {
        this.workflowExecutionUseCase = workflowExecutionUseCase;
        this.agentPort = agentPort;
    }

    @ShellMethod("Executes a workflow from the specified path.")
    public void executeWorkflow(@ShellOption(value = { "path" }) String path) {
        // Validate the path is not null or empty
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Workflow path cannot be null or empty.");
        }

        Path workflowPath = Paths.get(path);

        // Check if the path exists
        if (!Files.exists(workflowPath)) {
            throw new IllegalArgumentException("Workflow file does not exist: " + path);
        }

        // Check if the path is a file
        if (!Files.isRegularFile(workflowPath)) {
            throw new IllegalArgumentException("Path is not a file: " + path);
        }

        // Check if the file is a YAML file
        String lowerCasePath = path.toLowerCase();
        if (!lowerCasePath.endsWith(".yaml") && !lowerCasePath.endsWith(".yml")) {
            throw new IllegalArgumentException("Workflow file must be a YAML file: " + path);
        }

        // Execute the workflow
        List<Agent> agents = new ArrayList<>();;
        try {
            WorkflowDefinitions workflowDef = workflowExecutionUseCase.readWorkflow(path);
            for(AgentDefinition agentDef : workflowDef.getAgents()) {
                String type = agentDef.getType();
                String name = agentDef.getName();
                String systemMessage = agentDef.getSystemMessage();
                Map<String, Object> properties = new HashMap<String, Object>();
                String sysMsgPropKey = agentPort.getSysMsgPropKey();
                properties.put(sysMsgPropKey, systemMessage);
                agents.add(agentPort.createAgent(name, type, properties));
            }

        } catch (IOException | AgentCreateException e) {
            throw new RuntimeException("Error reading workflow file: " + path, e);
        }

        agents.forEach(agent -> {
            // Execute the agent (this is a placeholder, actual execution logic will depend on your implementation)
            System.out.println("Executing agent: " + agent.getName());
            System.out.println("Agent type: " + agent.getType());
            System.out.println("Agent properties: " + agent.getProperties());
            System.out.println("Agent id: " + agent.getId());
        });

         
        // Additional execution logic can be added here
    }
}