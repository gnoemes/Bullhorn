package com.gnoemes.bullhorn.Models.Database;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;

import java.util.List;

import io.reactivex.Observable;

public interface DatabaseHelper {

    void saveArticles(String source, Observable<List<Article>> articles);

    Observable<List<Article>> getArticles(String source);

    boolean isContains(String source);
}
