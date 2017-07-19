package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

/**
 * Created by heena on 7/14/2017.
 */

public class Manual_Vedio extends AppCompatActivity {
    LinearLayout layMastering,layinerpeace,layspokenword,layDailyLife;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_vedio);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Videos");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Manual_Vedio.this, FreeStuff.class);
                startActivity(it);
            }
        });
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(INVISIBLE);
        layMastering=(LinearLayout)findViewById(R.id.lay_Mastering);
        layinerpeace=(LinearLayout)findViewById(R.id.lay_inerpeace);
        layspokenword=(LinearLayout)findViewById(R.id.lay_spokenword);
        layDailyLife=(LinearLayout)findViewById(R.id.lay_DailyLife);


        layinerpeace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),UrlVedioes.class);
                intent.putExtra("Title","How To Find Inner Peace");
                intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/video/Inner%20Peace%20video.mp4");
                startActivity(intent);
            }
        });
        layMastering.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(getApplicationContext(),UrlVedioes.class);
            intent.putExtra("Title","Mastering Life");
            intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/video/Mastering%20life%20video%201.mp4");
            startActivity(intent);
        }
       });
        layspokenword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),UrlVedioes.class);
                intent.putExtra("Title","Power of Spoken Word");
                intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/video/Power%20of%20SpokenWord.mp4");
                startActivity(intent);
            }
        });
    layDailyLife.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(getApplicationContext(),UrlVedioes.class);
            intent.putExtra("Title","Wisdome for Daily Life");
            intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/video/Wisdom%20FIXED.mp4");
            startActivity(intent);
        }
    });

    }
}
