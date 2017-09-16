package com.gnoemes.bullhorn.Models.Preference;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

import io.reactivex.Observable;

public interface PreferenceHelper {

    Observable<List<Source>> getData(String category);

    boolean isContains(String category);

    void saveData(Observable<List<Source>> source);

    String getId();

    void setId(String id,String name);

    void setSources(List<Source> sources);


}
