package com.gnoemes.bullhorn.di.Components;


import com.gnoemes.bullhorn.di.Modules.ActivityModule.MainModule;
import com.gnoemes.bullhorn.di.ActivityScope;
import com.gnoemes.bullhorn.ui.Main.IMainPresenter;
import com.gnoemes.bullhorn.ui.Main.IMainView;
import com.gnoemes.bullhorn.ui.Main.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    IMainView mIMainView();
    IMainPresenter mIMainPresenter();
    void inject(MainActivity mainActivity);
}
