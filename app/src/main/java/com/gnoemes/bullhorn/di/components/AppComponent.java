package com.gnoemes.bullhorn.di.components;


import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.di.modules.AppModule;
import com.gnoemes.bullhorn.di.modules.NewsLocalDataSourceModule;
import com.gnoemes.bullhorn.di.modules.PreferenceModule;
import com.gnoemes.bullhorn.di.modules.RemoteModule;
import com.gnoemes.bullhorn.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NewsLocalDataSourceModule.class,RemoteModule.class, PreferenceModule.class, RepositoryModule.class})
public interface AppComponent {
    NewsRepository getNewsRepository();
    void inject(App app);
}
