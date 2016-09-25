package com.example.mohamed.movieapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.movieapp.Adapters.ReviewsAdapter;
import com.example.mohamed.movieapp.Adapters.TrailersAdapter;
import com.example.mohamed.movieapp.Data.MovieDb;
import com.example.mohamed.movieapp.Data.MoviesData;
import com.example.mohamed.movieapp.Data.MoviesTrailers;
import com.example.mohamed.movieapp.Data.ReviewsData;
import com.example.mohamed.movieapp.Fetch.FetchMoviesTrailers;
import com.example.mohamed.movieapp.Fetch.FetchReviews;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mohamed on 8/23/2016.
 */
public class DetailFragment extends Fragment {
    TextView movie_title, release_date, voting_average, overview;
    ImageView movie_poster;
    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;
    private ArrayList<String> trailersKeyList;
    private ArrayList<String> reviewsList;
    MovieDb movieDb;
    Button fav_button;
    public static MoviesData.ResultsBean movieResultsBean;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieDb = new MovieDb(getActivity());
        View detailsView = inflater.inflate(R.layout.fragment_details, container, false);
        Intent intent = getActivity().getIntent();
        movieResultsBean = (MoviesData.ResultsBean) intent.getSerializableExtra("MoviesData");
        Bundle extras = this.getArguments();

        if (!extras.getSerializable("MoviesData").equals(null)) {
            movieResultsBean = (MoviesData.ResultsBean) extras.getSerializable("MoviesData");
        }


        trailersKeyList = new ArrayList<String>();
        trailersAdapter = new TrailersAdapter(getActivity(), trailersKeyList);

        ListView list = (ListView) detailsView.findViewById(R.id.trailer_list);
        list.setAdapter(trailersAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(trailersKeyList.get(position));
                intent.setData(uri);

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }


            }
        });


        reviewsList = new ArrayList<String>();
        reviewsAdapter = new ReviewsAdapter(getActivity(), reviewsList);

        ListView listReviews = (ListView) detailsView.findViewById(R.id.reviews_list);
        listReviews.setAdapter(reviewsAdapter);


        movie_title = (TextView) detailsView.findViewById(R.id.movie_title);
        release_date = (TextView) detailsView.findViewById(R.id.release_date);
        voting_average = (TextView) detailsView.findViewById(R.id.voting_average);
        overview = (TextView) detailsView.findViewById(R.id.overview);
        movie_poster = (ImageView) detailsView.findViewById(R.id.movie_poster);
        String movie_poster_path = movieResultsBean.getPoster_path();
        Log.i("mido", movie_poster_path);
        movie_title.setText(movieResultsBean.getOriginal_title());
        movie_title.setTextColor(Color.BLACK);

        overview.setTextColor(Color.BLACK);

        release_date.setText(movieResultsBean.getRelease_date());
        overview.setText(movieResultsBean.getOverview());

        voting_average.setText(String.valueOf(movieResultsBean.getVote_average()) + "/10");
        Picasso.with(getActivity()).load(MoviesFragment.BuildImageUrl(movie_poster_path)).into(movie_poster);
        fav_button = (Button) detailsView.findViewById(R.id.fav_button);
        fav_button.setText("Add to favourite");
        fav_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Added to favourites", Toast.LENGTH_SHORT).show();
                movieDb.addMovie(movieResultsBean);
            }
        });
        new FetchTrailersTask().execute(movieResultsBean.getId());
        new FetchReviewsTask().execute(movieResultsBean.getId());
        return detailsView;

    }

    public static String BuildTrailerUrl(String trailerKey) {
        Uri uri = Uri.parse("https://www.youtube.com/watch").buildUpon().appendQueryParameter("v", trailerKey).build();
        Log.i("umm", uri.toString());
        return uri.toString();
    }

    private class FetchTrailersTask extends AsyncTask<String, Void, MoviesTrailers> {

        @Override
        protected MoviesTrailers doInBackground(String... params) {

            try {
                return new FetchMoviesTrailers().fetchMovieTrailer(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MoviesTrailers movie) {
            super.onPostExecute(movie);


            trailersKeyList.clear();
            if (isNetworkAvailable() == true) {
                for (MoviesTrailers.ResultsBean bean : movie.getResults()) {
                    trailersKeyList.add(BuildTrailerUrl(bean.getKey()));

                }
            }


            trailersAdapter.notifyDataSetChanged();

        }
    }


    private class FetchReviewsTask extends AsyncTask<String, Void, ReviewsData> {

        @Override
        protected ReviewsData doInBackground(String... params) {

            try {
                return new FetchReviews().fetchReviews(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ReviewsData movie) {
            super.onPostExecute(movie);


            reviewsList.clear();
            if (isNetworkAvailable() == true) {
                for (ReviewsData.ResultsBean bean : movie.getResults()) {
                    reviewsList.add(bean.getContent());

                }
            }


            reviewsAdapter.notifyDataSetChanged();

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
