package com.gnoemes.bullhorn.ui.main;

import com.gnoemes.bullhorn.models.DataManagerHelper;

import javax.inject.Inject;

public class MainPresenter implements IMainPresenter {

    private IMainView mainView;
    private DataManagerHelper dataManagerHelper;


    @Inject
    public MainPresenter(IMainView mainView, DataManagerHelper dataManagerHelper) {
        this.mainView = mainView;
        this.dataManagerHelper = dataManagerHelper;
    }



    @Override
    public void loadSources(String category) {

    }

    @Override

    public void loadArticles(int sourceId) {

    }
}
