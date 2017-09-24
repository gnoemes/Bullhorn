package com.gnoemes.bullhorn.di.components;


import com.gnoemes.bullhorn.di.ArticleScope;
import com.gnoemes.bullhorn.di.modules.fragments.ArticlesModule;
import com.gnoemes.bullhorn.ui.news.ArticlesFragment;
import com.gnoemes.bullhorn.ui.news.IArticlesPresenter;
import com.gnoemes.bullhorn.ui.news.IArticlesView;

import dagger.Component;

@ArticleScope
@Component(dependencies = AppComponent.class, modules = ArticlesModule.class)
public interface ArticleComponent {

    IArticlesView mArticlesView();
    IArticlesPresenter mArticlesPresenter();
    void inject(ArticlesFragment articlesFragment);
}
