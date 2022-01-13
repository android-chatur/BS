package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.barayihsalem.UI.Adapter.Brand_Page_Adapter;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Menu_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
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

import static com.example.barayihsalem.UI.Filter_Activity.filte;
import static com.example.barayihsalem.UI.Retails_Details.retail;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Brand_Page_Activity extends AppCompatActivity implements View.OnClickListener {
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;

    TextView txtlab, shushi, lne, dd, phone_number;
    ImageView logoside, seach;
    LinearLayout sortandfilter;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_menu_list, rv_list_resu;
    Menu_Adapter menu_adapter;
    Spinner id_nationality, id_nationality1;
    Menu_Adapter allMenu_adapter;
    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    Nationality_Adapter nationality_adapter;
    ArrayList<Row> Menu_list = new ArrayList<Row>();


    Brand_Page_Adapter brand_page_adapter;
    LinearLayoutManager layoutManager;

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            appPreferences.SaveItem_xcode(Menu_list.get(position).getxCode());

            startActivity(new Intent(Brand_Page_Activity.this, Retail_Dish_Detail_Activity.class));
            finish();
           /* if (position == 0) {
                appPreferences.save_showhompage("Vision partner");
                startActivity(new Intent(WellcomefamilyPage.this, VisionPartner.class));
                finish();
            }
         */

        }

    };
    ImageView sortfilt;

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

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
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            // setContentView(R.layout.activity_all__food);
            setContentView(R.layout.activity_brand__page_);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_brand__page_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        txtlab.setText(appPreferences.getBrand_Name());


        if (Brand_Page_Activity.this.isNetworkConnected()) {
            new Get_Store_Brand_Wise_Item_List().execute();


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

        id_nationality1 = findViewById(R.id.id_nationality1);
        id_nationality = findViewById(R.id.id_nationality);
        id_nationality.setVisibility(View.GONE);
        rv_menu_list = findViewById(R.id.rv_menu_list);
        logoside = findViewById(R.id.logoside);
        seach = findViewById(R.id.seach);
        txtlab = findViewById(R.id.txtlab);
        logoside.setOnClickListener(this);
        seach.setOnClickListener(this);
        txtlab.setOnClickListener(this);


        sortfilt = findViewById(R.id.sortfilt);

        sortfilt.setOnClickListener(this);
        //txtlab.setTypeface(typebold);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Brand_Page_Activity.this, Retails_Details.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;


            case R.id.txtlab:
                id_nationality1.setVisibility(View.GONE);
                txtlab.setVisibility(View.GONE);
                id_nationality.setVisibility(View.VISIBLE);
                new Get_Store_Menu_List_By_SrNo().execute();

                if (view == txtlab) {
                    id_nationality.performClick();
                }
                break;

            case R.id.sortfilt:
                filte = false;
                appPreferences.saveBafilte("Brand_Page_Activity");
                startActivity(new Intent(Brand_Page_Activity.this, Filter_Activity.class));
                finish();
                break;
            case R.id.seach:
                retail = true;
                appPreferences.save_AddBackSeach("Brand_Page_Activity");
                startActivity(new Intent(Brand_Page_Activity.this, Search_Shopping.class));
                finish();
                break;
        }
    }


    private class Get_Store_Brand_Wise_Item_List extends AsyncTask<String, String, String> {
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
                if (filte == true) {
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Store_Brand_Wise_Item_List','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','" + appPreferences.geRetailSrno() + "','" + appPreferences.getBrand_SrNo() + "','','" + appPreferences.getfilter_sort() + "','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");

                } else {
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Store_Brand_Wise_Item_List','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','" + appPreferences.geRetailSrno() + "','" + appPreferences.getBrand_SrNo() + "','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");

                }
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
            Log.d("Brand_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Menu_list.addAll(partnerPojo.row);

                brand_page_adapter = new Brand_Page_Adapter(Brand_Page_Activity.this, onClickCall, Menu_list);
                layoutManager = new LinearLayoutManager(Brand_Page_Activity.this, LinearLayoutManager.VERTICAL, false);
                rv_menu_list.setLayoutManager(layoutManager);
                rv_menu_list.setAdapter(brand_page_adapter);
            } else {
             //   Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Store_Menu_List_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Store_Brand_Wise_Item_List','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Store_Menu_List_By_SrNo", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Nationality_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Brand_Page_Activity.this, R.layout.countryspinner_country_new, Nationality_list, res);
                id_nationality.setAdapter(nationality_adapter);
                id_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_nationality.setSelection(position);
                        String Nationality_Xcode = Nationality_list.get(position).xCode;
                        Log.e("Nationality_Xcode", Nationality_Xcode);
                        Menu_list.clear();

                        appPreferences.SaveRetailSrno(Nationality_Xcode);

                        new Get_Store_Brand_Wise_Item_List().execute();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


            }

        }
    }

}