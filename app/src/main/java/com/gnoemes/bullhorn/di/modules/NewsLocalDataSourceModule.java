package com.gnoemes.bullhorn.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gnoemes.bullhorn.data.source.NewsDataSource;
import com.gnoemes.bullhorn.data.source.local.ArticleDao;
import com.gnoemes.bullhorn.data.source.local.ArticleDb;
import com.gnoemes.bullhorn.data.source.local.Config;
import com.gnoemes.bullhorn.data.source.local.NewsLocalDataStorage;
import com.gnoemes.bullhorn.di.annotations.Local;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class NewsLocalDataSourceModule {
    private static final String DATABASE = "news_database";

    @Provides
    @Named(DATABASE)
    String provideDataBaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    ArticleDb provideNewsDb(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context,ArticleDb.class, databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    ArticleDao provideArticleDao(ArticleDb db) {
        return db.getArticleDao();
    }

    @Provides
    @Singleton
    @Local
    NewsDataSource provideNewsDataSource(ArticleDao articleDao) {
        return new NewsLocalDataStorage(articleDao);
    }
}
