package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button bt;



    @Override
    public void onBackPressed() {

        Intent ins =new Intent(Intent.ACTION_MAIN);
        ins.addCategory(Intent.CATEGORY_HOME);
        ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(ins);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_ip=(EditText)findViewById(R.id.editText);
        bt=(Button)findViewById(R.id.button2);
        bt.setOnClickListener(this);


        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed_ip.setText(sh.getString("ip",""));


    }

    @Override
    public void onClick(View view) {
        final String ip=ed_ip.getText().toString();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("ip", ip);


        ed.commit();
        Intent i=new  Intent(getApplicationContext(),publichome.class);
        startActivity(i);

    }
}
