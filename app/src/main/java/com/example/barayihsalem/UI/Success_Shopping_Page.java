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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Checkout_Adapter;
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Model.Save_User_Rating_Pojo;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Success_Shopping_Page extends AppCompatActivity implements View.OnClickListener {
    TextView check, rate, order_num, order_value, pay_status, paystatus_value, transaction_id, transaction_id_value, payment_id, payment_id_value;
    TextView check_f, txtlable_f, pay_status_f, paystatus_value_f, transaction_id_f, transaction_id_value_f, payment_id_f, txtlable_rati, payment_id_value_f, rating_heading;
    AppController appController;
    AppPreferences appPreferences;
    String rating = "";
    SessionManager sessionManager;
    APIInterface apiInterface;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Cart_Adapter cart_adapter;
    LinearLayout lin_rating, lin_success;
    Button btn_home, btn_rating, btn_failyre;
    Checkout_Adapter checkout_adapter;
    PayMode_Adapter payMode_adapter;
    RecyclerView rw_checkout, rw_pay_mode;
    RelativeLayout abcd;
    LinearLayout lin_failure;
    TextView txtlable, check1;
    Spinner id_city, id_country;
    EditText edit_fullname, editText_mob_num, editText_email, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RatingBar ratingbar;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ProgressDialog progressDialog;

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

            setContentView(R.layout.activity_success__shopping__page);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_success__shopping__page_ar);

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


        if (Success_Shopping_Page.this.isNetworkConnected()) {

            new Get_Checkout_Result_Details().execute();

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


        rating = String.valueOf(ratingbar.getRating());
        rate.setText(rating);

        ratingbar.setRating(Float.parseFloat("0.0"));
        rate.setText("0.0");
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating1,
                                        boolean fromUser) {

                String asew = String.valueOf(rating1);
                rating = String.valueOf(rating1);
                rate.setText(asew);
                // place intent for new activity

            }
        });

      /*  String rating = String.valueOf(ratingbar.getRating());
        rate.setText(rating);*/

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid() {
        rate = findViewById(R.id.rate);
        ratingbar = findViewById(R.id.ratingBar);
        //  ratingbar.setOnClickListener(this);
        check1 = findViewById(R.id.check1);
        btn_home = findViewById(R.id.btn_home);
        order_value = findViewById(R.id.order_value);
        payment_id_value = findViewById(R.id.payment_id_value);
        payment_id = findViewById(R.id.payment_id);
        pay_status = findViewById(R.id.pay_status);
        paystatus_value = findViewById(R.id.paystatus_value);
        transaction_id = findViewById(R.id.transaction_id);
        transaction_id_value = findViewById(R.id.transaction_id_value);
        check = findViewById(R.id.check);
        check_f = findViewById(R.id.check_f);
        txtlable_f = findViewById(R.id.txtlable_f);
        order_num = findViewById(R.id.order_num);
        txtlable = findViewById(R.id.txtlable);
        lin_rating = findViewById(R.id.lin_rating);
        lin_success = findViewById(R.id.lin_success);
        lin_success.setVisibility(View.VISIBLE);
        pay_status_f = findViewById(R.id.pay_status_f);
        paystatus_value_f = findViewById(R.id.paystatus_value_f);
        transaction_id_f = findViewById(R.id.transaction_id_f);
        transaction_id_value_f = findViewById(R.id.transaction_id_value_f);
        payment_id_f = findViewById(R.id.payment_id_f);
        payment_id_value_f = findViewById(R.id.payment_id_value_f);
        txtlable_rati = findViewById(R.id.txtlable_rati);
        rating_heading = findViewById(R.id.rating_heading);
        transaction_id_f.setTypeface(typeHeader);
        payment_id_f.setTypeface(typeHeader);
        check.setTypeface(typeHeader);
        order_num.setTypeface(typeHeader);
        transaction_id.setTypeface(typeHeader);
        pay_status.setTypeface(typeHeader);
        payment_id.setTypeface(typeHeader);
        payment_id_value.setTypeface(typeHeader);
        pay_status_f.setTypeface(typeHeader);
        check_f.setTypeface(typebold);
        txtlable.setTypeface(typeRegular);
        rate.setTypeface(typeRegular);
        payment_id_value_f.setTypeface(typeRegular);
        paystatus_value.setTypeface(typeRegular);
        paystatus_value_f.setTypeface(typeRegular);
        rating_heading.setTypeface(typebold);
        transaction_id_value_f.setTypeface(typeRegular);
        txtlable_rati.setTypeface(typeRegular);
        txtlable_f.setTypeface(typeRegular);
        check1.setTypeface(typebold);
        transaction_id_value.setTypeface(typeRegular);
        btn_home.setTypeface(typeRegular);
        order_value.setTypeface(typeRegular);
        logoside = findViewById(R.id.logoside);
        lin_failure = findViewById(R.id.lin_failure);
        btn_rating = findViewById(R.id.btn_rating);
        btn_failyre = findViewById(R.id.btn_failyre);
        btn_rating.setTypeface(typeRegular);
        lin_failure.setVisibility(View.GONE);
        lin_rating.setVisibility(View.GONE);
        btn_failyre.setVisibility(View.GONE);
        logoside.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_rating.setOnClickListener(this);
        btn_failyre.setOnClickListener(this);
        btn_failyre.setTypeface(typeRegular);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Success_Shopping_Page.this, Shopping_Category_page.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;


            case R.id.btn_home:

                btn_home.setVisibility(View.GONE);
                lin_success.setVisibility(View.GONE);
                btn_rating.setVisibility(View.VISIBLE);
                lin_rating.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_rating:


                if (Success_Shopping_Page.this.isNetworkConnected()) {

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



           //     onBackPressed();
                /*btn_rating.setVisibility(View.GONE);
                btn_home.setVisibility(View.GONE);
                lin_success.setVisibility(View.GONE);
                lin_rating.setVisibility(View.GONE);
                lin_failure.setVisibility(View.VISIBLE);
                btn_failyre.setVisibility(View.VISIBLE);*/

                break;


            case R.id.btn_failyre:

                onBackPressed();
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
        progressDialog = new ProgressDialog(Success_Shopping_Page.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_Checkout_Result_Details extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Checkout_Result_Details','" + appPreferences.get_checkout() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                Log.d("ven", query);
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

                      //  String Currency = collegeData.getString("Currency");
                       // String Mem_Price_ = String.format("%.3f", Price);
                        String Total_ = String.format("%.3f", GrandTotal);

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