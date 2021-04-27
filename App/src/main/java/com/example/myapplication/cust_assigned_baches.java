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

public class cust_assigned_baches extends BaseAdapter {
    String[] batchname,batchyr,batchmonth,coursename;
    private Context context;

    public cust_assigned_baches(Context appcontext,String[]batchname,String[]batchyr,String[]batchmonth,String[]coursename)
    {
        this.context=appcontext;

        this.batchname=batchname;
        this.batchyr=batchyr;
        this.batchmonth=batchmonth;
        this.coursename=coursename;

    }

    @Override
    public int getCount() {
        return batchname.length;
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
            gridView=inflator.inflate(R.layout.cust_view_assigned_batches,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView31);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView33);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView35);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView37);




        tv1.setTextColor(Color.BLACK);


        tv1.setText(batchname[i]);
        tv2.setText(batchyr[i]);
        tv3.setText(batchmonth[i]);
        tv4.setText(coursename[i]);







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
