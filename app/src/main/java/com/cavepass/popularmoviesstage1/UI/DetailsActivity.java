package com.cavepass.popularmoviesstage1.UI;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.cavepass.popularmoviesstage1.API.ApiInterface;
import com.cavepass.popularmoviesstage1.API.ApiReq;
import com.cavepass.popularmoviesstage1.Adapters.ReviewsAdapter;
import com.cavepass.popularmoviesstage1.Adapters.TrailersAdapter;
import com.cavepass.popularmoviesstage1.CheckNetwork;
import com.cavepass.popularmoviesstage1.Data.DbContract;
import com.cavepass.popularmoviesstage1.ModelClasses.MovieDetails;
import com.cavepass.popularmoviesstage1.ModelClasses.Reviews;
import com.cavepass.popularmoviesstage1.ModelClasses.ReviewsResults;
import com.cavepass.popularmoviesstage1.ModelClasses.TrailersClass;
import com.cavepass.popularmoviesstage1.ModelClasses.TrailersClassResults;
import com.cavepass.popularmoviesstage1.Data.PmDBHelper;
import com.cavepass.popularmoviesstage1.R;

import java.util.ArrayList;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsActivity extends AppCompatActivity {


    boolean favorite = false;
    boolean fromFavorite = false;


    ArrayList<TrailersClassResults> trailersArray;
    ArrayList<String> trailersStringArray;
    ArrayList<ReviewsResults> reviewsArray;
    String ID;


    MovieDetails movieObject;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);





        fromFavorite = getIntent().getBooleanExtra("fromFavorite", false);
        ID = getIntent().getStringExtra("movie_id");


        if (fromFavorite) {


            Uri uri = DbContract.FavouriteMovieDetails.CONTENT_URI;
            uri = uri.buildUpon().appendPath(ID).build();


            Cursor c = getContentResolver().query(uri, null, null, null, null);

            // void setValues(String title,String overview,String backdrop,String releaseDate, String popularity){


            c.moveToFirst();

            String title = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.TITLE));
            String overview = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.OVERVIEW));
            String backdrop = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.BACKDROP_PATH));
            String releaseDate = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.RELEASE_DATE));
            String popularity = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.RATING));
            String movie_id = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.MOVIE_ID));
            String poster = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.POSTER_ID));
            String trailerString = c.getString(c.getColumnIndex(DbContract.FavouriteMovieDetails.TRAILERS));

            setValues(title, overview, backdrop, releaseDate, popularity, trailerString);

            favButton(overview, title, movie_id, releaseDate, backdrop, poster, popularity);

            c.close();


        } else {

            movieObject = (MovieDetails) getIntent().getSerializableExtra("movieObject");

            ApiInterface apiService = ApiReq.getClient().create(ApiInterface.class);

            Call<Reviews> call = apiService.getReviews(movieObject.getId().toString(), getString(R.string.API));

            Call<TrailersClass> call2 = apiService.getTrailers(movieObject.getId().toString(), getString(R.string.API));

            call.enqueue(new Callback<Reviews>() {

                @Override
                public void onResponse(Call<Reviews> call, Response<Reviews> reviewResponse) {

                    reviewsArray = new ArrayList<>(Arrays.asList(reviewResponse.body().getResults()));

                    RecyclerView reviewsRecyclerView = findViewById(R.id.reviewRecyclerView);

                    reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));

                    reviewsRecyclerView.setAdapter(new ReviewsAdapter(DetailsActivity.this, reviewsArray));

                }

                @Override
                public void onFailure(Call<Reviews> call, Throwable t) {

                    // Log.e("ERROR", "FAILED");

                }
            });

            call2.enqueue(new Callback<TrailersClass>() {


                @Override
                public void onResponse(Call<TrailersClass> call, Response<TrailersClass> response) {

                    trailersArray = new ArrayList<>(Arrays.asList(response.body().getResults()));

                    trailersStringArray = toArrayString(trailersArray);

                    RecyclerView trailersRecyclerView = findViewById(R.id.trailersHorizontalRecyclerView);

                    trailersRecyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    trailersRecyclerView.setAdapter(new TrailersAdapter(DetailsActivity.this, trailersStringArray));

                }

                @Override
                public void onFailure(Call<TrailersClass> call, Throwable t) {

                }


            });

            favButton(movieObject.getOverview(), movieObject.getTitle(), movieObject.getId().toString(),
                    movieObject.getReleaseDate(), movieObject.getBackdropPath(), movieObject.getPosterPath(), movieObject.getPopularity().toString());


            try {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } catch (Exception e) {

            }

            if (CheckNetwork.isInternetAvailable(this)) {


                setValues(movieObject.getTitle(), movieObject.getOverview(), movieObject.getBackdropPath(), movieObject.getReleaseDate(), movieObject.getPopularity().toString(), null);


            } else {
                Toast.makeText(this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
            }
        }

    }

    void setValues(String mTitle, String mOverview, String mBackdrop, String mReleaseDate, String mPopularity, String trailerString) {


        TextView rating = (TextView) findViewById(R.id.ratingsText);
        TextView release = (TextView) findViewById(R.id.releaseDate);
        TextView overview = (TextView) findViewById(R.id.description);
        ImageView icon = (ImageView) findViewById(R.id.backdropImage);

        Glide.with(this).load(getString(R.string.glide) + mBackdrop).into(icon);
        //title.setText(title_);
        release.setText(mReleaseDate);
        overview.setText(mOverview);
        rating.setText(mPopularity + "/10");

        if (fromFavorite) {

            ArrayList<String> ar = fromString(trailerString);

//            Log.e("TRAILER",ar.get(0));

            RecyclerView trailersRecyclerView = findViewById(R.id.trailersHorizontalRecyclerView);
            trailersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            trailersRecyclerView.setAdapter(new TrailersAdapter(this, ar));

        }


    }


    void favButton(String mOverview, String mTitle, String mMovie_id, String mRelease_date, String mBackdrop, String mPoster, String mRating) {


        final String temp = mTitle;
        final String overview = mOverview;
        final String title = mTitle;
        final String movie_id = mMovie_id;
        final String release_date = mRelease_date;
        final String backdrop = mBackdrop;
        final String poster = mPoster;
        final String rating = mRating;


        final SharedPreferences sharedpreferences = getSharedPreferences("new3", Context.MODE_PRIVATE);


        if (sharedpreferences != null) {

            favorite = sharedpreferences.getBoolean(temp, false);

        }


        final ImageView fav = (ImageView) findViewById(R.id.fav_button);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        collapsingToolbarLayout.setTitle(temp);


        if (favorite) {

            fav.setImageResource(R.drawable.fav_on);

        } else if (!favorite) {

            fav.setImageResource(R.drawable.fav_off);

        }

        final PmDBHelper pm = new PmDBHelper(this);


        final SharedPreferences.Editor editor = sharedpreferences.edit();

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (favorite == false) {


                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DbContract.FavouriteMovieDetails.OVERVIEW, overview);
                    contentValues.put(DbContract.FavouriteMovieDetails.TITLE, title);
                    contentValues.put(DbContract.FavouriteMovieDetails.MOVIE_ID, movie_id);
                    contentValues.put(DbContract.FavouriteMovieDetails.RELEASE_DATE, release_date);
                    contentValues.put(DbContract.FavouriteMovieDetails.RATING, rating);
                    contentValues.put(DbContract.FavouriteMovieDetails.BACKDROP_PATH, backdrop);
                    contentValues.put(DbContract.FavouriteMovieDetails.TRAILERS, trailersStringArray.toString());
                    contentValues.put(DbContract.FavouriteMovieDetails.POSTER_ID, poster);


                    Uri uri = getContentResolver().insert(DbContract.FavouriteMovieDetails.CONTENT_URI, contentValues);

                    ContentValues reviewsContent = new ContentValues();





                    for(int k=0;k<reviewsArray.size();k++){

                        contentValues.put(DbContract.FavouriteMovieDetails.TITLE,title);
                        contentValues.put(DbContract.FavouriteMovieDetails.AUTHOR,reviewsArray.get(k).getAuthor());
                        contentValues.put(DbContract.FavouriteMovieDetails.REVIEW,reviewsArray.get(k).getContent());

                        Uri uri2 = getContentResolver().insert(DbContract.FavouriteMovieDetails.REVIEW_CONTENT, reviewsContent);


                    }




                    Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

                    favorite = true;

                    editor.putBoolean(temp, true);


                    if (uri != null) {
                        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
                    }


                    editor.commit();


                    fav.setImageResource(R.drawable.fav_on);

                    Toast.makeText(DetailsActivity.this, pm.databasetoString() + getString(R.string.added), Toast.LENGTH_SHORT).show();


                } else if (favorite) {

                    favorite = false;

                    editor.putBoolean(temp, false);


                    // Toast.makeText(DetailsActivity.this,"ID IS "+id,Toast.LENGTH_SHORT).show();

                    Uri uri = DbContract.FavouriteMovieDetails.CONTENT_URI;
                    uri = uri.buildUpon().appendPath(movie_id).build();

                    //   Log.e("URI IS ", "" + uri.toString());

                    editor.commit();

                    fav.setImageResource(R.drawable.fav_off);
                    getContentResolver().delete(uri, null, null);


                    Toast.makeText(DetailsActivity.this, pm.databasetoString() + getString(R.string.removed), Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    ArrayList<String> toArrayString(ArrayList<TrailersClassResults> ar) {


        ArrayList<String> array = new ArrayList<>();


        for (int i = 0; i < ar.size(); i++) {


            array.add(ar.get(i).getKey());

        }

        return array;
    }

    ArrayList<String> fromString(String str) {

        ArrayList<String> array = new ArrayList<>();

        String temp = "";

        for (int i = 0; i < str.length(); i++) {


            if (str.charAt(i) != ',' && str.charAt(i) != ' ') {

                temp = temp + str.charAt(i);

            }

            if (str.charAt(i) == ',') {

                array.add(temp.substring(1, temp.length()));

                // Log.e("THE TRAILER",temp.substring(1,temp.length())+"");

                temp = " ";
            }


        }

        array.add(temp.substring(1, temp.length() - 1));


        return array;


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


