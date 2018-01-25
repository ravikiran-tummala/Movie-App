package ravikirantummala.movieapp.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import ravikirantummala.movieapp.Common.AppConstants;

/**
 * Created by ravikirantummala on 12/01/18.
 */

public class MovieModel implements Serializable{

    public MovieModel(JSONObject movieModelJSON) throws JSONException {
        this.posterPath = movieModelJSON.getString(AppConstants.POSTER_PATH);
        this.isAdultRated = movieModelJSON.getBoolean(AppConstants.ADULT);
        this.overView = movieModelJSON.getString(AppConstants.OVERVIEW);
        this.releaseDate = movieModelJSON.getString(AppConstants.RELEASE_DATE);
        JSONArray genreIds = movieModelJSON.getJSONArray(AppConstants.GENRE_IDS);
        int genreIdsLength = genreIds.length();
        this.genreIds = new int[genreIdsLength];
        for (int i=0;i<genreIdsLength;i++){
            this.genreIds[i] = genreIds.getInt(i);
        }
        this.id = movieModelJSON.getInt(AppConstants.ID);
        this.originalTitle = movieModelJSON.getString(AppConstants.ORIGINAL_TITLE);
        this.originalLanguage = movieModelJSON.getString(AppConstants.ORIGINAL_LANGUAGE);
        this.title = movieModelJSON.getString(AppConstants.TITLE);
        this.backDropPath = movieModelJSON.getString(AppConstants.BACKDROP_PATH);
        this.popularity = (Number)movieModelJSON.get(AppConstants.POPULARITY);
        this.voteCount = movieModelJSON.getInt(AppConstants.VOTE_COUNT);
        this.isVideo = movieModelJSON.getBoolean(AppConstants.VIDEO);
        this.voteAverage = (Number) movieModelJSON.get(AppConstants.VOTE_AVERAGE);
    }

    private String posterPath;
    private Boolean isAdultRated;
    private String overView;
    private String releaseDate;
    private int[] genreIds;
    private int id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backDropPath;
    private Number popularity;
    private int voteCount;
    private Boolean isVideo;
    private Number voteAverage;

    public String getPosterPath(){
        return this.posterPath;
    }

    public Boolean getIsAdultRated(){
        return this.isAdultRated;
    }

    public String getOverView(){
        return this.overView;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public int[] getGenreIds(){
        return this.genreIds;
    }

    public int getId(){
        return this.id;
    }

    public String getOriginalTitle(){
        return this.originalTitle;
    }

    public String getOriginalLanguage(){
        return this.originalLanguage;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBackDropPath(){
        return this.backDropPath;
    }

    public Number getPopularity(){
        return this.popularity;
    }

    public int getVoteCount(){
        return this.voteCount;
    }

    public Boolean getIsVideo(){
        return this.isVideo;
    }

    public Number getVoteAverage(){
        return this.voteAverage;
    }


}
