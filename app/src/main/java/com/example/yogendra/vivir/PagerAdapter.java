package com.example.yogendra.vivir;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by yogendra on 17/3/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{

    int noOfActivity;
    public PagerAdapter(FragmentManager fm, int NoOfActivity){
        super(fm);
        this.noOfActivity = NoOfActivity;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                home homeAct = new home();
                return homeAct;
            case 1:
                search searchAct = new search();
                return searchAct;
            case 2:
                signup signupAct = new signup();
                return signupAct;
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfActivity;
    }
}
