package com.cavepass.popularmoviesstage1;

import com.google.gson.annotations.SerializedName;

/*



results
 0
 id	"51910979760ee320eb020fc2"
 author	"Andres Gomez"
 content	"Interesting film with an exceptional cast, fantastic performances and characterizations. The story, though, is a bit difficult to follow and, in the end, seems to not have a real point."
 url	"https://www.themoviedb.org/review/51910979760ee320eb020fc2"


 */

import java.io.Serializable;

/**
 * Created by Ajay on 17-11-2017.
 */

@SuppressWarnings("serial")
public class reviews implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("author")
    String author;

    @SerializedName("content")
    String content;

    @SerializedName("url")
    String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    reviews(String id, String author, String content, String url){


        this.id = id;
        this.author = author;
        this.content =content;
        this.url =url;

    }



}
