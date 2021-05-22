package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestStudentchatwithfaculty extends AppCompatActivity implements View.OnClickListener {
	ListView l1;
	EditText src;
	Button b1;
	String[] id,msg,dt,tm,type;

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		hnd.removeCallbacks(ad);
	}

	MessagesAdapter adapterMessages;
	Handler hnd;
	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


		SharedPreferences.Editor ed=sh.edit();
		ed.putString("msgid","0");
		ed.commit();

		hnd=new Handler();
		hnd.post(ad);


		l1=(ListView)findViewById(R.id.list_chat);
		src=(EditText)findViewById(R.id.input_chat_message);

		l1.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		l1.setStackFromBottom(true);

		b1=(Button)findViewById(R.id.button_chat_send);
		b1.setOnClickListener(this);


		adapterMessages = new MessagesAdapter(TestStudentchatwithfaculty.this);








//
//
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String ip=sh.getString("ip","");
//        String localhost="http://"+ip+":5000/police_chat";
//        Toast.makeText(getApplicationContext(),localhost,Toast.LENGTH_SHORT).show();
//
//
//
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, localhost,
//
//                //
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                        try {
//
//                            //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
//
//                            JSONObject j=new JSONObject(response);
//                            String status=j.getString("status");
//                            JSONArray jsa=j.getJSONArray("res");
//
//                            msg=new String[jsa.length()];
//
//                            dt=new String[jsa.length()];
//                            im=new String[jsa.length()];
//
//
//                            for(int i=0;i<jsa.length();i++)
//                            {
//                                JSONObject jf= jsa.getJSONObject(i);
//
//                                msg[i]=jf.getString("message");
//
//                                dt[i]=jf.getString("date");
//                                im[i]=jf.getString("photo");
//
//
//                            }
//                            l1.setAdapter(new custom_ur_chat(getApplicationContext(),msg,dt,im));
//
//
//
//
//                        } catch (Exception ex) {
//                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();;
//
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//
//
//
//                        Toast.makeText(getApplicationContext(),"error111112"+error.getMessage(),Toast.LENGTH_SHORT).show();;
//                        // error
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put("uid",sh.getString("id",""));
//
//
//
//
//
//
//
//
//
//
//
//
//                return params;
//            }
//        };
//
//        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        requestQueue.add(postRequest);








	}


	@Override
	public void onClick(View view) {
		final String msg=src.getText().toString();








		SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String ip=sh.getString("ip","");
		String localhost="http://"+ip+":8000/stock/and_studentchatinsert/";
//        Toast.makeText(getApplicationContext(),localhost,Toast.LENGTH_SHORT).show();



		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		//    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
		StringRequest postRequest = new StringRequest(Request.Method.POST, localhost,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {


						try {



src.setText("");




						} catch (Exception ex) {
							Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();;

						}


					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {





						Toast.makeText(getApplicationContext(),"error111112"+error.getMessage(),Toast.LENGTH_SHORT).show();;
						// error
					}
				}
		) {
			@Override
			protected Map<String, String> getParams() {
				SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				Map<String, String> params = new HashMap<String, String>();

				params.put("message",msg);
				params.put("sid", sh.getString("lid", ""));
				params.put("fid",sh.getString("facid", ""));



				return params;
			}
		};
		requestQueue.add(postRequest);
		postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));





	}


	Runnable ad=new Runnable() {
		@Override
		public void run() {



			SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			String ip=sh.getString("ip","");
			String localhost="http://"+ip+":8000/stock/studentviewchat/";
//            Toast.makeText(getApplicationContext(),localhost,Toast.LENGTH_SHORT).show();





			RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
			//    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
			StringRequest postRequest = new StringRequest(Request.Method.POST, localhost,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							//Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


							try {

								JSONObject j=new JSONObject(response);
								String status=j.getString("status");
								JSONArray jsa=j.getJSONArray("res2");
								id=new String[jsa.length()];
								msg=new String[jsa.length()];
								dt=new String[jsa.length()];
								tm=new String[jsa.length()];
								type=new String[jsa.length()];


								SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//                                String uid=sh.getString("farmerid", "");



								for(int i=0;i<jsa.length();i++)
								{
									JSONObject jf= jsa.getJSONObject(i);



									id[i]=jf.getString("id");
									msg[i]=jf.getString("message");
									dt[i]=jf.getString("date");
									type[i]=jf.getString("type");

									if(type[i].equalsIgnoreCase("student"))
									{
										ChatMessage	message = new ChatMessage();
										message.setUsername("Me");
										message.setMessage(msg[i]);
										message.setDate(dt[i]);
										message.setIncomingMessage(false);
										adapterMessages.add(message);
									}
									else
									{
										ChatMessage	message = new ChatMessage();
										message.setUsername("Faculty");
										message.setMessage(msg[i]);
										message.setDate(dt[i]);
										message.setIncomingMessage(true);
										adapterMessages.add(message);

									}

									SharedPreferences.Editor ed=sh.edit();
									ed.putString("msgid",id[i] );
									ed.commit();




								}

								l1.setAdapter(adapterMessages);





								//  l1.setAdapter(new custom_all_users(getApplicationContext(),msg,dt,tm,type));




							} catch (Exception ex) {
								Toast.makeText(getApplicationContext(),"error"+ex.getMessage().toString(),Toast.LENGTH_SHORT).show();;

							}


						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {





							Toast.makeText(getApplicationContext(),"error111112"+error.getMessage(),Toast.LENGTH_SHORT).show();;
							// error
						}
					}
			) {
				@Override
				protected Map<String, String> getParams() {
					SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					Map<String, String> params = new HashMap<String, String>();

					params.put("sid", sh.getString("lid", ""));
					params.put("facid",sh.getString("facid", ""));
					params.put("lid",sh.getString("msgid", ""));

					return params;
				}
			};
			requestQueue.add(postRequest);
			postRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));




			// TODO Auto-generated method stub

			hnd.postDelayed(ad, 2000);
		}
	};







}



