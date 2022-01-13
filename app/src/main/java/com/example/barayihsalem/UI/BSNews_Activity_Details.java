package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.BSNews_Adapter;
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

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.example.barayihsalem.UI.Opportunity_ForYou.oppo;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class BSNews_Activity_Details extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_club, aboutbs_Event, settings, offer, notifi, reach_help, abou_app, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside, ti;
    Button btnLogin;
    BSNews_Adapter bsNews_adapter;
    RecyclerView rw_bsnews;
    TextView auther, see_all_news, auther1;

    RelativeLayout abcd;
    EditText editText_work, editText_title, editText_job;
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
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_b_s_news___details);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_b_s_news___details_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        if (appPreferences.getCulturemode().equals("1")) {
            if (oppo == true) {
                aboutbs.setText("Opportunities for you");

            } else {
                aboutbs.setText("BS News");

            }
        } else {
            if (oppo == true) {
                aboutbs.setText("لفرص بالنسبة لك");

            } else {
                aboutbs.setText("بي إس نيوز");

            }


        }
        if (BSNews_Activity_Details.this.isNetworkConnected()) {

            new Get_BS_News_Details_BySrNo().execute();

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
        logoside = findViewById(R.id.logoside);
        aboutbs = findViewById(R.id.aboutbs);
        ti = findViewById(R.id.ti);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        rw_bsnews = findViewById(R.id.rw_bsnews);
        auther = findViewById(R.id.auther);
        see_all_news = findViewById(R.id.see_all_news);
        auther1 = findViewById(R.id.auther1);
        auther.setTypeface(typeSemibold);
        see_all_news.setTypeface(typebold);
        auther1.setTypeface(typeRegular);
    }

    @Override
    public void onBackPressed() {

        if (oppo == true) {
            startActivity(new Intent(BSNews_Activity_Details.this, Opportunity_ForYou.class));
            finish();
        } else {

            startActivity(new Intent(BSNews_Activity_Details.this, BSNews_Activity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();

                break;
        }
    }

    private class Get_BS_News_Details_BySrNo extends AsyncTask<String, String, String> {
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
                String user_id;
                if (appPreferences.get_Bs_newsBack().equals("Home")) {
                    user_id = appPreferences.getuserid();
                } else {
                    user_id = "0";
                }
                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BS_News_Details_BySrNo','" + appPreferences.getNewsSrno() + "','','','','','','" + user_id + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Bs_details", response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String SrNo = collegeData.getString("SrNo");
                        String Header = collegeData.getString("Header");
                        String Description_Short = collegeData.getString("Description_Short");
                        // String Author = collegeData.getString("Author");
                        String Image = collegeData.getString("Image");
                        String News_Date = collegeData.getString("News_Date");

                        see_all_news.setText(Header);
                        auther1.setText(Description_Short);
                        if (appPreferences.getCulturemode().equals("1")) {


                            auther.setText("Author : " + collegeData.getString("Author") + ",  " + "Date : " + News_Date);
                        } else {
                            auther.setText("تأليف : " + collegeData.getString("Author") + ",  " + "تاريخ  : " + News_Date);

                        }
                        Glide.with(getApplicationContext())
                                .load(getResources().getString(R.string.Imageurl_bsnews) + Image)

                                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }


                                })
                                .transition(withCrossFade())
                                .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(ti);
                    }
                }
            } catch (JSONException e) {


            }

        }
    }
}
