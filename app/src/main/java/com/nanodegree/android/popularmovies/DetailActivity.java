package com.nanodegree.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        ImageView imgPoster = (ImageView) findViewById(R.id.img_thumbnail);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        TextView tvVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        TextView tvOverview = (TextView) findViewById(R.id.tv_synopsis);

        Bundle bundle = getIntent().getExtras();
        Movie movie = bundle.getParcelable("movie");

        Picasso.with(this)
                .load(movie.getPosterUrl())
                .into(imgPoster);

        String title = movie.getTitle();
        if (title != null)
            tvMovieTitle.setText(title);
        String releaseDate = movie.getReleaseDate();
        if (releaseDate != null) {
            tvReleaseDate.setText(releaseDate);
        }
        String voteAverage = movie.getVoteAverage();
        if (voteAverage != null)
            //tvVoteAverage.setText(Double.toString(voteAverage));
            voteAverage.concat("/10");
            tvVoteAverage.setText(voteAverage);
        String overview = movie.getOverview();
        if (overview != null)
            tvOverview.setText(overview);
    }

}
