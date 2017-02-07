package com.example.akram.famousmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.akram.famousmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private GridViewAdapter mGridViewAdaper;
    private ProgressBar mProgressBar;
    private ArrayList<MovieItem> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.mv_gridview);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mMovieList = new ArrayList<>();
        mGridViewAdaper = new GridViewAdapter(this, R.layout.grid_view_item, mMovieList);
        mGridView.setAdapter(mGridViewAdaper);


        loadMovies("vote_average.desc");

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                MovieItem movie =  mMovieList.get(position);

                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("title", movie.getMovieTitle());
                intent.putExtra("image", movie.getMovieImage());
                intent.putExtra("rate", ""+movie.getMovieAvgVoteRate());
                intent.putExtra("plot", movie.getMoviePlotSynopsis());
                intent.putExtra("date", movie.getMovieDate());

                startActivity(intent);
            }
        });

    }

    private void loadMovies(String moviesSearchBY){

        new GitMoviesFromSiteTast().execute(NetworkUtils.buildUrl(moviesSearchBY));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_orderby_rate) {
            loadMovies("vote_average.desc");
            return true;
        }
        else if(itemThatWasClickedId == R.id.action_orderby_poplarity){
            loadMovies("popularity.desc");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class GitMoviesFromSiteTast extends AsyncTask<URL,Void,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mMovieList.clear();

        }

        @Override
        protected Integer doInBackground(URL... params) {
            URL searchUrl = params[0];
            String resultString;
            Integer result =0;
            try {
                resultString = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                NetworkUtils.parseResult(resultString,mMovieList);
                result = 1 ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (result == 1) {
                mGridViewAdaper.setData(mMovieList);
                mGridViewAdaper.notifyDataSetChanged();
                mGridView.invalidateViews();
                mGridView.setAdapter(mGridViewAdaper);
            }
            else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
            mProgressBar.setVisibility(View.GONE);

        }
    }

}
