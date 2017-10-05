package com.gnoemes.bullhorn.presenters;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.ui.articles.ArticlesPresenter;
import com.gnoemes.bullhorn.ui.articles.ArticlesView$$State;
import com.gnoemes.bullhorn.util.DummyModels;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArticlePresenterTest {
    private ArticlesPresenter presenter;

    @Mock
    NewsRepository repository;

    @Mock
    ArticlesView$$State state;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ArticlesPresenter(repository);
        presenter.setViewState(state);
    }

    @Test
    public void loadArticlesListShouldLoadFromRemoteThenShowLoadAndSuccess() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        List<ArticleEntity> entityList = Collections.singletonList(articleEntity);
        when(repository.getArticles(articleEntity.getSource(),true)).thenReturn(Flowable.just(entityList));
        presenter.loadArticlesList(articleEntity.getSource(),true);
        verify(state,times(1)).showLoad();
        verify(state,times(1)).showContent();
        verify(state,times(1)).updateArticleList(entityList);
    }

    @Test
    public void loadArticlesListShouldLoadFromLocal() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        List<ArticleEntity> entityList = Collections.singletonList(articleEntity);
        when(repository.getArticles(articleEntity.getSource(),false)).thenReturn(Flowable.just(entityList));
        presenter.loadArticlesList(articleEntity.getSource(),false);
        verify(state,times(1)).showLoad();
        verify(state,times(1)).showContent();
        verify(state,times(1)).updateArticleList(entityList);
    }

    @Test
    public void loadArticlesShouldLoadFromRemoteWithErrorThenLoadFromLocal() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        List<ArticleEntity> entityList = Collections.singletonList(articleEntity);
        when(repository.getArticles(articleEntity.getSource(),true)).thenReturn(Flowable.error(() -> {
            throw new UnknownHostException();
        }));
        when(repository.getArticles(articleEntity.getSource(),false)).thenReturn(Flowable.just(entityList));
        presenter.loadArticlesList(articleEntity.getSource(),true);
        verify(state,times(2)).showLoad();
        verify(state,times(1)).showError();
        verify(state,times(1)).showContent();
        verify(state,times(1)).updateArticleList(entityList);
    }

    @Test
    public void loadArticlesShouldLoadEmptyAndThrowException() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        when(repository.getArticles(articleEntity.getSource(),false)).thenReturn(Flowable.just(new ArrayList<>()));
        presenter.loadArticlesList(articleEntity.getSource(),false);
        verify(state,times(1)).showLoad();
        verify(state,times(1)).showError();
    }
}
