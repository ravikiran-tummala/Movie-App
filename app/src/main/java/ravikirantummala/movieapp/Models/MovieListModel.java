package ravikirantummala.movieapp.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ravikirantummala.movieapp.Common.AppConstants;

/**
 * Created by ravikirantummala on 12/01/18.
 */

public class MovieListModel {
    private int page;
    private ArrayList<MovieModel> movieModels;
    private int totalResults;
    private int totalPages;

    public MovieListModel(JSONObject movieListJSON) throws JSONException{
        this.page = movieListJSON.getInt(AppConstants.PAGE);
        this.totalPages = movieListJSON.getInt(AppConstants.TOTAL_PAGES);
        this.totalResults = movieListJSON.getInt(AppConstants.TOTAL_RESULTS);
        JSONArray movieModelList = movieListJSON.getJSONArray(AppConstants.RESULTS);
        int movieModelListLength = movieModelList.length();
        this.movieModels = new ArrayList<MovieModel>();
        for(int i=0;i<movieModelListLength;i++){
            MovieModel movieModel = new MovieModel(movieModelList.getJSONObject(i));
            this.movieModels.add(movieModel);
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

    public ArrayList<MovieModel> getMovieModels(){
        return this.movieModels;
    }
}
