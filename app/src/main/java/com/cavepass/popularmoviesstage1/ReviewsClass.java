package com.cavepass.popularmoviesstage1;

/**
 * Created by Ajay on 12-11-2017.
 *
 *
 id	83542
 page	1
 results
 0
 id	"51910979760ee320eb020fc2"
 author	"Andres Gomez"
 content	"Interesting film with an exceptional cast, fantastic performances and characterizations. The story, though, is a bit difficult to follow and, in the end, seems to not have a real point."
 url	"https://www.themoviedb.org/review/51910979760ee320eb020fc2"

 */


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class ReviewsClass implements Serializable {

    @SerializedName("m_id")
    public int mId;

    @SerializedName("m_Page")
    public int mPage;

    @SerializedName("m_reviews")
   public  ArrayList<reviews> reviews;

    @SerializedName("m_totalPages")
    public int totalPages;

    @SerializedName("m_totalResults")
   public  int totalResults;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmPage() {
        return mPage;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }

    public ArrayList<reviews> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<reviews> reviews) {
        this.reviews = reviews;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ReviewsClass(int id, int page, ArrayList<reviews> review, int totalPages, int totalResults) {


        mId=id;
        mPage=page;
       reviews = review;
       this.totalPages =totalPages;
       this.totalResults=totalResults;
    }

}
