package com.example.administrator.sharedprefe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 8/2/2016.
 */
public class ActorAdapter extends ArrayAdapter<Actors> {
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    ArrayList<Actors> actorList;

    public ActorAdapter(Context context, int resource, ArrayList<Actors> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        actorList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvDescription=(TextView)v.findViewById(R.id.tvdescription);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvName.setText(actorList.get(position).getName());
        holder.tvDescription.setText(actorList.get(position).getDescription());
        return v;

    }
    static class ViewHolder {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;


    }
    public void updateAdapter(ArrayList<Actors> actorList) {
        this.actorList = actorList;

    }
}
