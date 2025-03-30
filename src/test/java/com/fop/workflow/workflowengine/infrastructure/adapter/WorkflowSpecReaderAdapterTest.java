package com.fop.workflow.workflowengine.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import com.fop.workflow.workflowengine.adapter.io.WorkflowSpecReaderAdapter;
import com.fop.workflow.workflowengine.model.schema.WorkflowSpec;

class WorkflowSpecReaderAdapterTest {
    @Test
    void testReadWorkflowSpec() throws IOException {
        WorkflowSpecReaderAdapter adapter = new WorkflowSpecReaderAdapter();
        WorkflowSpec workflowSpec = adapter.readWorkflowSpec("architecture/specification/workflow-example.yaml");

        assertNotNull(workflowSpec);
        assertEquals("1.0", workflowSpec.getVersion());
        assertEquals("Sample Workflow", workflowSpec.getName());
        assertEquals("This is a sample workflow specification.", workflowSpec.getDescription());
        assertEquals(2, workflowSpec.getAgents().size());
        assertEquals("Agent A", workflowSpec.getAgents().get(0).getName());
        assertEquals("Type A", workflowSpec.getAgents().get(0).getType());
        assertEquals("This agent does something.", workflowSpec.getAgents().get(0).getDescription());
        assertEquals("System message for Agent A.", workflowSpec.getAgents().get(0).getSystemMessage());
        assertEquals("Prompt for Agent A.", workflowSpec.getAgents().get(0).getPrompt());
        assertEquals("Agent B", workflowSpec.getAgents().get(1).getName());
        assertEquals("Type B", workflowSpec.getAgents().get(1).getType());
        assertEquals("This agent does something else.", workflowSpec.getAgents().get(1).getDescription());
        assertEquals("System message for Agent B.", workflowSpec.getAgents().get(1).getSystemMessage());
        assertEquals("Prompt for Agent B.", workflowSpec.getAgents().get(1).getPrompt());
        assertEquals(2, workflowSpec.getWorkflow().size());
        assertEquals("Agent A", workflowSpec.getWorkflow().get(0).getFrom());
        assertEquals("Agent B", workflowSpec.getWorkflow().get(0).getTo());
        assertEquals("Some condition", workflowSpec.getWorkflow().get(0).getLoop().get(0).getCondition());
        assertEquals(5, workflowSpec.getWorkflow().get(0).getLoop().get(0).getMaxIterations());
        assertEquals("Agent B", workflowSpec.getWorkflow().get(1).getFrom());
        assertEquals("Agent A", workflowSpec.getWorkflow().get(1).getTo());
        assertEquals("Another condition", workflowSpec.getWorkflow().get(1).getLoop().get(0).getCondition());
        assertEquals(3, workflowSpec.getWorkflow().get(1).getLoop().get(0).getMaxIterations());
    }
}