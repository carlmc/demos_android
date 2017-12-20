package com.example.cmunayll.prueba2tablayout.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cmunayll.prueba2tablayout.fragments.CreditoFragment;
import com.example.cmunayll.prueba2tablayout.fragments.CuentaFragment;
import com.example.cmunayll.prueba2tablayout.fragments.TarjetaFragment;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class AdapterFragment extends FragmentStatePagerAdapter {

    int numTabs;

    public AdapterFragment(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CuentaFragment tab1 = new CuentaFragment();
                return tab1;
            case 1:
                TarjetaFragment tab2 = new TarjetaFragment();
                return tab2;
            case 2:
                CreditoFragment tab3 = new CreditoFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
