package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.BoothSize_Adapter;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Model.NewPojo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class UpcominiEvent_Details extends AppCompatActivity implements View.OnClickListener {
    public static boolean my_acount = false;
    TextView aboutbs, event_name, event_name_value, event_type, price_value, event_type_value, event_start_date, event_start_value, event_end_date, planner_value, email_value, price_iban, zone_value, evenbooth_value, event_end_value;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    ImageView logoside;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button proceed;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_booth_size, rw_cart;
    RelativeLayout abcd;
    EditText editText_area;
    LinearLayoutManager layoutManager;
    BoothSize_Adapter boothSize_adapter;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_upcomini_event__details);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_upcomini_event__details_ar);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        boothSize_adapter = new BoothSize_Adapter(this, newPojoArrayList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rw_booth_size.setLayoutManager(layoutManager);
        rw_booth_size.setAdapter(boothSize_adapter);*/
        if (UpcominiEvent_Details.this.isNetworkConnected()) {

            new Get_Event_Details_By_SrNo().execute();


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
        rw_booth_size = findViewById(R.id.rw_booth_size);
        aboutbs = findViewById(R.id.aboutbs);
        event_start_date = findViewById(R.id.event_start_date);
        email_value = findViewById(R.id.email_value);
        zone_value = findViewById(R.id.zone_value);
        event_start_value = findViewById(R.id.event_start_value);
        evenbooth_value = findViewById(R.id.evenbooth_value);
        event_end_date = findViewById(R.id.event_end_date);
        planner_value = findViewById(R.id.planner_value);
        event_end_value = findViewById(R.id.event_end_value);
        event_name = findViewById(R.id.event_name);
        event_name_value = findViewById(R.id.event_name_value);
        event_type = findViewById(R.id.event_type);
        price_value = findViewById(R.id.price_value);
        event_type_value = findViewById(R.id.event_type_value);
        price_iban = findViewById(R.id.price_iban);
        proceed = findViewById(R.id.proceed);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        proceed.setOnClickListener(this);
        aboutbs.setTypeface(typebold);
        event_name.setTypeface(typeHeader);
        event_name_value.setTypeface(typeHeader);
        event_type.setTypeface(typeHeader);
        event_start_date.setTypeface(typeHeader);
        event_type_value.setTypeface(typeHeader);
        event_start_value.setTypeface(typeHeader);
        event_end_date.setTypeface(typeHeader);
        event_end_value.setTypeface(typeHeader);
        proceed.setTypeface(typeRegular);
        price_value.setTypeface(typeRegular);
        planner_value.setTypeface(typeRegular);
        price_iban.setTypeface(typeRegular);
        email_value.setTypeface(typeRegular);
        evenbooth_value.setTypeface(typeRegular);
        zone_value.setTypeface(typeRegular);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Acount_Info.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.proceed:
                my_acount = true;
                startActivity(new Intent(this, Create_Booth_Slot.class));
                finish();
                break;

        }
    }

    private class Get_Event_Details_By_SrNo extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* progressDialog = new ProgressDialog(BecomeResident_member.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);*/

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
                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_Event_Details_By_SrNo','" + appPreferences.get_Event_SRNo() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("detail", response);
            //   progressDialog.dismiss();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);
                        ArrayList<NewPojo> newPojoArrayList = new ArrayList<>();
                        NewPojo newPojo = new NewPojo();

                        String Event_SrNo = collegeData.getString("Event_SrNo");
                        String Event_Name = collegeData.getString("Event_Name");
                        String Event_Type = collegeData.getString("Event_Type");
                        String SDate = collegeData.getString("SDate");
                        String SDate1 = collegeData.getString("SDate1");
                        String BoothTypes = collegeData.getString("BoothTypes");
                        String Zones = collegeData.getString("Zones");
                        String Boothtype_A = collegeData.getString("Boothtype_A");
                        String BoothType_A_Name = collegeData.getString("BoothType_A_Name");
                        String Boothtype_A_Size = collegeData.getString("Boothtype_A_Size");
                        String Boothtype_B = collegeData.getString("Boothtype_B");
                        String BoothType_A_Name1 = collegeData.getString("Boothtype_B");
                        String Boothtype_B_Size = collegeData.getString("Boothtype_B_Size");
                        String Boothtype_C = collegeData.getString("Boothtype_C");
                        String BoothType_A_Name2 = collegeData.getString("BoothType_A_Name2");
                        String Boothtype_B_Size1 = collegeData.getString("Boothtype_B_Size1");
                        String EventPlanner_Mobile = collegeData.getString("EventPlanner_Mobile");
                        String Email = collegeData.getString("Email");
                        String Currency = collegeData.getString("Currency");
                        String IBAN = collegeData.getString("IBAN");
                        String IsCreateboothSlot = collegeData.getString("IsCreateboothSlot");
                        Double Price = collegeData.getDouble("Price");

                        if (IsCreateboothSlot.equals("false")) {
                            proceed.setVisibility(View.VISIBLE);
                        } else {
                            proceed.setVisibility(View.GONE);

                        }

                        if (!Boothtype_A.equals("")) {
                            newPojo.setBoothtype(Boothtype_A);

                        }

                        if (!Boothtype_B.equals("")) {
                            newPojo.setBoothtype(Boothtype_B);

                        }

                        if (!Boothtype_C.equals("")) {
                            newPojo.setBoothtype(Boothtype_C);

                        }


                        if (!BoothType_A_Name.equals("")) {
                            newPojo.setBoothTypeName(BoothType_A_Name);

                        }
                        if (!BoothType_A_Name1.equals("")) {
                            newPojo.setBoothTypeName(BoothType_A_Name1);

                        }
                        if (!BoothType_A_Name2.equals("")) {
                            newPojo.setBoothTypeName(BoothType_A_Name2);

                        }

                        if (!Boothtype_A_Size.equals("")) {
                            newPojo.setBoothtype_A_Size(Boothtype_A_Size);

                        }


                        if (!Boothtype_B_Size.equals("")) {
                            newPojo.setBoothtype_A_Size(Boothtype_B_Size);

                        }


                        if (!Boothtype_B_Size1.equals("")) {
                            newPojo.setBoothtype_A_Size(Boothtype_B_Size1);

                        }


                        event_name_value.setText(Event_Name);
                        event_type_value.setText(Event_Type);
                        event_start_value.setText(SDate);
                        event_end_value.setText(SDate1);
                        evenbooth_value.setText(BoothTypes);
                        zone_value.setText(Zones);
                        planner_value.setText(EventPlanner_Mobile);
                        email_value.setText(Email);
                        price_iban.setText(IBAN);

                        price_value.setText(String.format("%.3f", Price) + " " + Currency);

                 /*       newPojoArrayList.add(newPojo);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(UpcominiPastEvent_Details.this, 1);
                        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                        boothSize_adapter = new BoothSize_Adapter(UpcominiPastEvent_Details.this,newPojoArrayList);
                        layoutManager = new LinearLayoutManager(UpcominiPastEvent_Details.this, LinearLayoutManager.VERTICAL, false);
                        rw_booth_size.setLayoutManager(layoutManager);
                        rw_booth_size.setAdapter(boothSize_adapter);*/


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }

    }

}