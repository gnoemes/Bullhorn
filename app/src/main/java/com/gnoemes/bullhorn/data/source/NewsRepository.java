package com.gnoemes.bullhorn.data.source;

import android.support.annotation.NonNull;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;
import com.gnoemes.bullhorn.di.annotations.Local;
import com.gnoemes.bullhorn.di.annotations.Preference;
import com.gnoemes.bullhorn.di.annotations.Remote;
import com.gnoemes.bullhorn.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class NewsRepository implements NewsDataSource {

    @NonNull
    private final NewsDataSource localDataStorage;
    @NonNull
    private final NewsDataSource remoteDataStorage;
    @NonNull
    private final NewsDataSource preferenceDataStorage;

    @Inject
    public NewsRepository(@NonNull @Local NewsDataSource localDataStorage,
                          @NonNull @Remote NewsDataSource remoteDataStorage,
                          @NonNull @Preference NewsDataSource preferenceDataStorage) {
        this.localDataStorage = localDataStorage;
        this.remoteDataStorage = remoteDataStorage;
        this.preferenceDataStorage = preferenceDataStorage;
    }

    @Override
    public boolean saveSourceData(String category, List<Source> source) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean saveArticleData(ArticleEntity article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<List<Source>> getSources(final String category) {
        return preferenceDataStorage.getSources(category)
                .concatWith(remoteDataStorage.getSources(category)
                                            .doOnNext(sources -> preferenceDataStorage.saveSourceData(category,sources)))
                .compose(RxUtil.applySchedulers())
                .take(1);
    }

    @Override
    public Flowable<List<ArticleEntity>> getArticles(final String source, boolean force) {
        if (force) {
            return remoteDataStorage.getArticles(source, true)
                    .map(articleEntities -> {
                        Observable.fromIterable(articleEntities)
                                .forEach(localDataStorage::saveArticleData);
                        return articleEntities;
                    })
                    .compose(RxUtil.applyFlowableSchedulers());
        }
       return localDataStorage.getArticles(source,false)
               .compose(RxUtil.applyFlowableSchedulers());
    }
}