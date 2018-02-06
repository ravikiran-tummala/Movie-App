package ravikirantummala.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        MovieModel movieModel = (MovieModel) intent.getSerializableExtra(AppConstants.MOVIE_MODEL_INTENT_KEY);
        this.setTitle(movieModel.getTitle());

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        MovieDetailFragment fragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.fragment);
        fragment.movieModel = movieModel;
    }

}
