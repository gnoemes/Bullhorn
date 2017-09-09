package com.gnoemes.bullhorn.models.networking;

import com.gnoemes.bullhorn.models.networking.data.ArticlesData;
import com.gnoemes.bullhorn.models.networking.data.NetworkEndpoints;
import com.gnoemes.bullhorn.models.networking.data.SourceData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsNetworkApp implements NewsNetworkHelper {

    public SourceData getSources() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkEndpoints.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SourceData sources = retrofit.create(SourceData.class);
        return sources;
    }

    public ArticlesData getArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkEndpoints.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticlesData articles = retrofit.create(ArticlesData.class);
        return articles;
    }
}
