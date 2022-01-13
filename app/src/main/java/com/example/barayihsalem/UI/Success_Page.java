package com.example.barayihsalem.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Adapter.Select_Membership_Adapter;

import static com.example.barayihsalem.R.layout.activity_pay__success__page;
import static com.example.barayihsalem.R.layout.activity_pay__success__page_ar;
import static com.example.barayihsalem.UI.Select_Payment_supplierActivity.dxy;

public class Success_Page extends AppCompatActivity implements View.OnClickListener {
    TextView check, txtlable, order_num, order_value, check1, pay_status, paystatus_value, transaction_id, transaction_id_value, payment_id, payment_id_value;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    RadioButton radioButton_month, radioButton_year;
    ImageView logoside;
    RecyclerView rw_checkout, rw_pay_mode;
    PayMode_Adapter payMode_adapter;
    Button btn_home;
    RecyclerView rw_select_membership, rw_comminity;
    Select_Membership_Adapter select_membership_adapter;
    RelativeLayout abcd;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
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
            setContentView(activity_pay__success__page);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(activity_pay__success__page_ar);


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
        abcd = findViewById(R.id.abcd);
        txtlable = findViewById(R.id.txtlable);
        check = findViewById(R.id.check);
        logoside = findViewById(R.id.logoside);
        check1 = findViewById(R.id.check1);
        btn_home = findViewById(R.id.btn_home);
        order_num = findViewById(R.id.order_num);
        order_value = findViewById(R.id.order_value);
        payment_id_value = findViewById(R.id.payment_id_value);
        payment_id = findViewById(R.id.payment_id);
        pay_status = findViewById(R.id.pay_status);
        paystatus_value = findViewById(R.id.paystatus_value);
        transaction_id = findViewById(R.id.transaction_id);
        transaction_id_value = findViewById(R.id.transaction_id_value);
        check.setTypeface(typeHeader);
        order_num.setTypeface(typeHeader);
        transaction_id.setTypeface(typeHeader);
        pay_status.setTypeface(typeHeader);
        payment_id.setTypeface(typeHeader);
        payment_id_value.setTypeface(typeHeader);
        paystatus_value.setTypeface(typeRegular);
        transaction_id_value.setTypeface(typeRegular);
        btn_home.setTypeface(typeRegular);
        txtlable.setTypeface(typeRegular);
        check1.setTypeface(typeRegular);
        order_value.setTypeface(typeRegular);
        logoside.setOnClickListener(this);
        btn_home.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // if (appPreferences.Get_AddBack_pay().equals("BO_Rigisred_Services")) {

        if (dxy.equals("success")) {
            startActivity(new Intent(Success_Page.this, Select_Payment_supplierActivity.class));
            finish();
        } else {
            startActivity(new Intent(Success_Page.this, BO_Registered_services.class));
            finish();
        }

       /* startActivity(new Intent(Success_Page.this, HomeActivity.class));
        finish();*/

        //}
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.btn_home:
                if (dxy.equals("success")) {
                    startActivity(new Intent(Success_Page.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(Success_Page.this, BO_Registered_services.class));
                    finish();
                }

                break;
        }
    }
}
