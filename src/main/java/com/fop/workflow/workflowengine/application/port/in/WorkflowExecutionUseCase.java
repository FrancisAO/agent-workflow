package com.fop.workflow.workflowengine.application.port.in;

import java.io.IOException;

import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;

public interface WorkflowExecutionUseCase {
    WorkflowDefinitions readWorkflow(String workflowPath) throws IOException;
}