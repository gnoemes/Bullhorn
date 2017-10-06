package com.gnoemes.bullhorn.di.components;

import com.gnoemes.bullhorn.di.annotations.ActivityScope;
import com.gnoemes.bullhorn.ui.articles.ArticlesPresenter;
import com.gnoemes.bullhorn.ui.sources.SourcePresenter;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface RepositoryComponent {
    void inject(ArticlesPresenter articlesPresenter);
    void inject(SourcePresenter sourcePresenter);
}
