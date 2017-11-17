package com.cavepass.popularmoviesstage1;

import java.util.ArrayList;

public class TrailersClass implements java.io.Serializable {
    private static final long serialVersionUID = -8930888215213787732L;
    private int id;
    private ArrayList<TrailersClassResults> results;

    public TrailersClass(int id, ArrayList<TrailersClassResults> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrailersClassResults> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<TrailersClassResults> results) {
        this.results = results;
    }
}
