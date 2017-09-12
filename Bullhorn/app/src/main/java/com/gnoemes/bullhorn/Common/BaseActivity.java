package com.gnoemes.bullhorn.Common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.di.Components.AppComponent;

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupComponent(App.getApp(this).getAppComponent());
    }

    public abstract void setupComponent(AppComponent upComponent);

}
