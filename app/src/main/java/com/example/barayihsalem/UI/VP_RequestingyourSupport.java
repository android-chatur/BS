package com.example.barayihsalem.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.All_VP_Adapter;
import com.example.barayihsalem.UI.Fragment.NewEntry;
import com.example.barayihsalem.UI.Fragment.NewEntry_support;
import com.example.barayihsalem.UI.Fragment.Recornded;
import com.example.barayihsalem.UI.Fragment.Recornded_support;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class VP_RequestingyourSupport extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    TextView aboutbs, items, filter, settings, offer, notifi, reach_help, abou_app, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    LinearLayout tabb;
    Pager adapter;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    All_VP_Adapter all_vp_adapter;
    Button btnLogin;
    RecyclerView rw_allvision, rw_comminity;
    RelativeLayout abcd;
    ViewPager viewPager;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_v_p__requestingyour_support);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_v_p__requestingyour_support_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

/*
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_allvision.setLayoutManager(gridLayoutManager);
        all_vp_adapter = new All_VP_Adapter(All_Vision.this);
        rw_allvision.setAdapter(all_vp_adapter);*/
    }

    private void findid() {


        abcd = findViewById(R.id.abcd);
        aboutbs = findViewById(R.id.aboutbs);
        aboutbs.setTypeface(typebold);
        tabb = findViewById(R.id.tabb);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);


        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
         adapter = new Pager(getSupportFragmentManager());
     /*   adapter.addFragment(new NewEntry_support(), "New entry");
        adapter.addFragment(new Recornded_support(), "Recorded");
        final TabLayout.Tab myDietTab = tabLayout.newTab();
        final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
        myDietTab.setText("New entry");
        myWorkOutTab.setText("Recorded");*/



        TabLayout.Tab myDietTab = null;
        final TabLayout.Tab myWorkOutTab;
        if (appPreferences.getCulturemode().equals("1")) {

            adapter.addFragment(new NewEntry_support(), "New Login");
            adapter.addFragment(new Recornded_support(), "Logged in");
            myDietTab = tabLayout.newTab();
            myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("New Login");
            myWorkOutTab.setText("Logged in");
        }else {
            adapter.addFragment(new NewEntry_support(), "سجيل دخول جديد");
            adapter.addFragment(new Recornded_support(), "تم التسجيل");
            myDietTab = tabLayout.newTab();
            myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("سجيل دخول جديد");
            myWorkOutTab.setText("تم التسجيل");
        }


        tabLayout.addTab(myDietTab, 0);
        tabLayout.addTab(myWorkOutTab, 1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

        }
    }

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

    private class Pager extends FragmentStatePagerAdapter {
        private List<String> fragmentTitleList = new ArrayList<String>();
        private List<Fragment> fragmentList = new ArrayList<Fragment>();


        public Pager(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);

        }
    }

}