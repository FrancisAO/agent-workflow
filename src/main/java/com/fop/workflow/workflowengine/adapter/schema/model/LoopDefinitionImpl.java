package com.fop.workflow.workflowengine.adapter.schema.model;

import com.fop.workflow.workflowengine.application.port.out.LoopDefinition;

/**
 * Implementation of the LoopDefinition interface.
 */
public class LoopDefinitionImpl implements LoopDefinition {

    private String condition;
    private Integer maxIterations;

    /**
     * Default constructor.
     */
    public LoopDefinitionImpl() {
    }

    /**
     * Constructor with all fields.
     *
     * @param condition     the condition for the loop
     * @param maxIterations the maximum number of iterations
     */
    public LoopDefinitionImpl(String condition, Integer maxIterations) {
        this.condition = condition;
        this.maxIterations = maxIterations;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public Integer getMaxIterations() {
        return maxIterations;
    }

    @Override
    public void setMaxIterations(Integer maxIterations) {
        this.maxIterations = maxIterations;
    }
}