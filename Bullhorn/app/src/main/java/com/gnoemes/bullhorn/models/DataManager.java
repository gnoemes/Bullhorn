package com.gnoemes.bullhorn.models;

import com.gnoemes.bullhorn.models.database.DatabaseHelper;
import com.gnoemes.bullhorn.models.networking.model.article.Article;
import com.gnoemes.bullhorn.models.networking.model.source.Source;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.models.preference.PreferenceHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DataManager implements DataManagerHelper {

    private NewsNetworkHelper newsNetworkHelper;
    private DatabaseHelper databaseHelper;
    private PreferenceHelper preferenceHelper;

    @Inject
    public DataManager(NewsNetworkHelper newsNetworkHelper, DatabaseHelper databaseHelper, PreferenceHelper preferenceHelper) {
        this.newsNetworkHelper = newsNetworkHelper;
        this.databaseHelper = databaseHelper;
        this.preferenceHelper = preferenceHelper;
    }


    @Override
    public Observable<List<Source>> saveSourceData(String category,Observable<List<Source>> source) {
        preferenceHelper.saveData(category,source);
        return source;
    }

    @Override
    public Observable<List<Article>> saveArticleData(String source, Observable<List<Article>> article) {
        databaseHelper.saveArticles(source,article);
        return article;
    }

    @Override
    public Observable<List<Source>> getSources(final String category) {
        return preferenceHelper.isContains(category) ? preferenceHelper.getData(category) : saveSourceData(category,newsNetworkHelper.getSources(category));
    }

    @Override
    public Observable<List<Article>> getArticles(final String source, boolean force) {
        if (force) {
            return saveArticleData(source,newsNetworkHelper.getArticles(source));
        }
        return databaseHelper.isContains(source) ? databaseHelper.getArticles(source): saveArticleData(source,newsNetworkHelper.getArticles(source));
    }
}
