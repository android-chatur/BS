package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Home_Adapter;
import com.example.barayihsalem.UI.Adapter.Home_Adapter_Common;
import com.example.barayihsalem.UI.Adapter.Home_Adapter_Commu;
import com.example.barayihsalem.UI.Adapter.SlidingImage_Adapter_home;
import com.example.barayihsalem.UI.Model.AdsDetail;
import com.example.barayihsalem.UI.Model.Common;
import com.example.barayihsalem.UI.Model.Get_Membership_DashboardPojo;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.List2;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.example.barayihsalem.Viewmodel.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static boolean boothback = false;
    private static ViewPager mPager;
    private static int currentPage = 0;
    TextView txtlable, txtlable1;
    AppController appController;
    APIInterface apiInterface;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    BottomSheetDialog dialog;
    ProgressDialog progressDialog;
    ArrayList<List> Membership_list = new ArrayList<>();
    ArrayList<List2> Community_list = new ArrayList<>();
    ArrayList<Common> Common_list = new ArrayList<>();
    ArrayList<AdsDetail> Add_list = new ArrayList<>();
    ArrayList<AdsDetail> Add_list_deatal = new ArrayList<>();
    TextView message, no, yes;
    Timer swipeTimer = null;
    ImageView logoside, twitter_l, img_change_lang;
    Button btnLogin, proceed;
    RecyclerView rw_vision, rw_commu, rw_common;
    RelativeLayout abcd;
    String ipaddress;
    String Uniqueids;
    HomeViewModel viewModel;
    Home_Adapter_Common adapter_common;
    Home_Adapter home_adapter;
    GridLayoutManager gridLayoutManager;
    Home_Adapter_Commu home_adapter_commu;
    OnItemClickListener.OnClickCallback onClickCall_community = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            if (Community_list != null && Community_list.size() > 0) {
                //Coming Event

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000001")) {
                    appPreferences.save_commu_color("entretaiment");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }


                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000002")) {
                    appPreferences.save_commu_color("bussiretail");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000003")) {
                    appPreferences.save_commu_color("service");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000004")) {
                    appPreferences.save_commu_color("food_be");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000005")) {
                    appPreferences.save_commu_color("envirome");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000006")) {
                    appPreferences.save_commu_color("industrial");
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000007")) {
                    appPreferences.save_commu_color("creative");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CmngEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000008")) {
                    appPreferences.save_commu_color("technology");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                    finish();
                }


                //create event
                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000001")) {
                    appPreferences.save_commu_color("entretaiment");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }


                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000002")) {
                    appPreferences.save_commu_color("bussiretail");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000003")) {
                    appPreferences.save_commu_color("service");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000004")) {
                    appPreferences.save_commu_color("food_be");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000005")) {
                    appPreferences.save_commu_color("envirome");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000006")) {
                    appPreferences.save_commu_color("industrial");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());


                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000007")) {
                    appPreferences.save_commu_color("creative");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("CrtEvnts") && Community_list.get(position).getCommSrNo().equals("CM00000008")) {
                    appPreferences.save_commu_color("technology");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Create_Events.class));
                    finish();
                }

// opportunity for you

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000001")) {
                    appPreferences.save_commu_color("entretaiment");

                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }


                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000002")) {
                    appPreferences.save_commu_color("bussiretail");

                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000003")) {
                    appPreferences.save_commu_color("service");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000004")) {
                    appPreferences.save_commu_color("food_be");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000005")) {
                    appPreferences.save_commu_color("envirome");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000006")) {
                    appPreferences.save_commu_color("industrial");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000007")) {
                    appPreferences.save_commu_color("creative");

                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Opprtnt") && Community_list.get(position).getCommSrNo().equals("CM00000008")) {
                    appPreferences.save_commu_color("technology");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                    finish();
                }


                // opportunity for you

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000001")) {
                    appPreferences.save_commu_color("entretaiment");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());

                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }


                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000002")) {
                    appPreferences.save_commu_color("bussiretail");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000003")) {
                    appPreferences.save_commu_color("service");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000004")) {
                    appPreferences.save_commu_color("food_be");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000005")) {
                    appPreferences.save_commu_color("envirome");

                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000006")) {
                    appPreferences.save_commu_color("industrial");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000007")) {
                    appPreferences.save_commu_color("creative");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Intatvs") && Community_list.get(position).getCommSrNo().equals("CM00000008")) {
                    appPreferences.save_commu_color("technology");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                    finish();
                }


                // Gallery

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000001")) {
                    appPreferences.save_commu_color("entretaiment");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }


                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000002")) {
                    appPreferences.save_commu_color("bussiretail");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000003")) {
                    appPreferences.save_commu_color("service");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000004")) {
                    appPreferences.save_commu_color("food_be");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000005")) {
                    appPreferences.save_commu_color("envirome");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000006")) {
                    appPreferences.save_commu_color("industrial");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000007")) {
                    appPreferences.save_commu_color("creative");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }

                if (Community_list.get(position).getxCode().equals("Gllry") && Community_list.get(position).getCommSrNo().equals("CM00000008")) {
                    appPreferences.save_commu_color("technology");
                    appPreferences.save_Community_srno(Community_list.get(position).getCommSrNo());
                    appPreferences.save_Commu_Xcode(Community_list.get(position).getxCode());
                    startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                    finish();
                }


            }


        }

    };
    OnItemClickListener.OnClickCallback onClickCall_common = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            if (Common_list != null && Common_list.size() > 0) {


                if (Common_list.get(position).xCode.equals("BsNews")) {
                    appPreferences.save_Bs_newsBack("Home");
                    startActivity(new Intent(HomeActivity.this, BSNews_Activity.class));
                    finish();
                }


                if (Common_list.get(position).xCode.equals("BookEvent")) {
                    boothback = true;
                    startActivity(new Intent(HomeActivity.this, Booth_Activity.class));
                    finish();
                }


                if (Common_list.get(position).xCode.equals("Shpng")) {

                    new Verify_IsUser_has_Address().execute();
                    /*startActivity(new Intent(HomeActivity.this, Shopping_Activity.class));
                    finish();*/
                }


            }


        }

    };
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            if (Membership_list != null && Membership_list.size() > 0) {


                if (Membership_list.get(position).getxCode().equals("VP")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, All_Vision.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("RqIniApvl")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, VP_ReqintialAproval.class));
                        finish();
                    }
                }
                if (Membership_list.get(position).getxCode().equals("RqSpprt")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, VP_RequestingyourSupport.class));
                        finish();
                    }
                }
                if (Membership_list.get(position).getxCode().equals("Accmdtn") && Membership_list.get(position).getMemSrNo().equals("MS00000001")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("vp colr");
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, AccomedationActivity.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("Vldntn") && Membership_list.get(position).getMemSrNo().equals("MS00000002")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("bussiness colr");
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Volunteering_Donation.class));
                        finish();

                    }
                }


                if (Membership_list.get(position).getxCode().equals("Accmdtn") && Membership_list.get(position).getMemSrNo().equals("MS00000002")) {
                    appPreferences.save_Color("bussiness colr");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, AccomedationActivity.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("Accmdtn") && Membership_list.get(position).getMemSrNo().equals("MS00000003")) {
                    appPreferences.save_Color("vp_Recident_VP");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, AccomedationActivity.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("Skjb") && Membership_list.get(position).getMemSrNo().equals("MS00000003")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, Seek_a_job.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("Accmdtn") && Membership_list.get(position).getMemSrNo().equals("MS00000004")) {
                    appPreferences.save_Color("passionate colr");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, AccomedationActivity.class));
                        finish();
                    }

                }
                if (Membership_list.get(position).getxCode().equals("Accmdtn") && Membership_list.get(position).getMemSrNo().equals("MS00000005")) {
                    appPreferences.save_Color("supp_registerd_services");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, AccomedationActivity.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("AdtnlSrvc") && Membership_list.get(position).getMemSrNo().equals("MS00000001")) {
                    appPreferences.save_Color("vp colr");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("AdtnlSrvc") && Membership_list.get(position).getMemSrNo().equals("MS00000002")) {
                    appPreferences.save_Color("bussiness colr");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("AdtnlSrvc") && Membership_list.get(position).getMemSrNo().equals("MS00000003")) {
                    appPreferences.save_Color("vp_Recident_VP");
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("AdtnlSrvc") && Membership_list.get(position).getMemSrNo().equals("MS00000004")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("passionate colr");
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                        finish();
                    }
                }


                if (Membership_list.get(position).getxCode().equals("AdtnlSrvc") && Membership_list.get(position).getMemSrNo().equals("MS00000005")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("supp_registerd_services");

                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                        finish();
                    }

                   /* startActivity(new Intent(HomeActivity.this, BO_Registered_services.class));
                    finish();*/
                }


                if (Membership_list.get(position).getxCode().equals("Vlntrng") && Membership_list.get(position).getMemSrNo().equals("MS00000003")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        /*   appPreferences.save_Color("recident");*/
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());


                        appPreferences.save_Color("resi_Volunteering_Donation");
                        startActivity(new Intent(HomeActivity.this, Resi_Volunteering.class));
                        finish();
                    }

                }


                if (Membership_list.get(position).getxCode().equals("Vlntrng") && Membership_list.get(position).getMemSrNo().equals("MS00000004")) {
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("passionate");
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());


                        startActivity(new Intent(HomeActivity.this, Passionate_Volunteering.class));
                        finish();
                    }
                }


                if (Membership_list.get(position).getxCode().equals("Vldntn") && Membership_list.get(position).getMemSrNo().equals("MS00000005")) {
                    appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("supplier_Volunteering_Donation");
                        appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());

                        startActivity(new Intent(HomeActivity.this, BO_Volunteering_Donation.class));
                        finish();

                    }
                }


                if (Membership_list.get(position).getxCode().equals("Vldntn") && Membership_list.get(position).getMemSrNo().equals("MS00000003")) {
                    appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("resi_Volunteering_Donation");
                        startActivity(new Intent(HomeActivity.this, Resi_Volunteering.class));
                        finish();
                    }
                }

                if (Membership_list.get(position).getxCode().equals("Bsales") && Membership_list.get(position).getMemSrNo().equals("MS00000005")) {
                    appPreferences.save_membership_srno(Membership_list.get(position).getMemSrNo());
                    if (Membership_list.get(position).getApprove().equals(true)) {

                        appPreferences.save_Color("supp_BootYourSales");
                        startActivity(new Intent(HomeActivity.this, BO_BootYourSales.class));
                        finish();

                    }
                }

            }


        }

    };

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    LinearLayout lin_creative_commu_initiaves, lin_techno_coming_event, lin_techno_create, lin_techno_community, lin_creative_gallary_club, lin_environmental_community, lin_creative_coming_event, lin_industrial_gallary_club, lin_industrial_create, lin_industrial_commu_initiaves, lin_industrial_opportuniti_buss, lin_enviro_beve, lin_industrial_coming_event, lin_industrial_community, lin_enviro_create, lin_enviro_commu_initiaves, lin_enviro_gallary_club;
    Observer<ArrayList<List>> userListUpdateObserver = new Observer<ArrayList<List>>() {
        @Override
        public void onChanged(ArrayList<List> userArrayList) {
            home_adapter = new Home_Adapter(HomeActivity.this, userArrayList, onClickCall);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            rw_vision.setLayoutManager(gridLayoutManager);
            rw_vision.setAdapter(home_adapter);
        }
    };

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

       /* if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }*/
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_home);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_home_ar);
        }

        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        ipaddress = UUID.randomUUID().toString();
        Uniqueids = uniquepwd();

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


        findid();
        /*  viewModel = ViewModelProviders.of(context).get(MainViewModel.class);*/
        viewModel = ViewModelProviders.of(HomeActivity.this).get(HomeViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, userListUpdateObserver);
        // viewModel.getUserMutableLiveData().observe(context, userListUpdateObserver);

        if (appPreferences.getCulturemode().equals("1")) {
            txtlable1.setText("Membership And Communities");
        } else {
            txtlable1.setText("العضويات & مجتمعات");

        }
        // txtlable1.setText("Membership And Communities");

        if (HomeActivity.this.isNetworkConnected()) {

            showProgress();

            Get_Membership_Dashboard_API(appPreferences.getuserid(), sessionManager.GetUniqueId(), appPreferences.getCulturemode(), appPreferences.get_company_id());


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


        if (appPreferences.getCulturemode().equals("1")) {
            txtlable.setText("Welcome" + " " + appPreferences.getfirstname());
        } else {
            txtlable.setText("مرحبًا" + " " + appPreferences.getfirstname());

        }
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

    private void Get_Membership_Dashboard_API(String getuserid, String getUniqueId, String culturemode, String company_id) {

        apiInterface.Get_Membership_Dashboard_API(getuserid, getUniqueId, culturemode, company_id).enqueue(new Callback<Get_Membership_DashboardPojo>() {


            @Override
            public void onResponse(Call<Get_Membership_DashboardPojo> call, Response<Get_Membership_DashboardPojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();

                    Membership_list.addAll(response.body().list);
                    Community_list.addAll(response.body().list2);
                    Common_list.addAll(response.body().commonList);
                    Add_list.addAll(response.body().adsDetails);
                    // init();

                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_vision.setLayoutManager(gridLayoutManager);
                    home_adapter = new Home_Adapter(HomeActivity.this, Membership_list, onClickCall);
                    rw_vision.setAdapter(home_adapter);

                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_common.setLayoutManager(gridLayoutManager);
                    adapter_common = new Home_Adapter_Common(HomeActivity.this, Common_list, onClickCall_common);
                    rw_common.setAdapter(adapter_common);

                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_commu.setLayoutManager(gridLayoutManager);
                    home_adapter_commu = new Home_Adapter_Commu(HomeActivity.this, Community_list, onClickCall_community);
                    rw_commu.setAdapter(home_adapter_commu);

                    init();

                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    Toast.makeText(appController, response.message(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<Get_Membership_DashboardPojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }


    private void init() {
        for (int i = 0; i < Add_list.size(); i++)
            Add_list_deatal.add(Add_list.get(i));


        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter_home(HomeActivity.this, Add_list_deatal));
        PageIndicatorView indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setSelectedColor(Color.BLACK);
        indicator.setUnselectedColor(Color.GRAY);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Add_list.size()) {
                    currentPage = 0;

                }
                mPager.setCurrentItem(currentPage++, true);

            }
        };
        swipeTimer = new Timer();
        // Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(Update);
                                }

                            },
                4000, 4000);


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (swipeTimer != null) {
            swipeTimer.cancel();
            swipeTimer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (swipeTimer != null) {
            swipeTimer.cancel();
            swipeTimer = null;
        }
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(HomeActivity.this);
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


    @Override
    public void onBackPressed() {
        exitByBackKey();
    }

    private void exitByBackKey() {
        if (appPreferences.getCulturemode().equals("1")) {
            AlertDialog alertbox = new AlertDialog.Builder(this)

                    .setMessage("Do you want to exit Barayih Salem?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //DoNothing
                        }
                    })
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_launcher_background)
                    .setCancelable(false)
                    .show();
        } else {
            AlertDialog alertbox = new AlertDialog.Builder(this)

                    .setMessage("Do you want to exit Barayih Salem?")

                    .setPositiveButton("نعم فعلا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                        }
                    })
                    .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //DoNothing
                        }
                    })
                    .setTitle("ىخرج")
                    .setIcon(R.drawable.ic_launcher_background)
                    .setCancelable(false)
                    .show();
        }
    }

    private void findid() {
        rw_vision = findViewById(R.id.rw_vision);
        rw_commu = findViewById(R.id.rw_commu);
        rw_common = findViewById(R.id.rw_common);


        logoside = findViewById(R.id.logoside);
        img_change_lang = findViewById(R.id.img_change_lang);

        logoside.setOnClickListener(this);
        img_change_lang.setOnClickListener(this);


        txtlable = findViewById(R.id.txtlable);

        txtlable1 = findViewById(R.id.txtlable1);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                appPreferences.save_DrawerBack("Home");

                startActivity(new Intent(HomeActivity.this, DraweActivity.class));
                finish();
                break;


            case R.id.img_change_lang:
                changeLanguage();

                break;


          /*  case R.id.vp_volutureenig_donation:
                startActivity(new Intent(HomeActivity.this, BO_Volunteering_Donation.class));
                finish();
                break;

            case R.id.Reci_seek_a_job:
                startActivity(new Intent(HomeActivity.this, Seek_a_job.class));
                finish();
                break;

            case R.id.lin_vluntering:
                appPreferences.save_Color("resi_Volunteering_Donation");
                startActivity(new Intent(HomeActivity.this, Resi_Volunteering.class));
                finish();
                break;




            case R.id.lin_vluntering_passsinate:
                startActivity(new Intent(HomeActivity.this, passionate_Volunteering.class));
                finish();
                break;


            case R.id.lin_suppier_valuation_donation:
                appPreferences.save_Color("supplier_Volunteering_Donation");
                startActivity(new Intent(HomeActivity.this, Resi_Volunteering.class));
                finish();
                break;
            case R.id.lin_entertai_commu_comming_event:
                appPreferences.save_commu_color("entretaiment");

                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;
            case R.id.lin_entertai_commu_comming_event_bu:
                appPreferences.save_commu_color("bussiretail");

                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;

            case R.id.lin_entertai_commu_service:
                appPreferences.save_commu_color("service");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;


            case R.id.lin_food_beve:
                appPreferences.save_commu_color("food_be");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;

            case R.id.lin_enviro_beve:
                appPreferences.save_commu_color("envirome");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;
            case R.id.lin_industrial_coming_event:
                appPreferences.save_commu_color("industrial");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;

            case R.id.lin_creative_coming_event:
                appPreferences.save_commu_color("creative");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;

            case R.id.lin_techno_coming_event:
                appPreferences.save_commu_color("technology");
                startActivity(new Intent(HomeActivity.this, Comming_Events.class));
                finish();
                break;


            case R.id.lin_creative_create:
                appPreferences.save_commu_color("creative");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;

            case R.id.lin_techno_create:
                appPreferences.save_commu_color("technology");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;


            case R.id.lin_industrial_create:
                appPreferences.save_commu_color("industrial");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;


            case R.id.lin_enviro_create:
                appPreferences.save_commu_color("envirome");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;
            case R.id.lin_food_beve_create:
                appPreferences.save_commu_color("food_be");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;

            case R.id.lin_entertai_commu_create_event:
                appPreferences.save_commu_color("entretaiment");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();

                break;

            case R.id.lin_entertai_commu_create_event_buss:
                appPreferences.save_commu_color("bussiretail");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;

            case R.id.lin_entertai_commu_create_event_service:
                appPreferences.save_commu_color("service");
                startActivity(new Intent(HomeActivity.this, Create_Events.class));
                finish();
                break;


            case R.id.lin_entertai_commu_opportuniti:
                appPreferences.save_commu_color("entretaiment");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;

            case R.id.lin_entertai_commu_opportuniti_buss:
                appPreferences.save_commu_color("bussiretail");

                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;

            case R.id.lin_entertai_service_opportuniti_buss:
                appPreferences.save_commu_color("service");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;
            case R.id.lin_food_beve_opportuniti_buss:
                appPreferences.save_commu_color("food_be");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;
            case R.id.lin_enviro_opportuniti_buss:
                appPreferences.save_commu_color("envirome");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;

            case R.id.lin_industrial_opportuniti_buss:
                appPreferences.save_commu_color("industrial");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;
            case R.id.lin_creative_opportuniti_buss:
                appPreferences.save_commu_color("creative");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;
            case R.id.lin_techno_opportuniti_buss:
                appPreferences.save_commu_color("technology");
                startActivity(new Intent(HomeActivity.this, Opportunity_ForYou.class));
                finish();
                break;

            case R.id.lin_techno_commu_initiaves:
                appPreferences.save_commu_color("technology");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.lin_creative_commu_initiaves:
                appPreferences.save_commu_color("creative");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.lin_industrial_commu_initiaves:
                appPreferences.save_commu_color("industrial");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;


            case R.id.lin_enviro_commu_initiaves:
                appPreferences.save_commu_color("envirome");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.linfood_beve_commu_initiaves:
                appPreferences.save_commu_color("food_be");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;
            case R.id.lin_entertai_commu_initiaves:
                appPreferences.save_commu_color("entretaiment");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.lin_entertai_commu_initiaves_buss:
                appPreferences.save_commu_color("bussiretail");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.lin_service_commu_initiaves_buss:
                appPreferences.save_commu_color("service");
                startActivity(new Intent(HomeActivity.this, Initiaves_FromYou.class));
                finish();
                break;

            case R.id.lin_entertai_commu_gallary:
                appPreferences.save_commu_color("entretaiment");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;
            case R.id.lin_entertai_commu_gallary_club:
                appPreferences.save_commu_color("bussiretail");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_service_commu_gallary_club:
                appPreferences.save_commu_color("service");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_food_beve_gallary_club:
                appPreferences.save_commu_color("food_be");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_enviro_gallary_club:
                appPreferences.save_commu_color("envirome");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_industrial_gallary_club:
                appPreferences.save_commu_color("industrial");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_creative_gallary_club:
                appPreferences.save_commu_color("creative");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;

            case R.id.lin_techno_gallary_club:
                appPreferences.save_commu_color("technology");
                startActivity(new Intent(HomeActivity.this, Gallery_Commu.class));
                finish();
                break;*/

          /*  case R.id.lin_supp_cash_settelment:
                //  showBottomSheetDialog("supplier");
                break;*/
        }
    }

    private void changeLanguage() {
        if (appController.isLangArebic()) {
            appController.changeLanguage(false);
            //   adss = false;
            startActivity(getIntent());
            finish();
        } else {
            appController.changeLanguage(true);
            // adss = false;

            startActivity(getIntent());
            finish();
        }

    }


    private class Verify_IsUser_has_Address extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    progressDialog = new ProgressDialog(Book_Location_Events.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(getResources().getString(R.string.GetMainurl));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Verify_IsUser_has_Address','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                bf.write(query);
                Log.d("asda", query);
                bf.flush();
                bf.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exceptin";
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String response) {
            Gson gson = new GsonBuilder().create();
            Log.d("Verify_IsUser_has_Address", response);
            //  progressDialog.dismiss();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String VDone = collegeData.getString("VDone");

                        if (VDone.equals("2")) {
                            startActivity(new Intent(HomeActivity.this, Shopping_Category_page.class));
                            finish();
                        } else {
                            startActivity(new Intent(HomeActivity.this, Shopping_Activity.class));
                            finish();

                         /*   startActivity(new Intent(HomeActivity.this, Shopping_Category_page.class));
                            finish();*/
                        }

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

}