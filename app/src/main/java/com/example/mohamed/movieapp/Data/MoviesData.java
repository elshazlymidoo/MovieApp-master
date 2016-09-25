package com.example.mohamed.movieapp.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 8/15/2016.
 */
public class MoviesData implements Serializable {


    /**
     * poster_path : /e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg
     * adult : false
     * overview : From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.
     * release_date : 2016-08-03
     * genre_ids : [28,80,878]
     * id : 297761
     * original_title : Suicide Squad
     * original_language : en
     * title : Suicide Squad
     * backdrop_path : /ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg
     * popularity : 61.528248
     * vote_count : 645
     * video : false
     * vote_average : 5.99
     */

    private ArrayList<ResultsBean> results;

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable {
        private String poster_path;
        private String overview;
        private String release_date;
        private String original_title;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private double vote_average;

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }
    }
}