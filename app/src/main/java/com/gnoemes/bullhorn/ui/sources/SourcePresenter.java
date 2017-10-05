package com.gnoemes.bullhorn.ui.sources;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.di.components.DaggerRepositoryComponent;
import com.gnoemes.bullhorn.di.components.RepositoryComponent;
import com.gnoemes.bullhorn.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class SourcePresenter extends MvpPresenter<SourceView>{

    private NewsRepository repository;
    private Disposable d;

    @Inject
    public SourcePresenter(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        initComponent();
    }

    private void initComponent() {
        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        repositoryComponent.inject(this);
    }

    public void loadSourcesList(String category) {
        getViewState().showLoad();
        d = repository.getSources(category)
                .subscribe(sources -> {
                    getViewState().updateSourceList(sources);
                    getViewState().showContent();
                }, throwable -> {
                    getViewState().showError();
                    throwable.printStackTrace();
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(d);
    }
}
