package com.fop.workflow.workflowengine.adapter.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;
import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class WorkflowSpecReaderAdapter implements WorkflowSpecReaderPort {
    @Override
    public WorkflowSpec readWorkflowSpec(String path) throws IOException {
        // 1. YAML-Datei lesen und in JSON umwandeln
        InputStream yamlInputStream = Files.newInputStream(Paths.get(path));
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object yamlObject = yamlReader.readValue(yamlInputStream, Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(yamlObject);

        // 2. JSON-Schema laden
        InputStream schemaInputStream = Files
                .newInputStream(Paths.get("architecture", "specification", "workflow-spec.json"));
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemaInputStream);

        // 3. JSON-Objekt validieren
        Set<ValidationMessage> validationMessages = schema.validate(objectMapper.readTree(jsonString));

        if (!validationMessages.isEmpty()) {
            throw new IllegalArgumentException("Workflow specification validation failed: " + validationMessages);
        }

        // Deserialize the JSON into a WorkflowSpec instance
        return objectMapper.readValue(jsonString, WorkflowSpec.class);
    }
}