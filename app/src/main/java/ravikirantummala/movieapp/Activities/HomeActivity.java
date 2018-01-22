package ravikirantummala.movieapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ravikirantummala.movieapp.R;

public class HomeActivity extends AppCompatActivity implements MovieClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onMovieClick(int position) {
        Toast.makeText(this,"Clicked position" + position,Toast.LENGTH_SHORT).show();
    }
}
