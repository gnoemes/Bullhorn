package com.gnoemes.bullhorn.ui.NewsFragments;

import android.util.Log;

import com.gnoemes.bullhorn.Models.DataManagerHelper;
import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.gnoemes.bullhorn.Models.Preference.ArticleParcelable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ArticlesPresenter implements IArticlesPresenter {

    private DataManagerHelper dataManagerHelper;
    private IArticlesView articlesView;

    @Inject
    public ArticlesPresenter(IArticlesView articlesView, DataManagerHelper dataManagerHelper) {
        this.articlesView = articlesView;
        this.dataManagerHelper = dataManagerHelper;
    }

    @Override
    public void loadArticlesList(String source,boolean force) {
        articlesView.showLoad();
        Observable<List<Article>> obs = dataManagerHelper.getArticles(source,force);
        dataManagerHelper.saveArticleData(source,obs);
            obs.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Article>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Article> articles) {
                        articlesView.updateArticleList(articles);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        articlesView.showError();
                    }

                    @Override
                    public void onComplete() {
                        articlesView.showContent();
                    }
                });

    }

    @Override
    public void itemClicked(Article item) {
        String[] date;
        date = item.getPublishedAt() == null ? new String[]{"No date"} :item.getPublishedAt().split("T");
        ArticleParcelable parcelable = new ArticleParcelable(item.getAuthor(),item.getDescription(),date[0],item.getTitle(),item.getUrl(),item.getUrlToImage());
        Log.i("DEVE", "itemClicked: " + parcelable.getTitle());
        articlesView.showDetails(parcelable);
    }
}
