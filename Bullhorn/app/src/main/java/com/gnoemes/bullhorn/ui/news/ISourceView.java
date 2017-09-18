package com.gnoemes.bullhorn.ui.news;

import com.gnoemes.bullhorn.models.networking.model.source.Source;

import java.util.List;

public interface ISourceView {

    void showLoad();

    void showContent();

    void showError();

    void updateSourceList(List<Source> sources);
}
