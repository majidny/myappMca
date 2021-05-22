package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Publicaddenquiry extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView tv_batch, tv_course;
    EditText edname,edphone,edemail,edenquiry;
    Button btn;

    int pos=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicaddenquiry);






        edname=(EditText) findViewById(R.id.editText13);
        edphone=(EditText) findViewById(R.id.editText14);
        edemail=(EditText) findViewById(R.id.editText15);
        edenquiry=(EditText) findViewById(R.id.editText5);



        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        btn=(Button)findViewById(R.id.button8);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String name=edname.getText().toString();
        final String phone=edphone.getText().toString();
        final String email=edemail.getText().toString();
        final String enquiry=edenquiry.getText().toString();


        if(name.length()==0){
            edname.setError("Missing");
        }
        else if(phone.length()==0){
            edphone.setError("Missing");
        }
        else if(email.length()==0){
            edemail.setError("Missing");
        }
        else if(enquiry.length()==0){
            edenquiry.setError("Missing");
        }


        else{
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/stock/publicqnquiryadd/";
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
                                    Toast.makeText(getApplicationContext(), "Enquiry Added", Toast.LENGTH_SHORT).show();

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
                    params.put("name", name);
                    params.put("email",email);
                    params.put("phone",phone);
                    params.put("enquiry",enquiry);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
