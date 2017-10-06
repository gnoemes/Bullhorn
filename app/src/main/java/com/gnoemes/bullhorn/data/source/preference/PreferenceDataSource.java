package com.gnoemes.bullhorn.data.source.preference;

import android.content.SharedPreferences;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class PreferenceDataSource implements NewsDataSource {

    private SharedPreferences sPrefs;

    @Inject
    public PreferenceDataSource(SharedPreferences sPrefs) {
        this.sPrefs = sPrefs;
    }

    private boolean isContains(String category) {
        return sPrefs.contains(category);
    }

    @Override
    public boolean saveSourceData(String category, List<Source> source) {
        String jsonString = new Gson().toJson(source);
        sPrefs.edit().putString(category, jsonString).apply();
        return isContains(category);
    }

    @Override
    public boolean saveArticleData(ArticleEntity article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<List<Source>> getSources(String category) {
        if (isContains(category)) {
            Type type = new TypeToken<List<Source>>(){}.getType();
            return Observable.just(new Gson().fromJson(sPrefs.getString(category, ""), type));
        } else {
            return Observable.empty();
        }
    }

    @Override
    public Flowable<List<ArticleEntity>> getArticles(String source, boolean force) {
        throw new UnsupportedOperationException();
    }

}
