package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Fragment.AditionalServices;
import com.example.barayihsalem.UI.Fragment.RegisterServices;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.barayihsalem.UI.Select_Payment_supplierActivity.dxy;

public class BO_Registered_services extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    TextView aboutbs, alredy_post, new_post, some_descri, price, tvmycarttotalvalue, item_name, item_price, unit, pack_unit;
    AppController appController;
    View view_newpost, view_alredy;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    BottomSheetDialog dialog;

    TextView message, no, yes;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    RecyclerView rw_regi_servi, rw_additional;
    LinearLayout lin_newpost;
    EditText editText_area, editText_price, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    TabLayout tabLayout;
    ViewPager viewPager;
    Pager adapter;
    LinearLayout tabb;

    OnItemClickListener.OnClickCallback onClickCall_addi = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            dxy = "asd";
            appPreferences.save_AddBack_pay("BO_Rigisred_Services");
            startActivity(new Intent(BO_Registered_services.this, Success_Page.class));
            finish();
            //  showBottomSheetDialog_addi();


/*
            if (position==0){
                startActivity(new Intent(WellcomefamilyPage.this,VisionPartner.class));
                finish();
            }*/


        }

    };


    private void showBottomSheetDialog() {
        {
            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                view = LayoutInflater.from(BO_Registered_services.this).inflate(R.layout.register_service_sheet_dialog, null);

            } else {
                view = LayoutInflater.from(BO_Registered_services.this).inflate(R.layout.fragment_bottom_sheet_dialog, null);

            }

            aboutbs = view.findViewById(R.id.aboutbs);
            some_descri = view.findViewById(R.id.some_descri);
            price = view.findViewById(R.id.price);
            proceed = view.findViewById(R.id.proceed);
            tvmycarttotalvalue = view.findViewById(R.id.tvmycarttotalvalue);
            aboutbs.setTypeface(typeHeader);
            price.setTypeface(typeHeader);
            some_descri.setTypeface(typeRegular);
            tvmycarttotalvalue.setTypeface(typeRegular);
            proceed.setTypeface(typeRegular);
            proceed.setOnClickListener(this);

            dialog = new BottomSheetDialog(BO_Registered_services.this);
            dialog.setContentView(view);
            dialog.show();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void showBottomSheetDialog_addi() {
        {
            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                view = LayoutInflater.from(BO_Registered_services.this).inflate(R.layout.addition_service_sheet_dialog, null);

            } else {
                view = LayoutInflater.from(BO_Registered_services.this).inflate(R.layout.fragment_bottom_sheet_dialog, null);

            }

            twitter_l = view.findViewById(R.id.twitter_l);
            aboutbs = view.findViewById(R.id.aboutbs);
            some_descri = view.findViewById(R.id.some_descri);
            price = view.findViewById(R.id.price);
            proceed = view.findViewById(R.id.proceed);
            tvmycarttotalvalue = view.findViewById(R.id.tvmycarttotalvalue);
            aboutbs.setTypeface(typeHeader);

            price.setTypeface(typeHeader);
            some_descri.setTypeface(typeRegular);
            tvmycarttotalvalue.setTypeface(typeRegular);
            proceed.setTypeface(typeRegular);
            proceed.setOnClickListener(this);

            dialog = new BottomSheetDialog(BO_Registered_services.this);
            dialog.setContentView(view);
            dialog.show();
        }
    }


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

            setContentView(R.layout.activity_b_o__registered_services);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_b_o__registered_services_ar);


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
        twitter_l = findViewById(R.id.twitter_l);
        tabb = findViewById(R.id.tabb);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);
        aboutbs = findViewById(R.id.aboutbs);
        lin_newpost = findViewById(R.id.lin_newpost);
        logoside = findViewById(R.id.logoside);
        view_newpost = findViewById(R.id.view_newpost);
        view_alredy = findViewById(R.id.view_alredy);
        rw_regi_servi = findViewById(R.id.rw_regi_servi);
        new_post = findViewById(R.id.new_post);
        alredy_post = findViewById(R.id.alredy_post);
        rw_additional = findViewById(R.id.rw_additional);
        rw_additional.setVisibility(View.GONE);
        new_post.setOnClickListener(this);
        alredy_post.setOnClickListener(this);
        logoside.setOnClickListener(this);
        view_alredy.setVisibility(View.INVISIBLE);
        String fri = appPreferences.get_Color();

        if (appPreferences.getCulturemode().equals("1")) {
            adapter = new Pager(getSupportFragmentManager());
            adapter.addFragment(new RegisterServices(), "Register Service");
            adapter.addFragment(new AditionalServices(), "Additional Service");


            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("Register Service");
            myWorkOutTab.setText("Additional Service");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        } else {

            adapter = new Pager(getSupportFragmentManager());
            adapter.addFragment(new RegisterServices(), "Register Service");

            adapter.addFragment(new AditionalServices(), "Additional Service");

            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("تسجيل الخدمة");
            myWorkOutTab.setText("الخدمات الإضافية");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setAdapter(adapter);

        if (fri.equals("passionate colr")) {
            aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
            twitter_l.setImageResource((R.drawable.passionate));
        }/*else {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            twitter_l.setImageResource((R.drawable.bussiness_owner));
        }*/
        if (appPreferences.get_Color().equals("bussiness colr")) {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
            twitter_l.setBackgroundResource(R.drawable.bussiness_owner);

        }
        if (appPreferences.get_Color().equals("vp_Recident_VP")) {
            aboutbs.setTextColor(getResources().getColor(R.color.recident_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
            twitter_l.setBackgroundResource(R.drawable.residency);

        }

        if (appPreferences.get_Color().equals("vp colr")) {
            aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
            twitter_l.setBackgroundResource(R.drawable.vispart);

        }
        if (appPreferences.get_Color().equals("supp_registerd_services")) {
            aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));


        }
        String fri1 = appPreferences.get_Color();
        Log.d("dddd", fri1);
        if (fri1.equals("bpassionate colr")) {

            //   aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
            // aboutbs.setTextColor(R.color.reject_color);
            aboutbs.setTextColor(getResources().getColor(R.color.reject_color));

            twitter_l.setBackgroundResource(R.drawable.passionate);


        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BO_Registered_services.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.alredy_post:
                lin_newpost.setVisibility(View.GONE);
                view_newpost.setVisibility(View.INVISIBLE);
                view_alredy.setVisibility(View.VISIBLE);
                rw_regi_servi.setVisibility(View.GONE);
                rw_additional.setVisibility(View.VISIBLE);
                // aboutbs.setText("Boots your sales by offers");
                break;
            case R.id.new_post:
                lin_newpost.setVisibility(View.VISIBLE);
                view_newpost.setVisibility(View.VISIBLE);
                view_alredy.setVisibility(View.INVISIBLE);
                rw_regi_servi.setVisibility(View.VISIBLE);
                rw_additional.setVisibility(View.GONE);
                //  aboutbs.setText("Boots your sales");

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