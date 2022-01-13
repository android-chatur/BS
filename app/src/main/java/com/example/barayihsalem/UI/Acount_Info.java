package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Contant_Adapter;
import com.example.barayihsalem.UI.Fragment.My_Details;
import com.example.barayihsalem.UI.Fragment.Offering;
import com.example.barayihsalem.UI.Fragment.Order_Detais;
import com.example.barayihsalem.UI.Fragment.Seeking;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Acount_Info extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
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
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_acount__info);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_acount__info_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();


        if (Acount_Info.this.isNetworkConnected()) {



        } else {
            Snackbar snackbar = Snackbar
                    .make(abcd, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });

            // Changing message text color
            snackbar.setActionTextColor(Color.RED);

            // Changing action button text color
            View sbView = snackbar.getView();
            // TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);

            snackbar.show();
        }


    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
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


        if (appPreferences.getCulturemode().equals("1")) {


            adapter.addFragment(new My_Details(), "My Details");
            adapter.addFragment(new Order_Detais(), "Request History");

            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("My Details");
            myWorkOutTab.setText("Request History");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        }else {
            adapter.addFragment(new My_Details(), "التفاصيل الخاصة بي");
            adapter.addFragment(new Order_Detais(), "تاريخ الطلب");

            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("التفاصيل الخاصة بي");
            myWorkOutTab.setText("تاريخ الطلب");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);

            /*tabLayout.addTab(myWorkOutTab, 0);
            tabLayout.addTab(myDietTab, 1);*/
        }


    /*    adapter.addFragment(new My_Details(), "My Details");
        adapter.addFragment(new Order_Detais(), "Order History");
        final TabLayout.Tab myDietTab = tabLayout.newTab();
        final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
        myDietTab.setText("My Details");
        myWorkOutTab.setText("Order History");
        tabLayout.addTab(myDietTab, 0);
        tabLayout.addTab(myWorkOutTab, 1);*/
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