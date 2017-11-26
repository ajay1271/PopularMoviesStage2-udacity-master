package com.cavepass.popularmoviesstage1.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ajay on 26-11-2017.
 */

public class ReviewsDBHelper extends SQLiteOpenHelper {

    private String TABLE_NAME="" ;



    public ReviewsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, PmDBHelper.DATABASE__NAME, factory, version);

        TABLE_NAME = name ;

        SQLiteDatabase db = getWritableDatabase();



    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TABLE = "CREATE TABLE "+  TABLE_NAME+ "(" +
                "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "author_name" + " TEXT, " +
                "review" + " TEXT, " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
