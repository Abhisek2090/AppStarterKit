package com.example.starterkit.restservice;

import com.example.starterkit.model.NasaModelClass;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestInterface {

    String BASE_URL = "https://api.nasa.gov/planetary/";


    //example api

    @GET("apod")
    Observable<Response<NasaModelClass>> getPlanetaryData(@Query("api_key") String api_key);

}
