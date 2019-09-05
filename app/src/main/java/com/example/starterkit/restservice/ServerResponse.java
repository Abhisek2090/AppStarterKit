package com.example.starterkit.restservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import okhttp3.Headers;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerResponse<T> {

    private  final String TAG = ServerResponse.class.getSimpleName();

    private T data;
    private T [] dataArray;
    private String message;


    private Map<String, Object> error;


    public ServerResponse(@JsonProperty("data") T data,
                          @JsonProperty("dataArray") T[] dataArray,
                          @JsonProperty("message") String message)
    {
        this.data = data;
        this.dataArray = dataArray;
        this.message=message;

    }

    public T getData() {
        if (data != null) {

            return data;
        }
        return null;
    }


    public T[] getDataArray() {
        return dataArray;
    }


    public String getMessage() {
        return message;
    }



    public Map<String, Object> getError() {
        return error;
    }

}
