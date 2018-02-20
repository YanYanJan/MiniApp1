package com.example.yanyan.miniapp1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yanyan on 2/7/18.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        //initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Methods
    //a list of methods we need to override
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    //returns the item at the position
    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    //return the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            //inflate
            convertView = mInflater.inflate(R.layout.list_movie, parent, false);
            //add the views to the holder
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.charTextView = convertView.findViewById(R.id.movie_list_char);
            holder.seenTextView = convertView.findViewById(R.id.movie_list_seen);
            holder.movieImageView = convertView.findViewById(R.id.Movie_image);
            //add the holder to the view
            convertView.setTag(holder);
        } else {
            //get the view holder from converview
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView charTextView = holder.charTextView;
        TextView seenTextView = holder.seenTextView;
        ImageView movieImageView = holder.movieImageView;


        //get corresponding movie for each row
        Movie movie = (Movie) getItem(position);
        String seenStatus = movie.seenStatus;
        //titleTextview
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAsh));
        titleTextView.setTextSize(20);

        //descriptionTextView

        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAsh));
        descriptionTextView.setTextSize(9);

        //charTextView

        ArrayList<String> myList = new ArrayList<String>(Arrays.asList
                (movie.main_characters.split(",")));

//        String myList = (ArrayList)movie.main_characters

        charTextView.setText(myList.get(0)+ myList.get(1)+ myList.get(2));
        charTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAsh));
        charTextView.setTextSize(14);

        //seenTextView
        seenTextView.setText(seenStatus);
        seenTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAsh));
        seenTextView.setTextSize(11);

        //ImageView
        //Use picasso library to load image for the image url
        Picasso.with(mContext).load(movie.poster).into(movieImageView);

        return convertView;
    }


    // View holder custormize what you want ot put into the view
    //it depents on the layout design of your row
    //This will be a private static class you have to define

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView charTextView;
        public TextView seenTextView;
        public ImageView movieImageView;


    }
}