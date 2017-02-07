package com.example.akram.famousmovies;

/**
 * Created by akram on 06/02/2017.
 */

public class MovieItem {

    private String movieTitle;
    private String movieImage;
    private String movieDate;
    private Integer movieAvgVoteRate;
    private String moviePlotSynopsis;

    public MovieItem(){

    }
    public MovieItem(String MovieTitle, String MovieImage){
        this.movieTitle = MovieTitle;
        this.movieImage =  MovieImage;
    }

    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle(){
        return this.movieTitle;
    }

    public void setMovieImage(String movieImageUrl){
        String baseURL = "http://image.tmdb.org/t/p/w185/";
        this.movieImage = baseURL + movieImageUrl ;
    }


    public String getMovieImage(){
        return this.movieImage;
    }
    public void setMovieDate(String date){
        this.movieDate = date;
    }

    public String getMovieDate(){
        return this.movieDate;
    }

    public void setMovieAvgVoteRate(Integer rate){
        this.movieAvgVoteRate = rate;
    }

    public Integer getMovieAvgVoteRate(){
        return this.movieAvgVoteRate;
    }

    public void setMoviePlotSynopsis(String plot){
        this.moviePlotSynopsis = plot;
    }

    public String getMoviePlotSynopsis(){
        return this.moviePlotSynopsis;
    }


}
