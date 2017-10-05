package com.gnoemes.bullhorn.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDb extends RoomDatabase {
    public abstract ArticleDao getArticleDao();
}
