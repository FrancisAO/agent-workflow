package com.fop.workflow.workflowengine.adapter.ui;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.fop.workflow.workflowengine.application.port.in.WorkflowExecutionUseCase;
import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

@ShellComponent
public class WorkflowShellCommands {

    private final WorkflowExecutionUseCase workflowExecutionUseCase;

    public WorkflowShellCommands(WorkflowExecutionUseCase workflowExecutionUseCase) {
        this.workflowExecutionUseCase = workflowExecutionUseCase;
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
        try {
            WorkflowSpec workflowSpec = workflowExecutionUseCase.readWorkflow(path);
        } catch (IOException e) {
            throw new RuntimeException("Error reading workflow file: " + path, e);
        }

         
        // Additional execution logic can be added here
    }
}