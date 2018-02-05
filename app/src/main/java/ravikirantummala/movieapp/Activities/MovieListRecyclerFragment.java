package ravikirantummala.movieapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import ravikirantummala.movieapp.Services.PaginationListener;
import ravikirantummala.movieapp.Services.ServerListener;
import ravikirantummala.movieapp.Services.ServiceFactory;

public class MovieListRecyclerFragment extends Fragment implements ServerListener,ImageRecyclerAdapter.OnItemClickListener,PaginationListener{
    public ArrayList<MovieModel> mMovieModels;
    private OnMovieClickListener mMovieClickListener;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private int mTotalPages;
    private int mTotalResults;
    private int mCurrentPage  = 1;
    private int mPagesLoaded = 0;
    private int mMoviesPerPage = 20;
    private static final String LOG_TAG = MovieListRecyclerFragment.class.getSimpleName();

    public MovieListRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initializeDataAndAdapter(){
        mMovieModels = new ArrayList<MovieModel>();
        mGridLayoutManager = new GridLayoutManager(getActivity(),AppConstants.gridElements);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        ImageRecyclerAdapter imageRecyclerAdapter = new ImageRecyclerAdapter(getActivity(),R.layout.imageview_adapter,R.id.imageView,mMovieModels,this);
        mRecyclerView.setAdapter(imageRecyclerAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItems,totalItems, firstVisibleItem, lastVisibleItem;
                totalItems = mGridLayoutManager.getItemCount();
                visibleItems = mGridLayoutManager.getChildCount();
                firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();

                mCurrentPage = lastVisibleItem/mMoviesPerPage+1;
                if(mPagesLoaded == mCurrentPage && mPagesLoaded!=mTotalPages){
                    // Load more
                    MovieListRecyclerFragment movieListRecyclerFragment = (MovieListRecyclerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);

                    ServiceFactory.getPopularMovieList(mPagesLoaded+1,getActivity(),movieListRecyclerFragment);
                }

                Log.d(LOG_TAG," Total Items "+totalItems+" Visible Items "+visibleItems+" First Item "+firstVisibleItem+" Last Item "+lastVisibleItem);
            }
        });



    }

    private void loadPagesInitially(){
        for(int i=1;i<3;i++){
            ServiceFactory.getPopularMovieList(i,getActivity(),this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list_recycler,container,false);
        mRecyclerView = view.findViewById(R.id.recycleView);
        initializeDataAndAdapter();
        loadPagesInitially();
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
        ServiceFactory.getTotalPagesAndResults(context,this);
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
            mPagesLoaded++;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ImageRecyclerAdapter imageRecyclerAdapter = (ImageRecyclerAdapter) mRecyclerView.getAdapter();
        imageRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageFetcherResponse(int totalPages, int totalResults) {
        this.mTotalPages = totalPages;
        this.mTotalResults = totalResults;
    }

    @Override
    public void onErrorResponse(Exception error) {

    }

    @Override
    public void onItemClicked(int position) {
        mMovieClickListener.onMovieClick(mMovieModels.get(position));
    }
}
