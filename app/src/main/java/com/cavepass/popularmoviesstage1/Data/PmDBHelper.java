package com.cavepass.popularmoviesstage1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cavepass.popularmoviesstage1.ModelClasses.MovieDetails;

/**
 * Created by Ajay on 13-11-2017.
 */

public class PmDBHelper extends SQLiteOpenHelper {

    public static String DATABASE__NAME = "likes_Data12.db";
    private static int DATABASE__VERSION = 1;




    public PmDBHelper(Context context) {
        super(context, DATABASE__NAME, null, DATABASE__VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TABLE = "CREATE TABLE "+

                DbContract.FavouriteMovieDetails.TABLE_NAME+ "(" +
                DbContract.FavouriteMovieDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.FavouriteMovieDetails.MOVIE_ID + " TEXT, " +
                DbContract.FavouriteMovieDetails.TITLE + " TEXT, " +
                DbContract.FavouriteMovieDetails.POSTER_ID + " TEXT, " +
                DbContract.FavouriteMovieDetails.BACKDROP_PATH + " TEXT, " +
                DbContract.FavouriteMovieDetails.RELEASE_DATE + " TEXT, " +
                DbContract.FavouriteMovieDetails.TRAILERS + " TEXT, " +
                DbContract.FavouriteMovieDetails.RATING + " TEXT, " +
                DbContract.FavouriteMovieDetails.OVERVIEW + " TEXT " +
                "); ";


        //CREATE TABLE review_db ( String _ID, String movie_title, string Author, String review );


        final String SQL_CREATE_TABLE_REVIEWS = "CREATE TABLE "+

                DbContract.FavouriteMovieDetails.REVIEW+ "(" +
                DbContract.FavouriteMovieDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.FavouriteMovieDetails.TITLE + " TEXT, " +
                DbContract.FavouriteMovieDetails.AUTHOR + " TEXT, " +
               "review" + " TEXT " +

                ") ";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_REVIEWS);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(" DROP TABLE '"+ DbContract.FavouriteMovieDetails.TABLE_NAME+"'");

        onCreate(sqLiteDatabase);
    }




    public String databasetoString(){

        String dbString="";

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+ DbContract.FavouriteMovieDetails.TABLE_NAME;

        Cursor c = db.rawQuery(query,null);

        c.moveToLast();

        if(!c.isAfterLast()){

            if(c.getString(c.getColumnIndex("title_DB"))!=null){

                dbString+=c.getString(c.getColumnIndex("title_DB"));



            }

        }

        return dbString;

    }

    public Cursor getData(String id){

        SQLiteDatabase db = getWritableDatabase();

        String s = "SELECT * FROM "+ DbContract.FavouriteMovieDetails.TABLE_NAME+" WHERE "+ DbContract.FavouriteMovieDetails._ID+" = '"+ id+"' ";


        return db.rawQuery(s,null);


    }




}
