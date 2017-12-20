package com.example.cmunayll.prueba2tablayout;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cmunayll.prueba2tablayout.adapters.AdapterFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        TabLayout.Tab uno = tabLayout.newTab();
        uno.setText("Cuentas");
        uno.setIcon(R.drawable.account);
        tabLayout.addTab(uno);

        TabLayout.Tab dos = tabLayout.newTab();
        dos.setText("Tarjetas");
        dos.setIcon(R.drawable.card);
        tabLayout.addTab(dos);

        TabLayout.Tab tres = tabLayout.newTab();
        tres.setText("Créditos");
        tres.setIcon(R.drawable.credit);
        tabLayout.addTab(tres);

        AdapterFragment adapterFragment = new AdapterFragment(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterFragment);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

}
