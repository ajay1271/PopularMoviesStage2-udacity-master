package com.cavepass.popularmoviesstage1.API;

/**
 * Created by Ajay on 15-11-2017.
 */

import com.cavepass.popularmoviesstage1.ModelClasses.ApiResponce;
import com.cavepass.popularmoviesstage1.ModelClasses.Reviews;
import com.cavepass.popularmoviesstage1.ModelClasses.TrailersClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//http://api.themoviedb.org/3/movie/157336/videos?api_key=2bfc2845a8e711f212828a7f8d23d3a7&append_to_response=reviews

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<ApiResponce> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<ApiResponce> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<Reviews> getReviews(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailersClass> getTrailers(@Path("id") String id, @Query("api_key") String apiKey);
}