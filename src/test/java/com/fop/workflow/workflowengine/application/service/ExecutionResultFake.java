package com.fop.workflow.workflowengine.application.service;

import java.util.Collections;
import java.util.List;

import com.fop.workflow.agents.application.port.in.AgentOutput;
import com.fop.workflow.agents.application.port.in.ExecutionResult;

public class ExecutionResultFake implements ExecutionResult {

    private final List<AgentOutput> result;
    private final String errorMessage;
    private final boolean success;

    public ExecutionResultFake(String type, Object randomValue) {
        this.result = Collections.singletonList(new AgentOutputFake(type, randomValue));
        this.errorMessage = "";
        this.success = true;
    }

    @Override
    public List<AgentOutput> getResult() {
        return result;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    /**
     * Innere Klasse, die das AgentOutput-Interface implementiert
     */
    private static class AgentOutputFake implements AgentOutput {
        private final String type;
        private final Object value;

        public AgentOutputFake(String type, Object value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public Object getValue() {
            return value;
        }
    }
}