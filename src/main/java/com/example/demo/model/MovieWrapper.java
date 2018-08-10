package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieWrapper {
    private List<Movie> search;

    private String totalResults;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Search")
    public List<Movie> getSearch() {
        return search;
    }

    @JsonProperty("Search")
    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    @JsonIgnore
    public String getTotalResults() {
        return totalResults;
    }

    @JsonIgnore
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    @JsonIgnore
    public String getResponse() {
        return response;
    }

    @JsonIgnore
    public void setResponse(String response) {
        this.response = response;
    }
}