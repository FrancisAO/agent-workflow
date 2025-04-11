package com.fop.workflow.workflowengine.application.port.out;

import java.util.List;

public interface WorkflowDefinition {

    /**
     * 
     * (Required)
     * 
     */
    String getFrom();

    /**
     * 
     * (Required)
     * 
     */
    void setFrom(String from);

    /**
     * 
     * (Required)
     * 
     */
    String getTo();

    /**
     * 
     * (Required)
     * 
     */
    void setTo(String to);

    List<LoopDefinition> getLoop();

    void setLoop(List<LoopDefinition> loop);

}