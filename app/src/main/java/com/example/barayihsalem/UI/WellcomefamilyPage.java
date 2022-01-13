package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Community_Adapter;
import com.example.barayihsalem.UI.Adapter.Membeship_Adapter;
import com.example.barayihsalem.UI.Model.LiComm;
import com.example.barayihsalem.UI.Model.LiMem;
import com.example.barayihsalem.UI.Model.WellcomePojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
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
import java.util.Random;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class WellcomefamilyPage extends AppCompatActivity implements View.OnClickListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public static boolean selectcommunity = false;
    TextView txtlable, txtlable1, txtla, txtla_, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    String ipaddress;
    String Uniqueids;
    ImageView img_change_lang, logoside, twitter_l;
    Button btnLogin;
    ProgressDialog progressDialog;
    RecyclerView rw_membership, rw_comminity;
    RelativeLayout abcd;
    ArrayList<LiMem> Membership_list = new ArrayList<com.example.barayihsalem.UI.Model.LiMem>();
    ArrayList<LiComm> community_list = new ArrayList<com.example.barayihsalem.UI.Model.LiComm>();
    EditText editText_email_phone, edit_pswd;
    Membeship_Adapter membeship_adapter;
    Community_Adapter community_adapter;
    String dbString2 = "[]";
    APIInterface apiInterface;

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


            if (Membership_list != null && Membership_list.size() > 0) {


                if (position == 0) {

                    if ((Membership_list.get(position).isDisable)) {

                        Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                    } else {

                        appPreferences.save_membership_srno(Membership_list.get(position).membershipSrNo);
                        appPreferences.save_showhompage("Vision partner");
                        startActivity(new Intent(WellcomefamilyPage.this, VisionPartner.class));
                        finish();
                    }


                }
                if (position == 1) {

                    if ((Membership_list.get(position).isDisable)) {
                        Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                    } else {
                        appPreferences.save_showhompage("Bussiness Owner");
                        appPreferences.save_membership_srno(Membership_list.get(position).membershipSrNo);

                        startActivity(new Intent(WellcomefamilyPage.this, Bussiness_Owner.class));
                        finish();
                    }

                }

                if (position == 2) {

                    if ((Membership_list.get(position).isDisable)) {
                        Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                    } else {

                        appPreferences.save_membership_srno(Membership_list.get(position).membershipSrNo);
                        appPreferences.save_showhompage("Recident");
                        startActivity(new Intent(WellcomefamilyPage.this, Resident.class));
                        finish();
                    }

                }


                if (position == 3) {

                    if ((Membership_list.get(position).isDisable)) {
                        Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                    } else {
                        appPreferences.save_membership_srno(Membership_list.get(position).membershipSrNo);
                        appPreferences.save_showhompage("passionate");
                        startActivity(new Intent(WellcomefamilyPage.this, Passionate_Visitor.class));
                        finish();
                    }
                }
                if (position == 4) {

                    if ((Membership_list.get(position).isDisable)) {
                        Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                    } else {
                        appPreferences.save_membership_srno(Membership_list.get(position).membershipSrNo);
                        appPreferences.save_showhompage("supplier");
                        startActivity(new Intent(WellcomefamilyPage.this, Supplier_.class));
                        finish();
                    }
                }
            }



          /*  if (animalNames != null && animalNames.size() > 0) {


            }*/


        }

    };


    OnItemClickListener.OnClickCallback onClickCall_commi = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            if (community_list != null && community_list.size() > 0) {

                if (selectcommunity == true) {


                    if (position == 0) {


                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {

                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());
                            appPreferences.save_showhompage("Entertainment");
                            startActivity(new Intent(WellcomefamilyPage.this, Entertainment_Community.class));
                            finish();
                        }


                    }

                    if (position == 1) {

                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());

                            appPreferences.save_showhompage("buusi retails");
                            startActivity(new Intent(WellcomefamilyPage.this, BuusiAndRetails.class));
                            finish();
                        }
                    }

                    if (position == 2) {
                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());
                            appPreferences.save_showhompage("service");
                            startActivity(new Intent(WellcomefamilyPage.this, Service_Details.class));
                            finish();
                        }
                    }

                    if (position == 3) {

                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());
                            appPreferences.save_showhompage("food_beve");
                            startActivity(new Intent(WellcomefamilyPage.this, Food_Beverages.class));
                            finish();
                        }

                    }
                    if (position == 4) {
                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());
                            appPreferences.save_showhompage("enviro");
                            startActivity(new Intent(WellcomefamilyPage.this, Enviromental_commu.class));
                            finish();
                        }

                    }
                    if (position == 5) {
                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());

                            appPreferences.save_showhompage("industrial");
                            startActivity(new Intent(WellcomefamilyPage.this, Industrial_Commu.class));
                            finish();
                        }
                    }
                    if (position == 6) {
                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_showhompage("creative");
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());

                            startActivity(new Intent(WellcomefamilyPage.this, Creative_Commu.class));
                            finish();
                        }
                    }
                    if (position == 7) {
                        if ((community_list.get(position).isDisable)) {

                            Toast.makeText(appController, "Alredy Selected", Toast.LENGTH_SHORT).show();

                        } else {
                            appPreferences.save_Community_srno(community_list.get(position).getCommunitySrNo());

                            appPreferences.save_showhompage("technology");
                            startActivity(new Intent(WellcomefamilyPage.this, Technology_Commu.class));
                            finish();
                        }
                    }
                } else {
                    Toast.makeText(appController, "Please Select first Membership....!", Toast.LENGTH_SHORT).show();
                }

            }
        }

    };

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

    /*    if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white)); //status bar or the time bar at the top
        }
*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_wellcomefamily_page);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_wellcomefamily_page_ar);

        }
        //  appPreferences.saveHomepage(true);

        find_id();
        apiInterface = APIClient.getClient().create(APIInterface.class);


        ipaddress = UUID.randomUUID().toString();
        Uniqueids = uniquepwd();

        if (sessionManager.GetIpAddress() != null) {
            if (sessionManager.GetIpAddress().equals("") || sessionManager.GetIpAddress().equals("null")) {
                sessionManager.SetIpAddress(ipaddress);
            }
        } else {
            sessionManager.SetIpAddress(ipaddress);
        }

        if (sessionManager.GetUniqueId() != null) {
            if (sessionManager.GetUniqueId().equals("") || sessionManager.GetUniqueId().equals("null")) {
                sessionManager.SetUniqueId(Uniqueids);
            }
        } else {
            sessionManager.SetUniqueId(Uniqueids);
        }


        if (WellcomefamilyPage.this.isNetworkConnected()) {


            new Get_Welcome_Page_Banner().execute();

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


        if (WellcomefamilyPage.this.isNetworkConnected()) {

            showProgress();
            //Get_Welcome_API();
            Get_Welcome_API(appPreferences.getuserid(), sessionManager.GetUniqueId(), appPreferences.getCulturemode(), appPreferences.get_company_id());
            //getHeroes();

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
        if (selectcommunity == true) {
            rw_comminity.setAlpha(1.0f);
            rw_comminity.setClickable(true);
            rw_comminity.setFocusable(true);

        } else {
            rw_comminity.setClickable(false);
            rw_comminity.setFocusable(false);
            rw_comminity.setAlpha(0.5f);

        }

    }

    public String uniquepwd() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }

    public String uniqueid() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }




    private void Get_Welcome_API(String getuserid, String uniqid, String culturemode, String company_id) {

        apiInterface.Get_Welcome_API(getuserid, uniqid, culturemode, company_id).enqueue(new Callback<WellcomePojo>() {


            @Override
            public void onResponse(Call<WellcomePojo> call, Response<WellcomePojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();

                    Membership_list.addAll(response.body().getObj().liMem);
                    community_list.addAll(response.body().getObj().liComm);
                    Log.i(getClass().getName(), "list Size : " + Membership_list.size());
                    Log.i(getClass().getName(), "list Size commu : " + community_list.size());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                    rw_membership.setLayoutManager(gridLayoutManager);
                    membeship_adapter = new Membeship_Adapter(WellcomefamilyPage.this, Membership_list, onClickCall);
                    rw_membership.setAdapter(membeship_adapter);

                    //   sessionManager.SetUniqueId("");

                    GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                    rw_comminity.setLayoutManager(gridLayoutManager1);
                    community_adapter = new Community_Adapter(WellcomefamilyPage.this, community_list, onClickCall_commi);
                    rw_comminity.setAdapter(community_adapter);
                    community_adapter.notifyDataSetChanged();


                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);


                }
            }


            @Override
            public void onFailure(Call<WellcomePojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }


    private void showProgress() {
        progressDialog = new ProgressDialog(WellcomefamilyPage.this);
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

    private void find_id() {
        abcd = findViewById(R.id.abcd);
        twitter_l = findViewById(R.id.twitter_l);
        img_change_lang = findViewById(R.id.img_change_lang);
        logoside = findViewById(R.id.logoside);
        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        txtla_ = findViewById(R.id.txtla_);
        txtla = findViewById(R.id.txtla);
        img_change_lang.setOnClickListener(this);
        logoside.setOnClickListener(this);
      /*  txtlable.setTypeface(typeHeader);
        txtlable1.setTypeface(typeHeader);*/
        // txtla.setTypeface(typebold);
        txtla_.setTypeface(typebold);
        rw_membership = findViewById(R.id.rw_membership);
        rw_comminity = findViewById(R.id.rw_comminity);
    }

    @Override
    public void onBackPressed() {

        exit();
    }

    private void exit() {
        if (appPreferences.getCulturemode().equals("1")) {
            AlertDialog alertbox = new AlertDialog.Builder(this)

                    .setMessage("Do you want to exit Barayih Salem?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //DoNothing
                        }
                    })
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_launcher_background)
                    .setCancelable(false)
                    .show();
        } else {
            AlertDialog alertbox = new AlertDialog.Builder(this)

                    .setMessage("Do you want to exit Barayih Salem?")

                    .setPositiveButton("نعم فعلا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                        }
                    })
                    .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //DoNothing
                        }
                    })
                    .setTitle("ىخرج")
                    .setIcon(R.drawable.ic_launcher_background)
                    .setCancelable(false)
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_change_lang:
                changeLanguage();

                break;

            case R.id.logoside:
              /*  appPreferences.save_DrawerBack("welcome");

                startActivity(new Intent(WellcomefamilyPage.this, DraweActivity.class));
                finish();*/
                break;
        }
    }

    private void changeLanguage() {
        if (appController.isLangArebic()) {
            appController.changeLanguage(false);
            //   adss = false;
            startActivity(getIntent());
            finish();
        } else {
            appController.changeLanguage(true);
            // adss = false;

            startActivity(getIntent());
            finish();
        }

    }


    private class Get_Welcome_Page_Banner extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Welcome_Page_Banner','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("welcome_list", response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String SrNo = collegeData.getString("SrNo");
                        String Title = collegeData.getString("Tital");
                        String Description = collegeData.getString("Description");
                        String Image1 = collegeData.getString("Image1");
                        txtla.setTypeface(typebold);
                        txtla_.setTypeface(typebold);
                        txtla.setText(Title);
                        txtla_.setText(Description);
                        txtla.bringToFront();
                        txtla_.bringToFront();
                        Glide.with(getApplicationContext())
                                .load(getResources().getString(R.string.Imageurl) + Image1)

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
                                .into(twitter_l);
                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }
        }
    }
}