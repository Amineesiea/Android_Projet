package com.example.ilyes.android_projet;

import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by ilyes on 11/04/2018.
 */

public class MovieFinder {

    private static MovieFinder MOVIEFINDER = null;
    private ArrayList<Film> movieList = null;

    private MovieFinder(){
        if(movieList == null){
            movieList = new ArrayList<Film>();
        }
    }


    public static MovieFinder getInstance(){

        if(MOVIEFINDER == null){

            MOVIEFINDER = new MovieFinder();
        }
        return MOVIEFINDER;
    }

    public ArrayList<Film> getFilmsList() {
        return this.movieList;
    }

    public void clearList() {

        movieList.clear();
    }

    public void addFilm (Film f) {
        if(movieList != null) {
            movieList.add(f);
            Log.i("Movie Finder", f.getTitre()+ "ajouté");
        }
    }


    public Film getFilmById(String id) {

        for(Film f : movieList) {
            if (f.getId() == id) {
                return f;
            }
        }

        return null;
    }


}



