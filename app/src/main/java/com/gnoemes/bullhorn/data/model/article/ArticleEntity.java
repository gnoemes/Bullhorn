package com.gnoemes.bullhorn.data.model.article;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "articles",indices = @Index("source"))
public class ArticleEntity {

    @ColumnInfo(name = "source")
    private String source;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "publishedAt")
    private String publishedAt;
    @ColumnInfo(name = "title")
    @PrimaryKey
    @NonNull
    private String title;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "imageUrl")
    private String urlToImage;

    public ArticleEntity() {}

    @Ignore
    public ArticleEntity(String source, String author, String description, String publishedAt, @NonNull String title, String url, String urlToImage) {
        this.source = source;
        this.author = author;
        this.description = description;
        this.publishedAt = publishedAt;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
