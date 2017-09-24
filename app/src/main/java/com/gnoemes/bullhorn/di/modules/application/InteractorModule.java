package com.gnoemes.bullhorn.di.modules.application;


import android.content.Context;

import com.gnoemes.bullhorn.models.DataManager;
import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.models.database.DatabaseApp;
import com.gnoemes.bullhorn.models.database.DatabaseHelper;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.models.preference.PreferenceHelper;

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
