package com.gnoemes.bullhorn;

import android.app.Application;

import com.gnoemes.bullhorn.di.components.AppComponent;
import com.gnoemes.bullhorn.di.components.DaggerAppComponent;
import com.gnoemes.bullhorn.di.modules.AppModule;
import com.gnoemes.bullhorn.di.modules.NewsLocalDataSourceModule;
import com.gnoemes.bullhorn.di.modules.PreferenceModule;
import com.gnoemes.bullhorn.di.modules.RemoteModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent(this);
        getAppComponent().inject(this);
    }

    private void initAppComponent(App app) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .newsLocalDataSourceModule(new NewsLocalDataSourceModule())
                .preferenceModule(new PreferenceModule())
                .remoteModule(new RemoteModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
