package ravikirantummala.movieapp.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Common.Utility;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    private static final String imageSize = "w500";

    private ImageView mDetailImageView;
    private TextView mMovieTitleTextView;
    private TextView mReleaseDateTextView;
    private TextView mVoteAverageTextView;
    private TextView mSynopsisTextView;

    public MovieModel movieModel;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        this.mDetailImageView = view.findViewById(R.id.detailImageView);
        this.mMovieTitleTextView = view.findViewById(R.id.movieTitleEditText);
        this.mMovieTitleTextView.setKeyListener(null);
        this.mReleaseDateTextView = view.findViewById(R.id.releaseDateEditText);
        this.mReleaseDateTextView.setKeyListener(null);
        this.mVoteAverageTextView = view.findViewById(R.id.voteAverageEditText);
        this.mVoteAverageTextView.setKeyListener(null);
        this.mSynopsisTextView = view.findViewById(R.id.synopsisEditText);
        this.mSynopsisTextView.setKeyListener(null);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Poster loading

        Utility.loadPicasso(AppConstants.POSTER_BASE_URL,imageSize,movieModel,getActivity(),this.mDetailImageView);

        // Text View loading

        mMovieTitleTextView.setText(movieModel.getOriginalTitle());
        mReleaseDateTextView.setText(movieModel.getReleaseDate());
        mSynopsisTextView.setText(movieModel.getOverView());
        mVoteAverageTextView.setText(movieModel.getVoteAverage().toString());
    }
}
