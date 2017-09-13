package com.gnoemes.bullhorn.ui.Main;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;

import java.util.List;

public interface IMainView {

    void updateSources(List<String> sources);

    void showLoading();

    void showContent();

    List<String> getCurrentCategory();

    void updateArticles(List<Article> articleList);
}
