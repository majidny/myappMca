package com.example.myapplication;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class cust_salary extends BaseAdapter {
    String[] year,month,salary;
    private Context context;



    public cust_salary(Context appcontext, String[]year, String[]month, String[]salary)
    {
        this.context=appcontext;

        this.year=year;
        this.month=month;
        this.salary=salary;
//        this.id=id;


    }

    @Override
    public int getCount() {
        return year.length;
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
    public View getView(int i, final View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.cust_view_salary,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView129);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView130);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView128);



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(year[i]);
        tv2.setText(month[i]);
        tv3.setText(salary[i]);


        return gridView;
    }
}







//        Button bt1=(Button) gridView.findViewById(R.id.button11);
//        Button bt2=(Button) gridView.findViewById(R.id.button12);
//        bt1.setTag(i);
//        bt2.setTag(i);


//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//
//                final int pos=(int)v.getTag();
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//
//                String hu = sh.getString("ip", "");
//                String url = "http://" + hu + ":8000/stock/faculty_del_work/";
//                //  Toast.makeText(getApplicationContext(),"tt="+url,Toast.LENGTH_LONG).show();
//
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                // response
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//
//                                        Intent ins= new Intent(context,fc_view_work.class);
//                                        ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        context.startActivity(ins);
//
//
//
//                                    }
//                                    else{
//                                        Toast.makeText(context, "Failed to delete work", Toast.LENGTH_SHORT).show();
//
//                                    }
//
//
//                                    // }
////                                    else {
////                                     Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
////                                    }
//
//                                } catch (Exception e) {
//                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                        Map<String, String> params = new HashMap<String, String>();
//
////                String id=sh.getString("uid","");
//                        params.put("wid", id[pos]);
//
////                params.put("mac",maclis);
//
//                        return params;
//                    }
//                };
//
//                int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);
//
//
//
//            }
//        });
//
//
//
//
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos=(int)view.getTag();
//                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("wid",id[pos]);
//                ed.putString("descr",desc[pos]);
//                ed.commit();
//                Intent ij=new Intent(context,fc_edit_work.class);
//                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(ij);
//
//
//            }
//        });








//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":8000/media/"+Image[i]+".jpg";
//
//
//        Picasso.with(context).load(url). into(im);







//    private ProgressDialog pDialog;
//    public static final int progress_bar_type=0;
//
//    //    @Override
//    protected Dialog onCreateDialog(int id) {
//
//        switch (id) {
//
//            case progress_bar_type: // we set this to 0
//                ProgressDialog pDialog = new ProgressDialog(context);
//                pDialog.setMessage("Downloading file. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setMax(100);
//                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pDialog.setCancelable(true);
//                pDialog.show();
//                return pDialog;
//            default:
//                return null;
//        }
//    }


//class DownloadFileFromURL extends AsyncTask<String, String, String> {
//
//    /**
//     * Before starting background thread Show Progress Bar Dialog
//     * */
////        @Override
////        protected void onPreExecute() {
////            super.onPreExecute();
////            showDialog(progress_bar_type);
////            pDialog=new ProgressDialog(context);
////        }
//
//    /**
//     * Downloading file in background thread
//     * */
//    @Override
//    protected String doInBackground(String... f_url) {
//        int count;
//        try {
//            URL url = new URL(f_url[0]);
//            URLConnection conection = url.openConnection();
//            conection.connect();
//
//            // this will be useful so that you can show a tipical 0-100%
//            // progress bar
//            int lenghtOfFile = conection.getContentLength();
//
//            // download the file
//            InputStream input = new BufferedInputStream(url.openStream(),
//                    8192);
//
//            // Output stream
//            OutputStream output = new FileOutputStream(Environment
//                    .getExternalStorageDirectory().toString()
//                    + "/"+tiiit);
//
//            byte data[] = new byte[1024];
//
//            long total = 0;
//
//            while ((count = input.read(data)) != -1) {
//                total += count;
//                // publishing the progress....
//                // After this onProgressUpdate will be called
//                publishProgress("" + (int) ((total * 100) / lenghtOfFile));
//
//                // writing data to file
//                output.write(data, 0, count);
//            }
//
//            // flushing output
//            output.flush();
//
//            // closing streams
//            output.close();
//            input.close();
//
//        } catch (Exception e) {
//            Log.e("Error: ", e.getMessage());
//        }
//
//
//
//
//        return null;
//    }
//
//    /**
//     * Updating progress bar
//     * */
//    protected void onProgressUpdate(String... progress) {
//        // setting progress percentage
//        pDialog.setProgress(Integer.parseInt(progress[0]));
//    }
//
//    /**
//     * After completing background task Dismiss the progress dialog
//     * **/
////        @Override
////        protected void onPostExecute(String file_url) {
////            // dismiss the dialog after the file was downloaded
////            dismissDialog(progress_bar_type);
////
////        }
//}