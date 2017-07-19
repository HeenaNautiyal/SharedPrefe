package com.example.administrator.sharedprefe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Authorchange extends AppCompatActivity {
    ArrayList<Data1> datalist;
    TextView tvDescription;
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer);
        datalist = new ArrayList<Data1>();
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(
                R.drawable.gradient));
        tvDescription = (TextView)findViewById(R.id.tvDescriptionn);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Authorchange.this, Home.class);
                startActivity(it);
            }
        });
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(View.INVISIBLE);
        TextView tv=(TextView)view.findViewById(R.id.textv);
        tv.setText("About the Author");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts2/Nexa Bold.otf");
        tv.setTypeface(tf);
        tv.setTextSize(25);
        tv.setTextColor(Color.WHITE);
        new JSONAsyncTask().execute("http://www.rituchopra.com/mobile_app/author.php");
        String abc="<p>Ritu is Founder and President of Everest Learning, a writer, TV show host, motivational speaker and a leadership coach. Over 25 years in Fortune 500 companies gave her tremendous opportunities to grow her professional expertise to a level that encompasses wide range of skills and leadership experiences. Ritu wants to part her knowledge and gives back to the community.</p>" +
                "<p>On the personal side, while balancing work and family, Ritu also managed to keep her passion alive over the years. Her books,&nbsp;<strong>Art of Life</strong>&nbsp;and&nbsp;<strong>Mastering Life,&nbsp;<em>Exploring your untapped potential to reach new heights</em></strong>&nbsp;show clearly her passion&nbsp;<em>to help individuals who want to help themselves</em>. She is a leadership coach and NLP practitioner.</p>" +
                "<p>She is a host of an inspirational TV show&nbsp;<em><strong>Despite the Challenges</strong></em>&nbsp;televised via Princeton Community Television. Her show provides a platform for people to shine their talents despite the barriers they face.</p>" +
                "<p>She is an advocate of Domestic Violence awareness &amp; prevention, and actively supports the violence victims and survivors. Ritu has presented internationally and can speak on various leadership development, empowerment and topics about role of spirituality in our lives and seeking fullness and meaning in life.</p>" +
                "<p>For further information to invite her for speaking engagements, please contact at: Info@RituChopra.com</p>";
        Spannable text= (Spannable) Html.fromHtml(abc);
        tvDescription.setText(text);

        ListView listview = (ListView) findViewById(R.id.list);
        adapter = new DataAdapter(getApplicationContext(), R.layout.row4, datalist);

        listview.setAdapter((ListAdapter) adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                // Toast.makeText(getApplicationContext(), datalist.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Authorchange.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONObject jarray = jsono.getJSONObject("result");

                    String jimage= jarray.getString("author_image");
                    Data1 actor = new Data1();

                    // actor.setName(jid);

                    //  actor.setDob(jcontent);

                    actor.setImage(jimage);
                    datalist.add(actor);
                }
                return true;
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            //------------------>>
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();

            adapter.notifyDataSetChanged();
        }
    }

    public void onResume(){
        super.onResume();
    }
}
