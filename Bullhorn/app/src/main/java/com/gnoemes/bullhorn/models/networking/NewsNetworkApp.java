package com.gnoemes.bullhorn.models.networking;

import com.gnoemes.bullhorn.models.networking.data.ArticlesData;
import com.gnoemes.bullhorn.models.networking.data.NetworkConstants;
import com.gnoemes.bullhorn.models.networking.data.SourceData;
import com.gnoemes.bullhorn.models.networking.model.article.Article;
import com.gnoemes.bullhorn.models.networking.model.article.Articles;
import com.gnoemes.bullhorn.models.networking.model.source.Source;
import com.gnoemes.bullhorn.models.networking.model.source.Sources;

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
