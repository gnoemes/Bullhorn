package com.gnoemes.bullhorn.di.Modules.FragmentModule;


import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.di.SourceScope;
import com.gnoemes.bullhorn.ui.NewsFragments.ISourcePresenter;
import com.gnoemes.bullhorn.ui.NewsFragments.ISourceView;
import com.gnoemes.bullhorn.ui.NewsFragments.SourcePresenter;

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
