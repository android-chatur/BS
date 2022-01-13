package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Adapter.Select_Membership_Adapter;
import com.example.barayihsalem.UI.Model.Confirm_Membership_Community_Flow_Pojo;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.R.layout.activity_pay__success__page;
import static com.example.barayihsalem.R.layout.activity_pay__success__page_ar;
import static com.example.barayihsalem.UI.Select_Payment_Type.paymentback;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Pay_Success_Page extends AppCompatActivity implements View.OnClickListener {
    TextView check, rate, order_num, order_value, check1, pay_status, paystatus_value, transaction_id, transaction_id_value, payment_id, payment_id_value;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    APIInterface apiInterface;

    ProgressDialog progressDialog;
    TextView message, no, yes;
    RadioButton radioButton_month, radioButton_year;
    ImageView logoside;
    RecyclerView rw_checkout, rw_pay_mode;
    PayMode_Adapter payMode_adapter;
    Button btn_home;
    RecyclerView rw_select_membership, rw_comminity;
    Select_Membership_Adapter select_membership_adapter;
    RelativeLayout abcd;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(activity_pay__success__page);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(activity_pay__success__page_ar);


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


        if (Pay_Success_Page.this.isNetworkConnected()) {

            new Get_Business_Owner_Result_Details().execute();

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
        logoside = findViewById(R.id.logoside);
        check1 = findViewById(R.id.check1);
        btn_home = findViewById(R.id.btn_home);
        order_num = findViewById(R.id.order_num);
        order_value = findViewById(R.id.order_value);
        payment_id_value = findViewById(R.id.payment_id_value);
        payment_id = findViewById(R.id.payment_id);
        pay_status = findViewById(R.id.pay_status);
        paystatus_value = findViewById(R.id.paystatus_value);
        transaction_id = findViewById(R.id.transaction_id);
        transaction_id_value = findViewById(R.id.transaction_id_value);


        check.setTypeface(typeHeader);
        order_num.setTypeface(typeHeader);
        transaction_id.setTypeface(typeHeader);
        pay_status.setTypeface(typeHeader);
        payment_id.setTypeface(typeHeader);
        payment_id_value.setTypeface(typeHeader);
        paystatus_value.setTypeface(typeRegular);
        transaction_id_value.setTypeface(typeRegular);
        btn_home.setTypeface(typeRegular);
        check1.setTypeface(typeRegular);
        order_value.setTypeface(typeRegular);
        logoside.setOnClickListener(this);
        btn_home.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        if (paymentback == true) {
            startActivity(new Intent(Pay_Success_Page.this, WellcomefamilyPage.class));
            finish();
        } else {
            startActivity(new Intent(Pay_Success_Page.this, Adjust_Membership.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.btn_home:
                if (paymentback == true) {

                   // showProgress();
                   // Confirm_Membership_Community_Flow();
                    startActivity(new Intent(Pay_Success_Page.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(Pay_Success_Page.this, Adjust_Membership.class));
                    finish();
                }


                break;
        }
    }

    private void Confirm_Membership_Community_Flow() {
        Confirm_Membership_Community_Flow_Pojo confirm_membership_community_flow_pojo = new Confirm_Membership_Community_Flow_Pojo();
        confirm_membership_community_flow_pojo.setUserId(appPreferences.getuserid());

        confirm_membership_community_flow_pojo.setUniqueId(sessionManager.GetUniqueId());
        confirm_membership_community_flow_pojo.setCorpcentreby(appPreferences.get_company_id());
        confirm_membership_community_flow_pojo.setIpAddress(sessionManager.GetIpAddress());


        Call<Confirm_Membership_Community_Flow_Pojo> call2 = apiInterface.Confirm_Membership_Community_Flow(confirm_membership_community_flow_pojo);
        call2.enqueue(new Callback<Confirm_Membership_Community_Flow_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Confirm_Membership_Community_Flow_Pojo> call, Response<Confirm_Membership_Community_Flow_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sessionManager.SetUniqueId("");

                        appPreferences.saveHomepage(true);
                        Intent intent = new Intent(appController, HomeActivity.class);
                        startActivity(intent);
                        finish();

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
            public void onFailure(Call<Confirm_Membership_Community_Flow_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private class Get_Business_Owner_Result_Details extends AsyncTask<String, String, String> {
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
                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Business_Owner_Result_Details','" + appPreferences.get_Attribute_mem() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Event_Details_ByResult", response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        String OrderNumber = collegeData.getString("OrderNumber");
                        String PaymentStatus = collegeData.getString("PaymentStatus");
                        String TransactionId = collegeData.getString("TransactionId");
                        String PayMmentId = collegeData.getString("PayMmentId");
                        // double Price = collegeData.getDouble("Price");
                        double GrandTotal = collegeData.getDouble("GrandTotal");

                        /*String Currency = collegeData.getString("Currency");
                        String Mem_Price_ = String.format("%.3f", Price);
                        String Total_ = String.format("%.3f", GrandTotal);*/

                        order_value.setText(OrderNumber);
                        paystatus_value.setText(PaymentStatus);
                        transaction_id_value.setText(TransactionId);
                        payment_id_value.setText(PayMmentId);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }


        }
    }

}
