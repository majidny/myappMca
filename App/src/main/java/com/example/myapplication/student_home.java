package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class student_home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();

            Toast.makeText(getApplicationContext(),"Use logout in menu to exit",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent i=new Intent(getApplicationContext(),stu_view_profile.class);
            startActivity(i);


        } else if (id == R.id.nav_slideshow) {

            Intent i=new Intent(getApplicationContext(),stud_view_studymaterial.class);
            startActivity(i);


        } else if (id == R.id.nav_tools) {

            Intent i=new Intent(getApplicationContext(),stud_view_syllabus.class);
            startActivity(i);

        }
        else if (id == R.id.nav_otherstudents) {

            Intent i=new Intent(getApplicationContext(),Studentviewotherstudents.class);
            startActivity(i);

        }

        else if (id == R.id.work) {

            Intent i=new Intent(getApplicationContext(),stud_view_work.class);
            startActivity(i);

        }
        else if (id == R.id.nav_lgout) {
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);


        }
        else if (id == R.id.add_ach) {
            Intent i=new Intent(getApplicationContext(),stud_add_achvement.class);
            startActivity(i);


        }  else if (id == R.id.view_att) {
            Intent i=new Intent(getApplicationContext(),View_attendance.class);
            startActivity(i);


        }
        else if (id == R.id.view_ach) {
            Intent i=new Intent(getApplicationContext(),stud_view_achivement.class);
            startActivity(i);


        }

        else if (id == R.id.nav_fac) {
            Intent i=new Intent(getApplicationContext(),public_view_faculties.class);
            startActivity(i);


        }

        else if (id == R.id.view_vacc) {
            Intent i=new Intent(getApplicationContext(),Studentviewvaccancies.class);
            startActivity(i);


        }
        else if (id == R.id.view_vacc_applied) {
            Intent i=new Intent(getApplicationContext(),Studentviewvaccancies_applied.class);
            startActivity(i);


        }

        else if (id == R.id.review) {
            Intent i=new Intent(getApplicationContext(),Sentreviews.class);
            startActivity(i);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
