package com.gnoemes.bullhorn.di.modules.application;


import com.gnoemes.bullhorn.models.networking.data.NetworkConstants;
import com.gnoemes.bullhorn.models.networking.NewsNetworkApp;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstants.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    NewsNetworkHelper provideNewsNetworkHelper(Retrofit retrofit) {
        return new NewsNetworkApp(retrofit);
    }

}
