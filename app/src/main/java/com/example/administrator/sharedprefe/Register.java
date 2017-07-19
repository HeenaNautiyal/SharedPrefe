package com.example.administrator.sharedprefe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
public class Register  extends AppCompatActivity{
    EditText fname1, lname1, email1, pass1, confirm1, age1, countr1, username1, phno;
    String fname, lstname, emai, pass, age, username, country, number, confirm;
    Button btnsub;
    ImageView tback;
    TextView tvname1,tvname2,tvname3,tvname4,tvname5,tvname6,tvname7,tvname8;
    String Expn =
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface font = Typeface.createFromAsset(getAssets(),  "fonts2/Nexa Bold.otf");
        setContentView(R.layout.activity_register);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        final View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("REGISTER");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Register.this, MainActivity.class);
                startActivity(it);
            }
        });

        tvname1=(TextView)findViewById(R.id.textView2);
        tvname2=(TextView)findViewById(R.id.textView3);
        tvname3=(TextView)findViewById(R.id.textView4);
        tvname4=(TextView)findViewById(R.id.textView5);
        tvname5=(TextView)findViewById(R.id.textView6);
        tvname6=(TextView)findViewById(R.id.textView7);
        tvname7=(TextView)findViewById(R.id.textView8);
        tvname8=(TextView)findViewById(R.id.textView9);

        tvname1.setTypeface(font);
        tvname2.setTypeface(font);
        tvname3.setTypeface(font);
        tvname4.setTypeface(font);
        tvname5.setTypeface(font);
        tvname6.setTypeface(font);
        tvname7.setTypeface(font);
        tvname8.setTypeface(font);

        fname1 = (EditText) findViewById(R.id.ed_lastName);

        lname1 = (EditText) findViewById(R.id.ed_firstName);

        email1 = (EditText) findViewById(R.id.ed_email);

        pass1 = (EditText) findViewById(R.id.ed_password);

        confirm1 = (EditText) findViewById(R.id.ed_confirmpass);

        age1 = (EditText) findViewById(R.id.ed_age);

        age1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Register.this);
                    alertDialog.setMessage("Are you Age18+");
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    age1.setText("Yes");
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    age1.setText("NO");
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                }
            }
        });



        countr1 = (EditText) findViewById(R.id.ed_country);

        username1 = (EditText) findViewById(R.id.ed_username);
        btnsub = (Button) findViewById(R.id.btn_Submit);

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = fname1.getText().toString();
                lstname = lname1.getText().toString();
                emai = email1.getText().toString();
                pass = pass1.getText().toString();
                confirm = confirm1.getText().toString();
                age = age1.getText().toString();
                username = username1.getText().toString();
                country = countr1.getText().toString();
                if (fname.matches("") || lstname.matches("") || emai.matches("") || pass.matches("")
                        || confirm.matches("") || age.matches("") || username.matches("") ||
                        country.matches("")) {
                    android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                            Register.this);
                    alertDialogBuilder.setTitle("Warning!");
                    alertDialogBuilder
                            .setMessage("All fields are mendatory.")
                            .setCancelable(false)
                            .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    fname1.setText("");
                    lname1.setText("");
                    email1.setText("");
                    pass1.setText("");
                    fname1.setText("");
                    confirm1.setText("");
                    age1.setText("");
                    username1.setText("");
                    countr1.setText("");
                } else {
                    if (emai.matches(Expn) && emai.length() > 0) {
                        if (pass.equals(confirm)) {
                            new regi().execute();
                        } else {
                            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                                    Register.this);
                            alertDialogBuilder
                                    .setMessage("Password Do Not Match!!")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            pass1.setText("");
                        }
                    }else {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Register.this);
                        TextView myMsg = new TextView(Register.this);
                        myMsg.setText("Warning!");
                        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                        myMsg.setTextSize(20);
                        myMsg.setTextColor(Color.BLACK);
                        builder.setCustomTitle(myMsg);
                        builder.setMessage("Please enter a valid mail ID!");
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();
                    }
                }
            }
        });
    }


    private class regi extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
                       pass = pass1.getText().toString();
            fname = fname1.getText().toString();
            username = username1.getText().toString();
            emai = email1.getText().toString();
            age = age1.getText().toString();
            country = countr1.getText().toString();
            username = username1.getText().toString();
            lstname = lname1.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpClient httpClient = new DefaultHttpClient();
            String url ="http://rituchopra.com/mobile_app/registration.php?" +
                    "fname=" + fname + "&lname=" + lstname + "&email=" + emai + "&username=" + username +
                    "&password=" + pass + "&age=" + age + "&country=" + country+"";
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
            try {
                JSONObject jsonResult = new JSONObject(result);
                String message = jsonResult.getString("message");
                if (message.equals("1")) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    TextView myMsg = new TextView(Register.this);
                    myMsg.setText("Thanks!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.BLACK);
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("You have successfully registered to Serenity app! Please login again.");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Intent it = new Intent(Register.this,Home.class);
                                    startActivity(it);
                                }
                            });
                    builder.show();
                } else if (message.equals("0")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    TextView myMsg = new TextView(Register.this);
                    myMsg.setText("Sorry!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);
                    myMsg.setTextColor(Color.BLACK);
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("This email is already registered, please choose another one. ");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    email1.setText("");
                                }
                            });
                    builder.show();
                }
            } catch (JSONException e) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                TextView myMsg = new TextView(Register.this);
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
                                Intent it= new Intent(Register.this,MainActivity.class);
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
