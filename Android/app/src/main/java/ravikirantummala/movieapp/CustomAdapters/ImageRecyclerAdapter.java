package ravikirantummala.movieapp.CustomAdapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import ravikirantummala.movieapp.Common.AppConstants;
import ravikirantummala.movieapp.Common.Utility;
import ravikirantummala.movieapp.Models.MovieModel;

/**
 * Created by ravikirantummala on 01/02/18.
 */



public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieModel> mMovieModels;
    private int mImageViewResourceId;
    private int mResourceLayout;
    private LayoutInflater mInflater;
    private static final String imageSize = "w185";
    private OnItemClickListener mOnItemClickListener;

    public ImageRecyclerAdapter(@NonNull Context context, @LayoutRes int resource,
                        @IdRes int imageViewResourceId, @NonNull List<MovieModel> movieModels,@NonNull OnItemClickListener onItemClickListener){
        this.mInflater = (LayoutInflater.from(context));
        this.mContext = context;
        this.mMovieModels = movieModels;
        this.mImageViewResourceId = imageViewResourceId;
        this.mResourceLayout = resource;
        this.mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ViewHolder(ImageView v){
            super(v);
            imageView = v;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(mResourceLayout,parent,false);
        ImageView imageView = view.findViewById(mImageViewResourceId);
        view.removeView(imageView);
        ViewHolder viewHolder = new ViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ImageView imageView = holder.imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(position);
            }
        });
        Utility.loadPicasso(AppConstants.POSTER_BASE_URL,imageSize,mMovieModels.get(position),mContext,imageView);

    }

    @Override
    public int getItemCount() {
        return mMovieModels.size();
    }

    public interface OnItemClickListener{
        public void onItemClicked(int position);
    }
}
