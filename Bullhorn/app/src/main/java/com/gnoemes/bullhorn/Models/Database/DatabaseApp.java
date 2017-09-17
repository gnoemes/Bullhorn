package com.gnoemes.bullhorn.Models.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DatabaseApp extends SQLiteOpenHelper implements DatabaseHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "articles";
    private static final String TABLE_NAME = "ARTICLE";
    private static final String COLUMN_SOURCE_ID = "SOURCE_ID";
    private static final String COLUMN_JSON_STRING = "JSON_STRING";

    @Inject
    public DatabaseApp(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void saveArticles( String source,  Observable<List<Article>> articles) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        String jsonArticlesList = new Gson().toJson(articles.blockingFirst());

        cv.put(COLUMN_SOURCE_ID,source);
        cv.put(COLUMN_JSON_STRING,jsonArticlesList);

        db.insert(TABLE_NAME,null,cv);
        db.close();
        Log.i("DATABASE", "saveArticles: ");

    }

    @Override
    public Observable<List<Article>> getArticles(String source) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select " + COLUMN_JSON_STRING +" from " + TABLE_NAME  + " where " + COLUMN_SOURCE_ID + " = '" + source +"'",null);
        cursor.moveToFirst();
        Type type = new TypeToken<List<Article>>() {}.getType();
        Log.i("DATABASE", "getArticles: ");
        List<Article> articles = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(COLUMN_JSON_STRING)),type);
        db.close();
        return Observable.fromArray(articles).subscribeOn(Schedulers.io());
    }

    @Override
    public boolean isContains(String source) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + COLUMN_SOURCE_ID +" from " + TABLE_NAME  + " where " + COLUMN_SOURCE_ID + " = '" + source +"'",null);

        Log.i("DATABASE", "isContains: " + (cursor != null));
        return  cursor != null && cursor.getCount() > 0;
}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                +  "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SOURCE_ID + " TEXT,"
                + COLUMN_JSON_STRING + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
