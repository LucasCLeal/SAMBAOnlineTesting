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
"model"
})
public class ModelsInfo {

@JsonProperty("model")
private List<String> model = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public ModelsInfo() {
}

/**
* 
* @param model
*/
public ModelsInfo(List<String> model) {
super();
this.model = model;
}

/**
* 
* @return
* The model
*/
@JsonProperty("model")
public List<String> getModel() {
return model;
}

/**
* 
* @param model
* The model
*/
@JsonProperty("model")
public void setModel(List<String> model) {
this.model = model;
}

public ModelsInfo withModel(List<String> model) {
this.model = model;
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

public ModelsInfo withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}