package com.gnoemes.bullhorn.Models.Preference;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.List;

public interface PreferenceHelper {

    List<String> getData(String category);

    boolean isContains(String category);

    void saveData(Source source);

    String getId();

    void setId(String id,String name);

    void setSources(List<Source> sources);


}
