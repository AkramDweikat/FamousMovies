package com.example.akram.famousmovies;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akram on 06/02/2017.
 */

public class GridViewAdapter extends ArrayAdapter<MovieItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<MovieItem> data = new ArrayList();


    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<MovieItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public void setData(ArrayList<MovieItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.mv_grid_item_image);

        } else {
            imageView = (ImageView) convertView;

        }

        MovieItem item = data.get(position);

        Picasso.with(context).load(item.getMovieImage()).into(imageView);


        return imageView;
    }



}
