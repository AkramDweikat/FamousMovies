package com.example.akram.famousmovies;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView mMovieTitleTextview;
    private ImageView mMoviePosterView;
    private TextView mMovieRateTextView;
    private TextView mMovieDateTextView;
    private TextView mMoviePlotTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mMovieTitleTextview = (TextView) findViewById(R.id.movie_title);
        mMoviePosterView = (ImageView) findViewById(R.id.movie_item_image);
        mMovieRateTextView = (TextView) findViewById(R.id.movie_rate);
        mMovieDateTextView = (TextView) findViewById(R.id.movie_date);
        mMoviePlotTextView = (TextView) findViewById(R.id.movie_plot);


        Intent intentStartedThisActicity = getIntent();


        if(intentStartedThisActicity.hasExtra("title")){
            String movieTite = intentStartedThisActicity.getStringExtra("title");
            mMovieTitleTextview.setText(movieTite);
        }

        if(intentStartedThisActicity.hasExtra("image")){
            String movieImage = intentStartedThisActicity.getStringExtra("image");
            Picasso.with(this).load(movieImage).into(mMoviePosterView);
        }

        if(intentStartedThisActicity.hasExtra("rate")){
            String rate = intentStartedThisActicity.getStringExtra("rate");
            mMovieRateTextView.setText(rate);
        }

        if(intentStartedThisActicity.hasExtra("date")){
            String date = intentStartedThisActicity.getStringExtra("date");
            mMovieDateTextView.setText(date);
        }

        if(intentStartedThisActicity.hasExtra("plot")){
            String plot = intentStartedThisActicity.getStringExtra("plot");
            mMoviePlotTextView.setText(plot);
        }



    }
}
