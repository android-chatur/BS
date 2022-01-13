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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Older_event_Gallary_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Model.EventGallery;
import com.example.barayihsalem.UI.Model.EventList;
import com.example.barayihsalem.UI.Model.Gallery_CommunityPojo;
import com.example.barayihsalem.UI.Model.InitiativesPojo;
import com.example.barayihsalem.UI.Model.List;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallery_Commu extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, txt_full_name, txt_date, txt_gender, txt_nationality, txt_phone, txt_ptem_con, txt_Event_Size;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    ArrayList<List> Gallary_list = new ArrayList<>();

    AlertDialog alertDialog;
    BottomSheetDialog dialog;
    TextView message, no, yes;
    RelativeLayout abcd;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    RecyclerView rw_gallary, rw_additional;
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    EditText editText_age, editText_name, editText_mobile;
    Older_event_Gallary_Adapter older_event_gallary_adapter;
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
            setContentView(R.layout.activity_gallery__commu);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_gallery__commu_ar);

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

        if (appPreferences.get_commu_color().equals("service")) {
            aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
            twitter_l.setImageResource((R.drawable.supplir_image));
            twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));

            // proceed.setBackground(getResources().getDrawable(R.drawable.passionate_drawable));

        }
        if (appPreferences.get_commu_color().equals("entretaiment")) {
            aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
            twitter_l.setImageResource((R.drawable.passionate));
            // proceed.setBackground(getResources().getDrawable(R.drawable.passionate_drawable));

        }
        if (appPreferences.get_commu_color().equals("bussiretail")) {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.bussiness_owner));

        }

        if (appPreferences.get_commu_color().equals("food_be")) {
            aboutbs.setTextColor(getResources().getColor(R.color.fb_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.fb_color));

        }
        if (appPreferences.get_commu_color().equals("envirome")) {
            aboutbs.setTextColor(getResources().getColor(R.color.enviro_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.enviro_color));


        }
        if (appPreferences.get_commu_color().equals("technology")) {
            aboutbs.setTextColor(getResources().getColor(R.color.tq_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.tq_color));


        }
        if (appPreferences.get_commu_color().equals("industrial")) {
            aboutbs.setTextColor(getResources().getColor(R.color.indus_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));
            twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));
        }
        if (appPreferences.get_commu_color().equals("creative")) {
            aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
            //   proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.vispart));
            //     twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));


        }

        if (Gallery_Commu.this.isNetworkConnected()) {
            showProgress();
            Get_Event_Gallery_Community_wise_API();


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



    }

    private void Get_Event_Gallery_Community_wise_API() {
        Gallery_CommunityPojo initiativesPojo = new Gallery_CommunityPojo();
        initiativesPojo.setValue(appPreferences.get_Community_srno());
        initiativesPojo.setUserId(appPreferences.getuserid());
        initiativesPojo.setCultureId(appPreferences.getCulturemode());
        initiativesPojo.setCorpcentreby(appPreferences.get_company_id());


        Call<Gallery_CommunityPojo> call2 = apiInterface.Get_Event_Gallery_Community_wise_API(initiativesPojo);
        call2.enqueue(new Callback<Gallery_CommunityPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Gallery_CommunityPojo> call, Response<Gallery_CommunityPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();

                if (response.isSuccessful()) {

                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {


                        Gallary_list.addAll(response.body().getList());

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                        rw_gallary.setLayoutManager(gridLayoutManager);
                        older_event_gallary_adapter = new Older_event_Gallary_Adapter(Gallery_Commu.this,Gallary_list);
                        rw_gallary.setAdapter(older_event_gallary_adapter);

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

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
            public void onFailure(Call<Gallery_CommunityPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });

    }


    private void showProgress() {
        progressDialog = new ProgressDialog(Gallery_Commu.this);
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

    private void findid() {
        abcd = findViewById(R.id.abcd);
        aboutbs = findViewById(R.id.aboutbs);
        twitter_l = findViewById(R.id.twitter_l);
        rw_gallary = findViewById(R.id.rw_gallary);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        aboutbs.setTypeface(typebold);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Gallery_Commu.this, HomeActivity.class));
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