package com.example.administrator.sharedprefe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spannable;
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
public class DataAdapter  extends ArrayAdapter<Data1> {
    ArrayList<Data1> datalist;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    public DataAdapter(Context context, int resource, ArrayList<Data1> objects) {
        super(context, resource,objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        datalist = objects;
    }

    private class ViewHolder {
        public ImageView imageview;
        public TextView tvDescription;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.imageview.setImageResource(R.drawable.circle);
        new DownloadImageTask(holder.imageview).execute(datalist.get(position).getImage());
        String abc="<p>Ritu is Founder and President of Everest Learning, a writer, TV show host, motivational speaker and a leadership coach. Over 25 years in Fortune 500 companies gave her tremendous opportunities to grow her professional expertise to a level that encompasses wide range of skills and leadership experiences. Ritu wants to part her knowledge and gives back to the community.</p>" +
                "<p>On the personal side, while balancing work and family, Ritu also managed to keep her passion alive over the years. Her books,&nbsp;<strong>Art of Life</strong>&nbsp;and&nbsp;<strong>Mastering Life,&nbsp;<em>Exploring your untapped potential to reach new heights</em></strong>&nbsp;show clearly her passion&nbsp;<em>to help individuals who want to help themselves</em>. She is a leadership coach and NLP practitioner.</p>" +
                "<p>She is a host of an inspirational TV show&nbsp;<em><strong>Despite the Challenges</strong></em>&nbsp;televised via Princeton Community Television. Her show provides a platform for people to shine their talents despite the barriers they face.</p>" +
                "<p>She is an advocate of Domestic Violence awareness &amp; prevention, and actively supports the violence victims and survivors. Ritu has presented internationally and can speak on various leadership development, empowerment and topics about role of spirituality in our lives and seeking fullness and meaning in life.</p>" +
                "<p>For further information to invite her for speaking engagements, please contact at: Info@RituChopra.com</p>";
        Spannable text= (Spannable) Html.fromHtml(abc);
        return v;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView imageview) {
            this.bmImage=imageview;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
