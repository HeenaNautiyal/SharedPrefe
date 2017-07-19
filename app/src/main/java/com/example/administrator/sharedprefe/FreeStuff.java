package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 8/2/2016.
 */
public class FreeStuff extends AppCompatActivity {
    GridView grid;
    ImageView iv_article,iv_vedio,iv_audio;
    TextView tt1,tt2,tt3;
    ImageView tback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freestuff);
        //  CustomGrid adapter = new CustomGrid(FreeStuff.this, web, imageId);
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Free Stuff");
        tv.setTextSize(25);

        tv.setTextColor(Color.WHITE);
        iv_article=(ImageView)findViewById(R.id.i_author);
        iv_vedio=(ImageView)findViewById(R.id.i_programe);
        iv_audio=(ImageView)findViewById(R.id.i_tvshows);
        tt1=(TextView)findViewById(R.id.tv1);
        tt2=(TextView)findViewById(R.id.tv2);
        tt3=(TextView)findViewById(R.id.tv3);
        tt1.setTypeface(tf2);
        tt2.setTypeface(tf2);
        tt3.setTypeface(tf2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FreeStuff.this, Home.class);
                startActivity(it);
            }
        });
        iv_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeStuff.this,Pdf_Article.class);
                startActivity(intent);
            }
        });
        iv_vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeStuff.this,Manual_Vedio.class);
                startActivity(intent);
            }
        });
        iv_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeStuff.this,Manual_Audio.class);
                startActivity(intent);
            }
        });



    }
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void onResume(){
        super.onResume();
    }
}
