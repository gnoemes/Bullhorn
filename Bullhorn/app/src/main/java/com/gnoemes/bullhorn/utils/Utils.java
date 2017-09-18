package com.gnoemes.bullhorn.utils;

import com.gnoemes.bullhorn.models.networking.model.article.Article;

public final class Utils {

    public static String formatDate(Article item) {
        String[] date;
        date = item.getPublishedAt() == null ? new String[]{"No date"} :item.getPublishedAt().split("T");
        return date[0];
    }

}
