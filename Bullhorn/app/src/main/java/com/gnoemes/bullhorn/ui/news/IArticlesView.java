package com.gnoemes.bullhorn.ui.news;

import android.os.Parcelable;

import com.gnoemes.bullhorn.models.networking.model.article.Article;

import java.util.List;

public interface IArticlesView {

    void showLoad();

    void showContent();

    void showError();

    void updateArticleList(List<Article> articles);

    void showDetails(Parcelable item);
}
