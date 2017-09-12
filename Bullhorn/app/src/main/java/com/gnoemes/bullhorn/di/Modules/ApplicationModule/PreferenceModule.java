package com.gnoemes.bullhorn.di.Modules.ApplicationModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.gnoemes.bullhorn.Models.Preference.PreferenceApp;
import com.gnoemes.bullhorn.Models.Preference.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("sources",Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferenceHelper(SharedPreferences sPrefs) {
        return new PreferenceApp(sPrefs);
    }
}
