package com.example.yanyan.miniapp1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by yanyan on 2/7/18.
 */

public class Movie {

    // instant variable or fields
    public String title;
    public String episode_number;
    public String main_characters;
    public String description;
    public String poster;
    public String url;
    public String seenStatus;

    //Constructor
    //method

    //Constructor
    //method
    public static ArrayList<Movie> getMovieFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try{
            String jsonString = loadJsonFromAsset("movies.json",context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode_number = movies.getJSONObject(i).getString("episode_number");
                movie.main_characters = movies.getJSONObject(i).getString("main_characters");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.seenStatus = "Has Seen?";
                movieList.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;

    }


    //read from the json object
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
