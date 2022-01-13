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
import com.example.barayihsalem.UI.Fragment.Memberships;
import com.example.barayihsalem.UI.Fragment.PastMemberships;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Adjust_Membership extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    TextView aboutbs, aboutbs_club, aboutbs_Event, settings, offer, Sign_out, Notifications, language;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Button btnLogin;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_comming_events, lin_book_location_events, lin_book_your_booth, lin_registerat_event, lin_older_events_gallary;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout tabb;

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


            setContentView(R.layout.activity_adjust__membership);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_adjust__membership_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
    }

    private void findid() {
        aboutbs = findViewById(R.id.aboutbs);
        aboutbs.setTypeface(typebold);
        tabb = findViewById(R.id.tabb);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);


        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        Pager adapter = new Pager(getSupportFragmentManager());
       /* adapter.addFragment(new Memberships(), "Memberships");
        adapter.addFragment(new PastMemberships(), "Past Memberships");
        final TabLayout.Tab myDietTab = tabLayout.newTab();
        final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
        myDietTab.setText("Memberships");
        myWorkOutTab.setText("Past Memberships");
        tabLayout.addTab(myDietTab, 0);
        tabLayout.addTab(myWorkOutTab, 1);
*/


        if (appPreferences.getCulturemode().equals("1")) {


            adapter.addFragment(new Memberships(), "Memberships");
            adapter.addFragment(new PastMemberships(), "Previous Membership");
            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("Memberships");
            myWorkOutTab.setText("Previous Membership");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);

        } else {
            adapter.addFragment(new Memberships(), "العضويات");
            adapter.addFragment(new PastMemberships(), "العضوية السابقة");

            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("العضويات");
            myWorkOutTab.setText("العضوية السابقة");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        }


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Setting_Activity.class));
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
