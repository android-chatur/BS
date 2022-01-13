package com.example.barayihsalem.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Fragment.Donation;
import com.example.barayihsalem.UI.Fragment.Valuntureenig;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BO_Volunteering_Donation extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    TextView aboutbs, alredy_post, new_post, txt_full_name, txt_gender, txt_nationality, txt_age, txt_voluntee, txt_phone;
    TextView txt_full_name_do, txt_gender_do, txt_nationality_do, txt_age_do, txt_voluntee_do, txt_phone_do, txt_donate;
    AppController appController;
    View view_newpost, view_alredy;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    RadioButton male, female;
    RadioButton male_do, female_do;
    BottomSheetDialog dialog;
    TextView message, no, yes;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed, proceed_do;
    //  RecyclerView rw_regi_servi, rw_additional;
    LinearLayout lin_newpost, lin_donation;
    EditText editText_volun_name, editText_age, editText_mobile;
    EditText editText_volun_name_do, editText_age_do, editText_mobile_do;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    APIInterface apiInterface;


    OnItemClickListener.OnClickCallback onClickCall_addi = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            // showBottomSheetDialog_addi();


        }

    };

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            // showBottomSheetDialog();


        }

    };

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
            setContentView(R.layout.activity_b_o__volunteering__donation);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_b_o__volunteering__donation_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        findid();

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new Valuntureenig(), "Volunteering");
        adapter.addFragment(new Donation(), "Donations");
        if (appPreferences.getCulturemode().equals("1")) {

            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("Volunteering");
            myWorkOutTab.setText("Donations");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        } else {
            final TabLayout.Tab myDietTab = tabLayout.newTab();
            final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
            myDietTab.setText("التطوُّع");
            myWorkOutTab.setText("التبرعات");
            tabLayout.addTab(myDietTab, 0);
            tabLayout.addTab(myWorkOutTab, 1);
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setAdapter(adapter);
    }

    private void findid() {
        aboutbs = findViewById(R.id.aboutbs);
        male = findViewById(R.id.male);
        male_do = findViewById(R.id.male_do);
        female_do = findViewById(R.id.female_do);
        female = findViewById(R.id.female);
        txt_full_name = findViewById(R.id.txt_full_name);
        txt_gender = findViewById(R.id.txt_gender);
        lin_newpost = findViewById(R.id.lin_newpost);
        editText_volun_name = findViewById(R.id.editText_volun_name);
        editText_volun_name_do = findViewById(R.id.editText_volun_name_do);
        editText_age_do = findViewById(R.id.editText_age_do);
        editText_mobile_do = findViewById(R.id.editText_mobile_do);
        logoside = findViewById(R.id.logoside);
        twitter_l = findViewById(R.id.twitter_l);
        view_newpost = findViewById(R.id.view_newpost);
        view_alredy = findViewById(R.id.view_alredy);
        new_post = findViewById(R.id.new_post);
        txt_age = findViewById(R.id.txt_age);
        txt_voluntee = findViewById(R.id.txt_voluntee);
        txt_nationality = findViewById(R.id.txt_nationality);
        alredy_post = findViewById(R.id.alredy_post);
        editText_age = findViewById(R.id.editText_age);
        editText_mobile = findViewById(R.id.editText_mobile);
        txt_phone = findViewById(R.id.txt_phone);
        lin_donation = findViewById(R.id.lin_donation);
        proceed = findViewById(R.id.proceed);
        proceed_do = findViewById(R.id.proceed_do);
        txt_full_name_do = findViewById(R.id.txt_full_name_do);
        txt_age_do = findViewById(R.id.txt_age_do);
        txt_voluntee_do = findViewById(R.id.txt_voluntee_do);
        txt_gender_do = findViewById(R.id.txt_gender_do);
        txt_nationality_do = findViewById(R.id.txt_nationality_do);
        txt_phone_do = findViewById(R.id.txt_phone_do);
        txt_donate = findViewById(R.id.txt_donate);
        editText_mobile_do.setTypeface(typeRegular);
        male_do.setTypeface(typeRegular);
        txt_full_name.setTypeface(typeRegular);
        txt_voluntee_do.setTypeface(typeRegular);
        female_do.setTypeface(typeRegular);
        txt_phone_do.setTypeface(typeRegular);
        txt_full_name_do.setTypeface(typeRegular);
        txt_donate.setTypeface(typeRegular);
        txt_age_do.setTypeface(typeRegular);
        editText_age_do.setTypeface(typeRegular);
        txt_nationality_do.setTypeface(typeRegular);
        txt_gender_do.setTypeface(typeRegular);
        editText_volun_name_do.setTypeface(typeRegular);
        male.setTypeface(typeRegular);
        proceed_do.setTypeface(typeRegular);
        female.setTypeface(typeRegular);
        txt_age.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        txt_voluntee.setTypeface(typeRegular);
        txt_phone.setTypeface(typeRegular);
        txt_nationality.setTypeface(typeRegular);
        editText_volun_name.setTypeface(typeRegular);
        editText_age.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        aboutbs.setTypeface(typebold);
        alredy_post.setTypeface(typebold);
        new_post.setTypeface(typebold);
        new_post.setOnClickListener(this);
        alredy_post.setOnClickListener(this);
        logoside.setOnClickListener(this);
        view_alredy.setVisibility(View.INVISIBLE);
        lin_donation.setVisibility(View.GONE);
        tabb = findViewById(R.id.tabb);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        if (appPreferences.get_Color().equals("bussiness colr")) {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
            twitter_l.setBackgroundResource(R.drawable.bussiness_owner);
            tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.buss_oner_color)));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.buss_oner_color));

        }

        if (appPreferences.get_Color().equals("supplier_Volunteering_Donation")) {
            aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
            // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));
            tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.supplier_color)));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.supplier_color));

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BO_Volunteering_Donation.this, HomeActivity.class));
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