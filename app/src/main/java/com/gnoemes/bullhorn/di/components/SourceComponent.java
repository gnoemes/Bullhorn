package com.gnoemes.bullhorn.di.components;


import com.gnoemes.bullhorn.di.modules.fragments.SourceModule;
import com.gnoemes.bullhorn.di.SourceScope;
import com.gnoemes.bullhorn.ui.news.ISourcePresenter;
import com.gnoemes.bullhorn.ui.news.ISourceView;
import com.gnoemes.bullhorn.ui.news.SourceFragment;

import dagger.Component;

@SourceScope
@Component(dependencies = AppComponent.class, modules =  SourceModule.class)
public interface SourceComponent {

    ISourceView mSourceView();
    ISourcePresenter mSourcePresenter();
    void inject(SourceFragment sourceFragment);
}
