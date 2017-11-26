package com.cavepass.popularmoviesstage1.UI;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.cavepass.popularmoviesstage1.Adapters.FavoritesAdapter;
import com.cavepass.popularmoviesstage1.Data.DbContract;
import com.cavepass.popularmoviesstage1.R;

import java.util.ArrayList;

/**
 * Created by Ajay on 22-11-2017.
 */

public class FavoritesActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setValues();


    }

    @Override
    protected void onResume() {
        super.onResume();


        setValues();

    }

    void setValues(){



        ArrayList<String> moviePosters = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();

        setContentView(R.layout.activity_main);


        Uri uri = DbContract.FavouriteMovieDetails.CONTENT_URI;
        Cursor c = getContentResolver().query(uri, null, null, null, null);

        if(c.getCount()!=0) {
            c.moveToFirst();

            moviePosters.add(c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.POSTER_ID)));
            ids.add(c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.MOVIE_ID)));

            while (c.moveToNext()) {


                moviePosters.add(c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.POSTER_ID)));
                ids.add(c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.MOVIE_ID)));
                Log.e("POSTER", moviePosters.get(moviePosters.size() - 1));

            }

            c.close();


            RecyclerView recyclerView = findViewById(R.id.recyclerGrid);

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            recyclerView.setAdapter(new FavoritesAdapter(ids, moviePosters, this));
        }
        else{
            Toast.makeText(this,"No Favorote Movies Added",Toast.LENGTH_SHORT).show();
        }

    }

}
