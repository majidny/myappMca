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

public class cust_stu_studymaterials extends BaseAdapter {

    String[] date,title,file,id,cname;
    private Context context;

    String url_main="";

    public cust_stu_studymaterials(Context appcontext,String[]date,String[]title,String[]file,String [] id,String [] cname)
    {
        this.context=appcontext;

        this.date=date;
        this.title=title;
        this.file=file;
        this.cname=cname;

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
            gridView=inflator.inflate(R.layout.cust_stud_stdymaterial,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.datee);
        TextView tv2=(TextView)gridView.findViewById(R.id.ttt);
        TextView tv3=(TextView)gridView.findViewById(R.id.cnamee);



        Button bt1=(Button) gridView.findViewById(R.id.button7);


        bt1.setTag(i);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                url_main = "http://" + hu + ":8000"+file[pos];


                Intent b= new Intent(Intent.ACTION_VIEW);
                b.setDataAndType(Uri.parse(url_main),"application/pdf");
                b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(b);




                Toast.makeText(context,url_main,Toast.LENGTH_SHORT).show();

            }
        });




        tv1.setTextColor(Color.BLACK);


        tv1.setText(date[i]);
        tv2.setText(title[i]);
        tv3.setText(cname[i]);









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







