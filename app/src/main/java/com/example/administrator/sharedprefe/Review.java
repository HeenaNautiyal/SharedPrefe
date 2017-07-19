package com.example.administrator.sharedprefe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Review extends AppCompatActivity {
    TextView tv;
    String id,uri1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articlenew);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        Intent intent=getIntent();
        id = intent.getStringExtra("Title");

        uri1 = intent.getStringExtra("Url1");
        View view =getSupportActionBar().getCustomView();
        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText(id);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/Nexa Light.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Review.this, Pdf_Article.class);
                startActivity(it);
            }
        });

        WebView webView=new WebView(Review.this);
        // webView = (WebView) findViewById(R.id.web1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new Callback());
        String pdfURL = uri1;
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+pdfURL);
        setContentView(webView);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void onResume(){
        super.onResume();
    }

}
