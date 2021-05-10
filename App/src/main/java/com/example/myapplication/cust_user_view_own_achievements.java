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

public class cust_user_view_own_achievements extends BaseAdapter {
    String[] date,file,name,discription,id,status;
    private Context context;

    public cust_user_view_own_achievements(Context appcontext, String[] date,String[] file,String[] name,String[] discription,String[] id,String[] status )
    {
        this.context=appcontext;

        this.date=date;
        this.file=file;
        this.name=name;
        this.discription=discription;
        this.id=id;
        this.status=status;
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
            gridView=inflator.inflate(R.layout.cust_user_view_schievemts,null);

        }
        else
        {
            gridView=(View)view;

        }
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView9);
        TextView tvname=(TextView)gridView.findViewById(R.id.textView124);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView125);
        TextView tvdescription=(TextView)gridView.findViewById(R.id.textView126);

        tvname.setText(name[i]);
        tvdate.setText(date[i]);
        tvdescription.setText(discription[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+file[i];
        Picasso.with(context).load(url). into(im);



        return gridView;
    }
}
