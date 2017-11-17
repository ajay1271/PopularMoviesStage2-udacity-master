package com.cavepass.popularmoviesstage1;

import android.provider.BaseColumns;

/**
 * Created by Ajay on 13-11-2017.
 */

public class DbContract {

    public static final class FavouriteMovieDetails implements BaseColumns {

        public static final String TABLE_NAME = "favMoviesList1";
        public static final String TITLE = "title_DB";
        public static final String POSTER_ID = "poster_DB";
        public static final String RELEASE_DATE = "release_DB";
        public static final String RATING = "rating_DB";
        public static final String OVERVIEW = "overview_DB";
    }
}