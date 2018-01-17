package ravikirantummala.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ravikirantummala.movieapp.Models.MovieListModel;
import ravikirantummala.movieapp.Services.ServerListener;

import static ravikirantummala.movieapp.Services.ServiceFactory.getPopularMovieList;

public class MainActivity extends AppCompatActivity implements ServerListener {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPopularMovieList(1,this,this);

    }

    @Override
    public void onSuccessResponse(String response) {
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
            MovieListModel movieListModel = new MovieListModel(jsonResponse);
            Log.e(LOG_TAG,movieListModel.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(Exception error) {

    }
}
