package com.cavepass.popularmoviesstage1;

public class ReviewsClassJsonResults implements java.io.Serializable {
    private static final long serialVersionUID = 795968162000005251L;
    private String author;
    private String id;
    private String content;
    private String url;

    public ReviewsClassJsonResults(String author, String id, String content, String url) {
        this.author = author;
        this.id = id;
        this.content = content;
        this.url = url;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
