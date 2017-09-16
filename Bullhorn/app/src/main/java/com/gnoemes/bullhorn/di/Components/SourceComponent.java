package com.gnoemes.bullhorn.di.Components;


import com.gnoemes.bullhorn.di.Modules.FragmentModule.SourceModule;
import com.gnoemes.bullhorn.di.SourceScope;
import com.gnoemes.bullhorn.ui.NewsFragments.ISourcePresenter;
import com.gnoemes.bullhorn.ui.NewsFragments.ISourceView;
import com.gnoemes.bullhorn.ui.NewsFragments.SourceFragment;

import dagger.Component;

@SourceScope
@Component(dependencies = AppComponent.class, modules =  SourceModule.class)
public interface SourceComponent {


    ISourceView mSourceView();
    ISourcePresenter mSourcePresenter();
    void inject(SourceFragment sourceFragment);
}
