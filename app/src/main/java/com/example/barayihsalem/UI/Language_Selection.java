package com.example.barayihsalem.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

public class Language_Selection extends AppCompatActivity {
    public String lsGender = "MALE";
    AppController appController;
    AppPreferences appPreferences;
    RadioButton r_english, r_ar;
    int timeSetting1 = 2;
    int timeSetting = 1;
    TextView txtForot, sinin, ar_lang, eng_lang;
    LinearLayout lin_lang_english, lin_lang_ar;
    private SessionManager sessionManager;
    private Typeface typeSemibold, typeRegular, typeHeader, heding, normal;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white)); //status bar or the time bar at the top
        }
        //setContentView(R.layout.activity_language__selection);

        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_language__selection);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_language__selection_ar);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeRegular;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        findId();

        if (appPreferences.getCulturemode().equals("2")) {

            lin_lang_ar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (timeSetting1 == 2) {
                        r_ar.setChecked(true);
                        r_english.setChecked(false);
                        if (appController.isLangArebic()) {
                            appController.changeLanguage(true);
                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {
                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();


                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                }


                            }



                                /*{
                                startActivity(new Intent(Language_Selection.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }*/


                        } else {
                            appController.changeLanguage(false);

                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {

                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();

                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                }

                            }

                        }
                    }

                }
            });


            lin_lang_english.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (timeSetting == 1) {
                        r_english.setChecked(true);
                        r_ar.setChecked(false);

                        if (appController.isLangArebic()) {
                            appController.changeLanguage(false);


                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {

                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();

                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                }


                            }


                        } else {
                            appController.changeLanguage(true);


                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {
                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();

                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                }


                            }

                        }
                        //   showcustumdialog();

                    }

                }
            });


        } else {


            lin_lang_english.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    r_ar.setChecked(false);
                    r_english.setChecked(true);
                    /// showcustumdialog();
                    if (appController.isLangArebic()) {
                        appController.changeLanguage(true);

                        if (sessionManager.isLoggedIn()) {
                            startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                            finish();
                        } else {
                            if (appPreferences.getLanguage()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();

                            } else {
                                appPreferences.saveLanguage(true);
                                startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();

                            }


                        }


                    } else {
                        appController.changeLanguage(false);

                        if (sessionManager.isLoggedIn()) {
                            startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                            finish();
                        } else {
                            if (appPreferences.getLanguage()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();

                            } else {
                                appPreferences.saveLanguage(true);
                                startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();

                            }
                        }

                    }


                }
            });
            lin_lang_ar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (timeSetting == 1) {
                        r_english.setChecked(true);
                        r_ar.setChecked(false);

                        if (appController.isLangArebic()) {
                            appController.changeLanguage(false);


                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {
                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();

                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                }
                            }


                        } else {
                            appController.changeLanguage(true);
                            if (sessionManager.isLoggedIn()) {
                                startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                finish();
                            } else {
                                if (appPreferences.getLanguage()) {
                                    startActivity(new Intent(Language_Selection.this, WellcomefamilyPage.class));
                                    finish();

                                } else {
                                    appPreferences.saveLanguage(true);
                                    startActivity(new Intent(Language_Selection.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                }
                            }

                        }
                        //   showcustumdialog();

                    }

                }
            });


        }
    }

    private void findId() {
        r_english = findViewById(R.id.r_english);
        r_ar = findViewById(R.id.r_ar);
        lin_lang_ar = findViewById(R.id.lin_lang_ar);
        lin_lang_english = findViewById(R.id.lin_lang_english);
       /* lin_lang_english.setOnClickListener(this);
        lin_lang_ar.setOnClickListener(this);*/
        eng_lang = findViewById(R.id.eng_lang);
        eng_lang.setTypeface(normal);
        ar_lang = findViewById(R.id.ar_lang);
        ar_lang.setTypeface(normal);
        sinin = findViewById(R.id.sinin);
        sinin.setTypeface(heding);
        txtForot = findViewById(R.id.txtForot);
        txtForot.setTypeface(normal);

        r_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == r_ar) {
                    lin_lang_ar.performClick();
                }
            }
        });

        r_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == r_english) {
                    lin_lang_english.performClick();
                }
            }
        });
        if (appPreferences.getCulturemode().equals("1")) {
            r_ar.setChecked(false);
            r_english.setChecked(true);

            ar_lang.setTextColor(getResources().getColor(R.color.hinh_color));
            eng_lang.setTextColor(getResources().getColor(R.color.white));
            lin_lang_english.setBackgroundResource(R.drawable.choose_lang);
            lin_lang_ar.setBackgroundResource(R.drawable.instagram_border_btn);
        } else {
            r_ar.setChecked(true);
            r_english.setChecked(false);

            eng_lang.setTextColor(getResources().getColor(R.color.hinh_color));
            ar_lang.setTextColor(getResources().getColor(R.color.white));
            lin_lang_ar.setBackgroundResource(R.drawable.choose_lang);
            lin_lang_english.setBackgroundResource(R.drawable.instagram_border_btn);

        }

    }
}