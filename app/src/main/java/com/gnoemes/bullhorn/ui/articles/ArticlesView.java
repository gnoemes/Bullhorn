package com.gnoemes.bullhorn.ui.articles;

import android.os.Parcelable;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;

import java.util.List;


public interface ArticlesView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showLoad();

    @StateStrategyType(SkipStrategy.class)
    void showContent();

    @StateStrategyType(SkipStrategy.class)
    void showError();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateArticleList(List<ArticleEntity> articles);

    @StateStrategyType(SkipStrategy.class)
    void showDetails(Parcelable item);
}
