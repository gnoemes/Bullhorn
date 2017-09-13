package com.gnoemes.bullhorn.Models.Networking.Data;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Articles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesData {

    @GET("articles?")
    Observable<Articles> getArticlesData(@Query("source") String source, @Query("apiKey") String apiKey);

}
