package com.cavepass.popularmoviesstage1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ajay on 13-11-2017.
 */

public class PmDBHelper extends SQLiteOpenHelper {

    private static String DATABASE__NAME = "likes_Data.db";
    private static int DATABASE__VERSION = 1;




    public PmDBHelper(Context context) {
        super(context, DATABASE__NAME, null, DATABASE__VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TABLE = "CREATE TABLE "+

                DbContract.FavouriteMovieDetails.TABLE_NAME+ "(" +
                DbContract.FavouriteMovieDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.FavouriteMovieDetails.TITLE + " TEXT, " +
                DbContract.FavouriteMovieDetails.POSTER_ID + " TEXT, " +
                DbContract.FavouriteMovieDetails.RELEASE_DATE + " TEXT, " +
                DbContract.FavouriteMovieDetails.RATING + " TEXT, " +
                DbContract.FavouriteMovieDetails.OVERVIEW + " TEXT " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(" DROP TABLE '"+ DbContract.FavouriteMovieDetails.TABLE_NAME+"'");

        onCreate(sqLiteDatabase);
    }

    public void addProduct(MovieDetails movieDetails){


        ContentValues cv = new ContentValues();

        cv.put(DbContract.FavouriteMovieDetails.TITLE,movieDetails.getTitle());
        cv.put(DbContract.FavouriteMovieDetails.OVERVIEW,movieDetails.getOverview());
        cv.put(DbContract.FavouriteMovieDetails.POSTER_ID,movieDetails.getPosterPath());
        cv.put(DbContract.FavouriteMovieDetails.RELEASE_DATE,movieDetails.getReleaseDate());
        cv.put(DbContract.FavouriteMovieDetails.RATING,movieDetails.getVoteAverage());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(DbContract.FavouriteMovieDetails.TABLE_NAME,null,cv);

        db.close();


    }

    public void deleteProduct(MovieDetails movieDetails){


        SQLiteDatabase db = getWritableDatabase();

     // onUpgrade(db,1,2);

       String s = "DELETE FROM "+DbContract.FavouriteMovieDetails.TABLE_NAME+" WHERE "+DbContract.FavouriteMovieDetails.TITLE+" = '"+movieDetails.getTitle()+"';";


       db.execSQL(s);

    }

    public Cursor getCursor(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+DbContract.FavouriteMovieDetails.TABLE_NAME;

        Cursor c = db.rawQuery(query,null);

        return  c;



    }

    public String databasetoString(){

        String dbString="";

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+DbContract.FavouriteMovieDetails.TABLE_NAME;

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

        String s = "SELECT * FROM "+DbContract.FavouriteMovieDetails.TABLE_NAME+" WHERE "+DbContract.FavouriteMovieDetails._ID+" = '"+ id+"' ";


        return db.rawQuery(s,null);


    }




}
