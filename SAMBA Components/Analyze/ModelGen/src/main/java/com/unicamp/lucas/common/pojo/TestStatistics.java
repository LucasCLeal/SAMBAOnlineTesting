package com.unicamp.lucas.common.pojo;

/**
 *
 * @author LucasCLeal
 */
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "edgeCoverage",
    "result",
    "totalNumberOfEdges",
    "totalNumberOfUnvisitedEdges",
    "totalNumberOfUnvisitedVertices",
    "totalNumberOfVertices",
    "totalNumberOfVisitedEdges",
    "totalNumberOfVisitedVertices",
    "vertexCoverage"
})

public class TestStatistics {

    @JsonProperty("edgeCoverage")
    private String edgeCoverage;
    @JsonProperty("result")
    private String result;
    @JsonProperty("totalNumberOfEdges")
    private String totalNumberOfEdges;
    @JsonProperty("totalNumberOfUnvisitedEdges")
    private String totalNumberOfUnvisitedEdges;
    @JsonProperty("totalNumberOfUnvisitedVertices")
    private String totalNumberOfUnvisitedVertices;
    @JsonProperty("totalNumberOfVertices")
    private String totalNumberOfVertices;
    @JsonProperty("totalNumberOfVisitedEdges")
    private String totalNumberOfVisitedEdges;
    @JsonProperty("totalNumberOfVisitedVertices")
    private String totalNumberOfVisitedVertices;
    @JsonProperty("vertexCoverage")
    private String vertexCoverage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public TestStatistics() {
    }

    /**
     *
     * @param totalNumberOfVertices
     * @param result
     * @param edgeCoverage
     * @param vertexCoverage
     * @param totalNumberOfUnvisitedEdges
     * @param totalNumberOfEdges
     * @param totalNumberOfUnvisitedVertices
     * @param totalNumberOfVisitedVertices
     * @param totalNumberOfVisitedEdges
     */
    public TestStatistics(String edgeCoverage, String result, String totalNumberOfEdges, String totalNumberOfUnvisitedEdges, String totalNumberOfUnvisitedVertices, String totalNumberOfVertices, String totalNumberOfVisitedEdges, String totalNumberOfVisitedVertices, String vertexCoverage) {
        this.edgeCoverage = edgeCoverage;
        this.result = result;
        this.totalNumberOfEdges = totalNumberOfEdges;
        this.totalNumberOfUnvisitedEdges = totalNumberOfUnvisitedEdges;
        this.totalNumberOfUnvisitedVertices = totalNumberOfUnvisitedVertices;
        this.totalNumberOfVertices = totalNumberOfVertices;
        this.totalNumberOfVisitedEdges = totalNumberOfVisitedEdges;
        this.totalNumberOfVisitedVertices = totalNumberOfVisitedVertices;
        this.vertexCoverage = vertexCoverage;
    }

    /**
     *
     * @return The edgeCoverage
     */
    @JsonProperty("edgeCoverage")
    public String getEdgeCoverage() {
        return edgeCoverage;
    }

    /**
     *
     * @param edgeCoverage The edgeCoverage
     */
    @JsonProperty("edgeCoverage")
    public void setEdgeCoverage(String edgeCoverage) {
        this.edgeCoverage = edgeCoverage;
    }

    public TestStatistics withEdgeCoverage(String edgeCoverage) {
        this.edgeCoverage = edgeCoverage;
        return this;
    }

    /**
     *
     * @return The result
     */
    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result The result
     */
    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    public TestStatistics withResult(String result) {
        this.result = result;
        return this;
    }

    /**
     *
     * @return The totalNumberOfEdges
     */
    @JsonProperty("totalNumberOfEdges")
    public String getTotalNumberOfEdges() {
        return totalNumberOfEdges;
    }

    /**
     *
     * @param totalNumberOfEdges The totalNumberOfEdges
     */
    @JsonProperty("totalNumberOfEdges")
    public void setTotalNumberOfEdges(String totalNumberOfEdges) {
        this.totalNumberOfEdges = totalNumberOfEdges;
    }

    public TestStatistics withTotalNumberOfEdges(String totalNumberOfEdges) {
        this.totalNumberOfEdges = totalNumberOfEdges;
        return this;
    }

    /**
     *
     * @return The totalNumberOfUnvisitedEdges
     */
    @JsonProperty("totalNumberOfUnvisitedEdges")
    public String getTotalNumberOfUnvisitedEdges() {
        return totalNumberOfUnvisitedEdges;
    }

    /**
     *
     * @param totalNumberOfUnvisitedEdges The totalNumberOfUnvisitedEdges
     */
    @JsonProperty("totalNumberOfUnvisitedEdges")
    public void setTotalNumberOfUnvisitedEdges(String totalNumberOfUnvisitedEdges) {
        this.totalNumberOfUnvisitedEdges = totalNumberOfUnvisitedEdges;
    }

    public TestStatistics withTotalNumberOfUnvisitedEdges(String totalNumberOfUnvisitedEdges) {
        this.totalNumberOfUnvisitedEdges = totalNumberOfUnvisitedEdges;
        return this;
    }

    /**
     *
     * @return The totalNumberOfUnvisitedVertices
     */
    @JsonProperty("totalNumberOfUnvisitedVertices")
    public String getTotalNumberOfUnvisitedVertices() {
        return totalNumberOfUnvisitedVertices;
    }

    /**
     *
     * @param totalNumberOfUnvisitedVertices The totalNumberOfUnvisitedVertices
     */
    @JsonProperty("totalNumberOfUnvisitedVertices")
    public void setTotalNumberOfUnvisitedVertices(String totalNumberOfUnvisitedVertices) {
        this.totalNumberOfUnvisitedVertices = totalNumberOfUnvisitedVertices;
    }

    public TestStatistics withTotalNumberOfUnvisitedVertices(String totalNumberOfUnvisitedVertices) {
        this.totalNumberOfUnvisitedVertices = totalNumberOfUnvisitedVertices;
        return this;
    }

    /**
     *
     * @return The totalNumberOfVertices
     */
    @JsonProperty("totalNumberOfVertices")
    public String getTotalNumberOfVertices() {
        return totalNumberOfVertices;
    }

    /**
     *
     * @param totalNumberOfVertices The totalNumberOfVertices
     */
    @JsonProperty("totalNumberOfVertices")
    public void setTotalNumberOfVertices(String totalNumberOfVertices) {
        this.totalNumberOfVertices = totalNumberOfVertices;
    }

    public TestStatistics withTotalNumberOfVertices(String totalNumberOfVertices) {
        this.totalNumberOfVertices = totalNumberOfVertices;
        return this;
    }

    /**
     *
     * @return The totalNumberOfVisitedEdges
     */
    @JsonProperty("totalNumberOfVisitedEdges")
    public String getTotalNumberOfVisitedEdges() {
        return totalNumberOfVisitedEdges;
    }

    /**
     *
     * @param totalNumberOfVisitedEdges The totalNumberOfVisitedEdges
     */
    @JsonProperty("totalNumberOfVisitedEdges")
    public void setTotalNumberOfVisitedEdges(String totalNumberOfVisitedEdges) {
        this.totalNumberOfVisitedEdges = totalNumberOfVisitedEdges;
    }

    public TestStatistics withTotalNumberOfVisitedEdges(String totalNumberOfVisitedEdges) {
        this.totalNumberOfVisitedEdges = totalNumberOfVisitedEdges;
        return this;
    }

    /**
     *
     * @return The totalNumberOfVisitedVertices
     */
    @JsonProperty("totalNumberOfVisitedVertices")
    public String getTotalNumberOfVisitedVertices() {
        return totalNumberOfVisitedVertices;
    }

    /**
     *
     * @param totalNumberOfVisitedVertices The totalNumberOfVisitedVertices
     */
    @JsonProperty("totalNumberOfVisitedVertices")
    public void setTotalNumberOfVisitedVertices(String totalNumberOfVisitedVertices) {
        this.totalNumberOfVisitedVertices = totalNumberOfVisitedVertices;
    }

    public TestStatistics withTotalNumberOfVisitedVertices(String totalNumberOfVisitedVertices) {
        this.totalNumberOfVisitedVertices = totalNumberOfVisitedVertices;
        return this;
    }

    /**
     *
     * @return The vertexCoverage
     */
    @JsonProperty("vertexCoverage")
    public String getVertexCoverage() {
        return vertexCoverage;
    }

    /**
     *
     * @param vertexCoverage The vertexCoverage
     */
    @JsonProperty("vertexCoverage")
    public void setVertexCoverage(String vertexCoverage) {
        this.vertexCoverage = vertexCoverage;
    }

    public TestStatistics withVertexCoverage(String vertexCoverage) {
        this.vertexCoverage = vertexCoverage;
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

    public TestStatistics withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
