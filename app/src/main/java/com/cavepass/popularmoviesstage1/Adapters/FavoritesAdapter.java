package com.cavepass.popularmoviesstage1.Adapters;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cavepass.popularmoviesstage1.UI.DetailsActivity;
import com.cavepass.popularmoviesstage1.R;


import java.util.ArrayList;
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MovieViewHolder> {

    private ArrayList<String> movies;
    private Context context;

    private ArrayList<String> moviePosters = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();

    public class MovieViewHolder extends RecyclerView.ViewHolder {



        ImageView imageView;

        public MovieViewHolder(View v) {
            super(v);



            Log.e("AT ", "MovieHolder Constructor 002");
            imageView =(ImageView) v.findViewById(R.id.image);


            v.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Log.e("ID is",""+getLayoutPosition());

                    Intent i = new Intent(context,DetailsActivity.class);

                    i.putExtra("fromFavorite",true);
                    i.putExtra("movie_id",ids.get(getLayoutPosition()));

                    context.startActivity(i);



                   // Toast.makeText(view.getContext(),movies.get((getLayoutPosition())).getTitle()+" is Clicked",Toast.LENGTH_SHORT).show();


                }
            });





        }
    }

    public FavoritesAdapter(ArrayList<String> ids, ArrayList<String> moviePosters,Context context) {

        Log.e("AT ", "Adapter" );

        this.context = context;
        this.moviePosters=moviePosters;
        this.ids = ids;

    }

    @Override
    public FavoritesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("AT ", "onCreate ViewHolder");


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);


        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {



        Log.e("AT ", "onBindViewHolder");

        Glide.with(context).load("https://image.tmdb.org/t/p/w185/" +moviePosters.get(position) ).diskCacheStrategy( DiskCacheStrategy.RESULT).dontAnimate().into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        Log.e("AT ", "getCount");

        return moviePosters.size();
    }
}