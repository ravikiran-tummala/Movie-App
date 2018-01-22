package ravikirantummala.movieapp.CustomAdapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ravikirantummala.movieapp.Models.MovieModel;

/**
 * Created by ravikirantummala on 19/01/18.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<MovieModel> mMovieModels;
    private int mImageViewResourceId;
    private int mResourceLayout;
    private LayoutInflater mInflater;
    private static final String baseURL = "http://image.tmdb.org/t/p/";
    private static final String imageSize = "w185";

    public ImageAdapter(@NonNull Context context, @LayoutRes int resource,
                        @IdRes int imageViewResourceId, @NonNull List<MovieModel> movieModels){
        this.mInflater = (LayoutInflater.from(context));
        this.mContext = context;
        this.mMovieModels = movieModels;
        this.mImageViewResourceId = imageViewResourceId;
        this.mResourceLayout = resource;
    }
    @Override
    public int getCount() {
        return mMovieModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            View view = mInflater.inflate(mResourceLayout,null);
            imageView = view.findViewById(mImageViewResourceId);
        }else{
            imageView = (ImageView) convertView;
        }

        Uri.Builder builder = Uri.parse(baseURL).buildUpon()
                                    .appendPath(imageSize)
                                    .appendEncodedPath(this.mMovieModels.get(position).getPosterPath());

        String url = builder.toString();
        Picasso.with(mContext)
                .load(url)
                .into(imageView);
        return imageView;
    }
}
