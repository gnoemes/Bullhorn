package com.gnoemes.bullhorn.models.networking.data;

import com.gnoemes.bullhorn.models.networking.model.source.Source;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesData {

    @GET("articles?")
    Observable<Source> getArcticlesData(@Query("source") String source, @Query("apiKey") String apiKey);

}
