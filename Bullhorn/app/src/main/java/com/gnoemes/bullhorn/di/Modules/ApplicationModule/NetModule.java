package com.gnoemes.bullhorn.di.Modules.ApplicationModule;


import com.gnoemes.bullhorn.Models.Networking.Data.NetworkConstants;
import com.gnoemes.bullhorn.Models.Networking.NewsNetworkApp;
import com.gnoemes.bullhorn.Models.Networking.NewsNetworkHelper;

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
