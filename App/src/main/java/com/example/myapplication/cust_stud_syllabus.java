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

public class cust_stud_syllabus extends BaseAdapter {
    String[] course,title,file,id;
    private Context context;
    String url_main="";



    public cust_stud_syllabus(Context appcontext, String[]course, String[]title, String[]file, String [] id)
    {
        this.context=appcontext;

        this.course=course;
        this.title=title;
        this.file=file;
        this.id=id;


    }

    @Override
    public int getCount() {
        return course.length;
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
            gridView=inflator.inflate(R.layout.cust_stud_view_syllbus,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView54);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView94);


        Button bt1=(Button) gridView.findViewById(R.id.button16);

        bt1.setTag(i);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=(int)v.getTag();

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




//                Toast.makeText(context,url_main,Toast.LENGTH_SHORT).show();
            }
        });













        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);


        tv1.setText(course[i]);
        tv2.setText(title[i]);


        return gridView;









//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":8000/media/"+Image[i]+".jpg";
//
//
//        Picasso.with(context).load(url). into(im);


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