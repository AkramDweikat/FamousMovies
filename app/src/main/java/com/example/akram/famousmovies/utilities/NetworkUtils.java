package com.example.akram.famousmovies.utilities;

import android.net.Uri;

import com.example.akram.famousmovies.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by akram on 06/02/2017.
 */

public class NetworkUtils {

    final static String MovieDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie?";
    final static String PARAM_API_KEY= "api_key";
    final static String API_KEY= new APIKey().getAPIKEY();
    final static String PARAM_QUERY= "sort_by";
    final static String PARAM_QUERY_Sort_By="popularity.desc";


    public static URL buildUrl(String movieDB_SearchPreference) {

        Uri builtUri = Uri.parse(MovieDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,movieDB_SearchPreference)
                .appendQueryParameter(PARAM_API_KEY,API_KEY )
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void parseResult(String result, ArrayList<MovieItem> mylist) {
        try {
            JSONObject response = new JSONObject(result);
            //Parse Resopnse according to MOVEAPI.
            JSONArray movies = response.optJSONArray("results");
            MovieItem item;
            for (int i = 0; i < movies.length(); i++) {

                JSONObject movie = movies.optJSONObject(i);
                String title = movie.getString("original_title");
                String movieImage = movie.getString("poster_path");
                String movieDate = movie.getString("release_date");
                String moviePlot = movie.getString("overview");
                Integer movieRate = movie.getInt("vote_average");

                item = new MovieItem();
                //add items to Movie Item
                item.setMovieTitle(title);
                item.setMovieImage(movieImage);
                item.setMovieAvgVoteRate(movieRate);
                item.setMovieDate(movieDate);
                item.setMoviePlotSynopsis(moviePlot);

                mylist.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
