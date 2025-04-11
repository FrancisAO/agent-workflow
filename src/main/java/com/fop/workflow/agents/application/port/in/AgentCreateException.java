package com.fop.workflow.agents.application.port.in;

public class AgentCreateException extends Exception {

    private static final long serialVersionUID = 1L;

    public AgentCreateException(String message) {
        super(message);
    }

    public AgentCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgentCreateException(Throwable cause) {
        super(cause);
    }
    
}
