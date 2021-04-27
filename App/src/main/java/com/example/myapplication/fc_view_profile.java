package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class fc_view_profile extends AppCompatActivity {
    ImageView prof;
    TextView name,email,mob,housname,place,post,pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc_view_profile);
        prof=(ImageView)findViewById(R.id.imageView);
        name=(TextView)findViewById(R.id.textView3);
        email=(TextView)findViewById(R.id.textView5);
        mob=(TextView)findViewById(R.id.textView7);
        housname=(TextView)findViewById(R.id.textView9);
        place=(TextView)findViewById(R.id.textView11);
        post=(TextView)findViewById(R.id.textView13);
        pin=(TextView)findViewById(R.id.textView15);

//        final String name1=name.getText().toString();
//        final String email1=email.getText().toString();
//        final String mob1=mob.getText().toString();
//        final String housename1=housname.getText().toString();
//        final String place1=place.getText().toString();
//        final String post1=post.getText().toString();
//        final String pin1=pin.getText().toString();


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/stock/ad_faculty_view_profile/";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                String name2=jsonObj.getString("facultyname");
                                String email2=jsonObj.getString("email");

                                String phonenumber2=jsonObj.getString("phonenumber");
                                String house2=jsonObj.getString("house");
                                String place2=jsonObj.getString("place");
                                String post2=jsonObj.getString("post");
                                String pin2=jsonObj.getString("pin");

                                String img22=jsonObj.getString("image");


                                name.setTextColor(Color.BLACK);
                                email.setTextColor(Color.BLACK);
                                housname.setTextColor(Color.BLACK);
                                mob.setTextColor(Color.BLACK);
                                place.setTextColor(Color.BLACK);
                                post.setTextColor(Color.BLACK);
                                pin.setTextColor(Color.BLACK);



                                name.setText(name2);
                                email.setText(email2);
                                housname.setText(house2);
                                mob.setText(phonenumber2);
                                place.setText(place2);
                                post.setText(post2);
                                pin.setText(pin2);












                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip=sh.getString("ip","");
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("img",img22);
                                ed.commit();
                                String url="http://" + ip + ":8000"+img22;
//                                Toast.makeText(getApplicationContext(), "pt="+url , Toast.LENGTH_SHORT).show();
                                Picasso.with(getApplicationContext()).load(url).into(prof);

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("lid","");

                params.put("lid",id);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);











    }
}
