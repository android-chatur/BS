package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Passionate_Adapter;
import com.example.barayihsalem.UI.Adapter.Supplier_Adapter;
import com.example.barayihsalem.UI.Adapter.TermsAndConditionListAdapter;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.MembershiPojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.TemsandCond;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
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

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Supplier_ extends YouTubeBaseActivity implements View.OnClickListener {
    TextView txtlable, txtlable1, txtlable2, txtForgot, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    Button btnLogin;
    boolean adb = false;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    ImageView logoside;
    String youtube_Url="";
    YouTubePlayerView player;

    private final String API_KEY = "https://youtu.be/";
    CheckBox tems_cond;
    EditText editText_email_phone, edit_pswd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Supplier_Adapter supplier_adapter;
    APIInterface apiInterface;
    ArrayList<List> Supplier_list = new ArrayList<List>();
    ArrayList<Row> Terms_list = new ArrayList<Row>();
    LinearLayoutManager layoutManager;
    RecyclerView rw_checkbox;
    int termscodsize;
    int checkvalue=0;
    TermsAndConditionListAdapterBO termsAndConditionListAdapter;
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

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.supplier_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.supplier_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_supplier_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_supplier_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        findid();

        if (Supplier_.this.isNetworkConnected()) {
            new Get_Terms_Conditions_List().execute();

            showProgress();
            Get_Membership_Details_API(appPreferences.get_membership_srno(), appPreferences.getuserid(), sessionManager.GetUniqueId(), appPreferences.getCulturemode(), appPreferences.get_company_id());

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




    }

    private void Get_Membership_Details_API(String membership_srno, String getuserid, String getUniqueId, String culturemode, String company_id) {

        apiInterface.Get_Membership_Details_API(membership_srno, getuserid, getUniqueId, culturemode, company_id).enqueue(new Callback<MembershiPojo>() {


            @Override
            public void onResponse(Call<MembershiPojo> call, Response<MembershiPojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();

                    Supplier_list.addAll(response.body().list);

                    youtube_Url = response.body().details.getVideoURL();

                    txtlable.setText(response.body().details.getName());
                    player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if (!b) {
                                if (youtube_Url != null|| !youtube_Url.isEmpty()) {
                                    youTubePlayer.cueVideo(youtube_Url);
                                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                                } else {

                                }

                            }
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });


                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                    rw_vision.setLayoutManager(gridLayoutManager);
                    supplier_adapter = new Supplier_Adapter(Supplier_.this,Supplier_list);
                    rw_vision.setAdapter(supplier_adapter);

                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<MembershiPojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Supplier_.this);
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


    private void findid() {
        tems_cond = findViewById(R.id.tems_cond);
        abcd = findViewById(R.id.abcd);
        player = findViewById(R.id.player);
        rw_checkbox = findViewById(R.id.rw_checkbox);
        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(heding);
        rw_vision = findViewById(R.id.rw_vision);
        logoside = findViewById(R.id.logoside);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setTypeface(typeRegular);
        tems_cond.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        logoside.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Supplier_.this, WellcomefamilyPage.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (adb == true) {
                    startActivity(new Intent(Supplier_.this, BecomeSupplier.class));
                    finish();
                } else {

                    Toast.makeText(appController, "Please Agree Terms and Conditions ", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.logoside:
                onBackPressed();
                break;
        }
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

                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Terms_Conditions_List','','" + appPreferences.get_membership_srno() + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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

                termsAndConditionListAdapter = new TermsAndConditionListAdapterBO("suppli", Supplier_.this, Terms_list);
                layoutManager = new LinearLayoutManager(Supplier_.this, LinearLayoutManager.VERTICAL, false);
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