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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Model.Save_User_Rating_Pojo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rating_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView rating_heading, txtlable_rati, rate, abou, notifi, reach_help, abou_app, txtlab;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    APIInterface apiInterface;
    String rating = "";
    AlertDialog alertDialog;
    TextView message, no, yes;
    TextView txtlable;
    RatingBar ratingbar;
    ImageView logoside;
    ProgressDialog progressDialog;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    Button btn_home, btn_rating, btn_failyre;

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }*/
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_rating_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_rating_ar);
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

        //   ratingbar = new RatingBar(Rating_Activity.this);
// now set num of stars
       /* ratingbar.setNumStars(5);
        ratingbar.setRating(Float.parseFloat("1.5"));*/


        Intent intent = getIntent();

        String id = intent.getStringExtra("Mem_P_as");
        String rating1 = intent.getStringExtra("rating1");
        ratingbar.setRating(Float.parseFloat(id));
        rate.setText(rating1);
        rating = String.valueOf(ratingbar.getRating());
        rate.setText(rating);

/*        ratingbar.setRating(Float.parseFloat("0.0"));
        rate.setText("0.0");*/
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating1,
                                        boolean fromUser) {

                String asew = String.valueOf(rating1);
                rating = String.valueOf(rating1);
                rate.setText(asew);
                // place intent for new activity

            }
        });

    }

    private void findid() {

        btn_rating = findViewById(R.id.btn_rating);
        logoside = findViewById(R.id.logoside);
        rating_heading = findViewById(R.id.rating_heading);
        txtlable_rati = findViewById(R.id.txtlable_rati);
        rate = findViewById(R.id.rate);
        ratingbar = findViewById(R.id.ratingBar);

        rate.setTypeface(typeRegular);
        btn_rating.setTypeface(typeRegular);
        rating_heading.setTypeface(typebold);
        txtlable_rati.setTypeface(typeRegular);

        String rating = String.valueOf(ratingbar.getRating());
        rate.setText(rating);
        logoside.setOnClickListener(this);

        btn_rating.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Rating_Activity.this, Acount_Info.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btn_rating:
                /*  onBackPressed();*/
                if (Rating_Activity.this.isNetworkConnected()) {

                    showProgress();
                    Save_User_Rating();

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

    private void Save_User_Rating() {
        Save_User_Rating_Pojo create_event_pojo = new Save_User_Rating_Pojo();

        create_event_pojo.setStore_SrNo(appPreferences.geStore_SrNo());
        create_event_pojo.setUserId(appPreferences.getuserid());

        create_event_pojo.setTrackId(appPreferences.get_checkout());
        create_event_pojo.setRating(rating);

        create_event_pojo.setIpAddress(sessionManager.GetIpAddress());
        create_event_pojo.setCommand("Save");

        create_event_pojo.setCorpcentreby(appPreferences.get_company_id());


        Call<Save_User_Rating_Pojo> call2 = apiInterface.Save_User_Rating(create_event_pojo);
        call2.enqueue(new Callback<Save_User_Rating_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Save_User_Rating_Pojo> call, Response<Save_User_Rating_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                         Toast.makeText(appController,  response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        onBackPressed();



                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
                        Toast.makeText(appController,  response.body().getMessage(), Toast.LENGTH_SHORT).show();



                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {

                        Toast.makeText(appController,  response.body().getMessage(), Toast.LENGTH_SHORT).show();


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
            public void onFailure(Call<Save_User_Rating_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Rating_Activity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }
}