package com.gnoemes.bullhorn.di.modules.fragments;


import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.di.ArticleScope;
import com.gnoemes.bullhorn.ui.news.ArticlesPresenter;
import com.gnoemes.bullhorn.ui.news.IArticlesPresenter;
import com.gnoemes.bullhorn.ui.news.IArticlesView;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticlesModule {

    private IArticlesView articlesView;

    public ArticlesModule(IArticlesView articlesView) {
        this.articlesView = articlesView;
    }

    @Provides
    @ArticleScope
    IArticlesView provideArticlesView() {
        return articlesView;
    }

    @Provides
    @ArticleScope
    public IArticlesPresenter provideArticlesPresenter(DataManagerHelper dataManagerHelper) {
        return new ArticlesPresenter(articlesView,dataManagerHelper);
    }
}
