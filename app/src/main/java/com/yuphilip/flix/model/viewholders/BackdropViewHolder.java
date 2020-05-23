package com.yuphilip.flix.model.viewholders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.yuphilip.flix.R;
import com.yuphilip.flix.controller.activities.DetailActivity;
import com.yuphilip.flix.databinding.ItemMovieBackdropBinding;
import com.yuphilip.flix.model.Movie;

import org.parceler.Parcels;

public class BackdropViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout container;
    TextView tvTitle;
    TextView tvOverview;
    ImageView tvPoster;
    private final ItemMovieBackdropBinding binding;
    Context context;

    public BackdropViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.binding = ItemMovieBackdropBinding.bind(itemView);

        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        tvPoster = binding.tvBackdrop;
        container = binding.container;
        this.context = context;
    }

    public void bind(final Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String imageUrl;

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getPosterPath();
        } else {
            imageUrl = movie.getBackdropPath();
        }

        int radius = 30; // corner radius, higher value = more rounded

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .transform(new RoundedCorners(radius))
                .into(tvPoster);

        // 1. Register click listener on the whole row
        container.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 2. Navigate to a new activity on tap
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                context.startActivity(i);
            }
        });
    }
}
