package com.cavepass.popularmoviesstage1.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cavepass.popularmoviesstage1.R;

import java.util.ArrayList;

/**
 * Created by Ajay on 12-11-2017.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.MyViewHolder>{




    int dataSize;
    Context mContext;
    ArrayList<String> ar;




    public TrailersAdapter(Context context,ArrayList<String> ar) {

        this.ar=ar;

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

        Log.e("Size" , ar.size()+"");

        Glide.with(mContext).load("https://img.youtube.com/vi/" +
                ar.get(position)+"/0.jpg").dontAnimate().into(holder.thumbnail);

        Log.e("Image","https://img.youtube.com/vi/" +
                ar.get(position)+"/0.jpg");

    }

    @Override
    public int getItemCount() {
        return ar.size();
    }


}