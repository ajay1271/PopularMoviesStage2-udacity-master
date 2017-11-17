package com.cavepass.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private Cursor mCursor;
    private Context mContext;


    public FavoritesAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent, false);



        return new MyViewHolder(inflater,view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w185/"+ (mCursor.getString(mCursor.getColumnIndex(DbContract.FavouriteMovieDetails.POSTER_ID)))).into(holder.imageView);






    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public MyViewHolder(LayoutInflater layoutInflater,View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                     Intent intent = new Intent(context,DetailsofFavoriteMovies.class);
                   intent.putExtra("id",mCursor.getString(mCursor.getColumnIndex(DbContract.FavouriteMovieDetails._ID)));
                    context.startActivity(intent);
                }
            });


        }

    }
}