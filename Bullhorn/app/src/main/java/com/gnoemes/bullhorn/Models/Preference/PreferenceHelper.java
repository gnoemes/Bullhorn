package com.gnoemes.bullhorn.Models.Preference;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface PreferenceHelper {

    Observable<List<Source>> getData(String category);

    boolean isContains(String category);

    void saveData(String category,Observable<List<Source>> source);



}
