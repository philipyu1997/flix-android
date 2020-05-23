package com.yuphilip.flix.controller.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.yuphilip.flix.R;
import com.yuphilip.flix.databinding.ActivityDetailBinding;
import com.yuphilip.flix.model.Constant;
import com.yuphilip.flix.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.Objects;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    //region Properties
    private static final String YOUTUBE_API_KEY = Constant.YOUTUBE_API_KEY;
    private static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=" + Constant.API_KEY;

    private YouTubePlayerView youTubePlayerView;
    private ActivityDetailBinding binding;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        TextView tvTitle = binding.tvTitle;
        TextView tvOverview = binding.tvOverview;
        RatingBar ratingBar = binding.ratingBar;
        youTubePlayerView = binding.player;

        final Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        tvTitle.setText(Objects.requireNonNull(movie).getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());

        playVideo(movie);

    }

    private void playVideo(final Movie movie) {

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");

                    if (results.length() == 0) {
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailActivity", youtubeKey);
                    initializeYoutube(youtubeKey, movie.getRating());
                } catch (JSONException e) {
                    Log.e("DetailActivity", "Failed to parse JSON.");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d("DetailActivity", "Could not fetch video...");
                Log.d("YouTube", response);
            }
        });

    }

    private void initializeYoutube(final String youtubeKey, final double rating) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                double ratingThreshold = Constant.RATING_THRESHOLD;

                Log.d("DetailActivity", "onInitializationSuccess");

                if (rating < ratingThreshold) {
                    Log.d("YouTube", "Rating is below " + ratingThreshold + ". Display image preview.");
                    youTubePlayer.cueVideo(youtubeKey);
                } else {
                    Log.d("YouTube", "Rating is above " + ratingThreshold + ". Play video.");
                    youTubePlayer.loadVideo(youtubeKey);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");
            }
        });

    }

}
