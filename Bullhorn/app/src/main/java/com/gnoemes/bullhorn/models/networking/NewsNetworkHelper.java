package com.gnoemes.bullhorn.models.networking;

import com.gnoemes.bullhorn.models.networking.data.ArticlesData;
import com.gnoemes.bullhorn.models.networking.data.SourceData;

public interface NewsNetworkHelper {

    SourceData getSources();

    ArticlesData getArticles();

}
