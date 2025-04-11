package com.fop.workflow.workflowengine.adapter.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.fop.workflow.workflowengine.adapter.schema.json.WorkflowSpecReaderAdapter;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;

public class WorkflowSpecReaderAdapterTest {

    @Test
    void testReadWorkflowSpec() throws IOException {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        WorkflowDefinitions workflowSpec = adapter.readWorkflowSpec("architecture/specification/workflow-example.yaml");

        assertNotNull(workflowSpec);
        assertEquals("1.0", workflowSpec.getVersion());
        assertEquals("Sample Workflow", workflowSpec.getName());
        assertEquals("This is a sample workflow specification.", workflowSpec.getDescription());
        assertEquals(2, workflowSpec.getAgents().size());
        assertEquals("Agent A", workflowSpec.getAgents().get(0).getName());
        assertEquals("Type A", workflowSpec.getAgents().get(0).getType());
        assertEquals("This agent does something.", workflowSpec.getAgents().get(0).getDescription());
        assertEquals("System message for Agent A.", workflowSpec.getAgents().get(0).getSystemMessage());
        assertEquals("Prompt for Agent A.", workflowSpec.getAgents().get(0).getPrompt());
        assertEquals("Agent B", workflowSpec.getAgents().get(1).getName());
        assertEquals("Type B", workflowSpec.getAgents().get(1).getType());
        assertEquals("This agent does something else.", workflowSpec.getAgents().get(1).getDescription());
        assertEquals("System message for Agent B.", workflowSpec.getAgents().get(1).getSystemMessage());
        assertEquals("Prompt for Agent B.", workflowSpec.getAgents().get(1).getPrompt());
        assertEquals(2, workflowSpec.getWorkflow().size());
        assertEquals("Agent A", workflowSpec.getWorkflow().get(0).getFrom());
        assertEquals("Agent B", workflowSpec.getWorkflow().get(0).getTo());
        assertEquals("Some condition", workflowSpec.getWorkflow().get(0).getLoop().get(0).getCondition());
        assertEquals(5, workflowSpec.getWorkflow().get(0).getLoop().get(0).getMaxIterations());
        assertEquals("Agent B", workflowSpec.getWorkflow().get(1).getFrom());
        assertEquals("Agent A", workflowSpec.getWorkflow().get(1).getTo());
        assertEquals("Another condition", workflowSpec.getWorkflow().get(1).getLoop().get(0).getCondition());
        assertEquals(3, workflowSpec.getWorkflow().get(1).getLoop().get(0).getMaxIterations());
    }
    
    @Test
    public void testReadWorkflowSpec_NullPath() {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec(null)
        );
        
        assertEquals("Workflow path cannot be null or empty.", exception.getMessage());
    }
    
    @Test
    public void testReadWorkflowSpec_EmptyPath() {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec("")
        );
        
        assertEquals("Workflow path cannot be null or empty.", exception.getMessage());
    }
    
    @Test
    public void testReadWorkflowSpec_WhitespacePath() {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec("   ")
        );
        
        assertEquals("Workflow path cannot be null or empty.", exception.getMessage());
    }
    
    @Test
    public void testReadWorkflowSpec_NonExistentFile() {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        String nonExistentPath = "non-existent-file.yaml";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec(nonExistentPath)
        );
        
        assertEquals("Workflow file does not exist: " + nonExistentPath, exception.getMessage());
    }
    
    @Test
    public void testReadWorkflowSpec_NotAFile(@TempDir Path tempDir) throws IOException {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        Path directoryPath = Files.createDirectory(tempDir.resolve("test-directory"));
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec(directoryPath.toString())
        );
        
        assertEquals("Path is not a file: " + directoryPath, exception.getMessage());
    }
    
    @Test
    public void testReadWorkflowSpec_NotYamlFile(@TempDir Path tempDir) throws IOException {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        Path filePath = Files.createFile(tempDir.resolve("test-file.txt"));
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> adapter.readWorkflowSpec(filePath.toString())
        );
        
        assertEquals("Workflow file must be a YAML file: " + filePath, exception.getMessage());
    }
}