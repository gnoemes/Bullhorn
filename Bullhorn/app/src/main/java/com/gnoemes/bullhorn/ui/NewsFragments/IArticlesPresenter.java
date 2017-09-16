package com.gnoemes.bullhorn.ui.NewsFragments;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;

public interface IArticlesPresenter {

    void loadArticlesList(String source);

    void itemClicked(Article item);

}
