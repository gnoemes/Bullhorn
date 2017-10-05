package com.gnoemes.bullhorn.data;

import android.content.SharedPreferences;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.source.preference.PreferenceDataSource;
import com.gnoemes.bullhorn.util.DummyModels;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PreferenceDataSourceTest {
    private PreferenceDataSource preferenceDataSource;

    @Mock
    SharedPreferences prefs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        preferenceDataSource = new PreferenceDataSource(prefs);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveArticlesShouldThrowException() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        preferenceDataSource.saveArticleData(articleEntity);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getArticlesShouldThrowException() {
        ArticleEntity articleEntity = DummyModels.getDummyArticleEntity();
        preferenceDataSource.getArticles(articleEntity.getSource(),true);
    }
}
