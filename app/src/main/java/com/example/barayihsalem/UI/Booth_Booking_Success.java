package com.example.barayihsalem.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

import static com.example.barayihsalem.UI.Comming_Events.eventback;

public class Booth_Booking_Success extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, txt_transaction_id, txt_tras_idvalue, txt_event_named, txt_booth, txt_company_img, txt_lice_img, txt_booth_value, txt_eventname_value;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside, cross;
    Button proceed, procee1d;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

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
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_booth__booking__success);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_booth__booking__success_ar);


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

    @Override
    public void onBackPressed() {

        if (eventback==true){
            startActivity(new Intent(Booth_Booking_Success.this, Comming_Events.class));
            finish();
        }else {
            startActivity(new Intent(Booth_Booking_Success.this, Booth_Activity.class));
            finish();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }

    private void findid() {
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);

        aboutbs = findViewById(R.id.aboutbs);
        txt_transaction_id = findViewById(R.id.txt_transaction_id);
        txt_tras_idvalue = findViewById(R.id.txt_tras_idvalue);
        txt_event_named = findViewById(R.id.txt_event_named);
        txt_eventname_value = findViewById(R.id.txt_eventname_value);
        txt_booth = findViewById(R.id.txt_booth);
        txt_booth_value = findViewById(R.id.txt_booth_value);
        txt_company_img = findViewById(R.id.txt_company_img);
        txt_lice_img = findViewById(R.id.txt_lice_img);
        aboutbs.setTypeface(typebold);
        txt_transaction_id.setTypeface(typeRegular);
        txt_tras_idvalue.setTypeface(typeRegular);
        txt_event_named.setTypeface(typeRegular);
        txt_eventname_value.setTypeface(typeRegular);
        txt_booth.setTypeface(typeRegular);
        txt_booth_value.setTypeface(typeRegular);
        txt_company_img.setTypeface(typeRegular);
        txt_lice_img.setTypeface(typeRegular);
    }
}