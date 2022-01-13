package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.UserShippingAddressPojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.Shopping_Category_page.back_cate;
import static com.example.barayihsalem.UI.Shopping_Category_page.isedit;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Enter_Address extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, items, hi, abou, notifi, reach_help, abou_app, txtlab;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    APIInterface apiInterface;

    TextView message, no, yes;
    TextView txtlable;
    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    ProgressDialog progressDialog;
    String Area, Block, Street, Bulding_n0, Landmark, Apprtment_n0, Mobile_n0;
    String SrNoedi = "";//, Cityedi, Areaedi, Blockedi, Streetedi, Buildingedi, Landmarkedi, Apartment,Mobileedi;
    ImageView logoside;
    Nationality_Adapter nationality_adapter;
    Spinner id_city, id_country;
    String City_Xcode = "";
    String Command = "";
    Cart_Adapter cart_adapter;
    Button btnLogin;
    ArrayList<String> nationality_list = new ArrayList<>();

    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0, editText_mobile_n0;

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }*/
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_enter__address);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_enter__address_ar);
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


        if (Enter_Address.this.isNetworkConnected()) {

            new Get_Address_Area_List().execute();
            Command = "Save";
            SrNoedi = "";
            if (isedit == true) {
                Command = "Update";
                new Get_Address_Details_By_SrNo().execute();
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


    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid() {
        abcd = findViewById(R.id.abcd);
        txtlab = findViewById(R.id.txtlab);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        txtlab.setTypeface(typeRegular);
        id_city = findViewById(R.id.id_city);
        logoside = findViewById(R.id.logoside);
        id_country = findViewById(R.id.id_country);
        txtlable = findViewById(R.id.txtlable);
        editText_area = findViewById(R.id.editText_area);
        editText_street = findViewById(R.id.editText_street);
        edit_block = findViewById(R.id.edit_block);
        editText_street = findViewById(R.id.editText_street);
        editText_bulg_n0 = findViewById(R.id.editText_bulg_n0);
        editText_landmark = findViewById(R.id.editText_landmark);
        editText_apprtment_n0 = findViewById(R.id.editText_apprtment_n0);
        editText_mobile_n0 = findViewById(R.id.editText_mobile_n0);
        btnLogin = findViewById(R.id.btnLogin);
        editText_area.setTypeface(typeRegular);
        edit_block.setTypeface(typeRegular);
        editText_street.setTypeface(typeRegular);
        editText_bulg_n0.setTypeface(typeRegular);
        editText_landmark.setTypeface(typeRegular);
        editText_apprtment_n0.setTypeface(typeRegular);
        txtlable.setTypeface(normal);
        btnLogin.setTypeface(typeRegular);
        editText_mobile_n0.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {


        if (appPreferences.get_AddressBack().equals("Shopping_Activity")) {
            startActivity(new Intent(Enter_Address.this, Shopping_Activity.class));
            finish();
        }


        if (appPreferences.get_AddressBack().equals("Acount_Info")) {
            startActivity(new Intent(Enter_Address.this, Acount_Info.class));
            finish();
        }

        if (appPreferences.get_AddressBack().equals("Shopping_Category_page")) {
            startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
            finish();
        }

        if (appPreferences.get_AddressBack().equals("Food_Activity")) {
            startActivity(new Intent(Enter_Address.this, Food_Activity.class));
            finish();
        }

        if (appPreferences.get_AddressBack().equals("Grocery_Activity")) {
            startActivity(new Intent(Enter_Address.this, Grocery_Activity.class));
            finish();
        }

        if (appPreferences.get_AddressBack().equals("Reatils_Sub_Category_Page")) {
            startActivity(new Intent(Enter_Address.this, Reatils_Sub_Category_Page.class));
            finish();
        }

        if (appPreferences.get_AddressBack().equals("Reatils_category_page")) {
            startActivity(new Intent(Enter_Address.this, Reatils_category_page.class));
            finish();
        }










        /*if (back_cate == true) {
            startActivity(new Intent(Enter_Address.this, Shopping_Activity.class));
            finish();
        } else {
            startActivity(new Intent(Enter_Address.this, Acount_Info.class));
            finish();
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.btnLogin:


                if (Enter_Address.this.isNetworkConnected()) {

                    if (Validate()) {
                        if (!City_Xcode.equals("") || !City_Xcode.isEmpty()) {

                            showProgress();
                            Save_UserShippingAddress();

                        } else {
                            Toast.makeText(appController, "Please Select Nationality", Toast.LENGTH_SHORT).show();
                        }

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
              /*  if (back_cate == true) {
                    startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                    finish();
                } else {
                    startActivity(new Intent(Enter_Address.this, Acount_Info.class));
                    finish();
                }*/

               /* if (back_cate == true) {
                    startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                    finish();
                } else {
                    startActivity(new Intent(Enter_Address.this, Acount_Info.class));
                    finish();
                }*/

                break;


        }
    }

    private void Save_UserShippingAddress() {
        UserShippingAddressPojo shippingAddressPojo = new UserShippingAddressPojo();

        shippingAddressPojo.setSrNo(SrNoedi);
        shippingAddressPojo.setUserId(appPreferences.getuserid());
        shippingAddressPojo.setCity(City_Xcode);
        shippingAddressPojo.setArea(Area);
        shippingAddressPojo.setBlock(Block);
        shippingAddressPojo.setStreet(Street);
        shippingAddressPojo.setBuilding(Bulding_n0);
        shippingAddressPojo.setLandmark(Landmark);
        shippingAddressPojo.setApartment(Apprtment_n0);
        shippingAddressPojo.setActive(true);
        shippingAddressPojo.setDelete(false);
        shippingAddressPojo.setMobile(Mobile_n0);
        shippingAddressPojo.setSource("Android");
        shippingAddressPojo.setCorpcentreby(appPreferences.get_company_id());
        shippingAddressPojo.setIpAddress(sessionManager.GetIpAddress());
        shippingAddressPojo.setCommand(Command);


        Call<UserShippingAddressPojo> call2 = apiInterface.Save_UserShippingAddress(shippingAddressPojo);
        call2.enqueue(new Callback<UserShippingAddressPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<UserShippingAddressPojo> call, Response<UserShippingAddressPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(Enter_Address.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        appPreferences.save_track_id_address(String.valueOf(response.body().getTrackId()));
                        //exitByBackKey();

                        if (appPreferences.get_AddressBack().equals("Shopping_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                            finish();
                        }


                        if (appPreferences.get_AddressBack().equals("Acount_Info")) {
                            startActivity(new Intent(Enter_Address.this, Acount_Info.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Food_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Food_Activity.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Grocery_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Grocery_Activity.class));
                            finish();
                        } if (appPreferences.get_AddressBack().equals("Shopping_Category_page")) {
                            startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Reatils_Sub_Category_Page")) {
                            startActivity(new Intent(Enter_Address.this, Reatils_Sub_Category_Page.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Reatils_category_page")) {
                            startActivity(new Intent(Enter_Address.this, Reatils_category_page.class));
                            finish();
                        }


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {

                        Toast.makeText(Enter_Address.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        appPreferences.save_track_id_address(String.valueOf(response.body().getTrackId()));
                        //exitByBackKey();
                        if (appPreferences.get_AddressBack().equals("Shopping_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                            finish();
                        }
                        if (appPreferences.get_AddressBack().equals("Shopping_Category_page")) {
                            startActivity(new Intent(Enter_Address.this, Shopping_Category_page.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Acount_Info")) {
                            startActivity(new Intent(Enter_Address.this, Acount_Info.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Food_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Food_Activity.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Grocery_Activity")) {
                            startActivity(new Intent(Enter_Address.this, Grocery_Activity.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Reatils_Sub_Category_Page")) {
                            startActivity(new Intent(Enter_Address.this, Reatils_Sub_Category_Page.class));
                            finish();
                        }

                        if (appPreferences.get_AddressBack().equals("Reatils_category_page")) {
                            startActivity(new Intent(Enter_Address.this, Reatils_category_page.class));
                            finish();
                        }







                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-8")) {

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
            public void onFailure(Call<UserShippingAddressPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Enter_Address.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private boolean Validate() {
        Area = editText_area.getText().toString();
        Block = edit_block.getText().toString();
        Street = editText_street.getText().toString();
        Bulding_n0 = editText_bulg_n0.getText().toString();
        Landmark = editText_landmark.getText().toString();
        Apprtment_n0 = editText_apprtment_n0.getText().toString();
        Mobile_n0 = editText_mobile_n0.getText().toString();

        if (TextUtils.isEmpty(editText_area.getText().toString())) {
            editText_area.setError("Please Enter Area");
            editText_area.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(edit_block.getText().toString())) {
            edit_block.setError("Please Enter Block");
            edit_block.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_street.getText().toString())) {
            editText_street.setError("Please Enter Street");
            editText_street.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_bulg_n0.getText().toString())) {
            editText_bulg_n0.setError("Please Enter Bulding Number");
            editText_bulg_n0.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_mobile_n0.getText().toString())) {
            editText_mobile_n0.setError("Please Enter Mobile Number");
            editText_mobile_n0.requestFocus();
            return false;
        }


        return true;
    }


    private class Get_Address_Area_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Address_Area_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_city_List", response);
            //   progressDialog.dismiss();
           /* Nationality_list = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
            if (appPreferences.getCulturemode().equals("1")) {
                worldpop.setxName("Select City");
            } else {
                worldpop.setxName("المدينة");
            }
            // worldpop.setXname("Select Country");
            //  worldpop.setXname("Kuwait");
            Nationality_list.add(worldpop);*/

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Nationality_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Enter_Address.this, R.layout.countryspinner_country_new, Nationality_list, res);
                id_city.setAdapter(nationality_adapter);
                id_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_city.setSelection(position);
                        City_Xcode = Nationality_list.get(position).xCode;
                        Log.e("City_Xcode", City_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Address_Details_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Address_Details_By_SrNo','" + appPreferences.get_AddressXcode() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("edit", response);
            //   progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        SrNoedi = collegeData.getString("SrNo");
                        City_Xcode = collegeData.getString("City");
                        Area = collegeData.getString("Area");
                        Block = collegeData.getString("Block");
                        Street = collegeData.getString("Street");
                        Bulding_n0 = collegeData.getString("Building");
                        Landmark = collegeData.getString("Landmark");
                        Apprtment_n0 = collegeData.getString("Apartment");
                        Mobile_n0 = collegeData.getString("Mobile");


                        editText_area.setText(Area);
                        edit_block.setText(Block);
                        editText_street.setText(Street);
                        editText_bulg_n0.setText(Bulding_n0);
                        editText_landmark.setText(Landmark);
                        editText_apprtment_n0.setText(Apprtment_n0);
                        editText_mobile_n0.setText(Mobile_n0);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

}