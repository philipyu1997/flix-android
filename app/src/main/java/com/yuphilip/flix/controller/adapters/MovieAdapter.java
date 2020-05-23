package com.yuphilip.flix.controller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuphilip.flix.R;
import com.yuphilip.flix.model.Constant;
import com.yuphilip.flix.model.Movie;
import com.yuphilip.flix.model.viewholders.BackdropViewHolder;
import com.yuphilip.flix.model.viewholders.PosterViewHolder;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region Properties
    private final Context context;
    private final List<Movie> movies;
    //endregion

    public MovieAdapter(Context context, List<Movie> movies) {

        this.context = context;
        this.movies = movies;

    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("MovieAdapter", "onCreateViewHolder");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;
        View movieView;

        if (viewType == 1) {
            movieView = inflater.inflate(R.layout.item_movie_backdrop, parent, false);
            viewHolder = new BackdropViewHolder(movieView, context);
        } else {
            movieView = inflater.inflate(R.layout.item_movie_poster, parent, false);
            viewHolder = new PosterViewHolder(movieView, context);
        }

        return viewHolder;

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        Log.d("MovieAdapter", "onBindViewHolder " + position);

        // Get the movie at the passed in position
        Movie movie = movies.get(position);

        // Bind the data into the WH
        if (viewHolder.getItemViewType() == 1) {
            BackdropViewHolder backdropViewHolder = (BackdropViewHolder) viewHolder;
            backdropViewHolder.bind(movie);
        } else {
            PosterViewHolder posterViewHolder = (PosterViewHolder) viewHolder;
            posterViewHolder.bind(movie);
        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {

        // Return an integer here representing the type of View.
        // Note: Integers must be in the range 0 to getViewTypeCount() - 1
        double THRESHOLD = Constant.RATING_THRESHOLD;

        if (movies.get(position).getRating() < THRESHOLD) {
            return 0;
        } else {
            return 1;
        }

    }

}
