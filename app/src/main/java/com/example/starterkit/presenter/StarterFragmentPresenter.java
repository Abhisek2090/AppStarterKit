package com.example.starterkit.presenter;

import com.example.starterkit.constants.Constants;
import com.example.starterkit.eventbus.Event;
import com.example.starterkit.eventbus.EventBus;
import com.example.starterkit.eventbus.EventSubscriber;
import com.example.starterkit.fragment.PresentedFragment;
import com.example.starterkit.restservice.RestService;

import javax.inject.Inject;

public class StarterFragmentPresenter extends PresenterStub implements EventSubscriber {


    @Inject
    EventBus eventBus;

    @Inject
    RestService restService;

    private IStarterFragmentView iStarterFragmentView;

    public StarterFragmentPresenter(IStarterFragmentView iStarterFragmentView) {
        this.iStarterFragmentView = iStarterFragmentView;
    }

    //Fetching data using method in restservice

    public void getData() {
        restService.getStarterData(Constants.GET_DATA);
    }


    @Override
    public void onCreate() {
        eventBus.subscribe(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unSubscribe();
    }


    @Override
    public void onEvent(Event event) {

        switch (event.getType()) {

            case Event.TYPE_SUCCESS:
                switch (event.getRequestCode()) {
                    case Constants.GET_DATA:
                        break;
                }

            case Event.TYPE_COMPLETION:
                break;

            case Event.TYPE_ERROR:
                break;


        }


    }

    public interface IStarterFragmentView{
        void showProgressBar();
    }
}
