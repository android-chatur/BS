package com.example.barayihsalem.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.BoothAdapter;
import com.example.barayihsalem.UI.Adapter.EqupmentBoothAdapter;
import com.example.barayihsalem.UI.Model.ExcelPojo;
import com.example.barayihsalem.UI.Model.NewPojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class UpcominiEvent_Download extends AppCompatActivity implements View.OnClickListener {
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    AppController  appController;
    AppPreferences appPreferences;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    APIInterface apiInterface;
    TextView btotal_table_value, equpmentr_value, btotal_chair_value,selectedammo,selectedammo_value, equpment, total_chair, booth_value, aboutbs1, bottobokkig, total_table;
    RelativeLayout abcd;
    Button btn_exel, btn_pdf;
    RecyclerView rw_past_comingevent, rw_up_comingevent;
    ImageView logoside;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    ArrayList<Row> Event_list = new ArrayList<Row>();
    ArrayList<Row> Equpment_list = new ArrayList<Row>();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall_UpcomingEvent = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            String event=Event_list.get(position).getSrNo();
            appPreferences.Savedetail_SRNo(event);
            startActivity(new Intent(UpcominiEvent_Download.this, Event_Details.class));
            finish();

        }

    };
    private long downloadReference;
    private DownloadManager downloadManager;

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_upcomini_event__download);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_upcomini_event__download_ar);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        findid();
        if (UpcominiEvent_Download.this.isNetworkConnected()) {

            new Get_Event_Settlement_Amount_By_SrNo().execute();
            new Get_My_UpComing_Event_Requirement_1_BySrNo().execute();
            new Get_My_UpComing_Event_Requirement_2_BySrNo().execute();
            new Get_My_UpComing_Event_Bookings_UserList().execute();


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
        btn_exel = findViewById(R.id.btn_exel);
        rw_past_comingevent = findViewById(R.id.rw_past_comingevent);
        rw_up_comingevent = findViewById(R.id.rw_up_comingevent);
        abcd = findViewById(R.id.abcd);
        aboutbs1 = findViewById(R.id.aboutbs1);
        bottobokkig = findViewById(R.id.bottobokkig);
        total_table = findViewById(R.id.total_table);
        booth_value = findViewById(R.id.booth_value);
        btotal_table_value = findViewById(R.id.btotal_table_value);
        total_chair = findViewById(R.id.total_chair);
        equpment = findViewById(R.id.equpment);
        btotal_chair_value = findViewById(R.id.btotal_chair_value);
        selectedammo_value = findViewById(R.id.selectedammo_value);
        equpmentr_value = findViewById(R.id.equpmentr_value);
        selectedammo = findViewById(R.id.selectedammo);
        btn_pdf = findViewById(R.id.btn_pdf);
        equpmentr_value.setTypeface(typebold);
        equpment.setTypeface(typebold);
        aboutbs1.setTypeface(typebold);
        selectedammo .setTypeface(typebold);
        total_chair.setTypeface(typebold);
        btotal_chair_value.setTypeface(typebold);
        total_table.setTypeface(typebold);
        booth_value.setTypeface(typebold);
        btn_exel.setTypeface(typeRegular);
        selectedammo_value.setTypeface(typebold);
        btn_pdf.setTypeface(typeRegular);
        btotal_table_value.setTypeface(typebold);
        bottobokkig.setTypeface(typebold);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        btn_exel.setOnClickListener(this);
        btn_pdf.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Acount_Info.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btn_exel:
                if (UpcominiEvent_Download.this.isNetworkConnected()) {


                    try {

                        Event_Details_PDF_Downlaod(appPreferences.get_Event_SRNo(), appPreferences.getuserid(), appPreferences.getCulturemode(), appPreferences.get_company_id());

                    } catch (Exception e) {
                        // This will catch any exception, because they are all descended from Exception
                        System.out.println("Error " + e.getMessage());
                        Toast.makeText(appController, "", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // Event_Details_Excel_Downlaod(appPreferences.get_Event_SRNo(), appPreferences.getuserid(), appPreferences.getCulturemode(), appPreferences.get_company_id());


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
                break;

            case R.id.btn_pdf:
                if (UpcominiEvent_Download.this.isNetworkConnected()) {


                    //  Event_Details_PDF_Downlaod(appPreferences.get_Event_SRNo(), appPreferences.getuserid(), appPreferences.getCulturemode(), appPreferences.get_company_id());
                    Event_PDF_Downlaod(appPreferences.get_Event_SRNo(), appPreferences.getuserid(), appPreferences.getCulturemode(), appPreferences.get_company_id());


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
                break;


        }
    }


    private void Event_PDF_Downlaod(String event_srNo, String getuserid, String culturemode, String company_id) {


        String geturl = "https://api.barayihsalem.com/api/BS/Event_Details_PDF_Downlaod/?Value=" + event_srNo + "&UserId=" + getuserid + "&CultureId=" + culturemode + "&CorpcentreBy=" + company_id + "";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(geturl));

        String Title = "" + event_srNo + ".pdf";
        request.setTitle(Title);
        request.setDescription("Download File Please wait...........");
        String Cookie = CookieManager.getInstance().getCookie(geturl);
        request.addRequestHeader("cookie", Cookie);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Title);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        Toast.makeText(UpcominiEvent_Download.this, "Downloas started", Toast.LENGTH_SHORT).show();


    }


    private void Event_Details_Excel_Downlaod(String event_srNo, String getuserid, String culturemode, String company_id) {

        apiInterface.Event_Details_Excel_Downlaod(event_srNo, getuserid, culturemode, company_id).enqueue(new Callback<ExcelPojo>() {


            @Override
            public void onResponse(Call<ExcelPojo> call, Response<ExcelPojo> response) {

                if (response.isSuccessful()) {
                    hideProgress();
/*

                                       Membership_list.addAll(response.body().getObj().liMem);
                    community_list.addAll(response.body().getObj().liComm);
                    Log.i(getClass().getName(), "list Size : " + Membership_list.size());

*/


                } else {
                    // TastyToast.makeText(MY_Account.this, response.body().getMsg(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);


                }
            }


            @Override
            public void onFailure(Call<ExcelPojo> call, Throwable t) {
                hideProgress();

                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }

    private void Event_Details_PDF_Downlaod(String event_srNo, String getuserid, String culturemode, String company_id) {


        String geturl = "https://api.barayihsalem.com/api/BS/Event_Details_Excel_Downlaod/?Value=" + event_srNo + "&UserId=" + getuserid + "&CultureId=" + culturemode + "&CorpcentreBy=" + company_id + "";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(geturl));

        String Title = "" + event_srNo + ".xlsx";
        request.setTitle(Title);
        request.setDescription("Download File Please wait...........");
        String Cookie = CookieManager.getInstance().getCookie(geturl);
        request.addRequestHeader("cookie", Cookie);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Title);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        Toast.makeText(UpcominiEvent_Download.this, "Downloas started", Toast.LENGTH_SHORT).show();


    }


    private void showProgress() {
        progressDialog = new ProgressDialog(UpcominiEvent_Download.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_My_UpComing_Event_Requirement_1_BySrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_UpComing_Event_Requirement_1_BySrNo','" + appPreferences.get_Event_SRNo() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_My_UpComing_Event_Requirement_1_BySrNo", response);
            //   progressDialog.dismiss();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);
                        ArrayList<NewPojo> newPojoArrayList = new ArrayList<>();
                        NewPojo newPojo = new NewPojo();

                        String Total_Booth = collegeData.getString("Total_Booth");
                        String Booked_Booth = collegeData.getString("Booked_Booth");
                        String NoOfTables = collegeData.getString("NoOfTables");
                        String NoOfChairs = collegeData.getString("NoOfChairs");
                        String Event_Name = collegeData.getString("Event_Name");


                        booth_value.setText(Booked_Booth + "" + Total_Booth);
                        btotal_table_value.setText(NoOfTables);
                        btotal_chair_value.setText(NoOfChairs);
                        equpmentr_value.setText(Event_Name);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }

    }
    private class Get_Event_Settlement_Amount_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_Event_Settlement_Amount_By_SrNo','" + appPreferences.get_Event_SRNo() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Event_Settlement_Amount_By_SrNo", response);
            //   progressDialog.dismiss();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);
                        ArrayList<NewPojo> newPojoArrayList = new ArrayList<>();
                        NewPojo newPojo = new NewPojo();

                        Double Amount = collegeData.getDouble("Amount");
                        String Mem_Price_ = String.format("%.3f", Amount);
                        selectedammo_value.setText(Mem_Price_+" "+"KD");


//String Mem_Price_ = String.format("%.3f", Event_Price);
//                        booth_value.setText(Booked_Booth + "" + Total_Booth);
//                        btotal_table_value.setText(NoOfTables);
//                        btotal_chair_value.setText(NoOfChairs);
//                        equpmentr_value.setText(Event_Name);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }

    }

    private class Get_My_UpComing_Event_Requirement_2_BySrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_UpComing_Event_Requirement_2_BySrNo','" + appPreferences.get_Event_SRNo() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_My_UpComing_Event_Requirement_2_BySrNo", response);
            //   progressDialog.dismiss();


            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);

            if (bsNewsPojo.success.equals(1)) {

                Equpment_list.addAll(bsNewsPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_up_comingevent.setLayoutManager(gridLayoutManager);
                EqupmentBoothAdapter membeship_adapter = new EqupmentBoothAdapter(UpcominiEvent_Download.this, Equpment_list);
                rw_up_comingevent.setAdapter(membeship_adapter);
            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }

        }

    }

    private class Get_My_UpComing_Event_Bookings_UserList extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_UpComing_Event_Bookings_UserList','" + appPreferences.get_Event_SRNo() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_My_UpComing_Event_Bookings_UserList", response);
            //   progressDialog.dismiss();

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);

            if (bsNewsPojo.success.equals(1)) {

                Event_list.addAll(bsNewsPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_past_comingevent.setLayoutManager(gridLayoutManager);
                BoothAdapter membeship_adapter = new BoothAdapter(UpcominiEvent_Download.this, Event_list, onClickCall_UpcomingEvent);
                rw_past_comingevent.setAdapter(membeship_adapter);
            } else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }

        }

    }
}