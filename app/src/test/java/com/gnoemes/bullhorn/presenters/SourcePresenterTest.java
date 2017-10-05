package com.gnoemes.bullhorn.presenters;

import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.data.source.NewsRepository;
import com.gnoemes.bullhorn.ui.sources.SourcePresenter;
import com.gnoemes.bullhorn.ui.sources.SourceView$$State;
import com.gnoemes.bullhorn.util.DummyModels;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SourcePresenterTest {
    private SourcePresenter presenter;

    @Mock
    NewsRepository repository;

    @Mock
    SourceView$$State state;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new SourcePresenter(repository);
        presenter.setViewState(state);
    }

    @Test
    public void loadSourcesListShouldShowLoadAndSuccess() {
        Source source = DummyModels.getDummySource();
        List<Source> sourceList = Collections.singletonList(source);
        when(repository.getSources(source.getCategory())).thenReturn(Observable.just(sourceList));
        presenter.loadSourcesList(source.getCategory());
        verify(state,times(1)).showLoad();
        verify(state,times(1)).showContent();
    }

    @Test
    public void loadSourcesListShouldShowLoadAndShowError() throws Exception{
        Source source = DummyModels.getDummySource();
        when(repository.getSources(source.getCategory())).thenReturn(Observable.error(() -> {throw  new IllegalStateException();}));
        presenter.loadSourcesList(source.getCategory());
        verify(state,times(1)).showLoad();
        verify(state,times(1)).showError();
    }
}
