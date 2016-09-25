package com.example.mohamed.movieapp;


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mohamed.movieapp.Adapters.ImagesAdapter;
import com.example.mohamed.movieapp.Data.MovieDb;
import com.example.mohamed.movieapp.Data.MoviesData;
import com.example.mohamed.movieapp.Data.MoviesData.ResultsBean;
import com.example.mohamed.movieapp.Fetch.FetchMovies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 8/15/2016.
 */
public class MoviesFragment extends Fragment {

    ArrayList<MoviesData.ResultsBean> moviesList;
    private ImagesAdapter moviesAdapter;
    private ArrayList<String> mImageUrlList;
    Listener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        mImageUrlList = new ArrayList<String>();

        moviesAdapter = new ImagesAdapter(getActivity(), mImageUrlList);
        GridView list = (GridView) rootView.findViewById(R.id.gridview_movies);
        list.setAdapter(moviesAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.setSelectedMovie(moviesList.get(position));
            }
        });

        return rootView;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    public void updateMoviesDisplay() {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String type = prefs.getString(getString(R.string.pref_display_type_key),
                getString(R.string.pref_display_categeory_defualt));

        if (type.equals("favourite")) {
            mImageUrlList.clear();
            MovieDb db = new MovieDb(getActivity());
            moviesList = db.getAllMovie();
            List<MoviesData.ResultsBean> mResultsBean;
            mResultsBean = db.getAllMovie();
            for (int i = 0; i < mResultsBean.size(); i++) {
                mImageUrlList.add(mResultsBean.get(i).getPoster_path());

            }

            moviesAdapter.notifyDataSetChanged();
        } else if (type.equals("popular")) {
            fetchMoviesTask.execute("popular");
        } else if (type.equals("top_rated")) {
            fetchMoviesTask.execute("top_rated");
        }


    }


    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public static String BuildImageUrl(String imagePath) {
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/").buildUpon().appendEncodedPath("w342").appendEncodedPath(imagePath).build();
        return uri.toString();
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, MoviesData> {

        @Override
        protected MoviesData doInBackground(String... params) {

            try {
                return new FetchMovies().fetchMovieData(params[0], 1 + "");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(MoviesData movie) {
            super.onPostExecute(movie);
            if (movie == null) {
                Toast toast = Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG);
                toast.show();
                return;
            }

            moviesList = movie.getResults();

            mImageUrlList.clear();
            for (ResultsBean bean : movie.getResults()) {

                mImageUrlList.add(BuildImageUrl(bean.getPoster_path()));

            }

            moviesAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onStart() {
        super.onStart();

        updateMoviesDisplay();
    }


}





