package com.fop.workflow.workflowengine.application.port.in;

import java.io.IOException;

public interface WorkflowExecutionUseCase {
   

    void executeWorkflow(String workflowPath) throws IOException;
}