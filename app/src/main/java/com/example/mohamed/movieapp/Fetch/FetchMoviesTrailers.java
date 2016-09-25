package com.example.mohamed.movieapp.Fetch;

import android.net.Uri;


import com.example.mohamed.movieapp.BuildConfig;
import com.example.mohamed.movieapp.Data.MoviesTrailers;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mohamed on 9/7/2016.
 */
public class FetchMoviesTrailers {


    public String buildUrl(String movieId) {
        String url = Uri.parse("https://api.themoviedb.org/3/movie/").buildUpon()
                .appendEncodedPath(movieId)
                .appendEncodedPath("videos")
                .appendQueryParameter("api_key", BuildConfig.themoviedb_api_key)
                .build().toString();

        return url;
    }

    public String getUrl(String movieId) throws IOException {
        String urlSpec = buildUrl(movieId);
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toString();
        } finally {
            connection.disconnect();
        }
    }

    public MoviesTrailers fetchMovieTrailer(String movieId) throws IOException {
        try {
            String parsedData = getUrl(movieId);
            Gson gson = new Gson();
            MoviesTrailers movie = gson.fromJson(parsedData, MoviesTrailers.class);
            return movie;
        } catch (Exception e) {
            return null;
        }
    }


}
