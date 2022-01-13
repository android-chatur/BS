package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Alredy_post_Adapter;
import com.example.barayihsalem.UI.Adapter.TermsAndConditionListAdapter;
import com.example.barayihsalem.UI.Model.Boost_Your_Sales;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.TemsandCond;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class BO_BootYourSales extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs;
    AppController appController;
    String Company_name, Person_Name, Email, Mobile_num;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    APIInterface apiInterface;

    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    RelativeLayout abcd;
    CheckBox tems_cond;
    TextView txt_full_name, txt_gender, txt_phone, txt_email, txt_nationality, txt_register_service;
    ImageView logoside, twitter_l;
    Alredy_post_Adapter alredy_post_adapter;
    Button proceed;
    boolean adb = false;
    RecyclerView rw_alredypost, rw_comminity;
    LinearLayout lin_newpost, lin_newpos_, lin_tq, lin_newpos;
    EditText editText_volun_name, editText_last_name, editText_mobile, editText_email;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    LinearLayoutManager layoutManager;
    RecyclerView rw_vision, rw_checkbox;
    int termscodsize;
    int checkvalue=0;
    TermsAndConditionListAdapterBO termsAndConditionListAdapter;

    ArrayList<List> Vision_list = new ArrayList<List>();
    ArrayList<Row> Terms_list = new ArrayList<Row>();

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            //   if (Menu_list.get(position).getxCode().equals("Drw_2_01")) {
           /* appPreferences.SaveDrawerXcode_Sub(Menu_list.get(position).getxCode());
            startActivity(new Intent(AboutBSClub.this, What_is_Club.class));
            finish();*/


            Toast.makeText(appController, "checkbox", Toast.LENGTH_SHORT).show();
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
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_b_o__boot_your_sales);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_b_o__boot_your_sales_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

        if (BO_BootYourSales.this.isNetworkConnected()) {
            new Get_Terms_Conditions_List().execute();

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



    apiInterface = APIClient.getClient().create(APIInterface.class);


        tems_cond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    tems_cond.setSelected(isChecked);
                    adb = true;

                } else {
                    tems_cond.setSelected(false);
                    adb = false;

                }
            }
        });


      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_alredypost.setLayoutManager(gridLayoutManager);
        alredy_post_adapter = new Alredy_post_Adapter(BO_BootYourSales.this);
        rw_alredypost.setAdapter(alredy_post_adapter);*/

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    private void findid() {
        abcd = findViewById(R.id.abcd);
        rw_checkbox = findViewById(R.id.rw_checkbox);
        tems_cond = findViewById(R.id.tems_cond);
        txt_full_name = findViewById(R.id.txt_full_name);
        txt_gender = findViewById(R.id.txt_gender);
        txt_phone = findViewById(R.id.txt_phone);
        logoside = findViewById(R.id.logoside);
        twitter_l = findViewById(R.id.twitter_l);

        aboutbs = findViewById(R.id.aboutbs);


        proceed = findViewById(R.id.proceed);

        editText_volun_name = findViewById(R.id.editText_volun_name);
        editText_last_name = findViewById(R.id.editText_last_name);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_email = findViewById(R.id.editText_email);
        txt_email = findViewById(R.id.txt_email);

        aboutbs.setTypeface(typebold);


        txt_phone.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        txt_full_name.setTypeface(typeRegular);
        tems_cond.setTypeface(typeRegular);
        txt_email.setTypeface(typeRegular);
        editText_last_name.setTypeface(typeRegular);

        editText_last_name.setText(appPreferences.getfirstname());
        editText_mobile.setText(appPreferences.get_mobe());
        editText_email.setText(appPreferences.get_email());

        logoside.setOnClickListener(this);

        proceed.setOnClickListener(this);
        aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
        twitter_l.setImageResource((R.drawable.supplir_image));
        twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BO_BootYourSales.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.proceed:


                if (BO_BootYourSales.this.isNetworkConnected()) {
                    if (Validate()) {
                        if (adb == true) {

                            showProgress();
                            Save_Boost_Your_Sales();
                        } else {
                            Toast.makeText(appController, "Please Agree Terms and Conditions ", Toast.LENGTH_SHORT).show();

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
                   /* startActivity(new Intent(this, Select_Payment_supplierActivity.class));
                    finish();*/

                }


                break;


        }
    }

    private void Save_Boost_Your_Sales() {
        Boost_Your_Sales boost_your_sales = new Boost_Your_Sales();

        boost_your_sales.setUserId(appPreferences.getuserid());
        boost_your_sales.setMem_SrNo(appPreferences.get_membership_srno());


        boost_your_sales.setCompanyName(Company_name);
        boost_your_sales.setName(Person_Name);
        boost_your_sales.setMobile(Mobile_num);
        boost_your_sales.setEmail(Email);
        boost_your_sales.setSource("Android");
        boost_your_sales.setCultureId(appPreferences.getCulturemode());
        boost_your_sales.setCorpcentreby(appPreferences.get_company_id());
        boost_your_sales.setUniqueId(sessionManager.GetUniqueId());
        boost_your_sales.setIpAddress(sessionManager.GetIpAddress());
        boost_your_sales.setCommand("Save");


        Call<Boost_Your_Sales> call2 = apiInterface.Save_Boost_Your_Sales(boost_your_sales);
        call2.enqueue(new Callback<Boost_Your_Sales>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Boost_Your_Sales> call, Response<Boost_Your_Sales> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        String Attribute1 = response.body().getAttribute1();
                        String TrackId = response.body().getTrackId();

                        appPreferences.save_Attribute_Supp(Attribute1);
                        appPreferences.save_track_id(TrackId);

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(BO_BootYourSales.this, Payment_Activity_Supplier.class));
                        finish();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-6")) {

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
            public void onFailure(Call<Boost_Your_Sales> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(BO_BootYourSales.this);
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
        Company_name = editText_volun_name.getText().toString();
        Person_Name = editText_last_name.getText().toString();
        Mobile_num = editText_mobile.getText().toString();
        Email = editText_email.getText().toString();


        if (TextUtils.isEmpty(editText_volun_name.getText().toString())) {
            editText_volun_name.setError("Please Enter Company Name");
            editText_volun_name.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_last_name.getText().toString())) {
            editText_last_name.setError("Please Enter Name");
            editText_last_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Please Enter Mobile");
            editText_mobile.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;


        }


        return true;
    }


    private class Get_Terms_Conditions_List extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Terms_Conditions_List','','Bsales','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("Get_ContentPage_Menu", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Terms_list.addAll(partnerPojo.row);
                termscodsize = Terms_list.size();

                Log.d("size123", String.valueOf(termscodsize));

                termsAndConditionListAdapter = new TermsAndConditionListAdapterBO("sales", BO_BootYourSales.this, Terms_list);
                layoutManager = new LinearLayoutManager(BO_BootYourSales.this, LinearLayoutManager.VERTICAL, false);
                rw_checkbox.setLayoutManager(layoutManager);
                rw_checkbox.setAdapter(termsAndConditionListAdapter);


            } else {
                //    Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class TermsAndConditionListAdapterBO extends RecyclerView.Adapter<TemsandCond> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;

        ArrayList<Row> menu_list = new ArrayList<>();
        AppPreferences appPreferences;
        String resi;
        private DialogTermsAndConditions dialogTermsAndConditions;
        private Context context;
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public TermsAndConditionListAdapterBO(String resi, Context context, ArrayList<Row> menu_list) {
            this.resi = resi;
            this.context = context;
            this.menu_list = menu_list;


        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public TemsandCond onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tems_list, parent, false);
            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tems_list, parent, false);

            }
            return new TemsandCond(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TemsandCond holder, int position) {

            if (resi.equals("resi")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.recident_color));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.recident_color));
            }


            if (resi.equals("bussi")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
            }
            if (resi.equals("passi")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.reject_color));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.reject_color));
            }
            if (resi.equals("suppli")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.supplier_color));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.supplier_color));
            }
            if (resi.equals("sales")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.supplier_color));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.supplier_color));
            }

            if (resi.equals("create")) {
                holder.txtlable.setTextColor(context.getResources().getColor(R.color.black));
                holder. aboutbs.setTextColor(context.getResources().getColor(R.color.black));
            }

            holder.txtlable.setText(menu_list.get(position).getxName());

            holder. aboutbs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                    if (isChecked == true) {
                        tems_cond.setSelected(isChecked);
                        // adb = true;
                        checkvalue++;

                    } else {
                        tems_cond.setSelected(false);
                        //  adb = false;
                        checkvalue--;

                    }
                    if (termscodsize==checkvalue){
                        adb=true;
                    }else {
                        adb=false;
                    }

                }
            });


            holder.txtlable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (dialogTermsAndConditions != null && dialogTermsAndConditions.isShowing())
                        dialogTermsAndConditions.dismiss();
                    dialogTermsAndConditions = new DialogTermsAndConditions(context, menu_list.get(position).getxLink(), menu_list.get(position).getxCode());
                    dialogTermsAndConditions.show();


                    //  new Get_ContentPage_Data(context,menu_list.get(position).getxLink(),menu_list.get(position).getxCode()).execute();
                }
            });


        }


        @Override
        public int getItemCount() {
            return menu_list.size();
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