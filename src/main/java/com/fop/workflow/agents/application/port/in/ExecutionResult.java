package com.fop.workflow.agents.application.port.in;

import java.util.Map;

public interface ExecutionResult {

    Map<String, Object> getResult();
    
    String getErrorMessage();

    boolean isSuccess();
}
