
package com.fop.workflow.workflowengine.adapter.schema.json;

import java.util.LinkedHashMap;
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
    "condition",
    "max_iterations"
})
@Generated("jsonschema2pojo")
public class Loop {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("condition")
    private String condition;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("max_iterations")
    private Integer maxIterations;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Loop() {
    }

    public Loop(String condition, Integer maxIterations) {
        super();
        this.condition = condition;
        this.maxIterations = maxIterations;
    }

    /**
     * 
     * (Required)
     * 
     */
    
    @JsonProperty("condition")
    public String getCondition() {
        return condition;
    }

    /**
     * 
     * (Required)
     * 
     */
    
    @JsonProperty("condition")
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * 
     * (Required)
     * 
     */
    
    @JsonProperty("max_iterations")
    public Integer getMaxIterations() {
        return maxIterations;
    }

    /**
     * 
     * (Required)
     * 
     */
    
    @JsonProperty("max_iterations")
    public void setMaxIterations(Integer maxIterations) {
        this.maxIterations = maxIterations;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Loop.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("condition");
        sb.append('=');
        sb.append(((this.condition == null)?"<null>":this.condition));
        sb.append(',');
        sb.append("maxIterations");
        sb.append('=');
        sb.append(((this.maxIterations == null)?"<null>":this.maxIterations));
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

    
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.condition == null)? 0 :this.condition.hashCode()));
        result = ((result* 31)+((this.maxIterations == null)? 0 :this.maxIterations.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Loop) == false) {
            return false;
        }
        Loop rhs = ((Loop) other);
        return ((((this.condition == rhs.condition)||((this.condition!= null)&&this.condition.equals(rhs.condition)))&&((this.maxIterations == rhs.maxIterations)||((this.maxIterations!= null)&&this.maxIterations.equals(rhs.maxIterations))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
