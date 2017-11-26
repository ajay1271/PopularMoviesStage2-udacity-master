package com.cavepass.popularmoviesstage1.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ajay on 13-11-2017.
 */

public class DbContract {

    public static final class FavouriteMovieDetails implements BaseColumns {

        public static final String TABLE_NAME = "favMoviesList11";
        public static final String _ID = "_id";
        public static final String MOVIE_ID = "movie_id";
        public static final String TITLE = "title_DB";
        public static final String POSTER_ID = "poster_DB";
        public static final String BACKDROP_PATH = "backdropPath_DB";
        public static final String RELEASE_DATE = "release_DB";
        public static final String RATING = "rating_DB";
        public static final String OVERVIEW = "overview_DB";
        public static final String TRAILERS = "trailers_db";
        public static final String AUTHOR = "author_db";
        public static final String REVIEW = "review_db";
        public static final String REVIEW_TABLE = "review";
        public static final String AUTHORITY = "com.cavepass.popularmoviesstage1";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ AUTHORITY );
        public static final String PATH_TASKS = "favMoviesList11";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();
        public static final Uri REVIEW_CONTENT= BASE_CONTENT_URI.buildUpon().appendPath(REVIEW).build();
        public static final Uri REVIEW_CONTENT_URI = REVIEW_CONTENT.buildUpon().appendPath(REVIEW).build();




    }
}