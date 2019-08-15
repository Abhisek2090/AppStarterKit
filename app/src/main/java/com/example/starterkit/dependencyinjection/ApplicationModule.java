package com.example.starterkit.dependencyinjection;

import android.app.Activity;

import com.example.starterkit.datastore.DataStore;
import com.example.starterkit.eventbus.RxBus;
import com.example.starterkit.restservice.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private Activity activity;

    public ApplicationModule(Activity activity) {
        this.activity = activity;
    }

    @Singleton
    @Provides
    RxBus getEventBus() {return new RxBus();}

    @Provides
    RestService getRestService(RxBus eventBus) {
        return new RestService(activity, eventBus);
    }

    @Provides
    DataStore getDataStore() {
        return new DataStore(activity);
    }



}
