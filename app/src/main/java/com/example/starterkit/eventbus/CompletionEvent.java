package com.example.starterkit.eventbus;

import java.util.HashMap;

import okhttp3.ResponseBody;

public class CompletionEvent implements Event {

    private int requestCode;

    public CompletionEvent(int requestCode) {this.requestCode = requestCode;}
    public CompletionEvent() {}


    @Override
    public int getType() {
        return TYPE_COMPLETION;
    }

    @Override
    public <T> T getResult() {
        return null;
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
