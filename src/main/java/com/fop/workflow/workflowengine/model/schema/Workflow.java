
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
    "from",
    "to",
    "loop"
})
@Generated("jsonschema2pojo")
public class Workflow {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("from")
    private String from;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("to")
    private String to;
    @JsonProperty("loop")
    private List<Loop> loop = new ArrayList<Loop>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Workflow() {
    }

    public Workflow(String from, String to, List<Loop> loop) {
        super();
        this.from = from;
        this.to = to;
        this.loop = loop;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    public Workflow withFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

    public Workflow withTo(String to) {
        this.to = to;
        return this;
    }

    @JsonProperty("loop")
    public List<Loop> getLoop() {
        return loop;
    }

    @JsonProperty("loop")
    public void setLoop(List<Loop> loop) {
        this.loop = loop;
    }

    public Workflow withLoop(List<Loop> loop) {
        this.loop = loop;
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

    public Workflow withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Workflow.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("from");
        sb.append('=');
        sb.append(((this.from == null)?"<null>":this.from));
        sb.append(',');
        sb.append("to");
        sb.append('=');
        sb.append(((this.to == null)?"<null>":this.to));
        sb.append(',');
        sb.append("loop");
        sb.append('=');
        sb.append(((this.loop == null)?"<null>":this.loop));
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
        result = ((result* 31)+((this.from == null)? 0 :this.from.hashCode()));
        result = ((result* 31)+((this.to == null)? 0 :this.to.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.loop == null)? 0 :this.loop.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Workflow) == false) {
            return false;
        }
        Workflow rhs = ((Workflow) other);
        return (((((this.from == rhs.from)||((this.from!= null)&&this.from.equals(rhs.from)))&&((this.to == rhs.to)||((this.to!= null)&&this.to.equals(rhs.to))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.loop == rhs.loop)||((this.loop!= null)&&this.loop.equals(rhs.loop))));
    }

}
