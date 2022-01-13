package com.example.barayihsalem.UI.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.Select_Payment_supplierActivity;
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

import static com.example.barayihsalem.UI.Select_Payment_supplierActivity.dxy;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class AditionalServices extends Fragment implements View.OnClickListener {
    SessionManager sessionManager;
    AppPreferences appPreferences;
    RecyclerView rw_allvision, rw_comminity;

    EditText editText_volun_name_do, editText_age_do, editText_mobile_do;
    RecyclerView rw_regi_servi, rw_additional;
    Addition_services_Adapter addition_services_adapter;
    ArrayList<Row> Addtyiona_service_List = new ArrayList<Row>();

    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;

    RelativeLayout abcd;
    TextView editText_area, mobi_num, email_num, email_num_;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ImageView twitter_l;
    TextView aboutbs, alredy_post, new_post, some_descri, price, tvmycarttotalvalue, item_name, item_price, unit, pack_unit;
    BottomSheetDialog dialog;
    Button btnLogin, proceed;

    View v;
    LinearLayoutManager layoutManager;
    OnItemClickListener.OnClickCallback onClickCall_addi = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            if (Addtyiona_service_List != null && Addtyiona_service_List.size() > 0) {
                dxy = "asd";

                appPreferences.saveServiceXcode(Addtyiona_service_List.get(position).xCode);

                //  showBottomSheetDialog_addi();
                //   startActivity(new Intent(getActivity(), Success_Page.class));
                startActivity(new Intent(getActivity(), Select_Payment_supplierActivity.class));
                getActivity().finish();
            }
/*
            if (position==0){
                startActivity(new Intent(WellcomefamilyPage.this,VisionPartner.class));
                finish();
            }*/


        }

    };

    private void showBottomSheetDialog_addi() {
        {
            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.addition_service_sheet_dialog, null);

            } else {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bottom_sheet_dialog, null);

            }

            twitter_l = view.findViewById(R.id.twitter_l);
            aboutbs = view.findViewById(R.id.aboutbs);
            some_descri = view.findViewById(R.id.some_descri);
            price = view.findViewById(R.id.price);
            proceed = view.findViewById(R.id.proceed);
            tvmycarttotalvalue = view.findViewById(R.id.tvmycarttotalvalue);
            aboutbs.setTypeface(typeHeader);

            price.setTypeface(typeHeader);
            some_descri.setTypeface(typeRegular);
            tvmycarttotalvalue.setTypeface(typeRegular);
            proceed.setTypeface(typeRegular);
            proceed.setOnClickListener(this);

            dialog = new BottomSheetDialog(getActivity());
            dialog.setContentView(view);
            dialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        appPreferences = new AppPreferences(getActivity());
        sessionManager = new SessionManager(getActivity());

        // View v;

        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            v = inflater.inflate(R.layout.fragment_additional_services, parent, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            v = inflater.inflate(R.layout.fragment_additional_services, parent, false);


        }

        // apiInterface = APIClient.getClient().create(APIInterface.class);

        return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findid(v);

        if (AditionalServices.this.isNetworkConnected()) {

            new Get_Additional_Service_List().execute();
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
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid(View v) {
        rw_additional = v.findViewById(R.id.rw_additional);
        abcd = v.findViewById(R.id.abcd);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }


    private class Get_Additional_Service_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* progressDialog = new ProgressDialog(BecomeResident_member.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);*/
            // progressDialog.show();
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
                /*"+appPreferences.get_membership_srno()+"*/
                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Additional_Service_List','" + appPreferences.get_membership_srno() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("registerd", response);


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Addtyiona_service_List.addAll(partnerPojo.row);


                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_additional.setLayoutManager(gridLayoutManager);
                addition_services_adapter = new Addition_services_Adapter(getActivity(), onClickCall_addi, Addtyiona_service_List);
                rw_additional.setAdapter(addition_services_adapter);

            } else {
                Toast.makeText(getContext(), partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}

