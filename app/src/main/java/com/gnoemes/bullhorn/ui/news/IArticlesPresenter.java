package com.gnoemes.bullhorn.ui.news;

import com.gnoemes.bullhorn.models.networking.model.article.Article;

public interface IArticlesPresenter {

    void loadArticlesList(String source,boolean force);

    void itemClicked(Article item);

}
