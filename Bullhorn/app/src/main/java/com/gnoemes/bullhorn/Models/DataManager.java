package com.gnoemes.bullhorn.Models;

import android.content.Context;

import com.gnoemes.bullhorn.Models.Database.DatabaseHelper;
import com.gnoemes.bullhorn.Models.Networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.Models.Preference.PreferenceHelper;

import javax.inject.Inject;

public class DataManager implements DataManagerHelper {

    private Context context;
    private NewsNetworkHelper newsNetworkHelper;
    private DatabaseHelper databaseHelper;
    private PreferenceHelper preferenceHelper;

    @Inject
    public DataManager(Context context, NewsNetworkHelper newsNetworkHelper, DatabaseHelper databaseHelper, PreferenceHelper preferenceHelper) {
        this.context = context;
        this.newsNetworkHelper = newsNetworkHelper;
        this.databaseHelper = databaseHelper;
        this.preferenceHelper = preferenceHelper;
    }
}
