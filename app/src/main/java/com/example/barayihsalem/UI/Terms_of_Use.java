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
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

public class Terms_of_Use extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs_club, abou, aboutbs, abou_1, aboutbs_1, abou_2, aboutbs_2;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Button btnLogin;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_about_bs;
    String value;
    EditText editText_work, editText_title, editText_job;
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
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_terms_of__use);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_terms_of__use_ar);


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
        abou_1 = findViewById(R.id.abou_1);
        abou_2 = findViewById(R.id.abou_2);
        logoside = findViewById(R.id.logoside);
        aboutbs_club = findViewById(R.id.aboutbs_club);
        abou = findViewById(R.id.abou);
        aboutbs_1 = findViewById(R.id.aboutbs_1);
        aboutbs_2 = findViewById(R.id.aboutbs_2);
        logoside.setOnClickListener(this);
        abou.setTypeface(typebold);
        abou_1.setTypeface(typebold);
        abou_2.setTypeface(typebold);
        aboutbs_club.setTypeface(typeRegular);
        aboutbs_1.setTypeface(typeRegular);
        aboutbs_2.setTypeface(typeRegular);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Terms_of_Use.this, About_App.class));
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
}