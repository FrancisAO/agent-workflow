
package com.fop.workflow.workflowengine.model.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "name",
    "description",
    "agents",
    "workflow"
})
@Generated("jsonschema2pojo")
public class WorkflowSpec {

    @JsonProperty("version")
    private String version;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("agents")
    private List<Agent> agents = new ArrayList<Agent>();
    @JsonProperty("workflow")
    private List<Workflow> workflow = new ArrayList<Workflow>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public WorkflowSpec() {
    }

    public WorkflowSpec(String version, String name, String description, List<Agent> agents, List<Workflow> workflow) {
        super();
        this.version = version;
        this.name = name;
        this.description = description;
        this.agents = agents;
        this.workflow = workflow;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public WorkflowSpec withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public WorkflowSpec withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public WorkflowSpec withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("agents")
    public List<Agent> getAgents() {
        return agents;
    }

    @JsonProperty("agents")
    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public WorkflowSpec withAgents(List<Agent> agents) {
        this.agents = agents;
        return this;
    }

    @JsonProperty("workflow")
    public List<Workflow> getWorkflow() {
        return workflow;
    }

    @JsonProperty("workflow")
    public void setWorkflow(List<Workflow> workflow) {
        this.workflow = workflow;
    }

    public WorkflowSpec withWorkflow(List<Workflow> workflow) {
        this.workflow = workflow;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public WorkflowSpec withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WorkflowSpec.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("agents");
        sb.append('=');
        sb.append(((this.agents == null)?"<null>":this.agents));
        sb.append(',');
        sb.append("workflow");
        sb.append('=');
        sb.append(((this.workflow == null)?"<null>":this.workflow));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.workflow == null)? 0 :this.workflow.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.agents == null)? 0 :this.agents.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WorkflowSpec) == false) {
            return false;
        }
        WorkflowSpec rhs = ((WorkflowSpec) other);
        return (((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.workflow == rhs.workflow)||((this.workflow!= null)&&this.workflow.equals(rhs.workflow))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.agents == rhs.agents)||((this.agents!= null)&&this.agents.equals(rhs.agents))));
    }

}
