package com.gnoemes.bullhorn.di.modules;


import android.support.annotation.NonNull;

import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.remote.NewsRemoteDataSource;
import com.gnoemes.bullhorn.data.source.remote.endpoints.ArticlesData;
import com.gnoemes.bullhorn.data.source.remote.endpoints.NetworkConstants;
import com.gnoemes.bullhorn.data.source.remote.endpoints.SourceData;
import com.gnoemes.bullhorn.di.annotations.Remote;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteModule {

    @Provides
    @Singleton
    @Remote
    NewsDataSource provideNewsNetworkHelper(ArticlesData articlesData,SourceData sourceData) {
        return new NewsRemoteDataSource(articlesData,sourceData);
    }

    @Provides
    @Singleton
    ArticlesData provideArticlesData(OkHttpClient client) {
        return getArticlesData(getRetrofit(client));
    }

    @Provides
    @Singleton
    SourceData provideSourceData(OkHttpClient client) {
        return getSourceData(getRetrofit(client));
    }

    public static SourceData getSourceData(Retrofit retrofit) {
        return retrofit.create(SourceData.class);
    }

    public static ArticlesData getArticlesData(Retrofit retrofit) {
        return retrofit.create(ArticlesData.class);
    }


    @Provides
    @Singleton
     OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @NonNull
    public static Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstants.BASE_URL)
                .build();
    }

}
