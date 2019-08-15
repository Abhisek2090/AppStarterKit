package com.example.starterkit.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.starterkit.dependencyinjection.ApplicationModule;
import com.example.starterkit.dependencyinjection.DaggerPresenterComponent;
import com.example.starterkit.dependencyinjection.PresenterComponent;
import com.example.starterkit.presenter.Presenter;

import java.util.List;


public abstract class PresentedActivity<T extends Presenter> extends AppCompatActivity {

    private T presenter;
    private final String TAG = PresentedActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        presenter.onCreate();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        presenter.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    private boolean isAppOnForeground(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses  = activityManager.getRunningAppProcesses();

        if(appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for(ActivityManager.RunningAppProcessInfo appProcess: appProcesses) {
            if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName.equals(packageName)) {
                return true;
            }
        }

        return false;

    }

    private void inject() {
        presenter = onCreatePresenter();
        PresenterComponent presenterComponent = DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        injectPresenter(presenterComponent, presenter);


    }

    protected abstract T onCreatePresenter();

    protected  abstract void injectPresenter(PresenterComponent component, T presenter);



}
