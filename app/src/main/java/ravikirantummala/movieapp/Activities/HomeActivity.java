package ravikirantummala.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

public class HomeActivity extends AppCompatActivity implements MovieClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onMovieClick(MovieModel movieModel) {
        Intent intent = new Intent();
        intent.setClass(this,MovieDetailActivity.class);
        intent.putExtra(AppConstants.MOVIE_MODEL_INTENT_KEY,movieModel);
        startActivity(intent);
    }
}
