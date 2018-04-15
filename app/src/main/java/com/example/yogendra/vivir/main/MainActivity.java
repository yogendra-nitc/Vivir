package com.example.yogendra.vivir.main;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.adapter.PagerAdapter;
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.owner.OwnerDashboard;
import com.example.yogendra.vivir.tenant.user_dashboard;
import com.example.yogendra.vivir.user.home;

public class MainActivity extends AppCompatActivity implements home.OnFragmentInteractionListener,
        search.OnFragmentInteractionListener,finder.OnFragmentInteractionListener {
    SharedPrefManager SharedPrefManager_obj = SharedPrefManager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //USER LOGIN STATUS CHECKING
        if(SharedPrefManager_obj.isLoggedin())
        {
            if((SharedPrefManager_obj.getKeyUtype()).equals("tenant"))
            {
                finish();
                startActivity(new Intent(this , user_dashboard.class));
                return;
            }
            else
            {
                finish();
                startActivity(new Intent(this , OwnerDashboard.class));
                return;
            }

        }

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.addTab(tabLayout.newTab().setText("Finder"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
