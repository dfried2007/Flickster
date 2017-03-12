package com.example.dfrie.flickster.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dfrie on 3/8/2017.
 */

public class Movie {

    private static String POSTER_CONTEXT_PATH = "https://image.tmdb.org/t/p/w342%s";

    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;
    double popularity;
    double voteAverage;
    String releaseDate;
    long id;

    public String getPosterPath() {
        return String.format(POSTER_CONTEXT_PATH, posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format(POSTER_CONTEXT_PATH, backdropPath);
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public long getId() {
        return id;
    }

    public Movie(JSONObject jobj) throws JSONException {
        posterPath = jobj.getString("poster_path");
        originalTitle = jobj.getString("original_title");
        overview = jobj.getString("overview");
        backdropPath = jobj.getString("backdrop_path");
        popularity = jobj.getDouble("popularity");
        voteAverage = jobj.getDouble("vote_average");
        releaseDate = jobj.getString("release_date");
        id = jobj.getLong("id");
    }
}
