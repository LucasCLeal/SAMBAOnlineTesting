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
public class Properties {

    private Double x;
    private Double y;
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return The x
     */
    public Double getX() {
        return x;
    }

    /**
     *
     * @param x The x
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     *
     * @return The y
     */
    public Double getY() {
        return y;
    }

    /**
     *
     * @param y The y
     */
    public void setY(Double y) {
        this.y = y;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
