package com.gnoemes.bullhorn.ui.NewsFragments;

import android.os.Parcelable;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;

import java.util.List;

public interface IArticlesView {

    void showLoad();

    void showContent();

    void showError();

    void updateArticleList(List<Article> articles);

    void showDetails(Parcelable item);
}
