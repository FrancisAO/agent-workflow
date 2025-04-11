package com.fop.workflow.agents.adapter.jpa.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository für den Zugriff auf AgentEntity-Objekte in der Datenbank.
 */
public interface AgentEntityRepository extends JpaRepository<AgentEntity, Long> {
    
}