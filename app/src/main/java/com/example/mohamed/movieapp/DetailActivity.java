package com.example.mohamed.movieapp;


<<<<<<< HEAD
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
=======
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 0fa2b66cf92dad886a8afbf48dfca3619cdcf628


/**
 * Created by mohamed on 8/23/2016.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (null == savedInstanceState) {
            DetailFragment detailsFragment = new DetailFragment();
            detailsFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().add(R.id.details_fragment, detailsFragment).commit();
        }


    }
}
