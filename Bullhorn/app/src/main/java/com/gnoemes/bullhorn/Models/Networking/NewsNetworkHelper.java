package com.gnoemes.bullhorn.Models.Networking;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import io.reactivex.Observable;

public interface NewsNetworkHelper {

    Observable<Source> getSources(String category);

    Observable<Article> getArticles(String source);

}
