package com.gnoemes.bullhorn.di.modules.fragments;


import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.di.SourceScope;
import com.gnoemes.bullhorn.ui.news.ISourcePresenter;
import com.gnoemes.bullhorn.ui.news.ISourceView;
import com.gnoemes.bullhorn.ui.news.SourcePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SourceModule {

    private ISourceView sourceView;

    public SourceModule(ISourceView sourceView) {
        this.sourceView = sourceView;
    }

    @Provides
    @SourceScope
    public ISourceView provideSourceView() {
        return sourceView;
    }

    @Provides
    @SourceScope
    ISourcePresenter provideSourcePresenter(DataManagerHelper dataManagerHelper) {
        return new SourcePresenter(sourceView,dataManagerHelper);
    }
}
