package com.gnoemes.bullhorn.data;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.util.DummyModels;
import com.gnoemes.bullhorn.util.RxTestUtil;
import com.gnoemes.bullhorn.utils.RxUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RxUtil.class)
public class NewsRepositoryTest {
    private NewsRepository repository;

    @Mock
    NewsDataSource localDataSource;

    @Mock
    NewsDataSource remoteDataSource;

    @Mock
    NewsDataSource preferenceDataSource;

    private ArticleEntity article;
    private Source source;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RxTestUtil.mockRxSchedulers();
        repository = new NewsRepository(localDataSource,remoteDataSource,preferenceDataSource);
        article = DummyModels.getDummyArticleEntity();
        source = DummyModels.getDummySource();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveArticleShouldError() {
        repository.saveArticleData(article);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveSourceShouldError() {
        List<Source> sources = Collections.singletonList(source);
        repository.saveSourceData(source.getCategory(),sources);
    }

    @Test
    public void getSourcesShouldGetFromPreferenceAndRemote() {
        List<Source> sources = Collections.singletonList(source);
        when(preferenceDataSource.getSources(source.getCategory())).thenReturn(Observable.just(sources));
        when(remoteDataSource.getSources(source.getCategory())).thenReturn(Observable.just(sources));
        TestObserver<List<Source>> testObserver = repository.getSources(source.getCategory()).test();
        testObserver.assertNoErrors();
        testObserver.assertValue(sources);
        verify(preferenceDataSource, times(1)).getSources(source.getCategory());
        verify(remoteDataSource,times(1)).getSources(source.getCategory());
    }

    @Test
    public void getArticlesShouldReturnFromLocalStorage() {
        List<ArticleEntity> entityList= Collections.singletonList(article);
        when(localDataSource.getArticles(article.getSource(),false)).thenReturn(Flowable.just(entityList));
        TestSubscriber<List<ArticleEntity>> testObserver = repository.getArticles(article.getSource(),false).test();
        testObserver.assertNoErrors();
        testObserver.assertValue(entityList);
        verify(localDataSource,times(1)).getArticles(article.getSource(),false);
    }

    @Test
    public void getArticlesShouldReturnFromRemoteAndSaveInLocalStorage() {
        List<ArticleEntity> entityList = Collections.singletonList(article);
        when(remoteDataSource.getArticles(article.getSource(),true)).thenReturn(Flowable.just(entityList));
        TestSubscriber<List<ArticleEntity>> testObserver = repository.getArticles(article.getSource(),true).test();
        testObserver.assertNoErrors();
        testObserver.assertValue(articleEntities -> {
            verify(localDataSource,times(1)).saveArticleData(articleEntities.get(0));
            return true;
        });
        verify(remoteDataSource,times(1)).getArticles(article.getSource(),true);

    }
}
