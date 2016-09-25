package com.example.mohamed.movieapp.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mohamed on 9/7/2016.
 */
public class MoviesTrailers implements Serializable {
    /**
     * id : 271110
     * results : [{"id":"5794ccaa9251414236001173","iso_639_1":"en","iso_3166_1":"US","key":"43NWzay3W4s","name":"Official Trailer #1","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5738f0ac92514166fe000fb6","iso_639_1":"en","iso_3166_1":"US","key":"dKrVegVI0Us","name":"Official Trailer 2","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    /**
     * id : 5794ccaa9251414236001173
     * iso_639_1 : en
     * iso_3166_1 : US
     * key : 43NWzay3W4s
     * name : Official Trailer #1
     * site : YouTube
     * size : 1080
     * type : Trailer
     */

    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String key;
        private String name;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
