package com.gnoemes.bullhorn.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface ArticleDao {

    @Query("SELECT * FROM " + Config.TABLE_NAME + " WHERE source = :source")
    Flowable<List<ArticleEntity>> getArticles(String source);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ArticleEntity article);

    @Query("DELETE FROM " + Config.TABLE_NAME)
    void deleteAll();
}
