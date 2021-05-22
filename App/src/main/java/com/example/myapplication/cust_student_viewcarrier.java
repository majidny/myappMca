package com.example.myapplication;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;

public class cust_student_viewcarrier extends BaseAdapter {
    String[]date,title,discription,file,lastdat,id;
    private Context context;

    public cust_student_viewcarrier(Context appcontext, String[] date, String[] title, String[] discription, String[] file, String[] lastdat,String [] id)
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
            gridView=inflator.inflate(R.layout.cust_student_viewcarrier,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tvtitle=(TextView)gridView.findViewById(R.id.textView16);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView27);
        TextView tvdescription=(TextView)gridView.findViewById(R.id.textView45);
        TextView tvlastdate=(TextView)gridView.findViewById(R.id.textView49);
        Button bts=(Button) gridView.findViewById(R.id.button26);
        Button btapply=(Button) gridView.findViewById(R.id.button27);


        tvtitle.setText(title[i]);
        tvdate.setText(date[i]);
        tvdescription.setText(discription[i]);
        tvlastdate.setText(lastdat[i]);

        bts.setTag(file[i]);
        btapply.setTag(id[i]);


        btapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id= v.getTag().toString();

                ///////////////////////////////////////////



                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/stock/applyforvaccancy/";
                //  Toast.makeText(getApplicationContext(),"tt="+url,Toast.LENGTH_LONG).show();

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context, "Application  Added", Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(context, "Failed to apply", Toast.LENGTH_SHORT).show();

                                    }


                                    // }
//                                    else {
//                                     Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("cid", id);
                        params.put("sid",sh.getString("lid",""));

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);


















                /////////////////////////////////////////////////////


            }
        });



        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url_main = "http://" + hu + ":8000"+ v.getTag().toString();


                Intent b= new Intent(Intent.ACTION_VIEW);
                b.setDataAndType(Uri.parse(url_main),"application/pdf");
                b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(b);





            }
        });











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
