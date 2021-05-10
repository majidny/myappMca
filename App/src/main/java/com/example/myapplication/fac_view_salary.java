package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
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

public class fac_view_salary extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView facultyname,month,salary,attendence;

    Spinner sp;

    String[]dis555= new String[]{"january","january","march","april","may","june","july","august","september","october","november","december"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_view_salary);


        sp=(Spinner)findViewById(R.id.spnr1) ;
        salary=(TextView)findViewById(R.id.textView130);
        attendence=(TextView)findViewById(R.id.textView132);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dis555);
        sp.setAdapter(ad);

        sp.setOnItemSelectedListener(this);







    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final String kk=dis555[position];

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/stock/faculty_view_salary/";
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


                                String salry=jsonObj.getString("monthsalary");
                                String atten=jsonObj.getString("cnt22");


                                salary.setTextColor(Color.BLACK);
                                attendence.setTextColor(Color.BLACK);


                                salary.setText(salry);
                                attendence.setText(atten);





//                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                String ip=sh.getString("ip","");
//                                SharedPreferences.Editor ed=sh.edit();
//                                ed.putString("img",img22);
//                                ed.commit();
//                                String url="http://" + ip + ":8000"+img22;
////                                Toast.makeText(getApplicationContext(), "pt="+url , Toast.LENGTH_SHORT).show();
//                                Picasso.with(getApplicationContext()).load(url).into(prof);

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
                params.put("d1",kk);


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
//
//







//    }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {






    }



}
