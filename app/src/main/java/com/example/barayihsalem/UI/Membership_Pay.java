package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
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
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Adapter.Select_Membership_Adapter;
import com.example.barayihsalem.UI.Model.Pay_Remaining_BusinessPojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionPartnerland_loadsPojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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

import static com.example.barayihsalem.UI.Select_Payment_Type.paymentback;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Membership_Pay extends AppCompatActivity implements View.OnClickListener {
    TextView name, txtlable, mem_price, price, mont_instalment, pay_txt, _instalment_price, mem_name, gran_total, grand_price;
    AppController appController;
    String SrNo, Mem_SrNo, Mem_Name, Mem_Type_Name, MembershipTerm_Remaining;
    double Membership_Price, Membership_Remaining_Amount;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    APIInterface apiInterface;
    int preSelectedIndex = -1;
    String xcode_pay = "";
    TextView message, no, yes;
    ProgressDialog progressDialog;
    RadioButton radioButton_month, radioButton_year;
    ImageView logoside;
    RecyclerView rw_checkout;
    ListView rw_pay_mode;
    Row preRecord;
    ArrayList<Row> Payment_list = new ArrayList<Row>();
    Paymentadapter_check paymentadapter;
    PayMode_Adapter payMode_adapter;
    RadioGroup radioGroup_character;
    Button btnLogin;
    RecyclerView rw_select_membership, rw_comminity;
    Select_Membership_Adapter select_membership_adapter;
    RelativeLayout abcd;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
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
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_membership__pay);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_membership__pay_ar);


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

        if (Membership_Pay.this.isNetworkConnected()) {
            new Get_Remaining_Amount_Details().execute();
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
        btnLogin = findViewById(R.id.btnLogin);
        mem_name = findViewById(R.id.mem_name);
        radioGroup_character = findViewById(R.id.radioGroup_character);
        rw_pay_mode = findViewById(R.id.rw_pay_mode);
        mem_price = findViewById(R.id.mem_price);
        gran_total = findViewById(R.id.gran_total);
        name = findViewById(R.id.name);
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
        name.setTypeface(typeRegular);
        price.setTypeface(typeRegular);
        _instalment_price.setTypeface(typeRegular);
        gran_total.setTypeface(typeRegular);
        mem_name.setTypeface(typeRegular);
        mont_instalment.setTypeface(typeRegular);
        grand_price.setTypeface(typeRegular);
        pay_txt.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        logoside.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(Membership_Pay.this, Adjust_Membership.class));
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btnLogin:


                if (Membership_Pay.this.isNetworkConnected()) {
                    if (!xcode_pay.isEmpty() || !xcode_pay.equals("")) {

                        showProgress();
                        Pay_Remaining_Business_Owner_Amount();
                    } else {
                        Toast.makeText(appController, "Please Select Payment Mode", Toast.LENGTH_SHORT).show();

                    }
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







                break;

        }
    }

    private void Pay_Remaining_Business_Owner_Amount() {
        Pay_Remaining_BusinessPojo loadsPojo = new Pay_Remaining_BusinessPojo();

        loadsPojo.setUserId(appPreferences.getuserid());
        loadsPojo.setAmount(String.valueOf(Membership_Remaining_Amount));
        loadsPojo.setUniqueId(sessionManager.GetUniqueId());
        loadsPojo.setMOP(xcode_pay);
        loadsPojo.setCorpcentreby(appPreferences.get_company_id());
        loadsPojo.setIpAddress(sessionManager.GetIpAddress());
        loadsPojo.setSource("Android");
        loadsPojo.setPayType(Mem_Type_Name);


        Call<Pay_Remaining_BusinessPojo> call2 = apiInterface.Pay_Remaining_Business_Owner_Amount(loadsPojo);
        call2.enqueue(new Callback<Pay_Remaining_BusinessPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Pay_Remaining_BusinessPojo> call, Response<Pay_Remaining_BusinessPojo> response) {
                Log.i(getClass().getName(), "response_landload: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                       /* paymentback = false;*/
                        paymentback=false;
                        appPreferences.save_Attribute_mem(response.body().getTrackId());
                        startActivity(new Intent(Membership_Pay.this, Pay_Success_Page.class));
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
            public void onFailure(Call<Pay_Remaining_BusinessPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Membership_Pay.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_Remaining_Amount_Details extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_Remaining_Amount_Details','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("Get_Remaining_Amount_Details", response);
            //  progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        SrNo = collegeData.getString("SrNo");
                        Mem_SrNo = collegeData.getString("Mem_SrNo");
                        Mem_Name = collegeData.getString("Mem_Name");
                        Mem_Type_Name = collegeData.getString("Mem_Type_Name");
                        MembershipTerm_Remaining = collegeData.getString("MembershipTerm_Remaining");
                        Membership_Price = collegeData.getDouble("Membership_Price");
                        Membership_Remaining_Amount = collegeData.getDouble("Membership_Remaining_Amount");


                        String Mem_Price_ = String.format("%.3f", Membership_Price);
                        String Total_ = String.format("%.3f", Membership_Remaining_Amount);

                        name.setText(Mem_Name);
                        price.setText(Mem_Type_Name);
                        _instalment_price.setText(Mem_Price_);
                        grand_price.setText(Total_);
                        mont_instalment.setText(MembershipTerm_Remaining);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

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


                paymentadapter = new Paymentadapter_check(Membership_Pay.this, Payment_list);
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

    private class Paymentadapter_check extends BaseAdapter {
        Context context;
        AppPreferences appPreferences;
        List<Row> payment_List = new ArrayList<>();

        public Paymentadapter_check(Membership_Pay checkout_activity, List<Row> payment_List) {
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