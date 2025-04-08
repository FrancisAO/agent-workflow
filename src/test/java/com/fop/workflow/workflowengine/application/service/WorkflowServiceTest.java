package com.fop.workflow.workflowengine.application.service;

import com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort;
import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
        WorkflowSpec mockSpec = new WorkflowSpec(); // Assuming a default constructor exists
        when(workflowSpecReaderPort.readWorkflowSpec(workflowPath)).thenReturn(mockSpec);

        WorkflowSpec result = workflowService.readWorkflow(workflowPath);

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
    public void testReadWorkflow_NullPath() throws IOException {
        String nullPath = null;
        
        assertThrows(NullPointerException.class, () -> {
            workflowService.readWorkflow(nullPath);
        });
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