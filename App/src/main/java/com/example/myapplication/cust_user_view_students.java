package com.example.myapplication;



import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class cust_user_view_students extends BaseAdapter {
    String[]   id,student_name,place,district,pin,contactno,email,photo,gender,dob;
    private Context context;

    public cust_user_view_students(Context appcontext, String[] id, String[] student_name, String[] place, String[] district, String[] pin, String[] contactno, String[] email,String [] photo,String [] gender,String []dob)
    {
        this.context=appcontext;


        this.id=id;
        this.student_name=student_name;
        this.place=place;
        this.district=district;
        this.pin=pin;
        this.contactno=contactno;
        this.email=email;
        this.photo=photo;
        this.gender=gender;
        this.dob=dob;



    }

    @Override
    public int getCount() {
        return student_name.length;
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
            gridView=inflator.inflate(R.layout.cust_user_students,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tvname=(TextView)gridView.findViewById(R.id.coursname);
        TextView tvemail=(TextView)gridView.findViewById(R.id.title);
        TextView tvaddress=(TextView)gridView.findViewById(R.id.textView56);
        TextView tvphone=(TextView)gridView.findViewById(R.id.textView58);
        com.mikhaellopez.circularimageview.CircularImageView img=(com.mikhaellopez.circularimageview.CircularImageView) gridView.findViewById(R.id.imageView8);


        tvname.setText(student_name[i]);
        tvemail.setText(email[i]);
        tvaddress.setText(place[i]);
        tvphone.setText("Ph:"+contactno[i]);









        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+photo[i];



        Picasso.with(context).load(url). into(img);

        return gridView;
    }
}
