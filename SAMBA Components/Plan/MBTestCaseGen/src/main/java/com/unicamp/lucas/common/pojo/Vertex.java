/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.common.pojo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

/**
 * @Generated("org.jsonschema2pojo")
 * @Edited LucasCLeal
 */
public class Vertex {

    private String id;
    private String name;
    private Properties properties;
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
     * @return The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     *
     * @param properties The properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
