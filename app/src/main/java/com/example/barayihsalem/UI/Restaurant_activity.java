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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
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
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Restaurant_activity extends AppCompatActivity implements View.OnClickListener {
    TextView txtlab, tx_cy, tx_cate, tx_food, tx_Brand, tx_depat, tx_cancel;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;

    ImageView sortfilt;
    ImageView logoside;
    //  LinearLayout sortandfilter;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_list_resu;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    GridLayoutManager layoutManager;
    ArrayList<Row> Shopping_list = new ArrayList<Row>();

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            if (Shopping_list != null && Shopping_list.size() > 0) {

              appPreferences.SaveStore_SrNo(Shopping_list.get(position).getStoreSrNo());

              startActivity(new Intent(Restaurant_activity.this, Restaurant_Details.class));
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
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            // setContentView(R.layout.activity_all__food);
            setContentView(R.layout.activity_restaurant_activityu);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_restaurant_activityu_ar);

        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();


        if (Restaurant_activity.this.isNetworkConnected()) {
            new Get_Cuisines_Wise_All_Store_list().execute();

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

    private void findid() {
        rv_list_resu = findViewById(R.id.rv_list_resu);
        txtlab = findViewById(R.id.txtlab);
        //sortandfilter = findViewById(R.id.sortandfilter);
        sortfilt = findViewById(R.id.sortfilt);
        txtlab.setTypeface(typeRegular);
        sortfilt.setOnClickListener(this);
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Restaurant_activity.this, All_Food.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sortfilt:
                filte=false;
                appPreferences.saveBafilte("Restaurant_activity");
                startActivity(new Intent(Restaurant_activity.this, Filter_Activity.class));
                finish();
                break;
        }
    }

    private class Get_Cuisines_Wise_All_Store_list extends AsyncTask<String, String, String> {
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
                if (filte==true){
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Cuisines_Wise_All_Store_list','" + appPreferences.geShoppingSrno() + "','" + appPreferences.geCuisines_Xcod() + "','','','','"+appPreferences.getfilter_sort()+"','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");

                }else{
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Cuisines_Wise_All_Store_list','" + appPreferences.geShoppingSrno() + "','" + appPreferences.geCuisines_Xcod() + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");

                }
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
            Log.d("Get_Cuisines_Wise_All_Store_list", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Shopping_list.addAll(partnerPojo.row);

                all_food_adapter = new ReasturantAdapter(Restaurant_activity.this, onClickCall, Shopping_list);
                layoutManager = new GridLayoutManager(Restaurant_activity.this, 2);
                rv_list_resu.setLayoutManager(layoutManager);
                rv_list_resu.setAdapter(all_food_adapter);

            } else {
             //   Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }


}