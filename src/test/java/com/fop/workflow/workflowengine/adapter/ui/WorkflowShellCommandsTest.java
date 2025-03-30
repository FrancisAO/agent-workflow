package com.fop.workflow.workflowengine.adapter.ui;

import com.fop.workflow.workflowengine.application.port.in.WorkflowExecutionUseCase;
import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkflowShellCommandsTest {

    @Mock
    private WorkflowExecutionUseCase workflowExecutionUseCase;

    private WorkflowShellCommands workflowShellCommands;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workflowShellCommands = new WorkflowShellCommands(workflowExecutionUseCase);
    }

    @Test
    public void executeWorkflow_NullPath_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            workflowShellCommands.executeWorkflow(null);
        });
        assertEquals("Workflow path cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void executeWorkflow_EmptyPath_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            workflowShellCommands.executeWorkflow("   ");
        });
        assertEquals("Workflow path cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void executeWorkflow_NonExistentFile_ThrowsException() throws IOException {
        String nonExistentPath = "path/to/nonexistent/workflow.yaml";
        Path path = Paths.get(nonExistentPath);
        
        // Mock Files.exists to return false
        try (var mock = mockStatic(Files.class)) {
            mock.when(() -> Files.exists(path)).thenReturn(false);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                workflowShellCommands.executeWorkflow(nonExistentPath);
            });
            assertEquals("Workflow file does not exist: " + nonExistentPath, exception.getMessage());
        }
    }

    @Test
    public void executeWorkflow_PathIsNotAFile_ThrowsException() throws IOException {
        String directoryPath = "path/to/directory";
        Path path = Paths.get(directoryPath);
        
        // Mock Files.exists and Files.isRegularFile
        try (var mock = mockStatic(Files.class)) {
            mock.when(() -> Files.exists(path)).thenReturn(true);
            mock.when(() -> Files.isRegularFile(path)).thenReturn(false);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                workflowShellCommands.executeWorkflow(directoryPath);
            });
            assertEquals("Path is not a file: " + directoryPath, exception.getMessage());
        }
    }

    @Test
    public void executeWorkflow_InvalidFileExtension_ThrowsException() throws IOException {
        String invalidExtensionPath = "path/to/workflow.txt";
        Path path = Paths.get(invalidExtensionPath);
        
        // Mock Files.exists and Files.isRegularFile
        try (var mock = mockStatic(Files.class)) {
            mock.when(() -> Files.exists(path)).thenReturn(true);
            mock.when(() -> Files.isRegularFile(path)).thenReturn(true);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                workflowShellCommands.executeWorkflow(invalidExtensionPath);
            });
            assertEquals("Workflow file must be a YAML file: " + invalidExtensionPath, exception.getMessage());
        }
    }

    @Test
    public void executeWorkflow_ValidPath_CallsReadWorkflow() throws IOException {
        String validPath = Paths.get("architecture", "specification", "workflow-example.yaml").toString();
        WorkflowSpec mockSpec = new WorkflowSpec();

        // Mock Files.exists and Files.isRegularFile
        Path path = Paths.get(validPath);
        try (var mock = mockStatic(Files.class)) {
            mock.when(() -> Files.exists(path)).thenReturn(true);
            mock.when(() -> Files.isRegularFile(path)).thenReturn(true);
        }

        when(workflowExecutionUseCase.readWorkflow(validPath)).thenReturn(mockSpec);

        workflowShellCommands.executeWorkflow(validPath);

        verify(workflowExecutionUseCase, times(1)).readWorkflow(validPath);
    }
}