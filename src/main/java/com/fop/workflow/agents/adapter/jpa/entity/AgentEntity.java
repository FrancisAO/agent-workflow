package com.fop.workflow.agents.adapter.jpa.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * JPA Entity für Agents.
 * Speichert die grundlegenden Informationen eines Agents in der Datenbank.
 */
@Entity
public class AgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Lob
    @Convert(converter = AgentPropertiesConverter.class)
    @Column(name = "properties")
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Default-Konstruktor für JPA
     */
    public AgentEntity() {
    }

    /**
     * Konstruktor mit allen erforderlichen Feldern
     * 
     * @param name der Name des Agents
     * @param type der Typ des Agents
     */
    public AgentEntity(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Konstruktor mit allen Feldern
     * 
     * @param name der Name des Agents
     * @param type der Typ des Agents
     * @param properties die Eigenschaften des Agents
     */
    public AgentEntity(String name, String type, Map<String, Object> properties) {
        this.name = name;
        this.type = type;
        if (properties != null) {
            this.properties = properties;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties != null ? properties : new HashMap<>();
    }
}