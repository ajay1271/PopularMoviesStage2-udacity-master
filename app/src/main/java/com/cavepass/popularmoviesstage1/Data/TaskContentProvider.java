package com.cavepass.popularmoviesstage1.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;


/**
 * Created by Ajay on 17-11-2017.
 */


public class TaskContentProvider extends ContentProvider {

    PmDBHelper TaskDbHelper;
    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {

        Context context = getContext();

         TaskDbHelper = new PmDBHelper(context);



        return true;
    }

    public static UriMatcher buildUriMatcher() {


        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DbContract.FavouriteMovieDetails.AUTHORITY, DbContract.FavouriteMovieDetails.PATH_TASKS, TASKS);
        uriMatcher.addURI(DbContract.FavouriteMovieDetails.AUTHORITY, DbContract.FavouriteMovieDetails.PATH_TASKS + "/#", TASK_WITH_ID);

        return uriMatcher;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        final SQLiteDatabase db = TaskDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case TASKS:

                String table_name = uri.getPathSegments().get(0);
                long id = db.insert(table_name, null, values);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(DbContract.FavouriteMovieDetails.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        getContext().getContentResolver().notifyChange(uri, null);


        return returnUri;
    }


    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {


        final SQLiteDatabase db = TaskDbHelper.getReadableDatabase();


        int match = sUriMatcher.match(uri);

        Cursor retCursor;


        switch (match) {

            case TASKS:
                retCursor =  db.query(DbContract.FavouriteMovieDetails.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case TASK_WITH_ID:

                String movie_id = uri.getPathSegments().get(1);

                retCursor =  db.query(DbContract.FavouriteMovieDetails.TABLE_NAME,
                        projection,
                        DbContract.FavouriteMovieDetails.MOVIE_ID+"="+movie_id,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        retCursor.setNotificationUri(getContext().getContentResolver(), uri);


        return retCursor;
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        int tasksDeleted;
        final SQLiteDatabase mDb = TaskDbHelper.getWritableDatabase();     // array[]{id};


        switch (match) {



            case TASK_WITH_ID:



                String movie_id = uri.getPathSegments().get(1);

                Log.e("It is ",movie_id);

                tasksDeleted = mDb.delete(DbContract.FavouriteMovieDetails.TABLE_NAME, DbContract.FavouriteMovieDetails.MOVIE_ID+"="+movie_id, null);
                break;
            default:

                throw new UnsupportedOperationException("Unknown uri is: " + uri);
        }


        if (tasksDeleted != 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }


        return tasksDeleted;


    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public String getType(@NonNull Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

}
