package com.cavepass.popularmoviesstage1.API;

/**
 * Created by Ajay on 16-11-2017.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiReq {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}