package com.example.yogendra.vivir.tenant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.main.MainActivity;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.user.complainList;
import com.example.yogendra.vivir.user.home;
import com.example.yogendra.vivir.user.notification;
import com.example.yogendra.vivir.user.signup;

import static com.example.yogendra.vivir.database.SharedPrefManager.KEY_EMAIL;
import static com.example.yogendra.vivir.database.SharedPrefManager.KEY_NAME;
import static com.example.yogendra.vivir.database.SharedPrefManager.SHARED_PREF_NAME;

public class user_dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private TextView userNameAtNav,userCity, userState,userContact,userEmail,userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //USER LOGIN
       /* if(!SharedPrefManager.getInstance(this).isLoggedin())
        {
            finish();
            startActivity(new Intent(this , MainActivity.class));
        }*/

       // userNameAtNav = (TextView)findViewById(R.id.emailAtNav);
      //  userEmail = findViewById(R.id.emailAtNav);

        //userEmail.setText(SharedPrefManager.getInstance(this).getKeyEmail());
        //userNameAtNav.setText(SharedPrefManager.getInstance(this).getKeyName());
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
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

    // Edit Profile
    public void EditProfile(View v){
        Intent in = new  Intent(user_dashboard.this , com.example.yogendra.vivir.user.setProfile.class);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search)
        {
            Intent in = new  Intent(user_dashboard.this , RegUserSearch.class);
            in.putExtra("active" , "RegUserSearch");
            startActivity(in);
        }
        else if (id == R.id.nav_notification)
        {
            Intent in = new  Intent(user_dashboard.this , notification.class);
            startActivity(in);
        }
        else if (id == R.id.nav_rent_upload)
        {
            Intent in = new  Intent(user_dashboard.this , uploadRentDetails.class);
            startActivity(in);
        }
        else if (id == R.id.nav_logout)
        {
            SharedPrefManager.getInstance(this).logout();
            finish();
            Intent in = new  Intent(user_dashboard.this , MainActivity.class);
            startActivity(in);
        }
        else if (id == R.id.nav_query)
        {
            Toast.makeText(getApplicationContext(), "Your request has been sent",Toast.LENGTH_LONG).show();
            Intent in = new  Intent(user_dashboard.this , complainList.class);
            startActivity(in);
        }
        else if (id == R.id.nav_leaving)
        {
            Toast.makeText(getApplicationContext(), "Your request has been sent",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}