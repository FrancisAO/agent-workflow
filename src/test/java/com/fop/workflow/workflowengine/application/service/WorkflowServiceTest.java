package com.fop.workflow.workflowengine.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fop.workflow.workflowengine.application.port.out.WorkflowDefinitions;
import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;

public class WorkflowServiceTest {

    @Mock
    private WorkflowSpecReaderPort workflowSpecReaderPort;

    private WorkflowService workflowService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workflowService = new WorkflowService(workflowSpecReaderPort);
    }

    @Test
    public void testReadWorkflow_Success() throws IOException {
        String workflowPath = "path/to/workflow.yaml";
        WorkflowDefinitions mockSpec = Mockito.mock(WorkflowDefinitions.class); // Assuming a default constructor exists
        when(workflowSpecReaderPort.readWorkflowSpec(workflowPath)).thenReturn(mockSpec);

        WorkflowDefinitions result = workflowService.readWorkflow(workflowPath);

        assertNotNull(result);
        assertEquals(mockSpec, result);
        verify(workflowSpecReaderPort, times(1)).readWorkflowSpec(workflowPath);
    }

    @Test
    public void testReadWorkflow_IOException() throws IOException {
        String workflowPath = "path/to/nonexistent/workflow.yaml";
        IOException expectedException = new IOException("File not found");
        when(workflowSpecReaderPort.readWorkflowSpec(workflowPath)).thenThrow(expectedException);

        IOException thrownException = assertThrows(IOException.class, () -> {
            workflowService.readWorkflow(workflowPath);
        });

        assertEquals(expectedException, thrownException);
        verify(workflowSpecReaderPort, times(1)).readWorkflowSpec(workflowPath);
    }

    @Test
    public void testReadWorkflow_EmptyPath() throws IOException {
        String emptyPath = "";
        IOException expectedException = new IOException("Empty path is not allowed");
        when(workflowSpecReaderPort.readWorkflowSpec(emptyPath)).thenThrow(expectedException);

        IOException thrownException = assertThrows(IOException.class, () -> {
            workflowService.readWorkflow(emptyPath);
        });

        assertEquals(expectedException, thrownException);
        verify(workflowSpecReaderPort, times(1)).readWorkflowSpec(emptyPath);
    }
}