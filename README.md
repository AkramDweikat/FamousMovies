# FamousMovies
Android Application, that displays movies from the Movie Database (www.themoviedb.org), sorted by Average Rating or The Popularity. this is created during course Android Nanodegree Development FastTrack By Udacity.


Before you Run App:
    1 .create an empty class file under utilities. and call it, APIKey , utilities/APIKey.class.

    2 .Copy and Paste the code into the APIKey file.

            package com.example.akram.famousmovies.utilities;
            public final class APIKey {

                private final String APIKEY ="YOURKEY_GOESHERE";

                APIKey(){

                }

                public String getAPIKEY() {
                    return APIKEY;
                }
            }

    3. Replace YOURKEY_GOESHERE ,with your APIKey that you got from: https://www.themoviedb.org/. 
