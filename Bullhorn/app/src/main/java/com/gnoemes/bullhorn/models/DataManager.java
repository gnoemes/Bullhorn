package com.gnoemes.bullhorn.models;

import android.content.Context;

import com.gnoemes.bullhorn.models.database.DatabaseHelper;
import com.gnoemes.bullhorn.models.networking.NewsNetworkHelper;
import com.gnoemes.bullhorn.models.preference.PreferenceHelper;

public class DataManager implements DataManagerHelper{


    public DataManager(Context context, NewsNetworkHelper networkHelper,
                       PreferenceHelper preferenceHelper, DatabaseHelper databaseHelper) {

    }
}
