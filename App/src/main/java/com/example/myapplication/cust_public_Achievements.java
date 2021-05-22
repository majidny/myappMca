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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class cust_public_Achievements extends BaseAdapter {
    String[] date,file,description,name,image,email;
    private Context context;
    String url_main="";



    public cust_public_Achievements(Context appcontext, String[] date,String[] file,String[] description,String[] name,String[] image,String[] email)
    {
        this.context=appcontext;

        this.date=date;
        this.file=file;
        this.description=description;
        this.name=name;
        this.image=image;
        this.email=email;


    }

    @Override
    public int getCount() {
        return email.length;
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
            gridView=inflator.inflate(R.layout.cust_pub_achievements,null);
        }
        else
        {
            gridView=(View)view;
        }

        ImageView imimage=(ImageView) gridView.findViewById(R.id.imageView2);
        ImageView im_aimage=(ImageView) gridView.findViewById(R.id.imageView4);

        TextView tvname=(TextView) gridView.findViewById(R.id.textView67);
        TextView tvemail=(TextView) gridView.findViewById(R.id.textView79);
        TextView tvdescription=(TextView) gridView.findViewById(R.id.textView80);

        tvname.setText(name[i]);
        tvemail.setText(email[i]);
        tvdescription.setText(description[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+image[i];
        Picasso.with(context).load(url).transform(new CircleTransform()). into(imimage);

        String url1="http://" + ip + ":8000"+file[i];
        Picasso.with(context).load(url1). into(im_aimage);



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