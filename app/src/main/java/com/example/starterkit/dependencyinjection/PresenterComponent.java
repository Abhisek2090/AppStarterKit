package com.example.starterkit.dependencyinjection;


import com.example.starterkit.presenter.StarterActivityPresenter;
import com.example.starterkit.presenter.StarterFragmentPresenter;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, BinderModule.class})
public interface PresenterComponent {

    void inject(StarterActivityPresenter starterActivityPresenter);

    void inject(StarterFragmentPresenter starterFragmentPresenter);


}
