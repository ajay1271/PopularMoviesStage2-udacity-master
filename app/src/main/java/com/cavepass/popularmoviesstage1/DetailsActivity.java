package com.cavepass.popularmoviesstage1;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsActivity extends AppCompatActivity {

    RecyclerView horizontalRecyclerView;

    boolean favorite = false;

    private SQLiteDatabase mDb;

    MovieDetails movieObject;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

      //  Log.e("getID", "HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" +movieObject.getId().toString());



        //Reviews






        movieObject = (MovieDetails) getIntent().getSerializableExtra("movieObject");

        ApiInterface apiService =  ApiReq.getClient().create(ApiInterface.class);




        Call<ReviewsClassJson> call = apiService.getReviews(movieObject.getId().toString(),getString(R.string.API));

        call.enqueue(new Callback<ReviewsClassJson>() {
            @Override
            public void onResponse(Call<ReviewsClassJson> call, Response<ReviewsClassJson> reviewResponse) {

                Log.e("reviews",reviewResponse.body().getId()+"");


              ArrayList<ReviewsClassJsonResults> reviews = reviewResponse.body().getResults();

//                Log.e("SUCCESS"," Success with movieDetails size "+reviews.size());



               RecyclerView reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewRecyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailsActivity.this);



                ReviewsAdapter reviewsAdapter = new ReviewsAdapter(DetailsActivity.this,reviews);



                reviewsRecyclerView.setLayoutManager(linearLayoutManager);


                reviewsRecyclerView.setAdapter(reviewsAdapter);



                // Log.d(TAG, "Number of movies received: " + movieDetails.size());
            }




            @Override
            public void onFailure(Call<ReviewsClassJson> call, Throwable t) {

                Log.e("ERROR","FAILED");
                // Log error here since request failed
                // Log.e(TAG, t.toString());
            }
        });



        RecyclerView trailersRecyclerView = (RecyclerView) findViewById(R.id.trailersHorizontalRecyclerView);

        LinearLayoutManager trailersLayout = new LinearLayoutManager(this);

        trailersRecyclerView.setLayoutManager(trailersLayout);













        Call<TrailersClass> t_call = apiService.getTrailers(movieObject.getId().toString(),getString(R.string.API));

        t_call.enqueue(new Callback<TrailersClass>() {
            @Override
            public void onResponse(Call<TrailersClass> t_call, Response<TrailersClass> trailersResponce) {

                Log.e("reviews",trailersResponce.body().getId()+"");


                ArrayList<TrailersClassResults> trailersArray = trailersResponce.body().getResults();

//                Log.e("SUCCESS"," Success with movieDetails size "+reviews.size());



                RecyclerView reviewsRecyclerView = (RecyclerView) findViewById(R.id.trailersHorizontalRecyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailsActivity.this);



                TrailersAdapter trailersAdapter = new TrailersAdapter(DetailsActivity.this,trailersArray);



                reviewsRecyclerView.setLayoutManager(linearLayoutManager);


                reviewsRecyclerView.setAdapter(trailersAdapter);



                // Log.d(TAG, "Number of movies received: " + movieDetails.size());
            }




            @Override
            public void onFailure(Call<TrailersClass> call, Throwable t) {

                Log.e("ERROR","FAILED");
                // Log error here since request failed
                // Log.e(TAG, t.toString());
            }
        });





















        final String temp = movieObject.getTitle();


        final SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE);


        if (sharedpreferences != null) {

            favorite = sharedpreferences.getBoolean(temp, false);

        }

        PmDBHelper dbHelper = new PmDBHelper(this);

        mDb = dbHelper.getWritableDatabase();

        setTitle(movieObject.getTitle());

        setContentView(R.layout.activity_details);

        final ImageView fav = (ImageView) findViewById(R.id.fav_button);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        collapsingToolbarLayout.setTitle(movieObject.getTitle());


        if (favorite) {

            fav.setImageResource(R.drawable.fav_on);

        }

        final PmDBHelper pm = new PmDBHelper(this);


        final SharedPreferences.Editor editor = sharedpreferences.edit();

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (favorite == false) {


                    favorite = true;

                    editor.putBoolean(temp, true);

                    editor.commit();

                    pm.addProduct(movieObject);

                    fav.setImageResource(R.drawable.fav_on);

                    Toast.makeText(DetailsActivity.this, pm.databasetoString() + getString(R.string.added), Toast.LENGTH_SHORT).show();


                } else if (favorite) {

                    favorite = false;

                    editor.putBoolean(temp, false);

                    editor.commit();


                    fav.setImageResource(R.drawable.fav_off);

                    Toast.makeText(DetailsActivity.this, pm.databasetoString() + getString(R.string.removed), Toast.LENGTH_SHORT).show();

                    pm.deleteProduct(movieObject);


                }
            }
        });


        // horizontalRecyclerView = (RecyclerView) findViewById(R.id.horizontalRecyclerView);

        /*



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {


                String url = getString(R.string.youtube) + trailerArray.get(position);
                Intent youtube = new Intent(Intent.ACTION_VIEW);
                youtube.setData(Uri.parse(url));
                startActivity(youtube);


            }
        });

        */

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {

        }

        if (CheckNetwork.isInternetAvailable(this)) {

            TextView rating = (TextView) findViewById(R.id.ratingsText);
            // TextView title = (TextView) findViewById(R.id.movieTitle);
            TextView release = (TextView) findViewById(R.id.releaseDate);
            TextView overview = (TextView) findViewById(R.id.description);
            ImageView icon = (ImageView) findViewById(R.id.backdropImage);


            String title_ = String.valueOf(movieObject.getTitle());
            String overview_ = String.valueOf(movieObject.getOverview());
            String image1 = String.valueOf(movieObject.getBackdropPath());
            String release_ = String.valueOf(movieObject.getReleaseDate());
            String rating_ = String.valueOf(movieObject.getPopularity());


            Glide.with(this).load(getString(R.string.glide) + image1).into(icon);
            //title.setText(title_);
            release.setText(release_);
            overview.setText(overview_);
            rating.setText(rating_ + "/10");


        } else {
            Toast.makeText(this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
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
    }


}


