package com.gnoemes.bullhorn.Models.Networking;

import com.gnoemes.bullhorn.Models.Networking.Data.ArticlesData;
import com.gnoemes.bullhorn.Models.Networking.Data.NetworkConstants;
import com.gnoemes.bullhorn.Models.Networking.Data.SourceData;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Articles;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Sources;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NewsNetworkApp implements NewsNetworkHelper {

    private Retrofit retrofit;

    @Inject
    public NewsNetworkApp(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    public Observable<List<Source>> getSources(String category) {
        return retrofit.create(SourceData.class).getSourcesData(category, NetworkConstants.DEFAULT_LANGUAGE)
                .map(Sources::getSources).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Article>> getArticles(String source) {
        return retrofit.create(ArticlesData.class).getArticlesData(source, NetworkConstants.API_KEY)
                .map(Articles::getArticles).subscribeOn(Schedulers.io());
    }
}
