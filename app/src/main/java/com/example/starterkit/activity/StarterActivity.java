package com.example.starterkit.activity;

import android.os.Bundle;
import com.example.starterkit.R;
import com.example.starterkit.dependencyinjection.PresenterComponent;
import com.example.starterkit.presenter.StarterActivityPresenter;

public class StarterActivity extends PresentedActivity<StarterActivityPresenter> implements StarterActivityPresenter.IMainActivityView {


    private StarterActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected StarterActivityPresenter onCreatePresenter() {
        presenter=new StarterActivityPresenter(this);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent component, StarterActivityPresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void showProgressBar() {

    }
}
