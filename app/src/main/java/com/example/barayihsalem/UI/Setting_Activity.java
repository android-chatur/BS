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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

public class Setting_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_club, aboutbs_Event, settings, offer, Sign_out,language, Notifications, check;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Button btnLogin;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_comming_events, lin_adjust_membership, lin_book_your_booth, lin_registerat_event, lin_older_events_gallary;
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


            setContentView(R.layout.activity_setting_activity);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_setting_activity_ar);


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
        lin_older_events_gallary = findViewById(R.id.lin_older_events_gallary);
        lin_comming_events = findViewById(R.id.lin_comming_events);
        lin_adjust_membership = findViewById(R.id.lin_adjust_membership);
        lin_book_your_booth = findViewById(R.id.lin_book_your_booth);
        aboutbs = findViewById(R.id.aboutbs);
        aboutbs_club = findViewById(R.id.aboutbs_club);
        aboutbs_Event = findViewById(R.id.aboutbs_Event);
        settings = findViewById(R.id.settings);
        offer = findViewById(R.id.offer);
        Sign_out = findViewById(R.id.Sign_out);
        language = findViewById(R.id.language);
        check = findViewById(R.id.check);
        Notifications = findViewById(R.id.Notifications);
        aboutbs_club.setTypeface(typebold);
        aboutbs.setTypeface(typebold);
        aboutbs_Event.setTypeface(typebold);
        settings.setTypeface(typebold);
        offer.setTypeface(typebold);
        Sign_out.setTypeface(typebold);

        Notifications.setTypeface(typebold);
        check.setTypeface(typebold);
        language.setTypeface(typebold);


        logoside = findViewById(R.id.logoside);
        lin_registerat_event = findViewById(R.id.lin_registerat_event);

        logoside.setOnClickListener(this);
        Sign_out.setOnClickListener(this);
        language.setOnClickListener(this);
        lin_comming_events.setOnClickListener(this);
        lin_adjust_membership.setOnClickListener(this);
        lin_book_your_booth.setOnClickListener(this);
        lin_registerat_event.setOnClickListener(this);
        lin_older_events_gallary.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Setting_Activity.this, DraweActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.Sign_out:
                Toast.makeText(appController, "Logout", Toast.LENGTH_SHORT).show();
                appPreferences.saveHomepage(false);
                sessionManager.logoutUser();
                startActivity(new Intent(Setting_Activity.this, LoginActivity.class));
                finish();
                break;
            case R.id.language:

                changeLanguage();

                break;

            case R.id.lin_comming_events:
                startActivity(new Intent(Setting_Activity.this, Acount_Info.class));
                finish();
                break;


            case R.id.lin_adjust_membership:
                startActivity(new Intent(Setting_Activity.this, Adjust_Membership.class));
                finish();
                break;


        }
    }

    private void changeLanguage() {
        if (appController.isLangArebic()) {
            appController.changeLanguage(false);
            startActivity(getIntent());
            finish();
        } else {
            appController.changeLanguage(true);


            startActivity(getIntent());
            finish();
        }

    }
}