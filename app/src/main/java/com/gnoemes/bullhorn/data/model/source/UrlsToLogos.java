
package com.gnoemes.bullhorn.data.model.source;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UrlsToLogos {

    @SerializedName("large")
    private String mLarge;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("small")
    private String mSmall;

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

}
