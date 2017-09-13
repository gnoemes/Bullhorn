package com.gnoemes.bullhorn.Models.Networking;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface NewsNetworkHelper {

    Observable<List<Source>> getSources(String category);

    Observable<List<Article>> getArticles(String source);

}
