package com.gnoemes.bullhorn.data.source.local;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.source.NewsDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class NewsLocalDataStorage implements NewsDataSource{

    private ArticleDao articleDao;

    @Inject
    public NewsLocalDataStorage(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    @Override
    public boolean saveSourceData(String category, List<Source> source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveArticleData(ArticleEntity article) {
        return articleDao.insert(article) != -1;
    }


    @Override
    public Observable<List<Source>> getSources(String category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flowable<List<ArticleEntity>> getArticles(String source, boolean force) {
       return articleDao.getArticles(source);
    }
}
