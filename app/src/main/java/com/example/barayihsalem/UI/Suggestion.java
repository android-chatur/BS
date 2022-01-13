package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Model.SuggestionsPojo;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Suggestion extends AppCompatActivity implements View.OnClickListener {
    TextView txt_Event_Size, txt_gender, txt_full_name, aboutbs, abou_2, aboutbs_2;
    AppController appController;
    AppPreferences appPreferences;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    String value;

    Button proceed;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_about_bs;
    EditText editText_mobile, editText_volun_name, editText_job, editText_email;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    String FullName, Email, Suggetion;
    APIInterface apiInterface;

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


            setContentView(R.layout.activity_suggestion);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_suggestion_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }


        aboutbs.setText(value);
        if (value.equals("I have suggestion")) {

            if (appPreferences.getCulturemode().equals("1")) {
                txt_Event_Size.setText("Suggestion");
                editText_mobile.setHint("Write Your Suggestion here");
            } else {
                txt_Event_Size.setText("اقتراح");
                editText_mobile.setHint("اكتب اقتراحك هناe");
            }


        }
        if (value.equals("I have complaint")) {
            if (appPreferences.getCulturemode().equals("1")) {

                txt_Event_Size.setText("complaint");
                editText_mobile.setHint("Write Your complaint here");
            } else {

                txt_Event_Size.setText("شكوى");
                editText_mobile.setHint("اكتب شكواك هنا");
            }
        }
        if (value.equals("I have a question\nas CLUB member")) {
            if (appPreferences.getCulturemode().equals("1")) {
                txt_Event_Size.setText("Quetsion");
                editText_mobile.setHint("Write Your quetsion here");
            } else {
                txt_Event_Size.setText("سؤال  ");
                editText_mobile.setHint("اكتب سؤال هنا");
            }


        }

        if (appPreferences.getsuggetion().equals("opinion")) {
            if (appPreferences.getCulturemode().equals("1")) {
                txt_Event_Size.setText("complaint");
                editText_mobile.setHint("Share Your opinion / on the App");
            } else {
                txt_Event_Size.setText("شكوى");
                editText_mobile.setHint("شاركنا برأيك \\ حول التطبيق");
            }
        }


        if (value.equals("Share Your opinion\nabout the App.")) {
            if (appPreferences.getCulturemode().equals("1")) {
                txt_Event_Size.setText("Opinion");
                editText_mobile.setHint("write Your opinion about the App");
            } else {
                txt_Event_Size.setText("رأيك ");
                editText_mobile.setHint("شاركنا برأيك \\ حول التطبيق");
            }


        }
    }

    private void findid() {
        abcd = findViewById(R.id.abcd);
        logoside = findViewById(R.id.logoside);
        aboutbs = findViewById(R.id.aboutbs);
        proceed = findViewById(R.id.proceed);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_volun_name = findViewById(R.id.editText_volun_name);
        txt_Event_Size = findViewById(R.id.txt_Event_Size);
        editText_email = findViewById(R.id.editText_email);
        txt_gender = findViewById(R.id.txt_gender);
        txt_full_name = findViewById(R.id.txt_full_name);
        logoside.setOnClickListener(this);
        proceed.setOnClickListener(this);
        proceed.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        txt_Event_Size.setTypeface(typeRegular);
        txt_full_name.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        editText_volun_name.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        aboutbs.setTypeface(typebold);
    }

    @Override
    public void onBackPressed() {
        if (appPreferences.getsuggetion().equals("opinion")) {
            startActivity(new Intent(Suggestion.this, About_App.class));
            finish();
        } else {
            startActivity(new Intent(Suggestion.this, Reach_Us_help.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.proceed:


                if (Suggestion.this.isNetworkConnected()) {

                    if (validate()) {

                        if (appPreferences.getsuggetion().equals("suggestion")) {
                            showProgress();
                            Save_Suggestions();
                        }
                        if (appPreferences.getsuggetion().equals("complaint")) {
                            showProgress();
                            Save_Complain();
                        }

                        if (appPreferences.getsuggetion().equals("membe")) {
                            showProgress();
                            Save_Question();
                        }


                        if (appPreferences.getsuggetion().equals("opinion")) {
                            showProgress();
                            Save_Opinion();
                        }

                    }
                }else{
                    Snackbar snackbar = Snackbar
                            .make(abcd, "No internet connection!", Snackbar.LENGTH_LONG)
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
                }

                break;

        }
    }

    private void Save_Opinion() {
        SuggestionsPojo suggestionsPojo = new SuggestionsPojo();
        suggestionsPojo.setUserId(appPreferences.getuserid());
        suggestionsPojo.setFullName(FullName);
        suggestionsPojo.setEmail(Email);
        suggestionsPojo.setOpinion(Suggetion);
        suggestionsPojo.setSource("Android");
        suggestionsPojo.setCorpcentreby(appPreferences.get_company_id());
        suggestionsPojo.setIpAddress(sessionManager.GetIpAddress());
        suggestionsPojo.setCommand("Save");


        Call<SuggestionsPojo> call2 = apiInterface.Save_Opinion(suggestionsPojo);
        call2.enqueue(new Callback<SuggestionsPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SuggestionsPojo> call, Response<SuggestionsPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name.getText().clear();
                        editText_email.getText().clear();
                        editText_mobile.getText().clear();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SuggestionsPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void Save_Question() {
        SuggestionsPojo suggestionsPojo = new SuggestionsPojo();
        suggestionsPojo.setUserId(appPreferences.getuserid());
        suggestionsPojo.setFullName(FullName);
        suggestionsPojo.setEmail(Email);
        suggestionsPojo.setQuestion(Suggetion);
        suggestionsPojo.setSource("Android");

        suggestionsPojo.setCorpcentreby(appPreferences.get_company_id());
        suggestionsPojo.setIpAddress(sessionManager.GetIpAddress());
        suggestionsPojo.setCommand("Save");


        Call<SuggestionsPojo> call2 = apiInterface.Save_Question(suggestionsPojo);
        call2.enqueue(new Callback<SuggestionsPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SuggestionsPojo> call, Response<SuggestionsPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name.getText().clear();
                        editText_email.getText().clear();
                        editText_mobile.getText().clear();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SuggestionsPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void Save_Complain() {
        SuggestionsPojo suggestionsPojo = new SuggestionsPojo();
        suggestionsPojo.setUserId(appPreferences.getuserid());
        suggestionsPojo.setFullName(FullName);
        suggestionsPojo.setEmail(Email);
        suggestionsPojo.setComplain(Suggetion);
        suggestionsPojo.setSource("Android");

        suggestionsPojo.setCorpcentreby(appPreferences.get_company_id());
        suggestionsPojo.setIpAddress(sessionManager.GetIpAddress());
        suggestionsPojo.setCommand("Save");


        Call<SuggestionsPojo> call2 = apiInterface.Save_Complain(suggestionsPojo);
        call2.enqueue(new Callback<SuggestionsPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SuggestionsPojo> call, Response<SuggestionsPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name.getText().clear();
                        editText_email.getText().clear();
                        editText_mobile.getText().clear();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SuggestionsPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void Save_Suggestions() {
        SuggestionsPojo suggestionsPojo = new SuggestionsPojo();
        suggestionsPojo.setUserId(appPreferences.getuserid());
        suggestionsPojo.setFullName(FullName);
        suggestionsPojo.setEmail(Email);
        suggestionsPojo.setSuggestions(Suggetion);
        suggestionsPojo.setSource("Android");

        suggestionsPojo.setCorpcentreby(appPreferences.get_company_id());
        suggestionsPojo.setIpAddress(sessionManager.GetIpAddress());
        suggestionsPojo.setCommand("Save");


        Call<SuggestionsPojo> call2 = apiInterface.Save_Suggestions(suggestionsPojo);
        call2.enqueue(new Callback<SuggestionsPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SuggestionsPojo> call, Response<SuggestionsPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name.getText().clear();
                        editText_email.getText().clear();
                        editText_mobile.getText().clear();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SuggestionsPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Suggestion.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private boolean validate() {
        FullName = editText_volun_name.getText().toString();
        Email = editText_email.getText().toString();
        Suggetion = editText_mobile.getText().toString();


        if (TextUtils.isEmpty(editText_volun_name.getText().toString())) {
            editText_volun_name.setError("Enter Your Name");
            editText_volun_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            if (appPreferences.getsuggetion().equals("suggestion")) {

                editText_mobile.setError("Enter Your Suggetion");
            }

            if (appPreferences.getsuggetion().equals("complaint")) {

                editText_mobile.setError("Enter Your complaint");
            }

            if (appPreferences.getsuggetion().equals("membe")) {

                editText_mobile.setError("Enter Your quetsion here..");
            }


            editText_mobile.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }
}