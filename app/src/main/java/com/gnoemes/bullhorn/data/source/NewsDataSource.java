package com.gnoemes.bullhorn.data.source;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface NewsDataSource {

   boolean saveSourceData(String category, List<Source> source);

   boolean saveArticleData(ArticleEntity article);

    Observable<List<Source>> getSources(String category);

    Flowable<List<ArticleEntity>> getArticles(String source, boolean force);

}
