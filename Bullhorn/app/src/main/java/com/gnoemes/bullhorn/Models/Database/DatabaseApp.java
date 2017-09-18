package com.gnoemes.bullhorn.Models.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;
import com.google.gson.Gson;

import java.util.ArrayList;
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
    public void saveArticles(final String source, Observable<List<Article>> articles) {
        final SQLiteDatabase db = this.getWritableDatabase();

        articles.subscribeOn(Schedulers.computation())
                .subscribe(articles1 -> {
                    for (Article a: articles1) {
                        ContentValues cv = new ContentValues();
                        String jsonArticle = new Gson().toJson(a);
                        cv.put(COLUMN_SOURCE_ID, source);
                        cv.put(COLUMN_JSON_STRING,jsonArticle);

                        long count = db.insertWithOnConflict(TABLE_NAME,null,cv,SQLiteDatabase.CONFLICT_REPLACE);
                        Log.i("DATABASE", "SAVED: " + count );
                    }
                });
//        db.close();
        Log.i("DATABASE", "saveArticles: ");

    }

    @Override
    public Observable<List<Article>> getArticles(String source) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[] {COLUMN_JSON_STRING},COLUMN_SOURCE_ID + " = ? ",new String[] {source},null,null,null);
        cursor.moveToFirst();
        List<Article> articles = new ArrayList<>();
        do {
            for (String cn: cursor.getColumnNames()) {
                articles.add(new Gson().fromJson(cursor.getString(cursor.getColumnIndex(cn)),Article.class));
                Log.i("DATABASE", "getArticles: " + cursor.getString(cursor.getColumnIndex(cn)));
            }

        }while (cursor.moveToNext());

        return Observable.fromArray(articles).subscribeOn(Schedulers.io());
    }

    @Override
    public boolean isContains(String source) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[] {COLUMN_JSON_STRING},COLUMN_SOURCE_ID + " = ? ",new String[] {source},null,null,null);
        boolean isContains = cursor != null && cursor.getCount() > 0;
        Log.i("DATABASE", "isContains: " + isContains);
        cursor.close();
//        db.close();
        return isContains;
}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                +  "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SOURCE_ID + " TEXT,"
                + COLUMN_JSON_STRING + " TEXT UNIQUE" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
