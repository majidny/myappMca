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
import android.webkit.MimeTypeMap;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class cust_study_materials extends BaseAdapter {

//    AdapterView.OnItemSelectedListener
//    {
//        String[] item;
//    }


//        Spinner sp;
    String[] date,title,file,id,batchh;
    private Context context;

    String url_main="";
//    String tiiit="";



    public cust_study_materials(Context appcontext,String[]date,String[]title,String[]file,String [] id)
    {
        this.context=appcontext;

        this.date=date;
        this.title=title;
        this.file=file;
        this.id=id;

//        this.batchh=batchh;


    }




    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    public String get_mime_type(String url) {
        String ext = MimeTypeMap.getFileExtensionFromUrl(url);
        String mime = null;
        if (ext != null) {
            mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        return mime;
    }

    public void open_file(String filename) {
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOWNLOADS), filename);

        // Get URI and MIME type of file

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
           Uri uri = Uri.parse(filename);
//        }
        String mime = get_mime_type(uri.toString());

        // Open file with user selected app
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType(mime);
        context.startActivity(intent);//Intent.createChooser(intent, "Open file with"));
    }



    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.cust_study_material,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.datee);
        TextView tv2=(TextView)gridView.findViewById(R.id.ttt);



        Button bt1=(Button) gridView.findViewById(R.id.button7);
        Button bt2=(Button) gridView.findViewById(R.id.button9);

        bt1.setTag(i);
        bt2.setTag(id[i]);


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/stock/fac_del_material/";
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

                                        Intent ins= new Intent(context,study_meterial_first.class);
                                        ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(ins);



                                    }
                                    else{
                                        Toast.makeText(context, "Failed to delete material", Toast.LENGTH_SHORT).show();

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

//                String id=sh.getString("uid","");
                        params.put("id", v.getTag().toString());

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
        });




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();

//                tiiit=title[pos]+".pdf";

//                new DownloadFileFromURL().execute(url_main);
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                url_main = "http://" + hu + ":8000"+file[pos];


//                open_file(url_main);


//                new FileDownloader


                Intent b= new Intent(Intent.ACTION_VIEW);
                b.setDataAndType(Uri.parse(url_main),"application/pdf");
                b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(b);




                Toast.makeText(context,url_main,Toast.LENGTH_SHORT).show();
//                Uri uri = Uri.parse(url_main);
//
//                DownloadManager.Request r = new DownloadManager.Request(uri);
//
////// This put the download in the same Download dir the browser uses
//                r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "abcd");
////
////// When downloading music and videos they will be listed in the player
////// (Seems to be available since Honeycomb only)
//                r.allowScanningByMediaScanner();
////
////// Notify user when download is completed
////// (Seems to be available since Honeycomb only)
//                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
////
////// Start download
//                DownloadManager dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
//                dm.enqueue(r);

            }
        });




        tv1.setTextColor(Color.BLACK);


        tv1.setText(date[i]);
        tv2.setText(title[i]);









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