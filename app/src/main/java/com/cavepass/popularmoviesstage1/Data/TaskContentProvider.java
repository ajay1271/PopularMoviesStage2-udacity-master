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


    /* onCreate() is where you should initialize anything you’ll need to setup
    your underlying data source.
    In this case, you’re working with a SQLite database, so you’ll need to
    initialize a DbHelper to gain access to it.
     */
    @Override
    public boolean onCreate() {


        // TODO (2) Complete onCreate() and initialize a TaskDbhelper on startup
        // [Hint] Declare the DbHelper as a global variable

        Context context = getContext();

         TaskDbHelper = new PmDBHelper(context);



        return true;
    }

    public static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(DbContract.FavouriteMovieDetails.AUTHORITY, DbContract.FavouriteMovieDetails.PATH_TASKS, TASKS);
        uriMatcher.addURI(DbContract.FavouriteMovieDetails.AUTHORITY, DbContract.FavouriteMovieDetails.PATH_TASKS + "/#", TASK_WITH_ID);

        return uriMatcher;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        final SQLiteDatabase db = TaskDbHelper.getWritableDatabase();

        // COMPLETED (2) Write URI matching code to identify the match for the tasks directory
        int match = sUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case TASKS:

                String table_name = uri.getPathSegments().get(0);

                // COMPLETED (3) Insert new values into the database
                // Inserting values into tasks table
                long id = db.insert(table_name, null, values);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(DbContract.FavouriteMovieDetails.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            // COMPLETED (4) Set the value for the returnedUri and write the default case for unknown URI's
            // Default case throws an UnsupportedOperationException
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // COMPLETED (5) Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;
    }


    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = TaskDbHelper.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        int match = sUriMatcher.match(uri);

        Cursor retCursor;

        // Query for the tasks directory and write a default case
        switch (match) {
            // Query for the tasks directory
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
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the desired Cursor
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
                // Use selections/selectionArgs to filter for this ID
                tasksDeleted = mDb.delete(DbContract.FavouriteMovieDetails.TABLE_NAME, DbContract.FavouriteMovieDetails.MOVIE_ID+"="+movie_id, null);
                break;
            default:

                throw new UnsupportedOperationException("Unknown uri is: " + uri);
        }

        // COMPLETED (3) Notify the resolver of a change and return the number of items deleted
        if (tasksDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of tasks deleted
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
