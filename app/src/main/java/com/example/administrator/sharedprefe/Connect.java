package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Connect extends AppCompatActivity {
    GridView grid;
    String[] web = {
            "facebook",
            "Google",
            "linkedin",
            "instagram",
            "youtube",
            "pinterest"

    };
    int[] imageId = {
            R.drawable.facebook2,
            R.drawable.google2,
            R.drawable.linkedin2,
            R.drawable.instagram2,
            R.drawable.you2,
            R.drawable.pinterest2


    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        CustomGrid adapter = new CustomGrid(Connect.this, web, imageId);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Connect with us");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Connect.this, Home.class);
                startActivity(it);
            }
        });
        grid = (GridView) findViewById(R.id.gv);
        grid.setAdapter((android.widget.ListAdapter) adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fgroups%2F519440751560230%2F"));
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fgroups%2F519440751560230%2F"));
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.linkedin.com/in/rituchopra"));
                    startActivity(intent);
                }
                if(position==3)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/coachrituchopra/"));
                    startActivity(intent);
                }
                if(position==4)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/channel/UCl-_EURJfzPEiMyDJho2xuQ"));
                    startActivity(intent);
                }
                if(position==5)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.pinterest.com/CoachRChopra/"));
                    startActivity(intent);
                }
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

