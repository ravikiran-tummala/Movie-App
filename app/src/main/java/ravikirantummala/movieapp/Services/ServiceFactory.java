package ravikirantummala.movieapp.Services;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Models.PaginationModel;

/**
 * Created by ravikirantummala on 10/01/18.
 */



public class ServiceFactory {

    private final static String baseURL = "https://api.themoviedb.org/3/";
    private final static String movieTag = "movie";
    private final static String popularTag = "popular";
    private final static String language = "en-US";
    private final static String LOG_TAG = ServiceFactory.class.getSimpleName();

    public static void getTotalPagesAndResults(Context context,final PaginationListener listener){
        Uri.Builder builder = Uri.parse(baseURL).buildUpon().
                appendPath(movieTag).
                appendPath(popularTag).
                appendQueryParameter(AppConstants.API_KEY,AppConstants.SERVICE_API_KEY).
                appendQueryParameter(AppConstants.LANGUAGE_KEY,language).
                appendQueryParameter(AppConstants.PAGE_KEY,String.valueOf(1));


        String url = builder.toString();
        Singleton singleton = Singleton.getInstance(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG,response);
                        try {
                            JSONObject paginationJSONObject = new JSONObject(response);
                            PaginationModel paginationModel = new PaginationModel(paginationJSONObject);
                            listener.onPageFetcherResponse(paginationModel.getTotalPages(),paginationModel.getTotalResults());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG,error.getMessage());
                listener.onErrorResponse(error);
            }
        });

        singleton.addToRequestQueue(stringRequest);
    }

    public static void getPopularMovieList(int page, Context context, final ServerListener listener){

        Uri.Builder builder = Uri.parse(baseURL).buildUpon().
                appendPath(movieTag).
                appendPath(popularTag).
                appendQueryParameter(AppConstants.API_KEY,AppConstants.SERVICE_API_KEY).
                appendQueryParameter(AppConstants.LANGUAGE_KEY,language).
                appendQueryParameter(AppConstants.PAGE_KEY,String.valueOf(page));


        String url = builder.toString();
        Singleton singleton = Singleton.getInstance(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG,response);
                        listener.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG,error.getMessage());
                listener.onErrorResponse(error);
            }
        });

        singleton.addToRequestQueue(stringRequest);
    }

}
