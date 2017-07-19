package com.example.administrator.sharedprefe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.view.View.INVISIBLE;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Audio extends AppCompatActivity{
    ArrayList<Actors> actorsList;
    ActorAdapter adapter;
    Button btn_continue;
    String TAG="MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute("http://rituchopra.com/mobile_app/audio.php");

        ListView listview = (ListView)findViewById(R.id.list);
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
                Intent it = new Intent(Audio.this, FreeStuff.class);
                startActivity(it);
            }
        });
        ImageButton imageButton2= (ImageButton)view.findViewById(R.id.action_Logout2);
        imageButton2.setVisibility(INVISIBLE);
        btn_continue=(Button)findViewById(R.id.btn_continue);
        adapter = new ActorAdapter(getApplicationContext(), R.layout.row2, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Actors actors=actorsList.get(position);
                actors.setIsRowSelected(true);
                actorsList.set(position,actors);
                adapter.updateAdapter(actorsList);
                Log.e(TAG,"-----------------------------");
                for(Actors actors1:actorsList){
                    if(
                            actors1.getIsRowSelected()){
                        int id = Integer.parseInt(actors.getid());
                        if(id==4)
                        {
                            Intent intent= new Intent(getApplicationContext(),Audiourl.class);
                            intent.putExtra("Title",actors.getName());
                            intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/audio/Copy%20of%20Part%201%20Author%20Ritu%20Chopra.mp3");
                            startActivity(intent);
                        }
                        else if(id==5){
                            Intent intent= new Intent(getApplicationContext(),Audiourl.class);
                            intent.putExtra("Title",actors.getName());
                            intent.putExtra("Url1","http://www.rituchopra.com/mobile_app/audio/Copy%20of%20Part%202%20Author%20Ritu%20Chopra%201.mp3");
                            startActivity(intent);
                        }
                        Log.e(TAG,"selected name: "+actors.getid());
                    }
                }
            }

        });
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Audio.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("result");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        Actors actor = new Actors();
                        actor.setId(object.getString("id"));
                        actor.setName(object.getString("title"));
                        actor.setDescription(object.getString("description"));
                        actor.setImage(object.getString("image"));
                        actorsList.add(actor);
                    }
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
        }
    }
    public void onResume(){
        super.onResume();
    }
}


