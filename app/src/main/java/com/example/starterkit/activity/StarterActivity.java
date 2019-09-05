package com.example.starterkit.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.starterkit.R;
import com.example.starterkit.dependencyinjection.PresenterComponent;
import com.example.starterkit.presenter.StarterActivityPresenter;

public class StarterActivity extends PresentedActivity<StarterActivityPresenter> implements StarterActivityPresenter.IMainActivityView {


    private StarterActivityPresenter presenter;
    private TextView descriptionText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionText = (TextView)findViewById(R.id.descriptionText);
        imageView = (ImageView)findViewById(R.id.imageView);

        presenter.getData(getString(R.string.api_key));
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
    public void populateStarterActivity(String url, String desc) {
        //populate the views here
        try {

            Glide.with(this)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);

        } catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }

        descriptionText.setText(desc);
    }

    @Override
    public void showProgressBar() {
        //code to show progressbar here
    }
}
