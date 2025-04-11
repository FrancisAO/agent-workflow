package com.fop.workflow.agents.application.port.in;

import java.util.Map;

public interface AgentPort {

    /**
     * Creates an agent of the specified type with the given name and properties.
     * @param type the type of the agent to create (e.g., "text", "code", etc.)
     * @param agentName the name of the agent to create
     * @param agentProperties a map of properties to configure the agent
     * @return the created agent
     * @throws AgentCreateException if there is an error during agent creation
     */
    Agent createAgent(String type, String agentName, Map<String, Object> agentProperties) throws AgentCreateException;

    String getSysMsgPropKey();
   
}