package com.example.yanyan.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;
    private Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        movieList = Movie.getMovieFromFile("movies.json", this);
        adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                selectedMovie = movieList.get(position);

                Intent detailIntent = new Intent(mContext, MoviedetailActivity.class);
//              mListView.setTag(position);
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("poster", selectedMovie.poster);
//                detailIntent.putExtra("rowID",position);
                // rowID


//                startActivity(detailIntent);
                startActivityForResult(detailIntent, 1);

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if (resultCode ==RESULT_OK) {
                Seen_status(data);

            }
        }


    }

    public void Seen_status(Intent data){

        boolean seenBox = data.getBooleanExtra("seen", false);
        boolean wantBox = data.getBooleanExtra("want", false);
        boolean noBox = data.getBooleanExtra("no", false);
        int position = data.getIntExtra("rowID",-1);

        if (seenBox) {
            selectedMovie.seenStatus = "Already Seen";
        }
        else if (wantBox) {
            selectedMovie.seenStatus = "Want to See";
        }
        else if (noBox){
            selectedMovie.seenStatus = "Do not like";
        }
        else{
        }
        adapter.notifyDataSetChanged();
    }



}
