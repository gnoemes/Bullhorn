package com.gnoemes.bullhorn.util;

import com.gnoemes.bullhorn.data.model.article.Article;
import com.gnoemes.bullhorn.data.model.article.ArticleEntity;
import com.gnoemes.bullhorn.data.model.source.Source;

public final class DummyModels {

    public static Source getDummySource(){
        Source source = new Source();
        source.setId("1");
        source.setName("name");
        source.setCategory("category");
        source.setCountry("country");
        source.setDescription("description");
        source.setLanguage("en");
        source.setUrl("url");
        return source;
    }

    public static ArticleEntity getDummyArticleEntity() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setUrl("url");
        articleEntity.setDescription("desc");
        articleEntity.setAuthor("auth");
        articleEntity.setPublishedAt("date");
        articleEntity.setSource("source");
        articleEntity.setTitle("title");
        articleEntity.setUrlToImage("urlImg");
        return articleEntity;
    }

    public static Article getDummyArticle() {
        Article article = new Article();
        article.setPublishedAt("date");
        article.setUrl("url");
        article.setDescription("desc");
        article.setAuthor("auth");
        article.setTitle("title");
        article.setUrlToImage("urlImg");
        return article;
    }
}
