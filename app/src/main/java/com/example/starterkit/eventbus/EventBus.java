package com.example.starterkit.eventbus;

public interface EventBus {

    void subscribe(EventSubscriber subscriber);

    void unSubscribe();

    void postNext(Event event);

    void postError(Throwable t);

    void postCompletion();

    void postError(Throwable t, int requestCode);

    void postCompletion(int requestCode);

}
