package com.gnoemes.bullhorn.ui.sources;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gnoemes.bullhorn.data.model.source.Source;

import java.util.List;

public interface SourceView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showLoad();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showContent();

    @StateStrategyType(SkipStrategy.class)
    void showError();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateSourceList(List<Source> sources);
}
