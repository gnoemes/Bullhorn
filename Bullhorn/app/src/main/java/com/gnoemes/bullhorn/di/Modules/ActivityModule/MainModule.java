package com.gnoemes.bullhorn.di.Modules.ActivityModule;


import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.di.Modules.ActivityScope;
import com.gnoemes.bullhorn.ui.Main.IMainPresenter;
import com.gnoemes.bullhorn.ui.Main.IMainView;
import com.gnoemes.bullhorn.ui.Main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private IMainView mainView;

    public MainModule(IMainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    IMainView provideMainView() {
        return mainView;
    }

    @Provides
    @ActivityScope
    IMainPresenter provideMainPresenter(DataManagerHelper DataManagerHelper) {
        return new MainPresenter(mainView, DataManagerHelper);
    }
}
