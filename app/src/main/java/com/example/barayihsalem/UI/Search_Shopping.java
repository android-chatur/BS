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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Menu_Adapter;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
import com.example.barayihsalem.UI.Adapter.Seach_Adapter;
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

public class Search_Shopping extends AppCompatActivity {
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    EditText edtSearch;
    TextView lnewly, shushi, lne, dd, phone_number;
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
    RecyclerView rv_search_list, rv_list_resu;
    Menu_Adapter menu_adapter;
    Seach_Adapter allMenu_adapter;
    ArrayList<Row> Seach_list = new ArrayList<Row>();
    LinearLayoutManager layoutManager;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            appPreferences.SaveItem_xcode(Seach_list.get(position).getxCode());


            startActivity(new Intent(Search_Shopping.this, Dish_Detail_Activity.class));
            finish();
           /* if (position == 0) {
                appPreferences.save_showhompage("Vision partner");
                startActivity(new Intent(WellcomefamilyPage.this, VisionPartner.class));
                finish();
            }
         */

        }

    };
    private int searchLength = 0;
    private String strQuery = "";

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
            setContentView(R.layout.activity_search__shopping);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_search__shopping_ar);

        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                searchLength = count;
                if (count > 0) {

                    strQuery = s.toString();
                    if (Search_Shopping.this.isNetworkConnected()) {

                        new Get_Store_Menu_Wise_ItemList_By_SrNo().execute();
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
                } else {
                    //lstSearch.setVisibility(View.GONE);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });


    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onBackPressed() {
        filte = false;

        if (appPreferences.get_AddBackSeach().equals("Menu_Activity")) {
            startActivity(new Intent(Search_Shopping.this, Menu_Activity.class));
            finish();
        }
        if (appPreferences.get_AddBackSeach().equals("Menu_Reatil_Activity")) {
            startActivity(new Intent(Search_Shopping.this, Menu_Reatil_Activity.class));
            finish();
        }
        if (appPreferences.get_AddBackSeach().equals("Retails_Details")) {
            startActivity(new Intent(Search_Shopping.this, Retails_Details.class));
            finish();
        }
        if (appPreferences.get_AddBackSeach().equals("Restaurant_Details")) {
            startActivity(new Intent(Search_Shopping.this, Restaurant_Details.class));
            finish();
        }
        if (appPreferences.get_AddBackSeach().equals("Restaurant_Details")) {
            startActivity(new Intent(Search_Shopping.this, Brand_Page_Activity.class));
            finish();
        }
        startActivity(new Intent(Search_Shopping.this, Shopping_Category_page.class));
        finish();
    }

    private void findid() {
        rv_search_list = findViewById(R.id.rv_search_list);
        edtSearch = findViewById(R.id.edtSearch);
        edtSearch.setTypeface(typeRegular);
    }


    private class Get_Store_Menu_Wise_ItemList_By_SrNo extends AsyncTask<String, String, String> {
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
                if (retail == false) {

                } else {
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Store_Search_Wise_ItemList_By_SrNo','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','','','" + strQuery + "','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                    builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Store_Search_Wise_ItemList_By_SrNo','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','" + appPreferences.geRetailSrno() + "','','" + strQuery + "','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("menu_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Seach_list.addAll(partnerPojo.row);

                allMenu_adapter = new Seach_Adapter(Search_Shopping.this, onClickCall, Seach_list);
                layoutManager = new LinearLayoutManager(Search_Shopping.this, LinearLayoutManager.VERTICAL, false);
                rv_search_list.setLayoutManager(layoutManager);
                rv_search_list.setAdapter(allMenu_adapter);
                allMenu_adapter.notifyDataSetChanged();


            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}