package com.example.yanyan.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by yanyan on 2/12/18.
 */

public class MoviedetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView titleText;
    private ImageView posterImage;
    private TextView descriptionText;
    private RadioButton seen_Button;
    private RadioButton want_Button;
    private RadioButton no_Button;
    private Button submitButton;
    private Boolean alreadyChecked;
    private Boolean wantChecked;
    private Boolean noChecked;
    private int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext=this;
        titleText= findViewById(R.id.detail_title);
        posterImage =findViewById(R.id.detail_image);
        descriptionText = findViewById(R.id.detail_text);
        seen_Button =findViewById(R.id.already_seen);
        want_Button =findViewById(R.id.want_to_see);
        no_Button =findViewById(R.id.do_not_like);
        submitButton =findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                Intent radioIntent = new Intent();
                radioIntent.putExtra("seen", alreadyChecked);
                radioIntent.putExtra("want", wantChecked);
                radioIntent.putExtra("no",noChecked);
//                radioIntent.putExtra("rowID", position);

                setResult(RESULT_OK, radioIntent);

                finish();
            }




        });


//        String username= this.getIntent().getExtras().getString("username");

        String title = this.getIntent().getExtras().getString("title");
        this.setTitle(title);
        String description = this.getIntent().getExtras().getString("description");
        position = this.getIntent().getExtras().getInt("rowID");
        titleText.setText(title);
        descriptionText.setText(description);
        Picasso.with(mContext).load(this.getIntent().getExtras().getString("poster")).into(posterImage);




    }


    public void alreadyChecked(View view){
        alreadyChecked = ((RadioButton) view).isChecked();

    }
    public void wantChecked(View view){
        wantChecked =((RadioButton) view).isChecked();
    }
    public void noChecked(View view) {
        noChecked = ((RadioButton) view).isChecked();
    }


}
