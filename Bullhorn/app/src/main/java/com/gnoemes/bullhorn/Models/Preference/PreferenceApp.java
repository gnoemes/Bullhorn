package com.gnoemes.bullhorn.Models.Preference;

import android.content.SharedPreferences;

import com.gnoemes.bullhorn.Models.Networking.Model.Source.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PreferenceApp implements PreferenceHelper {

    private SharedPreferences sPrefs;
    private List<Source> sources;

    @Inject
    public PreferenceApp(SharedPreferences sPrefs) {
        this.sPrefs = sPrefs;
        sources = new ArrayList<>();
    }


    @Override
    public Observable<List<Source>> getData(final String category) {
//        List<String> sourcesNames = new ArrayList<>();
//        for (int i = 0; i < sources.size(); i++) {
//            sourcesNames.add(sPrefs.getString(category,""));
//        }
        return null;

    }

    @Override
    public boolean isContains(String category) {
        return sPrefs.contains(category);
    }

    @Override
    public void saveData(Observable<List<Source>> source) {

    }


    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id, String name) {
        sPrefs.edit().putString(id,name).apply();
    }


    @Override
    public void setSources(List<Source> sources) {
        this.sources = sources;
    }



}
