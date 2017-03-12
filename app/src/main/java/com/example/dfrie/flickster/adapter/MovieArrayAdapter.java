package com.example.dfrie.flickster.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dfrie.flickster.R;
import com.example.dfrie.flickster.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.R.layout.simple_list_item_1;
import static com.example.dfrie.flickster.R.id.tvOverview;
import static com.example.dfrie.flickster.R.id.tvReleaseDate;
import static com.example.dfrie.flickster.R.id.tvTitle;

/**
 * Created by dfrie on 3/8/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static int max_popularity;

    private static class ViewHolder {
        ImageView ivImage;
        ImageView ivBackdrop;
        TextView tvTitle;
        TextView tvOverview;
        ProgressBar pbPopularity;
        TextView tvReleaseDate;
        RatingBar rbStars;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
       super(context, simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (position==0) {
            max_popularity = (int)movie.getPopularity();
        }
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.tvTitle = (TextView)convertView.findViewById(tvTitle);
            viewHolder.tvOverview = (TextView)convertView.findViewById(tvOverview);
            viewHolder.ivImage = (ImageView)convertView.findViewById(R.id.ivMovieImage);
            viewHolder.ivBackdrop = (ImageView)convertView.findViewById(R.id.ivBackdropImage);

            viewHolder.pbPopularity = (ProgressBar)convertView.findViewById(R.id.pbPopularity);
            viewHolder.tvReleaseDate = (TextView)convertView.findViewById(tvReleaseDate);
            viewHolder.rbStars = (RatingBar)convertView.findViewById(R.id.rbStars);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        if (viewHolder.ivImage != null) {
            // Clear out image from convertView...  (not needed)
            //viewHolder.ivImage.setImageResource(0);
            //Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
            Picasso.with(getContext()).load(movie.getPosterPath())
                    .placeholder(R.drawable.loading)
                    .transform(new RoundedCornersTransformation(28, 28)).into(viewHolder.ivImage);
        }
        if (viewHolder.ivBackdrop != null) {
            // Clear out image from convertView...  (not needed)
            //viewHolder.ivBackdrop.setImageResource(0);
            //Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .placeholder(R.drawable.loading)
                    .transform(new RoundedCornersTransformation(28, 28)).into(viewHolder.ivBackdrop);
        }

        viewHolder.pbPopularity.setMax(max_popularity);
        viewHolder.pbPopularity.setProgress((int)movie.getPopularity());

        if (movie.getReleaseDate().substring(5,6).equals("1")) {
            viewHolder.tvReleaseDate.setText("("+movie.getReleaseDate().substring(5,7)+"/"
                    +movie.getReleaseDate().substring(0,4)+")");
        } else {
            viewHolder.tvReleaseDate.setText("("+movie.getReleaseDate().substring(6,7)+"/"
                    +movie.getReleaseDate().substring(0,4)+")");
        }

        //viewHolder.rbStars.setMax(5);
        viewHolder.rbStars.setRating((float)movie.getVoteAverage()/2);

        return convertView;
    }
}
