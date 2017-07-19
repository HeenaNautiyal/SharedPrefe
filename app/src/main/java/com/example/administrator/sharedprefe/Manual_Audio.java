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

public class Manual_Audio extends AppCompatActivity {
    LinearLayout layMastering,layuntappotential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_audio);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Audios");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Manual_Audio.this, FreeStuff.class);
                startActivity(it);
            }
        });
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(INVISIBLE);
        layuntappotential=(LinearLayout)findViewById(R.id.lay_untappotential);
        layMastering=(LinearLayout)findViewById(R.id.lay_Mastering);
        layuntappotential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Audiourl.class);
                intent.putExtra("Title","Explore Your Untap Potential");
                intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/audio/Copy%20of%20Part%201%20Author%20Ritu%20Chopra.mp3");
                startActivity(intent);
            }
        });
        layMastering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Audiourl.class);
                intent.putExtra("Title","Seminar of Mastering life");
                intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/audio/Copy%20of%20Part%202%20Author%20Ritu%20Chopra%201.mp3");
                startActivity(intent);
            }
        });
    }

}
