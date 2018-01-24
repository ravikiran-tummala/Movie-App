package ravikirantummala.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        MovieModel movieModel = (MovieModel) intent.getSerializableExtra(AppConstants.MOVIE_MODEL_INTENT_KEY);
    }

}
