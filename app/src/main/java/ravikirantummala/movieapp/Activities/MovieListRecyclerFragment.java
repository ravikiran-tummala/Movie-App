package ravikirantummala.movieapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.CustomAdapters.ImageRecyclerAdapter;
import ravikirantummala.movieapp.Models.MovieListModel;
import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;
import ravikirantummala.movieapp.Services.ServerListener;
import ravikirantummala.movieapp.Services.ServiceFactory;

public class MovieListRecyclerFragment extends Fragment implements ServerListener,ImageRecyclerAdapter.OnItemClickListener{
    public ArrayList<MovieModel> mMovieModels;
    private OnMovieClickListener mMovieClickListener;
    private RecyclerView mRecyclerView;

    public MovieListRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initializeDataAndAdapter(){
        mMovieModels = new ArrayList<MovieModel>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),AppConstants.gridElements));
        ImageRecyclerAdapter imageRecyclerAdapter = new ImageRecyclerAdapter(getActivity(),R.layout.imageview_adapter,R.id.imageView,mMovieModels,this);
        mRecyclerView.setAdapter(imageRecyclerAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list_recycler,container,false);
        mRecyclerView = view.findViewById(R.id.recycleView);
        initializeDataAndAdapter();
        ServiceFactory.getPopularMovieList(1,getActivity(),this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieClickListener) {
            mMovieClickListener = (OnMovieClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMovieClickListener = null;
    }

    @Override
    public void onSuccessResponse(String response) {
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
            MovieListModel movieListModel = new MovieListModel(jsonResponse);
            mMovieModels.addAll(movieListModel.getMovieModels());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ImageRecyclerAdapter imageRecyclerAdapter = (ImageRecyclerAdapter) mRecyclerView.getAdapter();
        imageRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorResponse(Exception error) {

    }

    @Override
    public void onItemClicked(int position) {
        mMovieClickListener.onMovieClick(mMovieModels.get(position));
    }
}
