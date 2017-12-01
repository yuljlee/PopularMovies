/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nanodegree.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private ArrayList<Movie> mMovieData;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie weatherForDay);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //public final TextView mMovieTextView;
        public final ImageView mMoviePoster1;

        public MovieAdapterViewHolder(View view) {
            super(view);
            //mMovieTextView = (TextView) view.findViewById(R.id.tv_movie_data);
            mMoviePoster1 = (ImageView) view.findViewById((R.id.img_poster1));

            view.setOnClickListener(this);
        }

        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMovieData.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.moive_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToRoot = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, attachToRoot);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int position) {
        Movie movie = mMovieData.get(position);
        //movieAdapterViewHolder.mMovieTextView.setText(movie.getTitle());
        //Log.v(TAG, "Built MOVIE ID URI --> " + movie.getPosterUrl());
        Context context = movieAdapterViewHolder.mMoviePoster1.getContext();
        Picasso.with(context)
                .load(movie.getPosterUrl())
                .into(movieAdapterViewHolder.mMoviePoster1);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.size();
    }

    public void setMovieData(ArrayList<Movie> movieList) {
        mMovieData = movieList;
        notifyDataSetChanged();
    }
}