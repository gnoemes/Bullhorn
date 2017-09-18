package com.gnoemes.bullhorn.ui.news;

import com.gnoemes.bullhorn.models.DataManagerHelper;
import com.gnoemes.bullhorn.models.networking.model.source.Source;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class SourcePresenter implements ISourcePresenter {

    private DataManagerHelper dataManagerHelper;
    private ISourceView sourceView;

    @Inject
    public SourcePresenter( ISourceView sourceView, DataManagerHelper dataManagerHelper) {
        this.sourceView = sourceView;
        this.dataManagerHelper = dataManagerHelper;
    }

    @Override
    public void loadSourcesList(String category) {
        sourceView.showLoad();
        dataManagerHelper.getSources(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Source>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Source> sources) {
                        sourceView.updateSourceList(sources);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        sourceView.showError();
                    }

                    @Override
                    public void onComplete() {
                        sourceView.showContent();
                    }
                });
    }
}
