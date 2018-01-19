package ravikirantummala.movieapp.Activities;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;

import ravikirantummala.movieapp.CustomAdapters.ImageAdapter;
import ravikirantummala.movieapp.Models.MovieListModel;
import ravikirantummala.movieapp.R;
import ravikirantummala.movieapp.Services.ServerListener;
import ravikirantummala.movieapp.Services.ServiceFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment implements ServerListener {

    private GridView mGridView;
    private ArrayAdapter<Image> mImageArrayAdapter;

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielist_fragment, container, false);
        mGridView = view.findViewById(R.id.gridView);
        ServiceFactory.getPopularMovieList(1,getActivity(),this);
        return view;
    }

    @Override
    public void onSuccessResponse(String response) {
        JSONObject jsonResponse = null;
        MovieListModel movieListModel = null;
        try {
            jsonResponse = new JSONObject(response);
            movieListModel = new MovieListModel(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(),R.layout.imageview_adapter,R.id.imageView,movieListModel.getMovieModels());
        mGridView.setAdapter(imageAdapter);
    }

    @Override
    public void onErrorResponse(Exception error) {

    }
}
