package com.cavepass.popularmoviesstage1.ModelClasses;

/**
 * Created by Ajay on 15-11-2017.
 */


import com.google.gson.annotations.*;

import java.io.Serializable;
import java.util.List;


public class ApiResponce implements Serializable
{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MovieDetails> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieDetails> getResults() {
        return results;
    }

    public void setResults(List<MovieDetails> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}