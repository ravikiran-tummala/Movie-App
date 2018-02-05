package ravikirantummala.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

public class HomeActivity extends FragmentActivity implements OnMovieClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle(R.string.title_activity_home);
    }

    @Override
    public void onMovieClick(MovieModel movieModel) {
        Intent intent = new Intent();
        intent.setClass(this,MovieDetailActivity.class);
        intent.putExtra(AppConstants.MOVIE_MODEL_INTENT_KEY,movieModel);
        startActivity(intent);
    }
}
