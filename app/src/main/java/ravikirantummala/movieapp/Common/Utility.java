package ravikirantummala.movieapp.Common;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ravikirantummala.movieapp.Models.MovieModel;
import ravikirantummala.movieapp.R;

/**
 * Created by ravikirantummala on 25/01/18.
 */

public class Utility {
    public static final void loadPicasso(String baseURL,String imageSize, MovieModel movieModel, Context context, ImageView imageView) {
        Uri.Builder builder = Uri.parse(baseURL).buildUpon()
                .appendPath(imageSize)
                .appendEncodedPath(movieModel.getPosterPath());

        String url = builder.toString();
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.movie_placeholder_icon)
                .into(imageView);
    }
}
