package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

/**
 * Created by heena on 7/14/2017.
 */

public class Pdf_Article extends AppCompatActivity {
LinearLayout lymoney,lyself;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_article);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Articles");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Pdf_Article.this, FreeStuff.class);
                startActivity(it);
            }
        });
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(INVISIBLE);

        lymoney=(LinearLayout)findViewById(R.id.lay_money);
        lyself=(LinearLayout)findViewById(R.id.lay_self);
        lymoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Pdf_Article.this,Review.class);
                intent.putExtra("Title","Money Matters");
                intent.putExtra("Url1","http://rituchopra.com/mobile_app/articles/Serenity-Money-Matters.pdf");
                startActivity(intent);
            }
        });
        lyself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Pdf_Article.this,Review.class);
                intent.putExtra("Title","Self-doubt");
                intent.putExtra("Url1","http://rituchopra.com/mobile_app/articles/Serenity-Self-Doubt.pdf");
                startActivity(intent);
            }
        });
    }
}
