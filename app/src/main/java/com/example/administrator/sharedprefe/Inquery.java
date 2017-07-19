package com.example.administrator.sharedprefe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Inquery extends AppCompatActivity {
    EditText fname1,lname1,email1,Query1;
    String fname, lstname, emai, Query;
    Button btnsub;
    ImageView tback;
    TextView textview,textView2,textView3,textView4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        Typeface font = Typeface.createFromAsset(getAssets(),  "fonts2/Nexa Bold.otf");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("Inquire");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Inquery.this, Home.class);
                startActivity(it);
            }
        });

        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView2.setTypeface(font);
        textView3.setTypeface(font);
        textView4.setTypeface(font);

        fname1 = (EditText) findViewById(R.id.ed_firstName);
        lname1 = (EditText) findViewById(R.id.ed_email);
        email1 = (EditText) findViewById(R.id.ed_lastName);
        Query1 = (EditText) findViewById(R.id.ed_Query);
        btnsub = (Button) findViewById(R.id.btn_Submit);

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = fname1.getText().toString();
                lstname = lname1.getText().toString();
                emai = email1.getText().toString();
                Query = Query1.getText().toString();
                if (fname.matches("") || lstname.matches("") || emai.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Inquery.this);
                    TextView myMsg = new TextView(Inquery.this);
                    myMsg.setText("Warning!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);

                    myMsg.setTextColor(Color.WHITE);
                    //set custom title
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("All fields are mendatory");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();

                                }
                            });

                    builder.show();
                    //Toast.makeText(getApplicationContext(), "All fields are mendatory", Toast.LENGTH_SHORT).show();
                    fname1.setText("");
                    lname1.setText("");
                    email1.setText("");
                }
                else
                {
                    new query().execute();
                }
            }
        });
    }

    private class query extends AsyncTask<String, String, String> {

        protected void onPreExecute()
        {
            super.onPreExecute();
            lstname = lname1.getText().toString();
            fname = fname1.getText().toString();
            emai = email1.getText().toString();
            Query=Query1.getText().toString();
        }
        @Override
        protected String doInBackground(String... params) {

            HttpClient httpClient = new DefaultHttpClient();
            String url ="http://rituchopra.com/Android_aPP/insertdata.php?caseid=1&email="+emai.replaceAll(" ","")+"" +
                    "&fname="+fname.replaceAll(" ","%20")+"&lname="+lstname.replaceAll(" ","%20")+"&Enquiry="+Query.replaceAll(" ","%20")+"";

            /*String url = "http://rituchopra.com/mobile_app/getenquiry.php?name="+fname.replaceAll(" ","%20")+"" +
                    "&lname="+lstname.replaceAll(" ","%20")+"&email="+emai+"&message="+Query.replaceAll(" ","%20")+"";*/
            String SetServerString = "";
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                SetServerString = httpClient.execute(httpget, responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + SetServerString);
            return SetServerString;
        }

        protected void onPostExecute(String result) {
            // dismiss the dialog once product deleted
            try {
                JSONObject jsonResult = new JSONObject(result);
                String message = jsonResult.getString("udata");
                if (message.equals("1")) {

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Inquery.this);
                    TextView myMsg = new TextView(Inquery.this);
                    myMsg.setText("Thank you for contacting us. !");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.BLACK);
                    builder.setCustomTitle(myMsg)
                    .setMessage("We will get back to you shortly.")
                            .setPositiveButton("Thanks",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            dialog.cancel();
                                        }
                                    });
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Inquery.this);
                    TextView myMsg = new TextView(Inquery.this);
                    myMsg.setText("Warning!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);

                    myMsg.setTextColor(Color.WHITE);
                    //set custom title
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("Unable to get Query!");
                    builder.setPositiveButton("Sorry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.show();
                }
            } catch (JSONException e) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Inquery.this);
                TextView myMsg = new TextView(Inquery.this);
                myMsg.setText("Some error has occured while recieving your request. Please come in sometime.");
                myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                myMsg.setTextSize(20);
                myMsg.setTextColor(Color.BLACK);
                builder.setCustomTitle(myMsg);
                builder.setMessage("Sorry for the inconvinence");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                                Intent it= new Intent(Inquery.this,Home.class);
                                startActivity(it);
                            }
                        });
                builder.show();
                e.printStackTrace();
            }
        }
    }
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void onResume(){
        super.onResume();
    }

}

