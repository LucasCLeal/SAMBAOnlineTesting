/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.common.pojo;

/**
 *
 * @author LucasCLeal
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "file"
})
public class FileInfo {

    @JsonProperty("file")
    private List<String> file = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public FileInfo() {
    }

    /**
     *
     * @param model
     */
    public FileInfo(List<String> file) {
        super();
        this.file = file;
    }

    /**
     *
     * @return The model
     */
    @JsonProperty("file")
    public List<String> getFile() {
        return file;
    }

    /**
     *
     * @param model The model
     */
    @JsonProperty("file")
    public void setFile(List<String> file) {
        this.file = file;
    }

    public FileInfo withFile(List<String> file) {
        this.file = file;
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

    public FileInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
