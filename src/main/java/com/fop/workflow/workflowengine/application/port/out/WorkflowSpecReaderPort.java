package com.fop.workflow.workflowengine.application.port.out;

import java.io.IOException;

public interface WorkflowSpecReaderPort {
    WorkflowDefinitions readWorkflowSpec(String path) throws IOException;
}