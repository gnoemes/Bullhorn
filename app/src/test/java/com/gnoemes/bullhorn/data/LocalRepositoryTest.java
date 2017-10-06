package com.gnoemes.bullhorn.data;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.source.local.ArticleDao;
import com.gnoemes.bullhorn.data.source.local.NewsLocalDataStorage;
import com.gnoemes.bullhorn.util.DummyModels;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LocalRepositoryTest {
    private NewsLocalDataStorage dataStorage;

    @Mock
    ArticleDao articleDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        dataStorage = new NewsLocalDataStorage(articleDao);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveSourcesShouldThrowException() {
        Source source = DummyModels.getDummySource();
        List<Source> sources = Collections.singletonList(source);
        dataStorage.saveSourceData(source.getCategory(),sources);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getSourcesShouldThrowException() {
        Source source = DummyModels.getDummySource();
        dataStorage.getSources(source.getCategory());
    }

    @Test
    public void getArticlesShouldReturnListOfArticles() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        List<ArticleEntity> entityList = Collections.singletonList(articleEntity);
        when(articleDao.getArticles(articleEntity.getSource())).thenReturn(Flowable.just(entityList));
        TestSubscriber<List<ArticleEntity>> testObservable = dataStorage.getArticles(articleEntity.getSource(),false).test();
        testObservable.assertNoErrors();
        testObservable.assertValue(entityList);
    }

    @Test
    public void saveArticlesShouldSuccess() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        when(articleDao.insert(articleEntity)).thenReturn(Long.valueOf(1));
        assertTrue(dataStorage.saveArticleData(articleEntity));
    }
}
