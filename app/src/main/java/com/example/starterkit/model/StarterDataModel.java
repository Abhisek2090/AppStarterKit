package com.example.starterkit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class StarterDataModel implements Serializable {

    private String name;
    private String email;

    public StarterDataModel(@JsonProperty("name") String name,
                            @JsonProperty("email") String email) {
                        this.name = name;
                        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
