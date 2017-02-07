package com.example.akram.famousmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


        new GitMoviesFromSiteTast().execute(NetworkUtils.buildUrl(0));

    }


    public class GitMoviesFromSiteTast extends AsyncTask<URL,Void,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);

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
            }
            else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
            mProgressBar.setVisibility(View.GONE);

        }





    }

}
