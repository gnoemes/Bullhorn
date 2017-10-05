package com.gnoemes.bullhorn.data;

import com.gnoemes.bullhorn.data.model.article.Article;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.article.Articles;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.model.source.Sources;
import com.gnoemes.bullhorn.data.source.remote.NewsRemoteDataSource;
import com.gnoemes.bullhorn.data.source.remote.endpoints.ArticlesData;
import com.gnoemes.bullhorn.data.source.remote.endpoints.NetworkConstants;
import com.gnoemes.bullhorn.data.source.remote.endpoints.SourceData;
import com.gnoemes.bullhorn.util.DummyModels;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RemoteDataSourceTest {
    private NewsRemoteDataSource remoteDataSource;

    @Mock
    ArticlesData articlesData;

    @Mock
    SourceData sourceData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        remoteDataSource = new NewsRemoteDataSource(articlesData,sourceData);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveArticlesShouldThrowException() {
        remoteDataSource.saveArticleData(new ArticleEntity());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveSourcesShouldThrowException() {
        List<Source> sources = new ArrayList<>();
        remoteDataSource.saveSourceData("category",sources);
    }

    @Test
    public void getSourcesShouldReturnSourcesFromServer() {
        Source source = DummyModels.getDummySource();
        Sources sources = new Sources();
        List<Source> sourceList = Collections.singletonList(source);
        sources.setSources(sourceList);
        when(sourceData.getSourcesData(source.getCategory(),source.getLanguage())).thenReturn(Observable.just(sources));
        TestObserver<List<Source>> testObserver = remoteDataSource.getSources(source.getCategory()).test();
        testObserver.assertNoErrors();
        testObserver.assertValue(sources.getSources());
    }

    @Test
    public void getArticlesShouldReturnArticlesMappedFromServer() {
        Articles articles = new Articles();
        Article article = DummyModels.getDummyArticle();
        List<Article> articleList = Collections.singletonList(article);
        articles.setArticles(articleList);
        articles.setSource("ign");
        ArticleEntity articleEntity = new ArticleEntity(articles.getSource(),article.getAuthor(),article.getDescription(),article.getPublishedAt(),article.getTitle(),article.getUrl(),article.getUrlToImage());
        List<ArticleEntity> entityList = Collections.singletonList(articleEntity);

        when(articlesData.getArticlesData(articles.getSource(), NetworkConstants.API_KEY)).thenReturn(Flowable.just(articles));
        TestSubscriber<List<ArticleEntity>> testObserver = remoteDataSource.getArticles(articles.getSource(),false).test();
            testObserver.assertNoErrors();
            testObserver.assertValue(articleEntities -> {
                assertEquals(articleEntities.get(0).getAuthor(), entityList.get(0).getAuthor());
                assertEquals(articleEntities.get(0).getDescription(), entityList.get(0).getDescription());
                assertEquals(articleEntities.get(0).getPublishedAt(), entityList.get(0).getPublishedAt());
                assertEquals(articleEntities.get(0).getSource(), entityList.get(0).getSource());
                assertEquals(articleEntities.get(0).getTitle(), entityList.get(0).getTitle());
                assertEquals(articleEntities.get(0).getUrl(), entityList.get(0).getUrl());
                assertEquals(articleEntities.get(0).getUrlToImage(), entityList.get(0).getUrlToImage());
                return articleEntities.size() == entityList.size();
            });
    }
}
