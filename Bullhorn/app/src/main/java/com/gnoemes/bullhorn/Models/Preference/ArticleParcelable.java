package com.gnoemes.bullhorn.Models.Preference;

import android.os.Parcel;
import android.os.Parcelable;


public class ArticleParcelable implements Parcelable {

    private String mAuthor;
    private String mDescription;
    private String mPublishedAt;
    private String mTitle;
    private String mUrl;
    private String mUrlToImage;

    public ArticleParcelable(String mAuthor, String mDescription, String mPublishedAt, String mTitle, String mUrl, String mUrlToImage) {
        this.mAuthor = mAuthor;
        this.mDescription = mDescription;
        this.mPublishedAt = mPublishedAt;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mUrlToImage = mUrlToImage;
    }

    public ArticleParcelable(Parcel in) {
        String[] data = new String[6];
        in.readStringArray(data);
        mAuthor = data[0];
        mDescription = data[1];
        mPublishedAt = data[2];
        mTitle = data[3];
        mUrl = data[4];
        mUrlToImage = data[5];
    }

    public static final Creator<ArticleParcelable> CREATOR = new Creator<ArticleParcelable>() {
        @Override
        public ArticleParcelable createFromParcel(Parcel in) {
            return new ArticleParcelable(in);
        }

        @Override
        public ArticleParcelable[] newArray(int size) {
            return new ArticleParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeStringArray(new String[] {mAuthor,mDescription,mPublishedAt,mTitle,mUrl,mUrlToImage});
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmUrlToImage() {
        return mUrlToImage;
    }

    public void setmUrlToImage(String mUrlToImage) {
        this.mUrlToImage = mUrlToImage;
    }
}
