package com.fop.workflow.workflowengine.application.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fop.workflow.workflowengine.application.port.in.WorkflowExecutionUseCase;
import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;

@Service
public class WorkflowService implements WorkflowExecutionUseCase {

    private final WorkflowSpecReaderPort workflowSpecReaderPort;

    public WorkflowService(WorkflowSpecReaderPort workflowSpecReaderPort) {
        this.workflowSpecReaderPort = workflowSpecReaderPort;
    }

    @Override
    public WorkflowDefinitions readWorkflow(String workflowPath) throws IOException {
        return workflowSpecReaderPort.readWorkflowSpec(workflowPath);
    }
}