package com.yuphilip.flix.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    private int movieId;
    private String backdropPath;
    private String posterPath;
    private String title;
    private String overview;
    private double rating;

    // empty constructor needed by the Parceler library
    public Movie() {

    }

    private Movie(JSONObject jsonObject) throws JSONException {

        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); ++i) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;

    }

    public String getBackdropPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);

    }

    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);


    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }

}
