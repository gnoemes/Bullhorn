package com.gnoemes.bullhorn.data.source.remote.endpoints;

import com.gnoemes.bullhorn.data.model.article.Articles;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesData {

    @GET("articles?")
    Flowable<Articles> getArticlesData(@Query("source") String source, @Query("apiKey") String apiKey);

}
