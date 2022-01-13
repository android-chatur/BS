package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Visipart_Adapter;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.MembershiPojo;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VisionPartner extends YouTubeBaseActivity implements View.OnClickListener {
    TextView txtlable, txtlable1, txtlable2, txtForgot, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    Button btnLogin;
    APIInterface apiInterface;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    ProgressDialog progressDialog;
    ImageView logoside;
    CheckBox tems_cond;
    String youtube_Url = "";
    YouTubePlayerView player;

    private final String API_KEY = "https://youtu.be/";
    // boolean adb = false;rel
    RelativeLayout topLayouts;
    EditText editText_email_phone, edit_pswd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Visipart_Adapter visipart_adapter;
    ArrayList<List> Vision_list = new ArrayList<List>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_vision_partner);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_vision_partner_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        findid();


        if (VisionPartner.this.isNetworkConnected()) {

            showProgress();
            Get_Membership_Details_API(appPreferences.get_membership_srno(), appPreferences.getuserid(), sessionManager.GetUniqueId(), appPreferences.getCulturemode(), appPreferences.get_company_id());

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

    private void Get_Membership_Details_API(String membership_srno, String getuserid, String getUniqueId, String culturemode, String company_id) {

        apiInterface.Get_Membership_Details_API(membership_srno, getuserid, getUniqueId, culturemode, company_id).enqueue(new Callback<MembershiPojo>() {


            @Override
            public void onResponse(Call<MembershiPojo> call, Response<MembershiPojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();

                    Vision_list.addAll(response.body().list);

                    youtube_Url = response.body().details.getVideoURL();
                    player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if (!b) {
                                if (youtube_Url != null|| !youtube_Url.isEmpty()) {
                                    youTubePlayer.cueVideo(youtube_Url);
                                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                                } else {

                                }

                            }
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });

                    txtlable.setText(response.body().details.getName());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_vision.setLayoutManager(gridLayoutManager);
                    visipart_adapter = new Visipart_Adapter(VisionPartner.this, Vision_list);
                    rw_vision.setAdapter(visipart_adapter);




                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<MembershiPojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(VisionPartner.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void findid() {
       /// topLayouts = findViewById(R.id.topLayouts);
        tems_cond = findViewById(R.id.tems_cond);
        abcd = findViewById(R.id.abcd);

        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(typebold);
        player = findViewById(R.id.player);

        rw_vision = findViewById(R.id.rw_vision);
        logoside = findViewById(R.id.logoside);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setTypeface(typeRegular);
        tems_cond.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        logoside.setOnClickListener(this);
        logoside.setOnClickListener(this);
/*
        tems_cond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    tems_cond.setSelected(isChecked);
                    adb = true;

                } else {
                    tems_cond.setSelected(false);
                    adb = false;

                }
            }
        });
*/

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VisionPartner.this, WellcomefamilyPage.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:


                startActivity(new Intent(VisionPartner.this, BecomeVisPart.class));
                finish();

                break;

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }
}