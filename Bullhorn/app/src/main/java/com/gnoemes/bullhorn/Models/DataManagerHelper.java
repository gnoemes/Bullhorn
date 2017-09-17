package com.gnoemes.bullhorn.Models;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface DataManagerHelper {

   Observable<List<Source>> saveSourceData(String category,Observable<List<Source>> source);

   Observable<List<Article>> saveArticleData(String source, Observable<List<Article>> article);

    Observable<List<Source>> getSources(String category);

    Observable<List<Article>> getArticles(String source, boolean force);


}
