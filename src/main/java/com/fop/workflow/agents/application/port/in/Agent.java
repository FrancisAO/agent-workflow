package com.fop.workflow.agents.application.port.in;

import java.util.Map;

public interface Agent {

    String getName();

    String getType();

    Map<String, Object> getProperties();

    Long getId();

    ExecutionResult execute();

    ExecutionResult execute(String prompt);

}
