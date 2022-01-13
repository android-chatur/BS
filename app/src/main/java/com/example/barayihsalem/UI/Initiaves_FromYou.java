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
import com.example.barayihsalem.UI.Model.InitiativesPojo;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Initiaves_FromYou extends AppCompatActivity implements View.OnClickListener {
  TextView txt_Event_Size, txt_gender, txt_full_name, aboutbs, abou_2, aboutbs_2;
  AppController appController;
  AppPreferences appPreferences;
  SessionManager sessionManager;
  AlertDialog alertDialog;
  TextView message, no, yes;
  ImageView logoside, twitter_l;
  String value;
  Button proceed;
  RecyclerView rw_vision, rw_comminity;
  RelativeLayout abcd;
  LinearLayout lin_about_bs;
  ProgressDialog progressDialog;
  EditText editText_mobile,editText_email, editText_volun_name, editText_job;
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

      setContentView(R.layout.activity_initiaves__from_you);

    } else {
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
      setContentView(R.layout.activity_initiaves__from_you_ar);

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

    if (appPreferences.get_commu_color().equals("entretaiment")) {

      aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
      twitter_l.setImageResource((R.drawable.passionate));
      proceed.setBackground(getResources().getDrawable(R.drawable.passionate_drawable));

      twitter_l.setColorFilter(getResources().getColor(R.color.reject_color));

    }
    if (appPreferences.get_commu_color().equals("bussiretail")) {

      aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
      twitter_l.setImageResource((R.drawable.bussiness_owner));


      twitter_l.setColorFilter(getResources().getColor(R.color.buss_oner_color));
      proceed.setBackground(getResources().getDrawable(R.drawable.bussine_drawable));


    }
    if (appPreferences.get_commu_color().equals("service")) {
      aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
      twitter_l.setImageResource((R.drawable.supplir_image));
      twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));

      proceed.setBackground(getResources().getDrawable(R.drawable.supplier));


    }
    if (appPreferences.get_commu_color().equals("food_be")) {

      aboutbs.setTextColor(getResources().getColor(R.color.fb_color));
      twitter_l.setImageResource((R.drawable.vispart));
      twitter_l.setColorFilter(getResources().getColor(R.color.fb_color));

      proceed.setBackground(getResources().getDrawable(R.drawable.food_beve_dra));


    }

    if (appPreferences.get_commu_color().equals("envirome")) {

      aboutbs.setTextColor(getResources().getColor(R.color.enviro_color));
      twitter_l.setImageResource((R.drawable.vispart));
      twitter_l.setColorFilter(getResources().getColor(R.color.enviro_color));

      proceed.setBackground(getResources().getDrawable(R.drawable.enviro_commu));

    }
    if (appPreferences.get_commu_color().equals("industrial")) {

      aboutbs.setTextColor(getResources().getColor(R.color.indus_color));
      twitter_l.setImageResource((R.drawable.vispart));
      twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));

      proceed.setBackground(getResources().getDrawable(R.drawable.indus_commu));

    }

    if (appPreferences.get_commu_color().equals("creative")) {

      aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
      twitter_l.setImageResource((R.drawable.vispart));
      // twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));

      proceed.setBackground(getResources().getDrawable(R.drawable.visionpatner));

    }
    if (appPreferences.get_commu_color().equals("technology")) {

      aboutbs.setTextColor(getResources().getColor(R.color.tq_color));
      twitter_l.setImageResource((R.drawable.vispart));
      twitter_l.setColorFilter(getResources().getColor(R.color.tq_color));
      proceed.setBackground(getResources().getDrawable(R.drawable.techno_commu));

    }

  }

  private void findid() {
    logoside = findViewById(R.id.logoside);
    abcd = findViewById(R.id.abcd);
    twitter_l = findViewById(R.id.twitter_l);
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
    startActivity(new Intent(Initiaves_FromYou.this, HomeActivity.class));
    finish();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.logoside:
        onBackPressed();
        break;


      case R.id.proceed:
        if (Initiaves_FromYou.this.isNetworkConnected()) {

          if (validate()) {
            showProgress();
            Save_Initiatives_From_You();
          }
        } else {
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

  private void Save_Initiatives_From_You() {
    InitiativesPojo initiativesPojo = new InitiativesPojo();
    initiativesPojo.setUserId(appPreferences.getuserid());
    initiativesPojo.setComm_SrNo(appPreferences.get_Community_srno());
    initiativesPojo.setComm_F_XCode(appPreferences.get_Commu_Xcode());
    initiativesPojo.setFullName(FullName);
    initiativesPojo.setEmail(Email);
    initiativesPojo.setInitiatives(Suggetion);
    initiativesPojo.setSource("Android");
    initiativesPojo.setActive(true);
    initiativesPojo.setDelete(false);
    initiativesPojo.setCorpcentreby(appPreferences.get_company_id());
    initiativesPojo.setIpAddress(sessionManager.GetIpAddress());
    initiativesPojo.setCommand("Save");


    Call<InitiativesPojo> call2 = apiInterface.Save_Initiatives_From_You(initiativesPojo);
    call2.enqueue(new Callback<InitiativesPojo>() {
      @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
      @Override
      public void onResponse(Call<InitiativesPojo> call, Response<InitiativesPojo> response) {
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
      public void onFailure(Call<InitiativesPojo> call, Throwable t) {
        call.cancel();
        hideProgress();
      }
    });


  }

  private void showProgress() {
    progressDialog = new ProgressDialog(Initiaves_FromYou.this);
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


      editText_mobile.setError("Enter Your Suggetion");
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