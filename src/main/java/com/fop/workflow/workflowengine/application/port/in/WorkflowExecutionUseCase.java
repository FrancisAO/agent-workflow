package com.fop.workflow.workflowengine.application.port.in;

import java.io.IOException;

import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;

public interface WorkflowExecutionUseCase {
    WorkflowSpec readWorkflow(String workflowPath) throws IOException;
}