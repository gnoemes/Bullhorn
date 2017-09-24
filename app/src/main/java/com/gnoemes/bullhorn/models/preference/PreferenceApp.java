package com.gnoemes.bullhorn.models.preference;

import android.content.SharedPreferences;
import android.util.Log;

import com.gnoemes.bullhorn.models.networking.model.source.Source;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class PreferenceApp implements PreferenceHelper {

    private SharedPreferences sPrefs;

    @Inject
    public PreferenceApp(SharedPreferences sPrefs) {
        this.sPrefs = sPrefs;
    }


    @Override
    public Observable<List<Source>> getData(final String category) {
        return Observable.fromArray(getSources(category)).subscribeOn(Schedulers.io());

    }

    @Override
    public boolean isContains(String category) {
        return sPrefs.contains(category);
    }

    @Override
    public void saveData(final String category, Observable<List<Source>> source) {
      source.subscribe(sources -> setSources(category,sources));
    }


    private List<Source> getSources(String category) {
        Type type = new TypeToken<List<Source>>() {}.getType();
        return new Gson().fromJson(sPrefs.getString(category,""),type);
    }



    private void setSources(String category, List<Source> sources) {
        String jsonString = new Gson().toJson(sources);
        Log.i("PreferenceApp", "setSources: " + jsonString);
        sPrefs.edit().putString(category,jsonString).apply();
    }



}
