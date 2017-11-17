package com.cavepass.popularmoviesstage1;


import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class DetailsofFavoriteMovies extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        /*



        // Log.e("ENTERED","001");

        setContentView(R.layout.fav_details);


        Bundle bundle = getIntent().getExtras();


        String id = String.valueOf(bundle.get("id"));


        PmDBHelper pm = new PmDBHelper(this);

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Cursor c = pm.getData(id);


        TextView rating = (TextView) findViewById(R.id.rating2);
        TextView title = (TextView) findViewById(R.id.Title);

        TextView release = (TextView) findViewById(R.id.release_date2);
        TextView overview = (TextView) findViewById(R.id.overview2);
        ImageView icon = (ImageView) findViewById(R.id.full_image_view2);


        c.moveToFirst();


        setTitle("Favorites");

        title.setText(c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.TITLE)));


        String overview_ = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.OVERVIEW));


        String image1 = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.POSTER_ID));
        String release_ = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.RELEASE_DATE));
        String rating_ = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.RATING));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/" + image1).into(icon);


        release.setText(release_);

        rating.setText("IMDB: " + rating_ + "/10");


        overview.setText(overview_);


        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);




        }


        */

    }


}