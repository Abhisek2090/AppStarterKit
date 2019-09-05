package com.example.starterkit.presenter;

import com.example.starterkit.constants.Constants;
import com.example.starterkit.dependencyinjection.PresenterComponent;
import com.example.starterkit.eventbus.Event;
import com.example.starterkit.eventbus.EventBus;
import com.example.starterkit.eventbus.EventSubscriber;
import com.example.starterkit.model.NasaModelClass;
import com.example.starterkit.restservice.RestService;
import com.example.starterkit.restservice.ServerResponse;

import java.util.List;

import javax.inject.Inject;

public class StarterActivityPresenter extends PresenterStub implements EventSubscriber {

    @Inject
    EventBus eventBus;

    @Inject
    RestService restService;


    @Override
    public void onCreate() {
        eventBus.subscribe(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unSubscribe();
    }


    private IMainActivityView iMainActivityView;

    public StarterActivityPresenter(IMainActivityView iMainActivityView) {
        this.iMainActivityView = iMainActivityView;
    }


    //Fetching data using method in restservice
    public void getData(String apiKey) {
       // iMainActivityView.showProgressBar();
        restService.getStarterData(apiKey, Constants.GET_DATA);
    }

    @Override
    public void onEvent(Event event) {

        switch (event.getType()) {
            case Event.TYPE_SUCCESS:
                switch (event.getRequestCode()) {
                    case Constants.GET_DATA:
                        NasaModelClass serverResponse = event.getResult();
                        iMainActivityView.populateStarterActivity(serverResponse.getUrl(), serverResponse.getTitle());
                        break;
                }

            case Event.TYPE_COMPLETION:
                break;

            case Event.TYPE_ERROR:
                break;


        }

    }
    public interface IMainActivityView {
        void populateStarterActivity(String url, String desc);
        void showProgressBar();

    }


}
