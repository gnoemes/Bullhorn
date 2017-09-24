package com.gnoemes.bullhorn.di.components;


import com.gnoemes.bullhorn.di.modules.activity.MainModule;
import com.gnoemes.bullhorn.di.ActivityScope;
import com.gnoemes.bullhorn.ui.main.IMainPresenter;
import com.gnoemes.bullhorn.ui.main.IMainView;
import com.gnoemes.bullhorn.ui.main.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    IMainView mIMainView();
    IMainPresenter mIMainPresenter();
    void inject(MainActivity mainActivity);
}
