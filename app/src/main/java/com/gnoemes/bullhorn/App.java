package com.gnoemes.bullhorn;

import android.app.Application;
import android.content.Context;

import com.gnoemes.bullhorn.di.components.AppComponent;
import com.gnoemes.bullhorn.di.components.DaggerAppComponent;
import com.gnoemes.bullhorn.di.modules.application.AppModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
