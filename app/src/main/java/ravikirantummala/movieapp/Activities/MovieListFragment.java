package ravikirantummala.movieapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ravikirantummala.movieapp.CustomAdapters.ImageAdapter;
import ravikirantummala.movieapp.Models.MovieListModel;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;
import ravikirantummala.movieapp.Services.ServerListener;
import ravikirantummala.movieapp.Services.ServiceFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment implements ServerListener {

    private GridView mGridView;
    public ArrayList<MovieModel> mMovieModels;
    private MovieClick mMovieClickListener;


    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mGridView = view.findViewById(R.id.gridView);
        initializeDataAndAdapters();
        ServiceFactory.getPopularMovieList(1,getActivity(),this);
        return view;
    }

    private void initializeDataAndAdapters(){
        mMovieModels = new ArrayList<MovieModel>();
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(),R.layout.imageview_adapter,R.id.imageView,mMovieModels);
        mGridView.setAdapter(imageAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            this.mMovieClickListener = (MovieClick) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MovieClick");
        }
    }

    @Override
    public void onSuccessResponse(String response) {
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
            MovieListModel movieListModel = new MovieListModel(jsonResponse);
            mMovieModels.addAll(movieListModel.getMovieModels());
            Log.e("Hell","Hello");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ImageAdapter adapter = (ImageAdapter) mGridView.getAdapter();
        adapter.notifyDataSetChanged();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMovieClickListener.onMovieClick(mMovieModels.get(position));
            }
        });
    }

    @Override
    public void onErrorResponse(Exception error) {

    }
}
