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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Drawer_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
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

public class Offer_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_club, aboutbs_Event, settings, offer, check;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    ArrayList<Row> Menu_list = new ArrayList<Row>();
    Drawer_Adapter brand_page_adapter;
    LinearLayoutManager layoutManager;
    Button btnLogin;
    RecyclerView rv_drawer_list, rw_comminity;
    RelativeLayout abcd;
    LinearLayout lin_service_offers, lin_shoping_offer, lin_groceries_offer, lin_registerat_event, lin_older_events_gallary;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            appPreferences.SaveDrawerXcode_Sub(Menu_list.get(position).getxCode());
            startActivity(new Intent(Offer_Activity.this, Service_Offers.class));
            finish();
          /*  }


            if (Menu_list.get(position).getxCode().equals("Drw_5_02")) {
                appPreferences.SaveDrawerXcode_Sub(Menu_list.get(position).getxCode());
                startActivity(new Intent(Offer_Activity.this, Service_Offers.class));
                finish();
            }

            if (Menu_list.get(position).getxCode().equals("Drw_5_03")) {

            }
            if (Menu_list.get(position).getxCode().equals("Drw_5_04")) {
                appPreferences.SaveDrawerXcode_Sub(Menu_list.get(position).getxCode());
                startActivity(new Intent(Offer_Activity.this, Service_Offers.class));
                finish();
            }


        }*/
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


            setContentView(R.layout.activity_offer_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_offer_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        if (Offer_Activity.this.isNetworkConnected()) {
            new Get_ContentPage_SubMenu().execute();


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
        check = findViewById(R.id.check);
        rv_drawer_list = findViewById(R.id.rv_drawer_list);

        check.setTypeface(typebold);


        logoside = findViewById(R.id.logoside);


        logoside.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Offer_Activity.this, DraweActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
          /*  case R.id.lin_service_offers:
                startActivity(new Intent(Offer_Activity.this, Service_Offers.class));
                finish();
                break;
            case R.id.lin_shoping_offer:
                if (appPreferences.getCulturemode().equals("1")) {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "Shopping Offers"));
                    finish();
                } else {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "???????? ????????????"));
                    finish();
                }

                break;

            case R.id.lin_groceries_offer:
                if (appPreferences.getCulturemode().equals("1")) {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "Groceries Offers"));
                    finish();
                } else {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "?????? ??????????????"));
                    finish();
                }


                break;

            case R.id.lin_registerat_event:
                if (appPreferences.getCulturemode().equals("1")) {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "Restaurants Offers"));
                    finish();
                } else {
                    startActivity(new Intent(Offer_Activity.this, Shoping_Offer.class).putExtra("key", "???????? ??????????????"));
                    finish();
                }


                break;*/

            
/*

           
            case R.id.lin_groceries_offer:
              */
/*  startActivity(new Intent(Events_activity.this, Principle_Of_BS.class));
                finish();*//*

                break;
            case R.id.lin_registerat_event:
                startActivity(new Intent(Offer_Activity.this, Registered_Event.class));
                finish();
                break;

            case R.id.lin_older_events_gallary:
                startActivity(new Intent(Offer_Activity.this, Older_Events_Gallary.class));
                finish();
                break;
*/


        }
    }

    private class Get_ContentPage_SubMenu extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ContentPage_SubMenu','" + appPreferences.get_DrawerXcode() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


                String query = builder.build().getEncodedQuery();
                Log.d("awe", query);
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
            Log.d("Get_ContentPage_Menu", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Menu_list.addAll(partnerPojo.row);

                brand_page_adapter = new Drawer_Adapter(Offer_Activity.this, onClickCall, Menu_list);
                layoutManager = new LinearLayoutManager(Offer_Activity.this, LinearLayoutManager.VERTICAL, false);
                rv_drawer_list.setLayoutManager(layoutManager);
                rv_drawer_list.setAdapter(brand_page_adapter);
            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}