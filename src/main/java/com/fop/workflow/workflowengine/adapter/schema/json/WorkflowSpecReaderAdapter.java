package com.fop.workflow.workflowengine.adapter.schema.json;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fop.workflow.workflowengine.adapter.schema.model.AgentDefinitionImpl;
import com.fop.workflow.workflowengine.adapter.schema.model.LoopDefinitionImpl;
import com.fop.workflow.workflowengine.adapter.schema.model.WorkflowDefinitionImpl;
import com.fop.workflow.workflowengine.adapter.schema.model.WorkflowDefinitionsImpl;
import com.fop.workflow.workflowengine.application.port.out.AgentDefinition;
import com.fop.workflow.workflowengine.application.port.out.LoopDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinition;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import org.springframework.stereotype.Component;

@Component
public class WorkflowSpecReaderAdapter implements WorkflowSpecReaderPort {
    @Override
    public WorkflowDefinitions readWorkflowSpec(String path) throws IOException {
        checkPath(path);

        Object yamlObject = readYAML(path);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(yamlObject);

        checkYamlAgainstSchema(objectMapper, jsonString);

        // Deserialize the JSON into a WorkflowSpec instance
        WorkflowSpec workflowSpec = objectMapper.readValue(jsonString, WorkflowSpec.class);

        // Convert WorkflowSpec to WorkflowDefinitions
        return convertToWorkflowDefinitions(workflowSpec);

    }

    private void checkYamlAgainstSchema(ObjectMapper objectMapper, String jsonString)
            throws IOException {
        JsonSchema schema = readSchema();
        Set<ValidationMessage> validationMessages = schema.validate(objectMapper.readTree(jsonString));

        if (!validationMessages.isEmpty()) {
            throw new IllegalArgumentException("Workflow specification validation failed: " + validationMessages);
        }
    }

    private JsonSchema readSchema() throws IOException {
        InputStream schemaInputStream = Files
                .newInputStream(Paths.get("architecture", "specification", "workflow-spec.json"));
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemaInputStream);
        return schema;
    }

    private Object readYAML(String path) throws IOException, StreamReadException, DatabindException {
        InputStream yamlInputStream = Files.newInputStream(Paths.get(path));
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object yamlObject = yamlReader.readValue(yamlInputStream, Object.class);
        return yamlObject;
    }

    private void checkPath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Workflow path cannot be null or empty.");
        }
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("Workflow file does not exist: " + path);
        }
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new IllegalArgumentException("Path is not a file: " + path);
        }
        if (!path.toLowerCase().endsWith(".yaml") && !path.toLowerCase().endsWith(".yml")) {
            throw new IllegalArgumentException("Workflow file must be a YAML file: " + path);
        }
    }

    /**
     * Converts a WorkflowSpec object to a WorkflowDefinitions object.
     *
     * @param workflowSpec the WorkflowSpec object to convert
     * @return the converted WorkflowDefinitions object
     */
    private WorkflowDefinitions convertToWorkflowDefinitions(WorkflowSpec workflowSpec) {
        // Create a new WorkflowDefinitionsImpl object
        WorkflowDefinitionsImpl workflowDefinitions = new WorkflowDefinitionsImpl();

        // Set the basic properties
        workflowDefinitions.setVersion(workflowSpec.getVersion());
        workflowDefinitions.setName(workflowSpec.getName());
        workflowDefinitions.setDescription(workflowSpec.getDescription());

        // Convert agents
        List<AgentDefinition> agentDefinitions = new ArrayList<>();
        for (Agent agent : workflowSpec.getAgents()) {
            AgentDefinitionImpl agentDefinition = new AgentDefinitionImpl();
            agentDefinition.setName(agent.getName());
            agentDefinition.setType(agent.getType());
            agentDefinition.setDescription(agent.getDescription());
            agentDefinition.setSystemMessage(agent.getSystemMessage());
            agentDefinition.setPrompt(agent.getPrompt());
            agentDefinitions.add(agentDefinition);
        }
        workflowDefinitions.setAgents(agentDefinitions);

        // Convert workflows
        List<WorkflowDefinition> workflowDefinitionList = new ArrayList<>();
        for (Workflow workflow : workflowSpec.getWorkflow()) {
           WorkflowDefinitionImpl workflowDefinition = new WorkflowDefinitionImpl();
            workflowDefinition.setFrom(workflow.getFrom());
            workflowDefinition.setTo(workflow.getTo());

            // Convert loops
            List<LoopDefinition> loopDefinitions = new ArrayList<>();
            if (workflow.getLoop() != null) {
                for (Loop loop : workflow.getLoop()) {
                    LoopDefinitionImpl loopDefinition = new LoopDefinitionImpl();
                    loopDefinition.setCondition(loop.getCondition());
                    loopDefinition.setMaxIterations(loop.getMaxIterations());
                    loopDefinitions.add(loopDefinition);
                }
            }
            workflowDefinition.setLoop(loopDefinitions);
            workflowDefinitionList.add(workflowDefinition);
        }
        workflowDefinitions.setWorkflow(workflowDefinitionList);

        return workflowDefinitions;
    }
}