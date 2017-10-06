package com.gnoemes.bullhorn.utils;

import com.gnoemes.bullhorn.data.model.article.ArticleEntity;

public final class Utils {

    public static String formatDate(ArticleEntity item) {
        String[] date;
        date = item.getPublishedAt() == null ? new String[]{"No date"} :item.getPublishedAt().split("T");
        return date[0];
    }

}
