package com.gnoemes.bullhorn.di.modules;


import android.support.annotation.NonNull;

import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.di.annotations.Local;
import com.gnoemes.bullhorn.di.annotations.Preference;
import com.gnoemes.bullhorn.di.annotations.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    NewsRepository provideDataManagerHelper(@NonNull @Local NewsDataSource localDataSource,
                                            @NonNull @Remote NewsDataSource remoteDataSource,
                                            @NonNull @Preference NewsDataSource preferenceDataSource) {
        return new NewsRepository(localDataSource,remoteDataSource,preferenceDataSource);
    }

}
