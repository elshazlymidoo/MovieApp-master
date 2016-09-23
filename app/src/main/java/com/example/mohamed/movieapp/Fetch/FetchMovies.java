package com.example.mohamed.movieapp.Fetch;


/**
 * Created by mohamed on 8/15/2016.
 */


import android.net.Uri;


import com.example.mohamed.movieapp.BuildConfig;
import com.example.mohamed.movieapp.Data.MoviesData;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchMovies {
    public static final String API = "454c068faee6611d2de8f0cb25c1a90a";


    public String buildUrl(String searchType, String page) {
        String url = Uri.parse("https://api.themoviedb.org/3/movie/").buildUpon()
                .appendEncodedPath(searchType)
                .appendQueryParameter("api_key", BuildConfig.themoviedb_api_key)
                .appendQueryParameter("page", page)
                .build().toString();

        return url;
    }

    public String getUrlBytes(String searchType, String page) throws IOException {
        String urlSpec = buildUrl(searchType, page);
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

    public MoviesData fetchMovieData(String searchType, String page) throws IOException {
        try {
            String parsedData = getUrlBytes(searchType, page);
            Gson gson = new Gson();
            MoviesData movie = gson.fromJson(parsedData, MoviesData.class);
            return movie;
        } catch (Exception e) {
            return null;
        }
    }

}



