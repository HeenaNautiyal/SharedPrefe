package com.example.administrator.sharedprefe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Programe extends AppCompatActivity {
    TextView tv;
    WebView webView;
    ProgressDialog pb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programes);
        webView = (WebView) findViewById(R.id.web1);
        webView.setWebViewClient(new MyWebViewClient());
        tv=(TextView)findViewById(R.id.textView11);
        String url = "http://rituchopra.com/mobile_app/store.php";

        pb = new ProgressDialog(Programe.this);
        pb.setMessage("Please wait while Loading...");
        pb.show();
        pb.setCancelable(false);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('hiconleft')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('bill_main_con')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('top_mid_con')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('logo_con')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('tag_line_con')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('footer_mid_con')[0].style.display='none'; })()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('responsive-menu-button responsive-menu-boring\n" +
                        "         responsive-menu-accessible')[0].style.display='none'; })()");
                pb.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( URLUtil.isNetworkUrl(url) ) {
                    return false;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
                return true;
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        });
        webView.loadUrl(url);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),Home.class);
                startActivity(it);
            }
        });
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void onResume(){
        super.onResume();
    }
}
