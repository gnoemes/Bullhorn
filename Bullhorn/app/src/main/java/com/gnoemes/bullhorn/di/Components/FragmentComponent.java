package com.gnoemes.bullhorn.di.Components;


import android.app.Fragment;

import com.gnoemes.bullhorn.di.Modules.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = MainComponent.class)
public interface FragmentComponent {
    void inject(Fragment fragment);
}
