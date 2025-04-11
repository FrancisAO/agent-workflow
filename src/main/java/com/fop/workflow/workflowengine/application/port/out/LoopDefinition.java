package com.fop.workflow.workflowengine.application.port.out;

public interface LoopDefinition {

    /**
     * 
     * (Required)
     * 
     */
    String getCondition();

    /**
     * 
     * (Required)
     * 
     */
    void setCondition(String condition);

    /**
     * 
     * (Required)
     * 
     */
    Integer getMaxIterations();

    /**
     * 
     * (Required)
     * 
     */
    void setMaxIterations(Integer maxIterations);

}