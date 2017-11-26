package com.cavepass.popularmoviesstage1.ModelClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailersClass implements Parcelable {
    public static final Creator<TrailersClass> CREATOR = new Creator<TrailersClass>() {
        @Override
        public TrailersClass createFromParcel(Parcel source) {
            TrailersClass var = new TrailersClass();
            var.id = source.readInt();
            var.results = source.createTypedArray(TrailersClassResults.CREATOR);
            return var;
        }

        @Override
        public TrailersClass[] newArray(int size) {
            return new TrailersClass[size];
        }
    };
    private int id;
    private TrailersClassResults[] results;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrailersClassResults[] getResults() {
        return this.results;
    }

    public void setResults(TrailersClassResults[] results) {
        this.results = results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedArray(this.results, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
