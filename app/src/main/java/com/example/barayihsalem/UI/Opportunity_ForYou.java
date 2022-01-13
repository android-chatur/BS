package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.BSNews_Adapter;
import com.example.barayihsalem.UI.Adapter.Oppertunities_for_You_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Opportunity_ForYou extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, txt_full_name, txt_gender, txt_nationality, txt_age, txt_voluntee, txt_phone;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    RadioButton male, female;
    ArrayList<Row> News_list = new ArrayList<Row>();
    public  static boolean oppo=false;
    BottomSheetDialog dialog;
    RecyclerView rw_bsnews;
    RelativeLayout abcd;
    TextView message, no, yes;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    //  RecyclerView rw_regi_servi, rw_additional;
    Oppertunities_for_You_Adapter oppertunities_for_you_adapter;
    EditText editText_volun_name, editText_age, editText_mobile;

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            if (News_list != null && News_list.size() > 0) {
                oppo=true;
                appPreferences.SaveNewsSrno(News_list.get(position).getSrNo());
                startActivity(new Intent(Opportunity_ForYou.this, BSNews_Activity_Details.class));
                finish();
            }


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
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_opportunity__for_you);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_opportunity__for_you_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

        if (appPreferences.get_commu_color().equals("entretaiment")) {

            aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
            twitter_l.setImageResource((R.drawable.passionate));
            //  proceed.setBackground(getResources().getDrawable(R.drawable.passionate_drawable));

        }
        if (appPreferences.get_commu_color().equals("bussiretail")) {
            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.bussiness_owner));


        }
        if (appPreferences.get_commu_color().equals("service")) {
            aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
          //  twitter_l.setImageResource((R.drawable.vispart));

            twitter_l.setImageResource((R.drawable.supplir_image));
            twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));


        }
        if (appPreferences.get_commu_color().equals("food_be")) {
            aboutbs.setTextColor(getResources().getColor(R.color.fb_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.fb_color));

        }
        if (appPreferences.get_commu_color().equals("envirome")) {
            aboutbs.setTextColor(getResources().getColor(R.color.enviro_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));

            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.enviro_color));

        }
        if (appPreferences.get_commu_color().equals("technology")) {
            aboutbs.setTextColor(getResources().getColor(R.color.tq_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.tq_color));

        }     if (appPreferences.get_commu_color().equals("industrial")) {
            aboutbs.setTextColor(getResources().getColor(R.color.indus_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));

        }   if (appPreferences.get_commu_color().equals("industrial")) {
            aboutbs.setTextColor(getResources().getColor(R.color.indus_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));

        }

        if (appPreferences.get_commu_color().equals("creative")) {
            aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
            //    proceed.setBackground(getResources().getDrawable(R.drawable.bussinedd_owner));
            twitter_l.setImageResource((R.drawable.vispart));
          //  twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));

        }


        if (Opportunity_ForYou.this.isNetworkConnected()) {

            new Get_BS_News_List_Community_wise().execute();

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

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private void findid() {
        abcd = findViewById(R.id.abcd);
        logoside = findViewById(R.id.logoside);
        aboutbs = findViewById(R.id.aboutbs);
        twitter_l = findViewById(R.id.twitter_l);
        aboutbs.setTypeface(typebold);
        logoside.setOnClickListener(this);
        rw_bsnews = findViewById(R.id.rw_bsnews);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Opportunity_ForYou.this, HomeActivity.class));
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


    private class Get_BS_News_List_Community_wise extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
                String user_id;
                if (appPreferences.get_Bs_newsBack().equals("Home")) {
                    user_id = appPreferences.getuserid();
                } else {
                    user_id = "0";
                }

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BS_News_List_Community_wise','"+appPreferences.get_Community_srno()+"','','','','','','" + user_id + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                bf.write(query);
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
            Log.d("oppo_list", response);

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {

                News_list.addAll(bsNewsPojo.row);


                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_bsnews.setLayoutManager(gridLayoutManager);
                oppertunities_for_you_adapter = new Oppertunities_for_You_Adapter(Opportunity_ForYou.this,News_list, onClickCall);
                rw_bsnews.setAdapter(oppertunities_for_you_adapter);


            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }
        }
    }

}