package ravikirantummala.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ravikirantummala.movieapp.Services.ServerListener;
import ravikirantummala.movieapp.Services.ServiceFactory;

public class MainActivity extends AppCompatActivity implements ServerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceFactory.getPopularMovieList(1,this,this);
    }

    @Override
    public void onSuccessResponse(String response) {

    }

    @Override
    public void onErrorResponse(Exception error) {

    }
}
