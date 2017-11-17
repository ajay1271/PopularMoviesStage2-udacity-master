package com.cavepass.popularmoviesstage1;

import java.util.ArrayList;

public class ReviewsClassJson implements java.io.Serializable {
    private static final long serialVersionUID = -4636847733754803936L;
    private int id;
    private int page;
    private int total_pages;
    private ArrayList<ReviewsClassJsonResults> results;
    private int total_results;

    public ReviewsClassJson(int id, int page, int total_pages, ArrayList<ReviewsClassJsonResults> results, int total_results) {
        this.id = id;
        this.page = page;
        this.total_pages = total_pages;
        this.results = results;
        this.total_results = total_results;
    }

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

    public ArrayList<ReviewsClassJsonResults> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<ReviewsClassJsonResults> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return this.total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
