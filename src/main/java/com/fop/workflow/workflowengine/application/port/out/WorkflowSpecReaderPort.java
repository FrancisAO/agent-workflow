package com.fop.workflow.workflowengine.application.port.out;

import java.io.IOException;

import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;

public interface WorkflowSpecReaderPort {
    WorkflowSpec readWorkflowSpec(String path) throws IOException;
}