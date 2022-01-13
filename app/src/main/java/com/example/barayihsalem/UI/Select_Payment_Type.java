package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.barayihsalem.UI.Adapter.Instalment_Adapter;
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Adapter.Select_Membership_Adapter;
import com.example.barayihsalem.UI.Model.Confirm_BusinessPojo;
import com.example.barayihsalem.UI.Model.Confirm_Membership_Community_Flow_Pojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Select_Payment_Type extends AppCompatActivity implements View.OnClickListener {
    public static boolean paymentback = false;
    TextView txtlable, mem_price, price, mont_instalment, pay_txt, _instalment_price, gran_total, grand_price;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    TextView message, no, yes;
    APIInterface apiInterface;

    Paymentadapter_check paymentadapter;
    int preSelectedIndex = -1;
    Row preRecord;

    RadioButton radioButton_month, radioButton_year;
    ImageView logoside;
    ListView rwinsta, rw_pay_mode;
    PayMode_Adapter payMode_adapter;
    RadioGroup radioGroup_character;
    String xcode_pay = "";
    Button btnLogin;
    String XCode_gender, Xcode_month, Xcode_year, Gendercode;
    ArrayList<Row> Installment_list = new ArrayList<Row>();
    ArrayList<Row> Payment_list = new ArrayList<Row>();
    Instalment_Adapter instalment_adapter;
    Double Mem_Price, Installment_Price, Total;

    RecyclerView rw_select_membership, rw_comminity;
    Select_Membership_Adapter select_membership_adapter;
    RelativeLayout abcd;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;


    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            if (Installment_list != null && Installment_list.size() > 0) {

              /*  appPreferences.SaveNewsSrno(News_list.get(position).getSrNo());
                startActivity(new Intent(BSNews_Activity.this, BSNews_Activity_Details.class));
                finish();*/
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

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_select__payment__type);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_select__payment__type_ar);


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

        if (Select_Payment_Type.this.isNetworkConnected()) {

            new Get_Mem_PaymentType_List().execute();
            new Get_PaymentMode_List().execute();

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

        btnLogin = findViewById(R.id.btnLogin);
        radioGroup_character = findViewById(R.id.radioGroup_character);
        rw_pay_mode = findViewById(R.id.rw_pay_mode);
        mem_price = findViewById(R.id.mem_price);
        gran_total = findViewById(R.id.gran_total);
        grand_price = findViewById(R.id.grand_price);
        price = findViewById(R.id.price);
        pay_txt = findViewById(R.id.pay_txt);
        logoside = findViewById(R.id.logoside);
        mont_instalment = findViewById(R.id.mont_instalment);
        _instalment_price = findViewById(R.id._instalment_price);
        txtlable = findViewById(R.id.txtlable);
        radioButton_month = findViewById(R.id.radioButton_month);
        radioButton_year = findViewById(R.id.radioButton_year);
        txtlable.setTypeface(typebold);
        radioButton_month.setTypeface(typeRegular);
        radioButton_year.setTypeface(typeRegular);
        mem_price.setTypeface(typeRegular);
        price.setTypeface(typeRegular);
        _instalment_price.setTypeface(typeRegular);
        gran_total.setTypeface(typeRegular);
        mont_instalment.setTypeface(typeRegular);
        grand_price.setTypeface(typeRegular);
        pay_txt.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        logoside.setOnClickListener(this);
        radioButton_year.setOnClickListener(this);
        radioButton_month.setOnClickListener(this);
       /* radioGroup_character.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                boolean isChecked = radioButton_month.isChecked();

                if (appPreferences.getCulturemode().equals("1")){
                    if (isChecked){

                      //  selectesxcode;
                    }
                      //  mont_instalment.setText("6 months installment");
                    }*//*else
                    {
                       //mont_instalment.setText("1 year installment");

                    }
                }else {
                    if (isChecked){
                        mont_instalment.setText("6 قسط شهري");
                    }else
                    {
                        mont_instalment.setText("1 قسط سنة");

                    }
                }*//*

            }
        });*/

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(Select_Payment_Type.this, WellcomefamilyPage.class));
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btnLogin:

                if (!xcode_pay.isEmpty() || !xcode_pay.equals("")) {

                    showProgress();
                    Confirm_Business_Owner();


                } else {
                    Toast.makeText(appController, "Please Select Payment Mode", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.radioButton_month:
                radioButton_month.isChecked();

                Gendercode = Xcode_month;
                new Get_Mem_PaymentType_Details_BySrNo().execute();

                break;

            case R.id.radioButton_year:
                radioButton_year.isChecked();
                Gendercode = Xcode_year;
                new Get_Mem_PaymentType_Details_BySrNo().execute();

                break;

        }
    }

    private void Confirm_Business_Owner() {
        Confirm_BusinessPojo loginPojo = new Confirm_BusinessPojo();


        loginPojo.setUserId(appPreferences.getuserid());
        loginPojo.setAmount(String.valueOf(Total));
        loginPojo.setPayType(Gendercode);
        loginPojo.setMOP(xcode_pay);

        loginPojo.setCorpcentreby(appPreferences.get_company_id());
        loginPojo.setIpAddress(sessionManager.GetIpAddress());
        loginPojo.setUniqueId(sessionManager.GetUniqueId());


        Call<Confirm_BusinessPojo> call2 = apiInterface.Confirm_Business_Owner(loginPojo);
        call2.enqueue(new Callback<Confirm_BusinessPojo>() {
            @Override
            public void onResponse(Call<Confirm_BusinessPojo> call, Response<Confirm_BusinessPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {


                        // sessionManager.SetUniqueId("");
                        String track_id = response.body().getTrackId();
                        appPreferences.save_Attribute_mem(track_id);
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        paymentback = true;
                        showProgress();
                        Confirm_Membership_Community_Flow();



                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
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
            public void onFailure(Call<Confirm_BusinessPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


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

                        startActivity(new Intent(Select_Payment_Type.this, Pay_Success_Page.class));
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
        progressDialog = new ProgressDialog(Select_Payment_Type.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_Mem_PaymentType_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Mem_PaymentType_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Bs_list", response);

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {

                Installment_list.addAll(bsNewsPojo.row);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String emailresponse = jsonObject.getString("success");
                    if (emailresponse.equals("0")) {

                    } else {

                        JSONArray result = jsonObject.getJSONArray("row");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject collegeData = result.getJSONObject(i);


                            XCode_gender = collegeData.getString("XCode");

                            if (XCode_gender.equals("01Half")) {
                                String XName_6month = collegeData.getString("XName");
                                Xcode_month = collegeData.getString("XCode");
                                //   monthxcode = Xcode_month;
                                Gendercode = Xcode_month;

                                radioButton_month.setText(XName_6month);
                            } else {
                                String XName_female = collegeData.getString("XName");
                                Xcode_year = collegeData.getString("XCode");
                                radioButton_year.setText(XName_female);
                            }
                        }
                        new Get_Mem_PaymentType_Details_BySrNo().execute();
                    }
                } catch (JSONException e) {


                }


            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class Get_PaymentMode_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_PaymentMode_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Bs_list", response);

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {

                Payment_list.addAll(bsNewsPojo.row);


                paymentadapter = new Paymentadapter_check(Select_Payment_Type.this, Payment_list);
                rw_pay_mode.setAdapter(paymentadapter);
                rw_pay_mode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Row model = Payment_list.get(position);
                        model.setSelected(true);
                        Payment_list.set(position, model);
                        if (model.getxCode() != "0") {
                            xcode_pay = model.getxCode();
                            String payment_name = model.getxName();

                            Log.e("xcode_pay", xcode_pay);
                            Log.e("payment_name", payment_name);
                            appPreferences.savePaymethod(payment_name);
                        }

                        if (preSelectedIndex > -1) {
                            preRecord = Payment_list.get(preSelectedIndex);
                            preRecord.setSelected(false);
                            Payment_list.set(preSelectedIndex, preRecord);
                        }

                        preSelectedIndex = position;
                        paymentadapter.updateRecords(Payment_list);
                    }
                });
              /*  rw_pay_mode.setLayoutManager(gridLayoutManager);
                payMode_adapter = new PayMode_Adapter(Select_Payment_Type.this,Payment_list);
                rw_pay_mode.setAdapter(payMode_adapter);*/


            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class Get_Mem_PaymentType_Details_BySrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Mem_PaymentType_Details_BySrNo','" + appPreferences.getmemtype() + "','" + Gendercode + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Mem_PaymentType_", response);

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {

                Installment_list.addAll(bsNewsPojo.row);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String emailresponse = jsonObject.getString("success");
                    if (emailresponse.equals("0")) {

                    } else {

                        JSONArray result = jsonObject.getJSONArray("row");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject collegeData = result.getJSONObject(i);

                            Mem_Price = collegeData.getDouble("Mem_Price");
                            Installment_Price = collegeData.getDouble("Installment_Price");
                            Total = collegeData.getDouble("Total");
                            String Mem_Price_ = String.format("%.3f", Mem_Price);
                            String Installment_Price_ = String.format("%.3f", Installment_Price);
                            String Total_ = String.format("%.3f", Total);
                            price.setText(Mem_Price_ + " " + "KD");


                            _instalment_price.setText(Installment_Price_ + " " + "KD");
                            grand_price.setText(Total_ + " " + "KD");

                        }

                    }
                } catch (JSONException e) {

                }


            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class Paymentadapter_check extends BaseAdapter {
        Context context;
        AppPreferences appPreferences;
        List<Row> payment_List = new ArrayList<>();

        public Paymentadapter_check(Select_Payment_Type checkout_activity, List<Row> payment_List) {
            this.context = checkout_activity;
            this.payment_List = payment_List;
        }

        @Override
        public int getCount() {
            return payment_List.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            appPreferences = new AppPreferences(context);

            if (appPreferences.getCulturemode().equals("1")) {
                convertView = inflater.inflate(R.layout.payment_layout, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.payment_layout_ar, parent, false);
            }

            Row m = this.payment_List.get(position);

            RelativeLayout cash = convertView.findViewById(R.id.cash);
            TextView tv_user_name = convertView.findViewById(R.id.tv_user_name);
            final ImageView iv_check_box = convertView.findViewById(R.id.iv_check_box);
            tv_user_name.setText(m.getxName());


            if (m.isSelected()) {
                iv_check_box.setBackgroundResource(R.drawable.radio_btn2);
            } else {
                iv_check_box.setBackgroundResource(R.drawable.blank_circle2);
            }


            return convertView;
        }

        public void updateRecords(List<Row> users) {
            this.payment_List = users;
            this.notifyDataSetChanged();
        }
    }

}