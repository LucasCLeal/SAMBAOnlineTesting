/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.common.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

/**
 * @Generated("org.jsonschema2pojo")
 * @edited by LucasCLeal
 */
public class Edge {

    private String id;
    private String name;
    private List<String> actions = new ArrayList<String>();
    private String targetVertexId;
    private String sourceVertexId;
    private String guard;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The actions
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     *
     * @param actions The actions
     */
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    /**
     *
     * @return The targetVertexId
     */
    public String getTargetVertexId() {
        return targetVertexId;
    }

    /**
     *
     * @param targetVertexId The targetVertexId
     */
    public void setTargetVertexId(String targetVertexId) {
        this.targetVertexId = targetVertexId;
    }

    /**
     *
     * @return The sourceVertexId
     */
    public String getSourceVertexId() {
        return sourceVertexId;
    }

    /**
     *
     * @param sourceVertexId The sourceVertexId
     */
    public void setSourceVertexId(String sourceVertexId) {
        this.sourceVertexId = sourceVertexId;
    }

    /**
     *
     * @return The guard
     */
    public String getGuard() {
        return guard;
    }

    /**
     *
     * @param guard The guard
     */
    public void setGuard(String guard) {
        this.guard = guard;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
