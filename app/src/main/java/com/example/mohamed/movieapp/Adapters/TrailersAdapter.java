package com.example.mohamed.movieapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.mohamed.movieapp.R;


import java.util.ArrayList;


/**
 * Created by mohamed on 9/7/2016.
 */
public class TrailersAdapter extends BaseAdapter {

    private Context moviesContext;
    private ArrayList<String> thumbs;


    public TrailersAdapter(Context c, ArrayList<String> imagesList) {
        moviesContext = c;
        thumbs = imagesList;

    }

    public int getCount() {
        return thumbs.size();
    }

    public Object getItem(int position) {
        return thumbs.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    ImageView imageView;

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(moviesContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            imageView.setPadding(0, 0, 0, 0);


        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(R.drawable.play5);

        return imageView;
    }
}

