package com.example.starterkit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaModelClass implements Serializable {

    private String title;
    private String url;

    public NasaModelClass(@JsonProperty("title") String title,
                          @JsonProperty("url") String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
