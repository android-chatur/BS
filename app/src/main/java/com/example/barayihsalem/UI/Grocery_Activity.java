package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Grocer_List_Adapter;
import com.example.barayihsalem.UI.Adapter.Grocer_partner_Adapter;
import com.example.barayihsalem.UI.Adapter.SlidingImage_Adapter_Grocery;
import com.example.barayihsalem.UI.Model.Address_Viewhoder;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;

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
import java.util.Timer;
import java.util.TimerTask;

import static com.example.barayihsalem.UI.Food_Activity.iffood;
import static com.example.barayihsalem.UI.Reatils_Sub_Category_Page.isgrosary;
import static com.example.barayihsalem.UI.Shopping_Category_page.colorred;
import static com.example.barayihsalem.UI.Shopping_Category_page.isedit;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Grocery_Activity extends AppCompatActivity implements View.OnClickListener {
    private static ViewPager mPager;
    private static int currentPage = 0;
    TextView txtlab, tx_cy, tx_cate, tx_food, tx_Brand, tx_depat, tx_cancel;
    AppController appController;
    AppPreferences appPreferences;
    Timer swipeTimer = null;

    SessionManager sessionManager;
    ArrayList<Row> Shopping_list = new ArrayList<Row>();
    AlertDialog alertDialog;
    TextView message, no, yes;
    ArrayList<Row> Banner_list = new ArrayList<Row>();

    TextView txtlable;
    ImageView logoside;
    LinearLayout lin_seeall_grocery;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    String AddressName;
    String AddressXcode = "";

    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rvList, rv_list_resu;
    OnItemClickListener.OnClickCallback onClickCall_resta = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            iffood = true;
            appPreferences.SaveStore_SrNo(Shopping_list.get(position).getStoreSrNo());
            startActivity(new Intent(Grocery_Activity.this, Restaurant_Details.class));
            finish();
           /* if (position == 0) {
                appPreferences.save_showhompage("Vision partner");
                startActivity(new Intent(WellcomefamilyPage.this, VisionPartner.class));
                finish();
            }
         */

        }

    };
    LinearLayoutManager layoutManager;
    //  SubFoodAdapter foodAdapter;
    Grocer_partner_Adapter resta_food_adapter;
    Grocer_List_Adapter grocer_list_adapter;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    RecyclerView change_list;
    TextView aboutbs;
    Button proceed;
    ArrayList<Row> bottom_list = new ArrayList<Row>();
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            isedit=true;
            appPreferences.AddressBack("Grocery_Activity");

            appPreferences.SaveAddressXcode(bottom_list.get(position).getxCode());
            startActivity(new Intent(Grocery_Activity.this, Enter_Address.class));
            finish();

        }

    };
    Address_Adapter_inner address_adapter;

    // Address_Adapter address_adapter;
    BottomSheetDialog dialog;

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
            setContentView(R.layout.activity_grocery_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_grocery_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        //init();
        getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        if (Grocery_Activity.this.isNetworkConnected()) {
            new Get_Top10_All_Store_list_By_Category().execute();
            new Get_Category_Wise_Banner().execute();
            new Get_Address_For_Category().execute();
            new Get_Address_List_For_Category().execute();

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







       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rv_list_resu.setLayoutManager(gridLayoutManager);
        grocer_list_adapter = new Grocer_List_Adapter(Grocery_Activity.this, onClickCall);
        rv_list_resu.setAdapter(grocer_list_adapter);*/


      /*  foodAdapter = new SubFoodAdapter(Grocery_Activity.this, onClickCall);
        layoutManager = new LinearLayoutManager(Grocery_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_list_resu.setLayoutManager(layoutManager);
        rv_list_resu.setAdapter(foodAdapter);*/
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    /* private void init() {
         mPager = findViewById(R.id.pager);
         mPager.setAdapter(new SlidingImage_Adapter_Grocery(this));
     }
 */


    private void init(ArrayList<Row> banner_list) {

        {
            ArrayList<String> Add_list_deatal = new ArrayList<>();
            mPager = findViewById(R.id.pager);


            for (int i = 0; i < banner_list.size(); i++)
                Add_list_deatal.add(banner_list.get(i).image);
            mPager = findViewById(R.id.pager);
            mPager.setAdapter(new SlidingImage_Adapter_Grocery(this, banner_list));

            // mPager.setAdapter(new Produtdeta_Adapter_Food(Dish_Detail_Activity.this, arrayList));
            PageIndicatorView indicator = findViewById(R.id.indicator);
            indicator.setViewPager(mPager);
            indicator.setSelectedColor(Color.BLACK);
            indicator.setUnselectedColor(Color.GRAY);


            // Auto start of viewpager
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == banner_list.size()) {
                        currentPage = 0;

                    }
                    mPager.setCurrentItem(currentPage++, true);

                }
            };
            swipeTimer = new Timer();
            // Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        handler.post(Update);
                                    }

                                },
                    4000, 4000);


        }


    }

    private void findid() {
        txtlab = findViewById(R.id.txtlab);
        tx_cate = findViewById(R.id.tx_cate);
        lin_seeall_grocery = findViewById(R.id.lin_seeall_grocery);
        rvList = findViewById(R.id.rv_list);
        rv_list_resu = findViewById(R.id.rv_list_resu);
        tx_cancel = findViewById(R.id.tx_cancel);
        tx_cancel.setTypeface(typeRegular);
        tx_cate.setTypeface(typeRegular);
        txtlab.setTypeface(typebold);
        lin_seeall_grocery.setOnClickListener(this);
        tx_cancel.setOnClickListener(this);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Grocery_Activity.this, Shopping_Category_page.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_seeall_grocery:
                isgrosary = true;
                startActivity(new Intent(Grocery_Activity.this, All_Retails.class));
                finish();
                break;

            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.tx_cancel:
                showBottomSheetDialog();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showBottomSheetDialog() {
        {
            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                view = LayoutInflater.from(Grocery_Activity.this).inflate(R.layout.changeaddress_dialog, null);

            } else {
                view = LayoutInflater.from(Grocery_Activity.this).inflate(R.layout.changeaddress_dialog_ar, null);

            }

            aboutbs = view.findViewById(R.id.aboutbs);

            change_list = view.findViewById(R.id.change_list);

            proceed = view.findViewById(R.id.proceed);
            aboutbs.setTypeface(typebold);

            proceed.setTypeface(typeRegular);

            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tx_cate.setText(AddressName);
                    dialog.dismiss();
                }
            });
            dialog = new BottomSheetDialog(Grocery_Activity.this);
            dialog.setContentView(view);
            dialog.show();

            address_adapter = new Address_Adapter_inner(Grocery_Activity.this, onClickCall, bottom_list);
            colorred = true;
            layoutManager = new LinearLayoutManager(Grocery_Activity.this, LinearLayoutManager.VERTICAL, false);
            change_list.setLayoutManager(layoutManager);
            change_list.setAdapter(address_adapter);
        }
    }


    private class Get_Top10_All_Store_list_By_Category extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Top10_All_Store_list_By_Category','" + appPreferences.geShoppingSrno() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Top10_Cuisines_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Shopping_list.addAll(partnerPojo.row);


                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 2);
                gridLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                rvList.setLayoutManager(gridLayoutManager1);

                resta_food_adapter = new Grocer_partner_Adapter(Grocery_Activity.this, onClickCall_resta, Shopping_list);
                layoutManager = new LinearLayoutManager(Grocery_Activity.this, LinearLayoutManager.VERTICAL, false);
                //    rvList.setLayoutManager(layoutManager);
                rvList.setAdapter(resta_food_adapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Category_Wise_Banner extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Category_Wise_Banner','" + appPreferences.geShoppingSrno() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("baner_food", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Banner_list.addAll(partnerPojo.row);

                init(Banner_list);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Address_For_Category extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Address_For_Category','" + AddressXcode + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Address_For_Category", response);

            //  progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String XCode = collegeData.getString("XCode");
                        String XName = collegeData.getString("XName");

                        tx_cate.setText(XName);


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

    private class Get_Address_List_For_Category extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Address_List_For_Category','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Address_List_For_Category", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                bottom_list.addAll(partnerPojo.row);


            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Address_Adapter_inner extends RecyclerView.Adapter<Address_Viewhoder> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
        ArrayList<Row> bottom_list = new ArrayList<>();
        int mSelectedPosition = 0;
        AppPreferences appPreferences;
        private int mCheckedPostion = -1;
        private Context context;
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Address_Adapter_inner(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> bottom_list) {
            this.context = context;
            this.bottom_list = bottom_list;
            this.onClickCallback_Benefi = onClickCall_resta;

        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public Address_Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView;
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();
            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;

            heding = appController.heding;
            typebold = appController.typebold;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details, parent, false);
            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details_ar, parent, false);

            }
            return new Address_Viewhoder(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull Address_Viewhoder holder, final int position) {
            //tvName.setText(list.get(position).getxName());


            holder.tx_cate.setTypeface(typeHeader);
            holder.tx_cy.setTypeface(typeRegular);
            holder.tx_cancel.setTypeface(typeRegular);
            holder.tx_cate.setChecked(position == mCheckedPostion);
            holder.tx_cate.setText(bottom_list.get(position).getxName());
            //  tx_cy.setText(bottom_list.get(position).getxName());
            String srn0 = String.valueOf(bottom_list.get(position).getSr());
            if (appPreferences.getCulturemode().equals("1")) {
                if (position == 1) {
                    holder.tx_cy.setText("Address" + " " + srn0);
                }
            } else {
                if (position == 1) {
                    holder.tx_cy.setText("العنوان " + "" + srn0);
                }
            }
            int selectedPosition = 0;

            if (colorred == true) {
                holder.proi.setVisibility(View.GONE);
                holder.tx_cancel.setTextColor(context.getColor(R.color.reject_color));

            } else {
                holder.proi.setVisibility(View.VISIBLE);

                holder.tx_cancel.setTextColor(context.getColor(R.color.supplier_color));

            }
            if (holder.tx_cate.isChecked()) {
                holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));
            } else {
                holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));
            }

            holder.tx_cate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == mCheckedPostion) {
                        holder.tx_cate.setChecked(false);
                        //  lowest.username = "";
                        mCheckedPostion = -1;
                        String asd = bottom_list.get(position).getxCode();
                        //   holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));


                    } else {
                        // filte = true;
                        mCheckedPostion = position;
                        String asd = bottom_list.get(position).getxCode();
                        AddressName = bottom_list.get(position).getxName();
                        AddressXcode = bottom_list.get(position).getxCode();

                        //  holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));

                        //   StringGen.username = holder.poll_option.getText().toString();
                        // Toast.makeText(mycontext, "select : "+position+holder.poll_option.getText(), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }
            });




        /*holder.tx_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selectedPosition == position)
                    holder.tx_cate.setTextColor(Color.GREEN);
                else
                    holder.tx_cate.setTextColor(Color.BLACK);

                // bottom_list.get(position).setSelected(!bottom_list.get(position).isSelected());

               *//* if (bottom_list.get(position).isSelected()){
                    holder.tx_cate.setTextColor( bottom_list.get(position).isSelected() ? Color.GREEN);
                }else {
                    holder.tx_cate.setTextColor( Color.BLACK);

                }*//*
                //  holder.tx_cate.setTextColor(bottom_list.get(position).isSelected() ? Color.GREEN : Color.WHITE);
            }
        });*/


            holder.tx_cancel.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

        }

        @Override
        public int getItemCount() {
            return bottom_list.size();
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }


    }

}