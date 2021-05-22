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

public class cust_view_faculty extends BaseAdapter {
    String[]Image,name,email,phonenumber,housname,place,quali,expp,id;
    private Context context;

    public cust_view_faculty(Context appcontext,String[]Image,String[]name,String[]email,String[]phonenumber,String[]housname,String[]place,String[]quali,String[]expp,String [] id )
    {
        this.context=appcontext;

        this.Image=Image;
        this.name=name;
        this.email=email;
        this.phonenumber=phonenumber;
        this.housname=housname;
        this.place=place;
//        this.post=post;
//        this.pin=pin;
        this.quali=quali;
        this.expp=expp;
        this.id=id;



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
            gridView=inflator.inflate(R.layout.cust_view_faculties,null);

        }
        else
        {
            gridView=(View)view;

        }
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);
        TextView tv1=(TextView)gridView.findViewById(R.id.textView46);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView48);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView50);



        tv1.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(email[i]);
        tv3.setText(phonenumber[i]);






        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+Image[i];


        Picasso.with(context).load(url). into(im);


        Button b3=(Button) gridView.findViewById(R.id.button3);

        b3.setTag(Image[i]+"#"+name[i]+"#"+email[i]+"#"+phonenumber[i]+"#"+housname[i]+"#"+place[i]+"#"+quali[i]+"#"+expp[i]);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kk=v.getTag().toString();
                String[]ar=kk.split("#");
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);


                SharedPreferences.Editor ed=sh.edit();
                ed.putString("Image",ar[0]);
                ed.putString("name",ar[1]);
                ed.putString("email",ar[2]);
                ed.putString("phonenumber",ar[3]);
                ed.putString("housname",ar[4]);
                ed.putString("place",ar[5]);
                ed.putString("qualification",ar[6]);
                ed.putString("experince",ar[7]);

                ed.commit();
                Intent i=new Intent(context,fc_view_faculty_more.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);




            }
        });
  Button bchat=(Button) gridView.findViewById(R.id.button28);

        bchat.setTag(id[i]);
        bchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kk=v.getTag().toString();


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);


                SharedPreferences.Editor ed=sh.edit();
                ed.putString("facid", kk);
                ed.commit();
                Intent i=new Intent(context,TestStudentchatwithfaculty.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);




            }
        });

        return gridView;
    }
}
