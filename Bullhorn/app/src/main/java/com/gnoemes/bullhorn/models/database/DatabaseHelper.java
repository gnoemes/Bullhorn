package com.gnoemes.bullhorn.models.database;

import com.gnoemes.bullhorn.models.networking.model.article.Article;

import java.util.List;

import io.reactivex.Observable;

public interface DatabaseHelper {

    void saveArticles(String source, Observable<List<Article>> articles);

    Observable<List<Article>> getArticles(String source);

    boolean isContains(String source);
}
