package com.example.barayihsalem.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

public class Communities_Club extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_club, abou, supp_services, ambassador, residence, bussi_owner, mem_vispart, industrial,creative,technology;
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

            setContentView(R.layout.activity_communities__club);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_communities__club_ar);
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
        logoside = findViewById(R.id.logoside);
        aboutbs_club = findViewById(R.id.aboutbs_club);
        supp_services = findViewById(R.id.supp_services);
        ambassador = findViewById(R.id.ambassador);
        residence = findViewById(R.id.residence);
        bussi_owner = findViewById(R.id.bussi_owner);
        mem_vispart = findViewById(R.id.mem_vispart);
        industrial = findViewById(R.id.industrial);
        abou = findViewById(R.id.abou);
        creative = findViewById(R.id.creative);
        technology = findViewById(R.id.technology);
        logoside.setOnClickListener(this);
        abou.setTypeface(typebold);
        aboutbs.setTypeface(typebold);
        ambassador.setTypeface(typebold);
        residence.setTypeface(typebold);
        mem_vispart.setTypeface(typebold);
        bussi_owner.setTypeface(typebold);
        supp_services.setTypeface(typebold);
        industrial.setTypeface(typebold);
        creative.setTypeface(typebold);
        technology.setTypeface(typebold);
        aboutbs_club.setTypeface(typeRegular);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Communities_Club.this, AboutBSClub.class));
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