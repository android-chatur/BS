package com.example.barayihsalem.UI;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Adapter.Contant_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class DialogTermsAndConditions extends Dialog implements View.OnClickListener {
    private Context context;
    private Bundle bundle;
    AppPreferences appPreferences;
    RecyclerView rvTerms;
    String xlink,xcode;
    ArrayList<Row> Menu_list = new ArrayList<Row>();
    LinearLayoutManager layoutManager;

    public DialogTermsAndConditions(Context context, String xlink, String xCode) {


        super(context, R.style.MaterialDialog);
        try {
            this.context = context;
            this.xlink = xlink;
            this.xcode = xCode;
            appPreferences = new AppPreferences(context);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (appPreferences.getCulturemode().equals("1")) {
                setContentView(R.layout.dialog_terms_and_condition_confirm);
            } else {
                setContentView(R.layout.dialog_terms_and_condition_confirm_ar);
            }

            this.getWindow().setGravity(Gravity.CENTER);
            this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            new Get_ContentPage_Data().execute();

            rvTerms = findViewById(R.id.rv_terms);
            ImageView ivCloseTerms = findViewById(R.id.iv_close_terms);
            ivCloseTerms.setOnClickListener(this);

            Button btnCloseDialog = findViewById(R.id.btn_close_dialog);
            btnCloseDialog.setOnClickListener(this);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close_dialog:
                dismiss();
                break;
            case R.id.iv_close_terms:
                dismiss();
                break;
        }
    }

    private class Get_ContentPage_Data extends AsyncTask<String, String, String> {
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
                url = new URL(context.getResources().getString(R.string.GetMainurl));

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

                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ContentPage_Data','" + xlink+ "','" + xcode + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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

                Menu_list.addAll(partnerPojo.row);

                Contant_Adapter brand_page_adapter = new Contant_Adapter(context, Menu_list);
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rvTerms.setLayoutManager(layoutManager);
                rvTerms.setAdapter(brand_page_adapter);
            } else {
               // Toast.makeText(context, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}