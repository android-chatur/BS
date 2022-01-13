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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Entertainment_Adapter;
import com.example.barayihsalem.UI.Model.CommunityPojo;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.Save_Community_Pojo;
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

public class Entertainment_Community extends YouTubeBaseActivity implements View.OnClickListener {
    //   private final String API_KEY = "AIzaSyCfpuW4YlQAXArFEGfSovo2txBBYLpalnQ";
    private final String API_KEY = "https://youtu.be/";
    TextView txtlable, txtlable1, txtlable2, txtForgot, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    Button btnLogin;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    YouTubePlayerView player;
    ArrayList<List> Entertainment_List = new ArrayList<>();

    String youtube_Url = "";
    ImageView logoside;
    ProgressDialog progressDialog;
    APIInterface apiInterface;
    boolean adb = false;
    //  CheckBox tems_cond;
    EditText editText_email_phone, edit_pswd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Entertainment_Adapter entertainment_adapter;
    AlertDialog alertDialog;
    TextView message, no, yes;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.reject_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.reject_color)); //status bar or the time bar at the top
        }

        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_entertainment__community);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_entertainment__community_ar);
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


        if (Entertainment_Community.this.isNetworkConnected()) {

            showProgress();
            Get_Community_Details_API(appPreferences.get_Community_srno(), appPreferences.getuserid(), sessionManager.GetUniqueId(), appPreferences.getCulturemode(), appPreferences.get_company_id());

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

    private void Get_Community_Details_API(String community_srno, String getuserid, String getUniqueId, String culturemode, String company_id) {

        apiInterface.Get_Community_Details_API(community_srno, getuserid, getUniqueId, culturemode, company_id).enqueue(new Callback<CommunityPojo>() {


            @Override
            public void onResponse(Call<CommunityPojo> call, Response<CommunityPojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();

                    Entertainment_List.addAll(response.body().list);

                    youtube_Url = response.body().details.getVideoURL();

                    txtlable.setText(response.body().details.getName());


                    player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if (!b) {
                                if (youtube_Url != null || !youtube_Url.isEmpty()) {
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
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_vision.setLayoutManager(gridLayoutManager);
                    entertainment_adapter = new Entertainment_Adapter(Entertainment_Community.this, Entertainment_List);
                    rw_vision.setAdapter(entertainment_adapter);

                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<CommunityPojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });


    }

    private void findid() {
        player = findViewById(R.id.player);

        abcd = findViewById(R.id.abcd);
        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(typeHeader);
        rw_vision = findViewById(R.id.rw_vision);
        logoside = findViewById(R.id.logoside);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        logoside.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Entertainment_Community.this, WellcomefamilyPage.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (Entertainment_Community.this.isNetworkConnected()) {

                    showProgress();
                    Save_Community();


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

                //    Showdialog();

                break;
            case R.id.no:
                startActivity(new Intent(Entertainment_Community.this, HomeActivity.class));
                finish();


                break;
            case R.id.yes:
                startActivity(new Intent(Entertainment_Community.this, WellcomefamilyPage.class));
                finish();
                break;

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }

    private void Save_Community() {
        Save_Community_Pojo community_pojo = new Save_Community_Pojo();
        community_pojo.setUserId(appPreferences.getuserid());
        community_pojo.setComm_SrNo(appPreferences.get_Community_srno());
        community_pojo.setUniqueId(sessionManager.GetUniqueId());
        community_pojo.setCorpcentreby(appPreferences.get_company_id());
        community_pojo.setIpAddress(sessionManager.GetIpAddress());
        community_pojo.setSource("Android");
        community_pojo.setCommand("Save");


        Call<Save_Community_Pojo> call2 = apiInterface.Save_Community(community_pojo);
        call2.enqueue(new Callback<Save_Community_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Save_Community_Pojo> call, Response<Save_Community_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Community_Dialog start_dialog = new Community_Dialog(Entertainment_Community.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();
                        // Showdialog();
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

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
            public void onFailure(Call<Save_Community_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Entertainment_Community.this);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void Showdialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_commu, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_commu_ar, viewGroup, false);


        }
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        message = dialogView.findViewById(R.id.message);
        no = dialogView.findViewById(R.id.no);
        yes = dialogView.findViewById(R.id.yes);
        message.setTypeface(heding);
        no.setTypeface(heding);
        yes.setTypeface(heding);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}