package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class publichome extends AppCompatActivity {


    ImageView imlogin, imenquiry, imachievements,imcourse;


    @Override
    public void onBackPressed() {

        Intent ins =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ins);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publichome);



        imlogin=(ImageView) findViewById(R.id.imageView12);
        imenquiry=(ImageView) findViewById(R.id.imageView13);
        imachievementss=(ImageView) findViewById(R.id.imageView14);
        imcourse=(ImageView) findViewById(R.id.imageView15);


        imlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ins= new Intent(getApplicationContext(),Login.class);
                startActivity(ins);

            }
        });


        imenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ins= new Intent(getApplicationContext(),Publicaddenquiry.class);
                startActivity(ins);


            }
        });


        imachievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent ins= new Intent(getApplicationContext(),public_view_achievements.class);
                startActivity(ins);

            }
        });

        imcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent ins= new Intent(getApplicationContext(),public_viewcourse.class);
                startActivity(ins);

            }
        });
    }
}
