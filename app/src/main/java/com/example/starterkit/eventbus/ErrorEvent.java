package com.example.starterkit.eventbus;

import java.util.HashMap;

import okhttp3.ResponseBody;

public class ErrorEvent implements Event {

    private Throwable t;
    private int requestCode;

    public ErrorEvent(Throwable t, int requestCode) {
        this.t  = t;
        this.requestCode = requestCode;
    }

    public ErrorEvent(Throwable t) {
        this.t = t;
    }
    @Override
    public int getType() {
        return TYPE_ERROR;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Throwable getResult() {
        return t;
    }

    @Override
    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public ResponseBody getErrorBody() {
        return null;
    }

}
