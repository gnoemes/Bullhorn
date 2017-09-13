package com.gnoemes.bullhorn.di.Components;


import android.content.Context;

import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.Models.Database.DatabaseHelper;
import com.gnoemes.bullhorn.Models.Networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.Models.Preference.PreferenceHelper;
import com.gnoemes.bullhorn.di.Modules.ApplicationModule.AppModule;
import com.gnoemes.bullhorn.di.Modules.ApplicationModule.InteractorModule;
import com.gnoemes.bullhorn.di.Modules.ApplicationModule.NetModule;
import com.gnoemes.bullhorn.di.Modules.ApplicationModule.PreferenceModule;

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
