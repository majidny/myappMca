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

public class cust_view_students extends BaseAdapter {
    String[] name,email,phonenumber,coursename;
    private Context context;

    public cust_view_students(Context appcontext,String[]name,String[]email,String[]phonenumber,String[]coursename)
    {
        this.context=appcontext;


        this.name=name;
        this.email=email;
        this.phonenumber=phonenumber;
        this.coursename=coursename;


    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView=inflator.inflate(R.layout.cust_view_students,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView52);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView54);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView56);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView58);




        tv1.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(email[i]);
        tv3.setText(phonenumber[i]);
        tv4.setText(coursename[i]);








//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":8000/media/"+image[i]+".jpg";
//
//
//        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}
