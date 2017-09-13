package com.gnoemes.bullhorn.Models.Networking.Data;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Sources;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SourceData {

    @GET("sources?")
    Observable<Sources> getSourcesData(@Query("category") String category, @Query("language") String language);

}
