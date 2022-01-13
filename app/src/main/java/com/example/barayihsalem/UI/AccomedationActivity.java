package com.example.barayihsalem.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import com.example.barayihsalem.UI.Fragment.Offering;
import com.example.barayihsalem.UI.Fragment.Seeking;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AccomedationActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
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
  EditText editText_area, editText_price, editText_job;
  Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
  TabLayout tabLayout;
  ViewPager viewPager;


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
    //  setContentView(R.layout.activity_signup);
    if (appPreferences.getCulturemode().equals("1")) {
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

      setContentView(R.layout.activity_accomedation);


    } else {
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

      setContentView(R.layout.activity_accomedation_ar);


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
    tabLayout = findViewById(R.id.tab_layout);
    viewPager = findViewById(R.id.pager);
    aboutbs = findViewById(R.id.aboutbs);
    logoside = findViewById(R.id.logoside);
    logoside.setOnClickListener(this);
    String fri = appPreferences.get_Color();

    Pager adapter = new Pager(getSupportFragmentManager());
    if (appPreferences.getCulturemode().equals("1")) {


      adapter.addFragment(new Seeking(), "Search");
      adapter.addFragment(new Offering(), "Offer");

      final TabLayout.Tab myDietTab = tabLayout.newTab();
      final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
      myDietTab.setText("Search");
      myWorkOutTab.setText("Offer");
      tabLayout.addTab(myDietTab, 0);
      tabLayout.addTab(myWorkOutTab, 1);
    } else {
      adapter.addFragment(new Seeking(), "البحث");
      adapter.addFragment(new Offering(), "العرض");

      final TabLayout.Tab myDietTab = tabLayout.newTab();
      final TabLayout.Tab myWorkOutTab = tabLayout.newTab();
      myDietTab.setText("البحث");
      myWorkOutTab.setText("العرض");
      tabLayout.addTab(myDietTab, 0);
      tabLayout.addTab(myWorkOutTab, 1);
    }
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.setOnTabSelectedListener(this);
    viewPager.setAdapter(adapter);

    if (fri.equals("passionate colr")) {
      aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
      twitter_l.setImageResource((R.drawable.passionate));

      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.reject_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.reject_color));
    }/*else {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            twitter_l.setImageResource((R.drawable.bussiness_owner));
        }*/
    if (appPreferences.get_Color().equals("bussiness colr")) {
      aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
      // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
      twitter_l.setBackgroundResource(R.drawable.bussiness_owner);
      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.buss_oner_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.buss_oner_color));

    }
    if (appPreferences.get_Color().equals("vp_Recident_VP")) {
      aboutbs.setTextColor(getResources().getColor(R.color.recident_color));
      // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
      twitter_l.setBackgroundResource(R.drawable.residency);
      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.recident_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.recident_color));
    }

    if (appPreferences.get_Color().equals("vp colr")) {
      aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
      // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));
      twitter_l.setBackgroundResource(R.drawable.vispart);
      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.vispart_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.vispart_color));
    }
    if (appPreferences.get_Color().equals("supp_registerd_services")) {
      aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
      // twitter_l.setImageResource(getResources().getDrawable(R.drawable.bussiness_owner));

      twitter_l.setImageResource((R.drawable.bussiness_owner));
      twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));

      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.supplier_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.supplier_color));
    }
    String fri1 = appPreferences.get_Color();
    Log.d("dddd", fri1);
    if (fri1.equals("bpassionate colr")) {

      //   aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
      // aboutbs.setTextColor(R.color.reject_color);
      aboutbs.setTextColor(getResources().getColor(R.color.reject_color));

      twitter_l.setBackgroundResource(R.drawable.passionate);

      tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.reject_color)));
      tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.reject_color));
    }

  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(AccomedationActivity.this, HomeActivity.class));
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