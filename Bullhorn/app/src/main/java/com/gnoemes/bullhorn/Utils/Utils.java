package com.gnoemes.bullhorn.Utils;

import com.gnoemes.bullhorn.Models.Networking.Model.Article.Article;

public final class Utils {

    public static String formatDate(Article item) {
        String[] date;
        date = item.getPublishedAt() == null ? new String[]{"No date"} :item.getPublishedAt().split("T");
        return date[0];
    }

}
