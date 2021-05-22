package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class cust_student_viewcarrier_Applied extends BaseAdapter {
    String[]date,title,discription,file,lastdat,id;
    private Context context;

    public cust_student_viewcarrier_Applied(Context appcontext, String[] date, String[] title, String[] discription, String[] file, String[] lastdat, String [] id)
    {
        this.context=appcontext;

        this.date=date;
        this.title=title;
        this.discription=discription;
        this.file=file;
        this.lastdat=lastdat;
        this.id=id;

    }

    @Override
    public int getCount() {
        return date.length;
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
            gridView=inflator.inflate(R.layout.cust_student_viewcarrierapplied,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tvtitle=(TextView)gridView.findViewById(R.id.textView16);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView27);
        TextView tvdescription=(TextView)gridView.findViewById(R.id.textView45);
        TextView tvlastdate=(TextView)gridView.findViewById(R.id.textView49);


        tvtitle.setText(title[i]);
        tvdate.setText(date[i]);
        tvdescription.setText(discription[i]);
        tvlastdate.setText(lastdat[i]);




        return gridView;
    }
}
