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
import android.widget.TextView;


public class cust_batch_assign extends BaseAdapter {

    String[] btchname,btchyear,btchmonth,course,bid;
    private Context context;

    public cust_batch_assign(Context appcontext,String[]btchname,String[]btchyear,String[]btchmonth,String[]course,String [] bid)
    {
        this.context=appcontext;

        this.btchname=btchname;
        this.btchyear=btchyear;
        this.btchmonth=btchmonth;
        this.course=course;
        this.bid=bid;

    }

    @Override
    public int getCount() {
        return btchname.length;
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


        Button btattendance=(Button) gridView.findViewById(R.id.button29);
        Button btstudents=(Button) gridView.findViewById(R.id.button30);

        btattendance.setTag(bid[i]);
        btattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("selbid", v.getTag().toString());
                ed.commit();

                Intent ins= new Intent(context,fc_add_attendance.class);
                ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ins);

            }
        });


        btstudents.setTag(bid[i]);
        btstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("selbid", v.getTag().toString());
                ed.commit();

                Intent ins= new Intent(context,fc_view_students.class);
                ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ins);

            }
        });





        tv1.setTextColor(Color.BLACK);


        tv1.setText(btchname[i]);
        tv2.setText(btchyear[i]);
        tv3.setText(btchmonth[i]);
        tv4.setText(course[i]);







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
