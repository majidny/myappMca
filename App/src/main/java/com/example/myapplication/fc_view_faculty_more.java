package com.example.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class fc_view_faculty_more extends AppCompatActivity {
    com.mikhaellopez.circularimageview.CircularImageView fprofile;
    TextView facname,email,mob,housname,place,quali,expp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc_view_faculty_more);
        fprofile=(com.mikhaellopez.circularimageview.CircularImageView)findViewById(R.id.img);
        facname=(TextView)findViewById(R.id.ifg);
        email=(TextView)findViewById(R.id.ks);
        mob=(TextView)findViewById(R.id.textView21);
        housname=(TextView)findViewById(R.id.textView23);
        place=(TextView)findViewById(R.id.textView25);
        quali=(TextView)findViewById(R.id.qu);
        expp=(TextView)findViewById(R.id.ex);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String ip=sh.getString("ip","");




        final String facname1=sh.getString("name","");
        facname.setText(facname1);

        final String email1=sh.getString("email","");
        email.setText(email1);

        final String mob1=sh.getString("phonenumber","");
        mob.setText(mob1);

        final String housename1=sh.getString("housname","");
        housname.setText(housename1);

        final String place1=sh.getString("place","");
        place.setText(place1);

        final String quao=sh.getString("qualification","");
        quali.setText(quao);

        final String exx=sh.getString("experince","");
        expp.setText(exx);

        String url="http://" + ip + ":8000"+sh.getString("Image","");
        Picasso.with(getApplicationContext()).load(url). into(fprofile);



    }
}
