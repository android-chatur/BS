package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Event_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.barayihsalem.UI.Comming_Events.eventback;
import static com.example.barayihsalem.UI.HomeActivity.boothback;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Booth_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, soso_event, abou_app, settings, offer, notifi, reach_help, oprated;
    AppController appController;
    AppPreferences appPreferences;
    com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    ArrayList<Row> Booth_List_list = new ArrayList<Row>();
    String formattedDateasd = "";
    Button btnLogin;
    RecyclerView rw_eventlist, rw_comminity;
    RelativeLayout abcd;
    Event_Adapter event_adapter;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    LinearLayout kuwait_event;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            if (Booth_List_list != null && Booth_List_list.size() > 0) {

                appPreferences.saveComingEventSRNO(Booth_List_list.get(position).getSrNo());
                eventback = false;
                startActivity(new Intent(Booth_Activity.this, Booth_Event_Activity.class));
                finish();
            }
          /*  startActivity(new Intent(getActivity(), Enter_Address.class));
            getActivity().finish();*/

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
            setContentView(R.layout.activity_booth_);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_booth_ar);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String formattedDate = df.format(c);
        SimpleDateFormat dfbak = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        formattedDateasd = dfbak.format(c);

        Log.d("formattedDate", formattedDate);
        Log.d("formattedDateasd", formattedDateasd);
        findid();
        calendarView. setDateSelected(CalendarDay.today(), true);
        Calendar calendar = Calendar.getInstance();
        calendarView.state().edit().setMinimumDate(calendar.getTime()).commit();

       // calendarView.setMinDate(System.currentTimeMillis() - 1000);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date12, boolean selected) {


                String date1 = String.valueOf(date12.getDate()); //new current date
//date is changed on real click...do things..
                String dateStr = date1;
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy",   Locale.ENGLISH);
                Date date = null;
                try {
                    date = (Date) formatter.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date);

                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(date);
                String formatedDate = cal.get(java.util.Calendar.YEAR) + "-" + (cal.get(java.util.Calendar.MONTH) + 1) + "-" + cal.get(java.util.Calendar.DATE);
                System.out.println("formatedDate12 : " + formatedDate);
                Booth_List_list.clear();
                formattedDateasd = formatedDate;
                Booth_List_list.clear();
                new Get_ComingEvent_List_By_Date().execute();
                new Get_ComingEvent_Dates_List().execute();

                //Log.d("awe",date1);
            }
        });


        if (Booth_Activity.this.isNetworkConnected()) {
            new Get_ComingEvent_Dates_List().execute();
            new Get_ComingEvent_List_By_Date().execute();

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

    @Override
    public void onBackPressed() {

        if (boothback == true) {

            startActivity(new Intent(Booth_Activity.this, HomeActivity.class));
            finish();

        } else {
            startActivity(new Intent(Booth_Activity.this, Events_activity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(Booth_Activity.this, HomeActivity.class));
                finish();
                break;

            case R.id.kuwait_event:
             /*   eventback=false;
                startActivity(new Intent(Booth_Activity.this, Booth_Event_Activity.class));
                finish();*/
                break;

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }


    private void findid() {
        rw_eventlist = findViewById(R.id.rw_eventlist);
        calendarView = findViewById(R.id.calendarView);
        abou_app = findViewById(R.id.abou_app);
        soso_event = findViewById(R.id.soso_event);
        aboutbs = findViewById(R.id.aboutbs);
        kuwait_event = findViewById(R.id.kuwait_event);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        kuwait_event.setOnClickListener(this);
        aboutbs.setTypeface(typebold);
        soso_event.setTypeface(typebold);
        abou_app.setTypeface(typebold);
    }

    private class Get_ComingEvent_Dates_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ComingEvent_Dates_List','" + formattedDateasd + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("ComingEvent_Dates_Lis", response);
            //  progressDialog.dismiss();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            List<CalendarDay> events = new ArrayList<>();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String SrNo = collegeData.getString("SrNo");
                        String Date = collegeData.getString("Date");
                        String Comm_Color = collegeData.getString("Comm_Color");
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        CalendarDay day = CalendarDay.from(date);

                        events.add(day);
                        //  events.add(day);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

            EventDecorator eventDecorator = new EventDecorator(Color.RED, events);
            calendarView.addDecorator(eventDecorator);
        }
    }

    private class Get_ComingEvent_List_By_Date extends AsyncTask<String, String, String> {
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
                //  builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ComingEvent_List_By_Date','" + formattedDateasd + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ComingEvent_List_By_Date','" + formattedDateasd + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("ComingEvent_List_By_Date", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Booth_List_list.addAll(partnerPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_eventlist.setLayoutManager(gridLayoutManager);
                event_adapter = new Event_Adapter(Booth_Activity.this, onClickCall, Booth_List_list);
                rw_eventlist.setAdapter(event_adapter);

            } else {
                rw_eventlist.setAdapter(null);
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}