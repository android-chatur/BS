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
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;
import com.example.barayihsalem.UI.Adapter.Select_Membership_Adapter;
import com.example.barayihsalem.UI.Model.Additional_Servicesojo;
import com.example.barayihsalem.UI.Model.Row;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Select_Payment_supplierActivity extends AppCompatActivity implements View.OnClickListener {
    public static boolean paymentback = false;
    public static String dxy;
    TextView txtlable, mem_price, price, mont_instalment, pay_txt, _instalment_price, gran_total, grand_price;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    String XCode, XName, Currency;
    double Price, GrandTotal;
    APIInterface apiInterface;

    TextView message, no, yes;
    RadioButton radioButton_month, radioButton_year;
    ImageView logoside;
    ListView rw_checkout, rw_pay_mode;
    PayMode_Adapter payMode_adapter;
    ArrayList<Row> Payment_list = new ArrayList<Row>();
    Paymentadapter_check paymentadapter;
    int preSelectedIndex = -1;
    RadioGroup radioGroup_character;
    Row preRecord;
    String xcode_pay = "";

    Button btnLogin;
    ProgressDialog progressDialog;
    RecyclerView rw_select_membership, rw_comminity;
    Select_Membership_Adapter select_membership_adapter;
    RelativeLayout abcd, topLayouts;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);
        if (!(dxy == "asd")) {
            if (Build.VERSION.SDK_INT >= 21) {
            }
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top

        }


        if (dxy.equals("asd")) {
            if (appPreferences.get_Color().equals("bussiness colr")) {

                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top

            }


            if (appPreferences.get_Color().equals("passionate colr")) {

                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.reject_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.reject_color)); //status bar or the time bar at the top

            }

            if (appPreferences.get_Color().equals("vp_Recident_VP")) {

                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.recident_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.recident_color)); //status bar or the time bar at the top

            }


            if (appPreferences.get_Color().equals("vp colr")) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top

            }

            if (appPreferences.get_Color().equals("supp_registerd_services")) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.supplier_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.supplier_color)); //status bar or the time bar at the top

            }


        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.supplier_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.supplier_color)); //status bar or the time bar at the top


        }


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_select__payment_supplier);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_select__payment_supplier_ar);


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

        if (Select_Payment_supplierActivity.this.isNetworkConnected()) {
            if (dxy.equals("asd")) {
                new Get_Additional_Service_Pymnt_Page().execute();
            }
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


        if (dxy.equals("asd")) {
            if (appPreferences.get_Color().equals("bussiness colr")) {

                topLayouts.setBackgroundColor(getResources().getColor(R.color.buss_oner_color));
            }


            if (appPreferences.get_Color().equals("passionate colr")) {

                topLayouts.setBackgroundColor(getResources().getColor(R.color.reject_color));
            }

            if (appPreferences.get_Color().equals("vp_Recident_VP")) {
                topLayouts.setBackgroundColor(getResources().getColor(R.color.recident_color));
            }

            if (appPreferences.get_Color().equals("vp_Recident_VP")) {
                topLayouts.setBackgroundColor(getResources().getColor(R.color.recident_color));
            }
            if (appPreferences.get_Color().equals("vp colr")) {
                topLayouts.setBackgroundColor(getResources().getColor(R.color.vispart_color));
            }

            if (appPreferences.get_Color().equals("supp_registerd_services")) {
                topLayouts.setBackgroundColor(getResources().getColor(R.color.supplier_color));
            }


        } else {
            topLayouts.setBackgroundColor(getResources().getColor(R.color.buss_oner_color));

        }
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Select_Payment_supplierActivity.this);
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
        abcd = findViewById(R.id.abcd);
        topLayouts = findViewById(R.id.topLayouts);
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
        radioGroup_character.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                boolean isChecked = radioButton_month.isChecked();

                if (isChecked) {
                    mont_instalment.setText("6 months installment");
                } else {
                    mont_instalment.setText("1 year installment");

                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (dxy.equals("asd")) {
            startActivity(new Intent(Select_Payment_supplierActivity.this, BO_Registered_services.class));
            finish();
        } else {
            startActivity(new Intent(Select_Payment_supplierActivity.this, BO_BootYourSales.class));
            finish();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btnLogin:
                dxy = "success";
                if (Select_Payment_supplierActivity.this.isNetworkConnected()) {
                    if (!xcode_pay.isEmpty() || !xcode_pay.equals("")) {

                        showProgress();
                        Save_User_Additional_Services();
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

               /* startActivity(new Intent(Select_Payment_supplierActivity.this, Success_Page.class));
                finish();*/

                break;

        }
    }

    private void Save_User_Additional_Services() {
        Additional_Servicesojo additional_servicesojo = new Additional_Servicesojo();
        additional_servicesojo.setUserId(appPreferences.getuserid());
        additional_servicesojo.setMem_SrNo(appPreferences.get_membership_srno());
        additional_servicesojo.setMOD(xcode_pay);
        additional_servicesojo.setService_SrNo(appPreferences.getServiceXcode());
        additional_servicesojo.setPrice(String.valueOf(Price));
        additional_servicesojo.setSource("Android");
        additional_servicesojo.setUniqueId(sessionManager.GetUniqueId());
        additional_servicesojo.setIpAddress(sessionManager.GetIpAddress());
        additional_servicesojo.setCorpcentreby(appPreferences.get_company_id());
        additional_servicesojo.setCommand("Save");


        Call<Additional_Servicesojo> call2 = apiInterface.Save_User_Additional_Services(additional_servicesojo);
        call2.enqueue(new Callback<Additional_Servicesojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Additional_Servicesojo> call, Response<Additional_Servicesojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //exitByBackKey();

                        startActivity(new Intent(Select_Payment_supplierActivity.this, Success_Page.class));
                        finish();
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


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
            public void onFailure(Call<Additional_Servicesojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }


    private class Get_Additional_Service_Pymnt_Page extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Additional_Service_Pymnt_Page','" + appPreferences.get_membership_srno() + "','" + appPreferences.getServiceXcode() + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("service data", response);

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String emailresponse = jsonObject.getString("success");
                    if (emailresponse.equals("0")) {

                    } else {

                        JSONArray result = jsonObject.getJSONArray("row");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject collegeData = result.getJSONObject(i);

                            XCode = collegeData.getString("XCode");
                            XName = collegeData.getString("XName");
                            Currency = collegeData.getString("Currency");
                            Price = collegeData.getDouble("Price");
                            GrandTotal = collegeData.getDouble("GrandTotal");


                            String Mem_Price_ = String.format("%.3f", Price);
                            String Total_ = String.format("%.3f", GrandTotal);
                            price.setText(Mem_Price_ + " " + "KD");

                            mem_price.setText(XName);

                            grand_price.setText(Total_ + " " + Currency);
                        }

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


                paymentadapter = new Paymentadapter_check(Select_Payment_supplierActivity.this, Payment_list);
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

        public Paymentadapter_check(Select_Payment_supplierActivity checkout_activity, List<Row> payment_List) {
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