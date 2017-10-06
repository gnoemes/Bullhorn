package com.gnoemes.bullhorn.data.source.remote.endpoints;

import com.gnoemes.bullhorn.data.model.source.Sources;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SourceData {

    @GET("sources?")
    Observable<Sources> getSourcesData(@Query("category") String category, @Query("language") String language);

}
