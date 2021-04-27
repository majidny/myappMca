package com.example.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class fc_view_faculty_more extends AppCompatActivity {
    ImageView fprofile;
    TextView facname,email,mob,housname,place,post,pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc_view_faculty_more);
        fprofile=(ImageView)findViewById(R.id.imageView2);
        facname=(TextView)findViewById(R.id.textView17);
        email=(TextView)findViewById(R.id.textView19);
        mob=(TextView)findViewById(R.id.textView21);
        housname=(TextView)findViewById(R.id.textView23);
        place=(TextView)findViewById(R.id.textView25);
        post=(TextView)findViewById(R.id.textView27);
        pin=(TextView)findViewById(R.id.textView29);
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
        final String post1=sh.getString("post","");
        post.setText(post1);
        final String pin1=sh.getString("pin","");
        pin.setText(pin1);
        String url="http://" + ip + ":8000"+sh.getString("Image","");
        Picasso.with(getApplicationContext()).load(url). into(fprofile);



    }
}
