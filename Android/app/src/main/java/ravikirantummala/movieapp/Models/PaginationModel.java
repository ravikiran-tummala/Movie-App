package ravikirantummala.movieapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import ravikirantummala.movieapp.Common.AppConstants;

/**
 * Created by ravikirantummala on 04/02/18.
 */

public class PaginationModel implements Serializable{

    private int page;
    private int totalResults;
    private int totalPages;
    public PaginationModel(JSONObject jsonObject) throws JSONException{
        this.page = jsonObject.getInt(AppConstants.PAGE);
        this.totalPages = jsonObject.getInt(AppConstants.TOTAL_PAGES);
        this.totalResults = jsonObject.getInt(AppConstants.TOTAL_RESULTS);
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
}
