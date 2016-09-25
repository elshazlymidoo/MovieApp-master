package com.example.mohamed.movieapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.mohamed.movieapp.Data.MoviesData;

public class MainActivity extends AppCompatActivity implements Listener {
    boolean mTwoPane;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.fragment_two) != null) {
            mTwoPane = true;

        } else {
            mTwoPane = false;
        }


        if (null == savedInstanceState) {
            MoviesFragment moviesFragment = new MoviesFragment();
            moviesFragment.setListener(this);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_movies, moviesFragment).commit();
        }


    }

    @Override
    public void setSelectedMovie(MoviesData.ResultsBean movie) {

        if (mTwoPane == true) {
            DetailFragment detailsFragment = new DetailFragment();
            Bundle extras = new Bundle();
            extras.putSerializable("MoviesData", movie);
            detailsFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_two, detailsFragment).commit();


        } else {

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("MoviesData", movie);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
