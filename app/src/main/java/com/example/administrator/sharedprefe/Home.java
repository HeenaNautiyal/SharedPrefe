package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Home extends AppCompatActivity{
    UserSessionManager session;


    ImageView iv_autor,iv_programe,iv_tvshow,iv_freestuff,
            iv_inquire,iv_connect,iv_contact,iv_services,iv_boook;
    String autor,programe,tvshow,freestuff,inquire,connect,contact,services,book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhome);
        session = new UserSessionManager(getApplicationContext());
        /*session.checkLogin();*/

        iv_autor=(ImageView)findViewById(R.id.i_author);
        iv_programe=(ImageView)findViewById(R.id.i_programe);
        iv_tvshow=(ImageView)findViewById(R.id.i_tvshows);
        iv_freestuff=(ImageView)findViewById(R.id.i_freestuff);
        iv_inquire=(ImageView)findViewById(R.id.i_inquire);
        iv_connect=(ImageView)findViewById(R.id.i_connect2);
        iv_contact=(ImageView)findViewById(R.id.i_contact);
        iv_services=(ImageView)findViewById(R.id.i_services);
        iv_boook=(ImageView)findViewById(R.id.i_book);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setVisibility(View.INVISIBLE);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Home");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        iv_autor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Authorchange.class);
              startActivity(intent);
            }
        });
        iv_programe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Programe.class);
                startActivity(intent);
            }
        });
        iv_tvshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Home.this,Tvshow.class);
              startActivity(intent);
            }
        });iv_freestuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,FreeStuff.class);
                startActivity(intent);
            }
        });
        iv_inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Inquery.class);
                startActivity(intent);
            }
        });iv_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Connect.class);
                startActivity(intent);
            }
        });
        iv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Contact.class);
                startActivity(intent);
            }
        });
        iv_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Services.class);
              startActivity(intent);
            }
        });
        iv_boook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Home.this,Books.class);
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
