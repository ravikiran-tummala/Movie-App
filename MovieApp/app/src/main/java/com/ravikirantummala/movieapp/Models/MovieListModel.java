package com.ravikirantummala.movieapp.Models;

import com.ravikirantummala.movieapp.Common.AppConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ravikirantummala on 12/01/18.
 */

public class MovieListModel {
    private int page;
    private ArrayList<MovieModel> movieModels;
    private int totalResults;
    private int totalPages;

    public MovieModel(String jsonString){
        JSONObject movieListJSON = new JSONObject(jsonString);
        this.page = movieListJSON.getInt(AppConstants.PAGE);
        this.totalPages = movieListJSON.getInt(AppConstants.TOTAL_PAGES);
        this.totalResults = movieListJSON.getInt(AppConstants.TOTAL_RESULTS);
        JSONArray movieModelList = movieListJSON.getJSONArray(AppConstants.RESULTS);
        int movieModelListLength = movieModelList.length();
        this.movieModels = new ArrayList<MovieModel>();
        for (JSONObject movieModel:movieModelList) {
            this.movieModels.add(MovieModel(movieModel));
        }
    }

    public int getPage(){
        return this.page;
    }

    public int getTotalResults(){
        return this.totalResults;
    }

    public int getTotalPages(){
        return this.totalPages;
    }

    public MovieModel[] getMovieModels(){
        this.movieModels;
    }
}
