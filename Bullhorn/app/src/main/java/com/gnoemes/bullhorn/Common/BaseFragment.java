package com.gnoemes.bullhorn.Common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.di.Components.AppComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.getApp(getActivity()).getAppComponent());

    }

    protected abstract void setupComponent(AppComponent appComponent);


}
