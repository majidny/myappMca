package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class stu_view_profile extends AppCompatActivity {

    com.mikhaellopez.circularimageview.CircularImageView pro;
    TextView name,email,phon,housename,place,post,pin,coursename,btc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_view_profile);

        pro=(com.mikhaellopez.circularimageview.CircularImageView)findViewById(R.id.img);
        name=(TextView)findViewById(R.id.ifg);

        email=(TextView)findViewById(R.id.ks);
        phon=(TextView)findViewById(R.id.textView143);
        housename=(TextView)findViewById(R.id.textView145);
        place=(TextView)findViewById(R.id.textView147);
        post=(TextView)findViewById(R.id.textView149);
        pin=(TextView)findViewById(R.id.textView151);
        coursename=(TextView)findViewById(R.id.textView153);
        btc=(TextView)findViewById(R.id.textView155);




        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/stock/ad_student_view_profile/";
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


                                String name2=jsonObj.getString("name");
                                String email2=jsonObj.getString("email");

                                String phonenumber2=jsonObj.getString("phonenumber");
                                String house2=jsonObj.getString("house");
                                String place2=jsonObj.getString("place");
                                String post2=jsonObj.getString("post");
                                String pin2=jsonObj.getString("pin");
                                String qualif=jsonObj.getString("coursename");
                                String expern=jsonObj.getString("batchname");

                                String img22=jsonObj.getString("image");


                                name.setTextColor(Color.BLACK);
                                email.setTextColor(Color.BLACK);
                                phon.setTextColor(Color.BLACK);
                                housename.setTextColor(Color.BLACK);
                                place.setTextColor(Color.BLACK);
                                post.setTextColor(Color.BLACK);
                                pin.setTextColor(Color.BLACK);
                                coursename.setTextColor(Color.BLACK);
                                btc.setTextColor(Color.BLACK);



                                name.setText(name2);
                                email.setText(email2);
                                phon.setText(house2);
                                housename.setText(phonenumber2);
                                place.setText(place2);
                                post.setText(post2);
                                pin.setText(pin2);
                                coursename.setText(qualif);
                                btc.setText(expern);












                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip=sh.getString("ip","");
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("img",img22);
                                ed.commit();
                                String url="http://" + ip + ":8000"+img22;
//                                Toast.makeText(getApplicationContext(), "pt="+url , Toast.LENGTH_SHORT).show();
                                Picasso.with(getApplicationContext()).load(url).into(pro);

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
