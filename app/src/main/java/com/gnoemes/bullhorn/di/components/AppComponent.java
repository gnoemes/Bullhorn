package com.gnoemes.bullhorn.di.components;


import android.content.Context;

import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.models.database.DatabaseHelper;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.models.preference.PreferenceHelper;
import com.gnoemes.bullhorn.di.modules.application.AppModule;
import com.gnoemes.bullhorn.di.modules.application.InteractorModule;
import com.gnoemes.bullhorn.di.modules.application.NetModule;
import com.gnoemes.bullhorn.di.modules.application.PreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, PreferenceModule.class, InteractorModule.class})
public interface AppComponent {

    Context context();
    NewsNetworkHelper mNewsNetworkHelper();
    PreferenceHelper mPreferenceHelper();
    DatabaseHelper mDatabaseHelper();
    DataManagerHelper mDataManagerHelper();
    void inject(App app);
}
