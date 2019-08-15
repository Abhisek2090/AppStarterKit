package com.example.starterkit.eventbus;

import java.util.HashMap;

import okhttp3.ResponseBody;

public interface Event {
    int TYPE_SUCCESS =1;
    int TYPE_ERROR = 2;
    int TYPE_COMPLETION = 3;

    int getType();

    <T> T getResult();

    int getRequestCode();

    int getStatusCode();

    ResponseBody getErrorBody();


}
