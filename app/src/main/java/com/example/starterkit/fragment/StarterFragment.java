package com.example.starterkit.fragment;

import com.example.starterkit.dependencyinjection.PresenterComponent;
import com.example.starterkit.presenter.StarterFragmentPresenter;

public class StarterFragment extends PresentedFragment<StarterFragmentPresenter> implements StarterFragmentPresenter.IStarterFragmentView  {

    private StarterFragmentPresenter presenter;

    @Override
    protected StarterFragmentPresenter onCreatePresenter() {
        presenter = new StarterFragmentPresenter(this);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent component, StarterFragmentPresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void showProgressBar() {

    }
}
