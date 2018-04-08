package com.example.yogendra.vivir.owner;

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

import com.example.yogendra.vivir.main.MainActivity;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.main.finder;
import com.example.yogendra.vivir.user.requestList;
import com.example.yogendra.vivir.user.complainList;
import com.example.yogendra.vivir.user.notification;

public class OwnerDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        getMenuInflater().inflate(R.menu.owner_dashboard, menu);
        return true;
    }

    //Edit Profile
    public void EditProfile(){
        Intent in = new  Intent(OwnerDashboard.this , com.example.yogendra.vivir.user.setProfile.class);
        startActivity(in);
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
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_flats)
        {
            Intent in = new  Intent(OwnerDashboard.this , MyFlats.class);
            startActivity(in);
        }
        else if (id == R.id.nav_NA)
        {
            Intent in = new  Intent(OwnerDashboard.this , finder.class);
            startActivity(in);
        }
        else if (id == R.id.nav_notification)
        {
            Intent in = new  Intent(OwnerDashboard.this , notification.class);
            startActivity(in);
        }
        else if (id == R.id.nav_logout)
        {
            Intent in = new  Intent(OwnerDashboard.this , MainActivity.class);
            startActivity(in);
        }
        else if (id == R.id.nav_complaints)
        {
            Intent in = new  Intent(OwnerDashboard.this , complainList.class);
            startActivity(in);
        }
        else if (id == R.id.nav_request)
        {
            Intent in = new  Intent(OwnerDashboard.this , requestList.class);
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
