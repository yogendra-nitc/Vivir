package com.example.yogendra.vivir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class user_dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn;
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in;
                in = new Intent(user_dashboard.this, signup.class);
                startActivity(in);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_dashboard, menu);
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

        if (id == R.id.nav_search)
        {
            Button regUserSearch;
            regUserSearch = view.findViewById(R.id.reg_user_search);
            regUserSearch.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent in = new  Intent(user_dashboard.this , RegUserSearch.class);
                    startActivity(in);
                }
            });
        }
        else if (id == R.id.nav_notification)
        {
            Button notification;
            notification = view.findViewById(R.id.notifications);
            notification.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent in = new  Intent(user_dashboard.this , notification.class);
                    startActivity(in);
                }
            });

        }
        else if (id == R.id.nav_rent_upload)
        {
            Button rentDetails;
            rentDetails = view.findViewById(R.id.upload_rent_details);
            rentDetails.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent in = new  Intent(user_dashboard.this , uploadRentDetails.class);
                    startActivity(in);
                }
            });
        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_query)
        {
            Button writeComplain;
            writeComplain = view.findViewById(R.id.writeComplain);
            writeComplain.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent in = new  Intent(user_dashboard.this , complainList.class);
                    startActivity(in);
                }
            });
        }
        else if (id == R.id.nav_leaving)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
