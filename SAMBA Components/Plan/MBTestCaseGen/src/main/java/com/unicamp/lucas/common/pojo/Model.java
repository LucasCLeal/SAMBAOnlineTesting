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
public class Model {

    private String name;
    private String creationDate;
    private List<ModelData> models = new ArrayList<ModelData>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }
    
     /**
     *
     * @return The creationDate
     */
    public String getCreationDate() {
        return creationDate;
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
     * @param creationDate The creationDate
     */
    public void setCreationDate(String creationDate) {
        this.name = creationDate;
    }

    /**
     *
     * @return The models
     */
    public List<ModelData> getModels() {
        return models;
    }

    /**
     *
     * @param models The models
     */
    public void setModels(List<ModelData> models) {
        this.models = models;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
