package com.example.yogendra.vivir.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.main.MainActivity;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.main.finder;
import com.example.yogendra.vivir.tenant.RegUserSearch;
import com.example.yogendra.vivir.user.notification;
import com.squareup.picasso.Picasso;

public class OwnerDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        private TextView userNameAtNav,userAdd,userEmail,userName,emailAtNav,userContact,active_since;
        private ImageView profileImage,ImageAtNav;
        SharedPrefManager sharedPrefManager_obj;
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

        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());

        View header=navigationView.getHeaderView(0);
        userNameAtNav = header.findViewById(R.id.userNameAtNav);
        emailAtNav    = header.findViewById(R.id.emailAtNav);
        userName      = findViewById(R.id.name);
        userAdd       = findViewById(R.id.address);
        userEmail     = findViewById(R.id.email);
        userContact   = findViewById(R.id.contact);
        active_since  = findViewById(R.id.active_since);
        profileImage = findViewById(R.id.profileImage);
        ImageAtNav    = header.findViewById(R.id.imageAtNav);

        userNameAtNav.setText(sharedPrefManager_obj.getKeyName());
        emailAtNav.setText(sharedPrefManager_obj.getKeyEmail());
        userEmail.setText(sharedPrefManager_obj.getKeyEmail());
        userName.setText(sharedPrefManager_obj.getKeyName());
        userContact.setText(sharedPrefManager_obj.getKeyContact());
        userAdd.setText(sharedPrefManager_obj.getKeyCity()+" , "+sharedPrefManager_obj.getKeyState());

        active_since.setText(sharedPrefManager_obj.getRegDate());

        String imageUrl = sharedPrefManager_obj.getKeyUrl();
        Picasso.with(this).load(imageUrl).into(profileImage);
        Picasso.with(this).load(imageUrl).into(ImageAtNav);

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
    public void EditProfile(View view){
        Intent in = new  Intent(OwnerDashboard.this , com.example.yogendra.vivir.user.setProfile.class);
        startActivity(in);
    }

    //Change Password
        public void changePassword(View v)
        {
            Intent in = new  Intent(OwnerDashboard.this , com.example.yogendra.vivir.user.changePassword.class);
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
            Intent in = new  Intent(OwnerDashboard.this , RegUserSearch.class);
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
            SharedPrefManager.getInstance(this).logout();
            finish();
            Intent in = new  Intent(OwnerDashboard.this , MainActivity.class);
            startActivity(in);
        }
        else if (id == R.id.nav_complaints)
        {
            Toast.makeText(getApplicationContext(),"Add this link",Toast.LENGTH_LONG).show();
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
