package com.gnoemes.bullhorn.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.preference.PreferenceDataSource;
import com.gnoemes.bullhorn.di.annotations.Preference;

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
    @Preference
    NewsDataSource providePreferenceHelper(SharedPreferences sPrefs) {
        return new PreferenceDataSource(sPrefs);
    }
}
