package com.gnoemes.bullhorn.models.preference;

import com.gnoemes.bullhorn.models.networking.model.source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface PreferenceHelper {

    Observable<List<Source>> getData(String category);

    boolean isContains(String category);

    void saveData(String category,Observable<List<Source>> source);



}
