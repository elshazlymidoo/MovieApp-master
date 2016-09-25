package com.example.mohamed.movieapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohamed on 9/9/2016.
 */
public class ReviewsAdapter extends BaseAdapter {

    private Context moviesContext;
    private ArrayList<String> reviews;


    public ReviewsAdapter(Context c, ArrayList<String> reviewsList) {
        moviesContext = c;
        reviews = reviewsList;

    }

    public int getCount() {
        return reviews.size();
    }

    public Object getItem(int position) {
        return reviews.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    TextView textView;

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(moviesContext);

            textView.setPadding(0, 0, 0, 0);


        } else {
            textView = (TextView) convertView;


        }

        textView.setText(reviews.get(position));
        textView.setTextColor(Color.BLACK);

        return textView;
    }
}
