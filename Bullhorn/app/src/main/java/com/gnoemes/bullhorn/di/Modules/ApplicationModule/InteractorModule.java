package com.gnoemes.bullhorn.di.Modules.ApplicationModule;


import android.content.Context;

import com.gnoemes.bullhorn.Models.DataManager;
import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.Models.Database.DatabaseApp;
import com.gnoemes.bullhorn.Models.Database.DatabaseHelper;
import com.gnoemes.bullhorn.Models.Networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.Models.Preference.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseApp(context);
    }


    @Provides
    @Singleton
    DataManagerHelper provideDataManagerHelper(NewsNetworkHelper newsNetworkHelper, DatabaseHelper databaseHelper, PreferenceHelper preferenceHelper) {
        return new DataManager(newsNetworkHelper,databaseHelper,preferenceHelper);
    }

}
