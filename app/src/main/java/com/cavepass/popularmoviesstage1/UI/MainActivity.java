package com.cavepass.popularmoviesstage1.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cavepass.popularmoviesstage1.API.ApiInterface;
import com.cavepass.popularmoviesstage1.API.ApiReq;
import com.cavepass.popularmoviesstage1.CheckNetwork;
import com.cavepass.popularmoviesstage1.ModelClasses.ApiResponce;
import com.cavepass.popularmoviesstage1.ModelClasses.MovieDetails;
import com.cavepass.popularmoviesstage1.Adapters.MoviesAdapter;
import com.cavepass.popularmoviesstage1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    String query = "";
    ArrayList<MovieDetails> movieDetails;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerGrid;
    MoviesAdapter moviesAdapter;
    boolean table_created = false;
    ArrayList<String> mainPosters = new ArrayList<>();
    boolean fromFavorite = false;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SharedPreferences sharedpreferences = getSharedPreferences("main", Context.MODE_PRIVATE);


        final SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("table_created", true);
        editor.commit();
        Log.e("onCreate", " At onCreate");


        if (getIntent().getStringExtra("query") != null) {

            query = getIntent().getStringExtra("query");

            fromFavorite = getIntent().getBooleanExtra("Favorites", false);

            setTitle(query);

        } else {

            query = getString(R.string.popularity);

            setTitle(query);

        }


        if (CheckNetwork.isInternetAvailable(this)) {


            Log.e("after checking network ", "after checking network");


            /*  FETCHING JSON USING RETROFIT */


            ApiInterface apiService = ApiReq.getClient().create(ApiInterface.class);

            Call<ApiResponce> call;

            if (query.equals(getString(R.string.toprated))) {

                call = apiService.getTopRatedMovies(getString(R.string.API));

            } else {

                call = apiService.getPopularMovies(getString(R.string.API));


            }


            call.enqueue(new Callback<ApiResponce>() {
                @Override
                public void onResponse(Call<ApiResponce> call, Response<ApiResponce> response) {


                    movieDetails = new ArrayList<>(response.body().getResults());

                    Log.e("SUCCESS", " Success with movieDetails size " + movieDetails.size());


                    recyclerGrid = (RecyclerView) findViewById(R.id.recyclerGrid);

                    gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);


                    moviesAdapter = new MoviesAdapter(movieDetails, MainActivity.this);


                    recyclerGrid.setLayoutManager(gridLayoutManager);


                    recyclerGrid.setAdapter(moviesAdapter);


                    // Log.d(TAG, "Number of movies received: " + movieDetails.size());
                }

                @Override
                public void onFailure(Call<ApiResponce> call, Throwable t) {

                    Log.e("ERROR", "FAILED");
                    // Log error here since request failed
                    // Log.e(TAG, t.toString());
                }
            });


        } else {
            Toast.makeText(this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
        }
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.action_bar_spinner, menu);

            MenuItem item = menu.findItem(R.id.spinner);
            final Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
            spinner.setSelection(0);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_list_item_array, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                    String items = spinner.getSelectedItem().toString();


                    if (items.equals(getString(R.string.toprated))) {

                        Intent j = new Intent(MainActivity.this, MainActivity.class);

                        j.putExtra("query", getString(R.string.toprated));

                        spinner.setSelection(0);

                        startActivity(j);


                    }

                    if (items.equals(getString(R.string.popularity))) {

                        Intent j = new Intent(MainActivity.this, MainActivity.class);

                        j.putExtra("query", getString(R.string.popularity));

                        spinner.setSelection(0);

                        startActivity(j);


                    }

                    if (items.equals(getString(R.string.favorites))) {

                        Intent k = new Intent(MainActivity.this, FavoritesActivity.class);


                        spinner.setSelection(0);

                        startActivity(k);


                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {

                }

            });


            return true;
        }

    }