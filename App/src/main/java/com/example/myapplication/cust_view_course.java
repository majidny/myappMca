package com.example.myapplication;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class cust_view_course extends BaseAdapter {
    String[] coursename,description,duration,fee;
    private Context context;

    public cust_view_course(Context appcontext,String[]coursename,String[]description,String[]duration,String[]fee)
    {
        this.context=appcontext;

        this.coursename=coursename;
        this.description=description;
        this.duration=duration;
        this.fee=fee;

    }

    @Override
    public int getCount() {
        return coursename.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.cust_view_crs,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView38);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView40);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView42);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView44);




        tv1.setTextColor(Color.BLACK);


        tv1.setText(coursename[i]);
        tv2.setText(description[i]);
        tv3.setText(duration[i]);
        tv4.setText(fee[i]);







//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":8000/media/"+Image[i]+".jpg";
//
//
//        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}
