package com.example.starterkit.dependencyinjection;

import com.example.starterkit.eventbus.EventBus;
import com.example.starterkit.eventbus.RxBus;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BinderModule {
    @Binds
    abstract EventBus bindEventBus(RxBus rxBus);
}
