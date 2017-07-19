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
public class Login extends AppCompatActivity {
    EditText username, password;
    UserSessionManager session;
    String user, pass;
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new UserSessionManager(getApplicationContext());
        username = (EditText) findViewById(R.id.ed_lastName);
        password = (EditText) findViewById(R.id.ed_password);

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

        tv.setText("Master Life");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/Nexa Light.otf");
        tv.setTypeface(tf);

        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, MainActivity.class);
                startActivity(it);
            }
        });
        btn = (Button) findViewById(R.id.btn_Submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                if(user.matches("")||pass.matches(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    TextView myMsg = new TextView(Login.this);
                    myMsg.setText("Warning!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.WHITE);
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
                    username.setText("");
                    password.setText("");
                }
                else{
                    new log().execute();
                }
            }
        });

    }

    private class log extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            user = username.getText().toString();
            pass = password.getText().toString();
        }
        @Override
        protected String doInBackground(String... params) {

            HttpClient httpClient = new DefaultHttpClient();
            String url = "http://www.rituchopra.com/mobile_app/login.php?email=" + user+ "&password=" + pass + "";
            String SetServerString = "";
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                SetServerString = httpClient.execute(httpget, responseHandler);
            } catch (IOException e) {e.printStackTrace();}
            Log.d("Response: ", "> " + SetServerString);
            return SetServerString;
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResult = new JSONObject(result);
                String message = jsonResult.getString("message");
                Log.d("Response: ", "> " + message);
                if (message.equals("1")) {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Login.this);
                    TextView myMsg = new TextView(Login.this);
                    myMsg.setText("Congratulations!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.BLACK);
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("You have successfully logged in to Serenity app!");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    session.createUserLoginSession(user,pass);
                                    Intent it = new Intent(getApplicationContext(), Home.class);
                                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(it);
                                }
                            });
                    builder.show();
                }
                else if (message.equals("null"))
                {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Login.this);
                    TextView myMsg = new TextView(Login.this);
                    myMsg.setText("Sorry for the inconvinence");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.BLACK);
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("Please add registered mail id only.");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                    Intent it= new Intent(Login.this,MainActivity.class);
                                    startActivity(it);
                                }
                            });
                    builder.show();
                }

            } catch (JSONException e) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Login.this);
                TextView myMsg = new TextView(Login.this);
                myMsg.setText("Some error has occured while recieving your request.\n Please come in sometime.!");
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
                                Intent it= new Intent(Login.this,MainActivity.class);
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
}
