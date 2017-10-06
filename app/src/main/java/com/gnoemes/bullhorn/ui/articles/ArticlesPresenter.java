package com.gnoemes.bullhorn.ui.articles;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gnoemes.bullhorn.App;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.data.source.preference.ArticleParcelable;
import com.gnoemes.bullhorn.di.components.DaggerRepositoryComponent;
import com.gnoemes.bullhorn.di.components.RepositoryComponent;
import com.gnoemes.bullhorn.utils.RxUtil;
import com.gnoemes.bullhorn.utils.Utils;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ArticlesPresenter extends MvpPresenter<ArticlesView> {

    private NewsRepository repository;
    private Disposable d;

    @Inject
    public ArticlesPresenter(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        initComponent();
    }

    private void initComponent() {
        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        repositoryComponent.inject(this);
    }

    public void loadArticlesList(String source,boolean force) {
        getViewState().showLoad();
        d = repository.getArticles(source,force)
//                .filter(articleEntities -> !articleEntities.isEmpty())
                .subscribe(articles -> {
                    if (articles.isEmpty()) {
                        throw new IllegalStateException("List is empty");
                    }
                    getViewState().updateArticleList(articles);
                    getViewState().showContent();
                }, throwable -> {
                    throwable.printStackTrace();
                    if (throwable.getClass() == UnknownHostException.class) {
                        loadArticlesList(source,false);
                    }
                    getViewState().showError();
                });
    }

    public void itemClicked(ArticleEntity item) {
        ArticleParcelable parcelable = new ArticleParcelable(item.getAuthor(),item.getDescription(), Utils.formatDate(item),item.getTitle(),item.getUrl(),item.getUrlToImage());
        Log.i("DEVE", "itemClicked: " + parcelable.getTitle());
        getViewState().showDetails(parcelable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(d);
    }
}
