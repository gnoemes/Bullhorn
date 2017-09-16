package com.gnoemes.bullhorn.di.Components;


import com.gnoemes.bullhorn.di.ArticleScope;
import com.gnoemes.bullhorn.di.Modules.FragmentModule.ArticlesModule;
import com.gnoemes.bullhorn.ui.NewsFragments.ArticlesFragment;
import com.gnoemes.bullhorn.ui.NewsFragments.IArticlesPresenter;
import com.gnoemes.bullhorn.ui.NewsFragments.IArticlesView;

import dagger.Component;

@ArticleScope
@Component(dependencies = AppComponent.class, modules = ArticlesModule.class)
public interface ArticleComponent {

    IArticlesView mArticlesView();
    IArticlesPresenter mArticlesPresenter();
    void inject(ArticlesFragment articlesFragment);
}
