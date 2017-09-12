package com.gnoemes.bullhorn.Models.Preference;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferenceApp implements PreferenceHelper {

    private SharedPreferences sPrefs;

    @Inject
    public PreferenceApp(SharedPreferences sPrefs) {
        this.sPrefs = sPrefs;
    }


}
