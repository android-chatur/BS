package com.example.barayihsalem.UI;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

import static com.example.barayihsalem.UI.BecomeResident_member.hasPermissions;
import static com.example.barayihsalem.UI.HomeActivity.boothback;

public class Events_activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_club, aboutbs_Event, settings, check,offer;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Button btnLogin;
    int PERMISSION_ALL = 1;

    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_comming_events, lin_book_location_events, lin_book_your_booth, lin_registerat_event, lin_older_events_gallary;
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


            setContentView(R.layout.activity_events_activity);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_events_activity_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        findid();
    }

    private void findid() {
        lin_older_events_gallary = findViewById(R.id.lin_older_events_gallary);
        lin_comming_events = findViewById(R.id.lin_comming_events);
        lin_book_location_events = findViewById(R.id.lin_book_location_events);
        lin_book_your_booth = findViewById(R.id.lin_book_your_booth);
        aboutbs = findViewById(R.id.aboutbs);
        aboutbs_club = findViewById(R.id.aboutbs_club);
        aboutbs_Event = findViewById(R.id.aboutbs_Event);
        settings = findViewById(R.id.settings);
        offer = findViewById(R.id.offer);
        check = findViewById(R.id.check);
        aboutbs_club.setTypeface(heding);
        aboutbs.setTypeface(heding);
        aboutbs_Event.setTypeface(heding);
        settings.setTypeface(heding);
        check.setTypeface(heding);
        offer.setTypeface(heding);


        logoside = findViewById(R.id.logoside);
        lin_registerat_event = findViewById(R.id.lin_registerat_event);

        logoside.setOnClickListener(this);
        lin_comming_events.setOnClickListener(this);
        lin_book_location_events.setOnClickListener(this);
        lin_book_your_booth.setOnClickListener(this);
        lin_registerat_event.setOnClickListener(this);
        lin_older_events_gallary.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Events_activity.this, DraweActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.lin_comming_events:
                boothback = false;
                startActivity(new Intent(Events_activity.this, Booth_Activity.class));
                finish();
                break;

            case R.id.lin_book_location_events:
                startActivity(new Intent(Events_activity.this, Book_Location_Events.class));
                finish();
                break;

            case R.id.lin_book_your_booth:
                boothback = false;
                startActivity(new Intent(Events_activity.this, Booth_Activity.class));
                finish();
                break;
            case R.id.lin_registerat_event:
                startActivity(new Intent(Events_activity.this, Registered_Event.class));
                finish();
                break;

            case R.id.lin_older_events_gallary:
                startActivity(new Intent(Events_activity.this, Older_Events_Gallary.class));
                finish();
                break;


        }
    }
}