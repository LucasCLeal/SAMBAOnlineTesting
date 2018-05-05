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
 * @Edited LucasCLeal
 */
public class ModelData {

    private String name;
    private String id;
    private String generator;
    private String startElementId;
    private List<String> actions = new ArrayList<>();
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<>();

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
     * @return The generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     *
     * @param generator The generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     *
     * @return The startElementId
     */
    public String getStartElementId() {
        return startElementId;
    }

    /**
     *
     * @param startElementId The startElementId
     */
    public void setStartElementId(String startElementId) {
        this.startElementId = startElementId;
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
     * @return The vertices
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     *
     * @param vertices The vertices
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    /**
     *
     * @return The edges
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     *
     * @param edges The edges
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
