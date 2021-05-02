package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class fc_add_work extends AppCompatActivity implements View.OnClickListener {
    TextView tv_batch, tv_course;
    EditText ed_work;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc_add_work);

        tv_course=(TextView) findViewById(R.id.textView71);
        tv_batch=(TextView) findViewById(R.id.textView73);
        ed_work=(EditText) findViewById(R.id.editText5);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tv_course.setText(sh.getString("cname",""));
        tv_batch.setText(sh.getString("bname",""));
        btn=(Button)findViewById(R.id.button8);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String work=ed_work.getText().toString();
        if(work.length()==0){
            ed_work.setError("Missing");
        } else{
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/stock/faculty_add_work/";
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
                                    Toast.makeText(getApplicationContext(), "Work Added", Toast.LENGTH_SHORT).show();
                                    Intent ij = new Intent(getApplicationContext(), fc_view_work.class);
                                    startActivity(ij);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();

                                }


                                // }
//                                    else {
//                                     Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                    }

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

//                String id=sh.getString("uid","");
                    params.put("lid", sh.getString("lid",""));
                    params.put("batchid", sh.getString("batch_id",""));
                    params.put("descr",work);

//                params.put("mac",maclis);

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
