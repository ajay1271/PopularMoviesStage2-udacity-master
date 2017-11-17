package com.cavepass.popularmoviesstage1;

public class TrailersClassResults implements java.io.Serializable {
    private static final long serialVersionUID = -5539158866486813921L;
    private String site;
    private int size;
    private String iso_3166_1;
    private String name;
    private String id;
    private String type;
    private String iso_639_1;
    private String key;

    public TrailersClassResults(String site, int size, String iso_3166_1, String name, String id, String type, String iso_639_1, String key) {
        this.site = site;
        this.size = size;
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
        this.id = id;
        this.type = type;
        this.iso_639_1 = iso_639_1;
        this.key = key;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIso_3166_1() {
        return this.iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIso_639_1() {
        return this.iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
