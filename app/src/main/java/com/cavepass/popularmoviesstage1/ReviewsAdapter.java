package com.cavepass.popularmoviesstage1;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by Ajay on 12-11-2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    Context context;

    ArrayList<ReviewsClassJsonResults> arrayReview;

    ReviewsClass reviewsClass;


    public ReviewsAdapter(Context context, ArrayList<ReviewsClassJsonResults> ar) {
        this.context = context;
        arrayReview = ar;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_list, parent, false);


        return new ReviewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.author.setText(arrayReview.get(position).getAuthor());
        holder.reviewContent.setText(arrayReview.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return arrayReview.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView reviewContent;


        MyViewHolder(View itemView) {


            super(itemView);

            author = (TextView) itemView.findViewById(R.id.authorName);
            reviewContent = (TextView) itemView.findViewById(R.id.reviewContent);

        }


    }


}