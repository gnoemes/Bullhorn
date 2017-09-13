package com.gnoemes.bullhorn.ui.Main;

import android.util.Log;

import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

        dataManagerHelper.getSources(category)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<List<Source>, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull List<Source> sources) throws Exception {
                        List<String> sourceNames = new ArrayList<>();
                        for (Source s:sources) {
                            sourceNames.add(s.getName());
                            Log.i("DEVE", "apply: " + s.getName());
                        }
                        return sourceNames;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> names) throws Exception {
                        mainView.updateSources(names);
                    }
                });

    }

    @Override

    public void loadArticles(int sourceId) {

        dataManagerHelper.getArticles(mainView.getCurrentCategory().get(sourceId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>() {

                    @Override
                    public void accept(List<Article> articleList) throws Exception {
                       mainView.updateArticles(articleList);
                    }
                });

    }

}
