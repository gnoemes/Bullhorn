package com.gnoemes.bullhorn.Common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.di.Components.AppComponent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFragment extends Fragment {

    private List<Article> articles;
    private List<String> sources;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.getApp(getActivity()).getAppComponent());

    }

    protected void transformArticles(Observable<List<Article>> listObservable) {
        listObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>() {
                    @Override
                    public void accept(List<Article> articles) throws Exception {
                        setArticles(articles);
                    }
                });
    }

    protected void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    protected List<Article> getArticles() {
        return articles;
    }



    protected abstract void setupComponent(AppComponent appComponent);


}
