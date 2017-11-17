package com.cavepass.popularmoviesstage1;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewsAPI implements Parcelable {
    public static final Creator<ReviewsAPI> CREATOR = new Creator<ReviewsAPI>() {
        @Override
        public ReviewsAPI createFromParcel(Parcel source) {
            ReviewsAPI var = new ReviewsAPI();
            var.id = source.readInt();
            var.page = source.readInt();
            var.total_pages = source.readInt();
            var.results = source.createTypedArray(ReviewsAPIResults.CREATOR);
            var.total_results = source.readInt();
            return var;
        }

        @Override
        public ReviewsAPI[] newArray(int size) {
            return new ReviewsAPI[size];
        }
    };
    private int id;
    private int page;
    private int total_pages;
    private ReviewsAPIResults[] results;
    private int total_results;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ReviewsAPIResults[] getResults() {
        return this.results;
    }

    public void setResults(ReviewsAPIResults[] results) {
        this.results = results;
    }

    public int getTotal_results() {
        return this.total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.page);
        dest.writeInt(this.total_pages);
        dest.writeTypedArray(this.results, flags);
        dest.writeInt(this.total_results);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
