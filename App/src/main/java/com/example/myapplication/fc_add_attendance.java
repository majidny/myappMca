package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class fc_add_attendance extends AppCompatActivity {
    ListView liststud;
    Button bts;
    String[] name,email,phonenumber,coursename,id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc_addattendance_students);
        liststud=(ListView)findViewById(R.id.listst);
        bts=(Button) findViewById(R.id.button31);


        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addattenance();
            }
        });

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        final String maclis=sh.getString("mac_list","");
//        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/stock/ad_view_students/";



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

                                JSONArray js= jsonObj.getJSONArray("users");

                                name=new String[js.length()];
                                email=new String[js.length()];
                                phonenumber=new String[js.length()];
                                coursename=new String[js.length()];
                                id=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    name[i]=u.getString("student_name");
                                    email[i]=u.getString("email");
                                    phonenumber[i]=u.getString("phone_no");
                                    coursename[i]=u.getString("course_name");
                                    id[i]=u.getString("id");
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,name );
                                liststud.setAdapter(adapter);
//                                lv.ChoiceMode = ChoiceMode.Multiple;

                                liststud.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);









//                                liststud.setAdapter(new cust_view_students(getApplicationContext(),name,email,phonenumber,coursename,id));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
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

                String id=sh.getString("selbid","");
                params.put("bid",id);
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }
String ids="";
    public void addattenance()
    {
        int len = liststud.getCount();


        SparseBooleanArray checked = liststud.getCheckedItemPositions();
        for (int i = 0; i < len; i++)
            if (checked.get(i)) {
                String item = id[i];
//                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_LONG).show();
                /* do whatever you want with the checked item */


                ids= ids+ item+",";
            }


        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/stock/fac_attendanceadd/";
        //  Toast.makeText(getApplicationContext(),"tt="+url,Toast.LENGTH_LONG).show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //
                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");
                            if (sucs.equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(),Tutor.class));
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
                params.put("studid", ids);
                params.put("bid", sh.getString("selbid",""));
//                params.put("tslot",sp3.getSelectedItem().toString());
//                params.put("tutid",sh.getString("userid",""));


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
