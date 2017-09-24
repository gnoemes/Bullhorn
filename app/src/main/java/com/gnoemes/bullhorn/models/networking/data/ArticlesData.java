package com.gnoemes.bullhorn.models.networking.data;

import com.gnoemes.bullhorn.models.networking.model.article.Articles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesData {

    @GET("articles?")
    Observable<Articles> getArticlesData(@Query("source") String source, @Query("apiKey") String apiKey);

}
