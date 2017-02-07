package com.example.akram.famousmovies;

import android.media.Image;

/**
 * Created by akram on 06/02/2017.
 */

public class MovieItem {

    private String MovieTitle;
    private String MovieImage;

    public MovieItem(){

    }
    public MovieItem(String MovieTitle, String MovieImage){
        this.MovieTitle = MovieTitle;
        this.MovieImage  =  MovieImage;
    }

    public void setMovieTitle(String movieTitle){
        this.MovieTitle = movieTitle;
    }

    public void setMovieImage(String movieImageUrl){
        String baseURL = "http://image.tmdb.org/t/p/w185/";
        this.MovieImage = baseURL + movieImageUrl ;
    }

    public String getMovieTitle(){
        return this.MovieTitle;
    }

    public String getMovieImage(){
        return this.MovieImage;
    }
}
