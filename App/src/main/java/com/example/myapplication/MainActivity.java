package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_ip=(EditText)findViewById(R.id.editText);
        bt=(Button)findViewById(R.id.button2);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ip=ed_ip.getText().toString();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("ip", ip);


        ed.commit();
        Intent i=new  Intent(getApplicationContext(),Login.class);
        startActivity(i);

    }
}
