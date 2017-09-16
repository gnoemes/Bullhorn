package com.gnoemes.bullhorn.di.Modules.FragmentModule;


import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.di.ArticleScope;
import com.gnoemes.bullhorn.ui.NewsFragments.ArticlesPresenter;
import com.gnoemes.bullhorn.ui.NewsFragments.IArticlesPresenter;
import com.gnoemes.bullhorn.ui.NewsFragments.IArticlesView;

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
