package com.example.administrator.sharedprefe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
  //  UserSessionManager session;
    TextView tv1, tv2;
    Button btnrg, btnlg;
    CheckBox chk;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        //session = new UserSessionManager(getApplicationContext());
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts2/Nexa Bold.otf");

        btnrg = (Button) findViewById(R.id.register);

        btnrg.setTypeface(font);
        btnlg = (Button) findViewById(R.id.login);

        btnlg.setTypeface(font);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setTypeface(font);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setTypeface(font);
        chk = (CheckBox) findViewById(R.id.checkBox1);
        chk.setTypeface(font);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((chk).isChecked()) {
                    Intent it = new Intent(MainActivity.this, Register.class);
                    startActivity(it);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    TextView myMsg = new TextView(MainActivity.this);
                    myMsg.setText("Warning!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);

                    myMsg.setTextColor(Color.WHITE);
                    //set custom title
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("Please select this condition.");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            });

                    builder.show();
                }
            }
        });

        btnlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((chk).isChecked()) {
                    Intent it = new Intent(MainActivity.this, Login.class);
                    startActivity(it);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    TextView myMsg = new TextView(MainActivity.this);
                    myMsg.setText("Warning!");
                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                    myMsg.setTextSize(20);

                    myMsg.setTextColor(Color.WHITE);
                    //set custom title
                    builder.setCustomTitle(myMsg);
                    builder.setMessage("Please select this condition");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                }
                            });

                    builder.show();
                }
            }
        });

    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }


}