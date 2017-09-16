package com.gnoemes.bullhorn.ui.NewsFragments;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

public interface ISourceView {

    void showLoad();

    void showContent();

    void showError();

    void updateSourceList(List<Source> sources);
}
