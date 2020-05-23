package com.yuphilip.flix.controller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yuphilip.flix.R;
import com.yuphilip.flix.model.viewholders.BackdropViewHolder;
import com.yuphilip.flix.model.Movie;
import com.yuphilip.flix.model.viewholders.PosterViewHolder;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;
    final int POSTER = 0, BACKDROP = 1;
    double THRESHOLD = 7.0;
    private AdapterViewBindingAdapter binding;

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

        switch (viewType) {
            case BACKDROP:
                movieView = inflater.inflate(R.layout.item_movie_backdrop, parent, false);
                viewHolder = new BackdropViewHolder(movieView, context);
                break;
            default:
                movieView = inflater.inflate(R.layout.item_movie_poster, parent, false);
                viewHolder = new PosterViewHolder(movieView, context);
                break;
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
        switch (viewHolder.getItemViewType()) {
            case BACKDROP:
                BackdropViewHolder backdropViewHolder = (BackdropViewHolder) viewHolder;
                backdropViewHolder.bind(movie);
                break;
            default:
                PosterViewHolder posterViewHolder = (PosterViewHolder) viewHolder;
                posterViewHolder.bind(movie);
                break;
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

        if (movies.get(position).getRating() < THRESHOLD) {
            return POSTER;
        } else {
            return BACKDROP;
        }

    }



}
