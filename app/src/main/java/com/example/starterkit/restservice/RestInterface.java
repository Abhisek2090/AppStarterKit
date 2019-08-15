package com.example.starterkit.restservice;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RestInterface {

    String BASE_URL = "https://marvelstefan-skliarovv1.p.rapidapi.com/";


    //dummy api call

    @GET("/getCharacters")
    Observable<Response<Void>> getCharacters();

}
