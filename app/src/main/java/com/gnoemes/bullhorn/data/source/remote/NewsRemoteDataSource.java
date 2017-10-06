package com.gnoemes.bullhorn.data.source.remote;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.model.source.Sources;
import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.remote.endpoints.ArticlesData;
import com.gnoemes.bullhorn.data.source.remote.endpoints.NetworkConstants;
import com.gnoemes.bullhorn.data.source.remote.endpoints.SourceData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class NewsRemoteDataSource implements NewsDataSource {

    private ArticlesData articlesData;
    private SourceData sourceData;

    @Inject
    public NewsRemoteDataSource(ArticlesData articlesData, SourceData sourceData) {
        this.articlesData = articlesData;
        this.sourceData = sourceData;
    }


    @Override
    public boolean saveSourceData(String category, List<Source> source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveArticleData(ArticleEntity article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<List<Source>> getSources(String category) {
        return sourceData.getSourcesData(category, NetworkConstants.DEFAULT_LANGUAGE)
                .map(Sources::getSources);
    }

    @Override
    public Flowable<List<ArticleEntity>> getArticles(String source, boolean force) {
        return articlesData.getArticlesData(source, NetworkConstants.API_KEY)
                            .map(articles -> {
                            List<ArticleEntity> articleEntities = new ArrayList<>();
                            String sourceName = articles.getSource();
                            Observable.fromIterable(articles.getArticles())
                                    .forEach(a -> {
                                        ArticleEntity articleEntity = new ArticleEntity(sourceName, a.getAuthor(), a.getDescription(), a.getPublishedAt(), a.getTitle(), a.getUrl(), a.getUrlToImage());
                                        articleEntities.add(articleEntity);
                                    });
//                            Log.i("DEVE", "getArticlesRemote: " + articleEntities.size());
                            return articleEntities;
                        });
    }
}
