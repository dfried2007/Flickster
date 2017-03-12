package com.example.dfrie.flickster.util;

import com.example.dfrie.flickster.model.Movie;
import com.example.dfrie.flickster.model.Trailer;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by dfrie on 3/8/2017.
 */

public class MovieUtil {

    public static ArrayList<Movie> moviesFromJsonArray(JSONArray arr) {
        ArrayList<Movie> movieList = new ArrayList<>();
        for (int i=0; i<arr.length(); i++) {
            try {
                movieList.add(new Movie(arr.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movieList;
    }

    public static ArrayList<Trailer> trailersFromJsonArray(JSONArray arr) {
        ArrayList<Trailer> trailerList = new ArrayList<>();
        for (int i=0; i<arr.length(); i++) {
            try {
                trailerList.add(new Trailer(arr.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return trailerList;
    }
}
