package com.cavepass.popularmoviesstage1;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


public class Favorites extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*

        setContentView(R.layout.favorite_details);

        setTitle(R.string.favoriteMoviesTitle);


        if (CheckNetwork.isInternetAvailable(this)) {

            RecyclerView favRecycler;

            // Set local attributes to corresponding views
            favRecycler = (RecyclerView) this.findViewById(R.id.r_v);

            // Set layout for the RecyclerView, because it's a list we are using the linear layout
            favRecycler.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));

            PmDBHelper pm = new PmDBHelper(this);


            Cursor mCursor = pm.getCursor();


            FavoritesAdapter favoritesAdapter = new FavoritesAdapter(Favorites.this, mCursor);


            // Link the adapter to the RecyclerView
            favRecycler.setAdapter(favoritesAdapter);


        } else {
            Toast.makeText(this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_spinner, menu);

        MenuItem item = menu.findItem(R.id.spinner);

        final Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String items = spinner.getSelectedItem().toString();


                if (items.equals(getString(R.string.toprated))) {

                    Intent j = new Intent(Favorites.this, MainActivity.class);

                    j.putExtra("queryUrl", getString(R.string.MovieDB_URL2));

                    spinner.setSelection(0);

                    startActivity(j);


                }

                if (items.equals(getString(R.string.popularity))) {

                    Intent j = new Intent(Favorites.this, MainActivity.class);

                    j.putExtra("queryUrl", getString(R.string.MovieDB_URL));


                    spinner.setSelection(0);

                    startActivity(j);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });


        return true;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 190);
        return noOfColumns;

        */

    }

}