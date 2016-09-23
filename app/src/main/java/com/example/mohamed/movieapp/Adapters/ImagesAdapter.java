package com.example.mohamed.movieapp.Adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.mohamed.movieapp.MoviesFragment;
import com.example.mohamed.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by mohamed on 8/24/2016.
 */

public class ImagesAdapter extends BaseAdapter {
    private Context moviesContext;
    private ArrayList<String> images;

    public ImagesAdapter(Context c, ArrayList<String> imagesList) {
        moviesContext = c;
        images = imagesList;
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return images.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(moviesContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setPadding(0, 0, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(moviesContext).load(MoviesFragment.BuildImageUrl(images.get(position))).placeholder(R.drawable.loading).into(imageView);
        return imageView;
    }
}


