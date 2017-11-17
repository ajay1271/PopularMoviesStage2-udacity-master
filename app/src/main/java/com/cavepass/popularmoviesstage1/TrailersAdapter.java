package com.cavepass.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Ajay on 12-11-2017.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.MyViewHolder>{




    int dataSize;
    Context mContext;
    ArrayList<TrailersClassResults> id;




    public TrailersAdapter(Context context,ArrayList<TrailersClassResults> id) {

        this.id=id;
        this.dataSize=dataSize;
        mContext =context;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;

        public MyViewHolder(View itemView) {

            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        }
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.trailers, parent, false);



        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TrailersAdapter.MyViewHolder holder, int position) {

        Glide.with(mContext).load("https://img.youtube.com/vi/ePbKGoIGAXY" +
                id.get(position).getKey()+"/0.jpg").diskCacheStrategy( DiskCacheStrategy.RESULT).dontAnimate().into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return id.size();
    }


}