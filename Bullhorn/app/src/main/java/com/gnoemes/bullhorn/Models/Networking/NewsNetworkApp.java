package com.gnoemes.bullhorn.Models.Networking;

import com.gnoemes.bullhorn.Models.Networking.Data.ArticlesData;
import com.gnoemes.bullhorn.Models.Networking.Data.NetworkConstants;
import com.gnoemes.bullhorn.Models.Networking.Data.SourceData;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class NewsNetworkApp implements NewsNetworkHelper {

    private Retrofit retrofit;

    @Inject
    public NewsNetworkApp(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    public Observable<Source> getSources(String category) {
        return retrofit.create(SourceData.class).getSourcesData(category, NetworkConstants.DEFAULT_LANGUAGE);

    }

    @Override
    public Observable<Article> getArticles(String source) {
        return retrofit.create(ArticlesData.class).getArticlesData(source, NetworkConstants.API_KEY);
    }
}
