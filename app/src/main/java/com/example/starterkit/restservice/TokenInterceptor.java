package com.example.starterkit.restservice;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

class TokenInterceptor implements Interceptor {

    private Context context;
    private String authStr;


    TokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader("x-rapidapi-host", "Marvelstefan-skliarovV1.p.rapidapi.com");
       // requestBuilder.addHeader("x-rapidapi-host", "Marvelstefan-skliarovV1.p.rapidapi.com");
     //   requestBuilder.addHeader("x-rapidapi-key", "e0f1e9731bmsh9392364a73fa57ap1848cbjsn8daef09509ee");

        request = requestBuilder.build();
        Response response = chain.proceed(request);
        return response;
    }



}