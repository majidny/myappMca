package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

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


public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText ed1;
    EditText ed2;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1=(EditText)findViewById(R.id.editText2);
        ed2=(EditText)findViewById(R.id.editText4);
//        ed1.setText("abilash@gmail.com");
//        ed2.setText("1");
        ed1.setText("rohan@gmail.com");
        ed2.setText("92226");
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == btn) {
            final String uname = ed1.getText().toString();
            final String password = ed2.getText().toString();


            int flag = 0;


            if (uname.equalsIgnoreCase("")) {
                ed1.setError("*");
                flag++;
            }
            if (password.equalsIgnoreCase("")) {
                ed2.setError("*");
                flag++;
            }
            if (flag == 0) {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/stock/ad_faculty_login/";
                //  Toast.makeText(getApplicationContext(),"tt="+url,Toast.LENGTH_LONG).show();

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
                                        String id = jsonObj.getString("id");
                                        String ty = jsonObj.getString("usertype");

                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", id);


                                        ed.commit();
                                        if (ty.equalsIgnoreCase("faculty")) {
                                            String batch_id = jsonObj.getString("batch_id");
                                            String bname = jsonObj.getString("bname");
                                            String cname = jsonObj.getString("cname");
                                            SharedPreferences.Editor ed1 = sh.edit();
                                            ed1.putString("batch_id", batch_id);





                                            ed1.putString("bname", bname);
                                            ed1.putString("cname", cname);
                                            ed1.commit();
                                            Intent ij = new Intent(getApplicationContext(), Home.class);
                                            startActivity(ij);
                                        }


                                        if (ty.equalsIgnoreCase("student")) {
                                            String lid = jsonObj.getString("id");
                                            String batch_id = jsonObj.getString("batch_id");

                                            SharedPreferences.Editor ed1 = sh.edit();
                                            ed1.putString("lid", lid);
                                            ed1.putString("batch_id", batch_id);


                                            ed1.commit();
                                            Intent ij = new Intent(getApplicationContext(), student_home.class);
                                            startActivity(ij);
                                        }


                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();

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

                        params.put("uname", uname);
                        params.put("pwd", password);


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
    }
}