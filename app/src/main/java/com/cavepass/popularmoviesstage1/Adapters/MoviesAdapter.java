package com.cavepass.popularmoviesstage1.Adapters;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cavepass.popularmoviesstage1.ModelClasses.MovieDetails;
import com.cavepass.popularmoviesstage1.R;
import com.cavepass.popularmoviesstage1.UI.DetailsActivity;


import java.util.ArrayList;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<MovieDetails> movies;
    private Context context;


    public class MovieViewHolder extends RecyclerView.ViewHolder {

       ImageView imageView;

        public MovieViewHolder(View v) {
            super(v);

            imageView =(ImageView) v.findViewById(R.id.image);


            v.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context,DetailsActivity.class);

                    i.putExtra("movieObject",movies.get((getLayoutPosition())));
                    i.putExtra("id",getLayoutPosition());

                    context.startActivity(i);



                    Toast.makeText(view.getContext(),movies.get((getLayoutPosition())).getTitle()+" is Clicked",Toast.LENGTH_SHORT).show();


                }
            });





        }
    }

    public MoviesAdapter(ArrayList<MovieDetails> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);


        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w185/" +movies.get(position).getPosterPath() ).diskCacheStrategy( DiskCacheStrategy.RESULT).dontAnimate().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}