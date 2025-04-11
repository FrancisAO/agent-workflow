package com.fop.workflow.agents.application.port.in;

/**
 * Internal interface for agents used by the persistence layer.
 * Defines the basic properties of an agent required for persistence.
 */
public interface AgentIntern extends Agent{
    
    /**
     * Sets the ID of the agent.
     *
     * @param id the ID to set
     */
    void setId(Long id);
}
