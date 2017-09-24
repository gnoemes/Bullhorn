package com.gnoemes.bullhorn.models.networking;

import com.gnoemes.bullhorn.models.networking.model.article.Article;
import com.gnoemes.bullhorn.models.networking.model.source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface NewsNetworkHelper {

    Observable<List<Source>> getSources(String category);

    Observable<List<Article>> getArticles(String source);

}
