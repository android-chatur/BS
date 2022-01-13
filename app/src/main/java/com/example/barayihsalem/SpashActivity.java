package com.example.barayihsalem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.UI.HomeActivity;
import com.example.barayihsalem.UI.LoginActivity;
import com.example.barayihsalem.UI.WellcomefamilyPage;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;
import java.util.UUID;

public class SpashActivity extends AppCompatActivity {
    public static boolean ishome = false;
    private static int SPLASH_TIME_OUT = 1500;
    Activity activity;
    Context context;
    String ipaddress;
    String Uniqueids;
    SessionManager sessionManager;
    AppPreferences appPreferences;
    private ConstraintLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        context = this;
        activity = this;

        ipaddress = UUID.randomUUID().toString();
        Uniqueids = uniquepwd();
        // sessionManager.SetUniqueId("");
        appPreferences.save_conpany_id("1");


        if (sessionManager.GetIpAddress() != null) {
            if (sessionManager.GetIpAddress().equals("") || sessionManager.GetIpAddress().equals("null")) {
                sessionManager.SetIpAddress(ipaddress);
            }
        } else {
            sessionManager.SetIpAddress(ipaddress);
        }

        if (sessionManager.GetUniqueId() != null) {
            if (sessionManager.GetUniqueId().equals("") || sessionManager.GetUniqueId().equals("null")) {
                sessionManager.SetUniqueId(Uniqueids);
            }
        } else {
            sessionManager.SetUniqueId(Uniqueids);
        }


        String IpAddress = sessionManager.GetIpAddress();
        String GetUniqueId = sessionManager.GetUniqueId();

        Log.d("IpAddress", IpAddress);
        Log.d("GetUniqueId", GetUniqueId);

        //      boolean bool = SharedPreferencesManager.getBoolean(getApplicationContext(), "lang", false);
        // Locale locale = new Locale( "en");

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        new Handler().postDelayed(new Runnable() {
            // Showing splash screen with a timer. This will be useful when you want to show case your app logo / company
            @Override
            public void run() {


                if (SpashActivity.this.isNetworkConnected()) {
                    if (!sessionManager.isLoggedIn()) {
                        appPreferences.getLanguage();

                        startActivity(new Intent(SpashActivity.this, LoginActivity.class));
                        finish();

                    } else {
                        String asd = String.valueOf(appPreferences.getLanguage());
                        Log.d("asd", asd);

                        if (appPreferences.getHomepage()) {
                            startActivity(new Intent(SpashActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(SpashActivity.this, WellcomefamilyPage.class));
                            finish();
                        }


                    }


                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
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
                    // TastyToast.makeText(context, "Check your Internet Connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }
            }
        }, SPLASH_TIME_OUT);
    }

    public String uniquepwd() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }

    public String uniqueid() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) this.getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }
}