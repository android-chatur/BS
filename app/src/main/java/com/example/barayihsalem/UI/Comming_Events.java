package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
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
import android.widget.ListView;
import android.widget.RadioButton;
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

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Event_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;


public class Comming_Events extends AppCompatActivity implements View.OnClickListener {
    public static boolean eventback = false;
    TextView aboutbs, abou_app, soso_event;
    AppController appController;
    AppPreferences appPreferences;
    //  CalendarView calendarView;
    com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    RadioButton male, female;
    LinearLayout kuwait_event;
    BottomSheetDialog dialog;

    TextView message, no, yes;
    RelativeLayout abcd;
    RecyclerView rw_eventlist;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    //  RecyclerView rw_regi_servi, rw_additional;
    // private List<CalendarDay> events = new ArrayList<>();
    EditText editText_volun_name, editText_age, editText_mobile;
    ListView listView;
    String formattedDateasd = "";

    ArrayList<Row> Booth_List_list = new ArrayList<Row>();
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Event_Adapter event_adapter;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            if (Booth_List_list != null && Booth_List_list.size() > 0) {
                appPreferences.saveComingEventSRNO(Booth_List_list.get(position).getSrNo());

                eventback = true;
                startActivity(new Intent(Comming_Events.this, Booth_Event_Activity.class));
                finish();
            }


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

            setContentView(R.layout.activity_comming__events);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_comming__events_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;


        Date c = android.icu.util.Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String formattedDate = df.format(c);
        SimpleDateFormat dfbak = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        formattedDateasd = dfbak.format(c);

        Log.d("formattedDate", formattedDate);
        Log.d("formattedDateasd", formattedDateasd);

        findid();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date12, boolean selected) {





                   String date1 = String.valueOf(date12.getDate()); //new current date
//date is changed on real click...do things..
                String dateStr = date1;
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy",   Locale.ENGLISH);
                Date date = null;
                try {
                    date = (Date)formatter.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +         cal.get(Calendar.DATE);
                System.out.println("formatedDate12 : " + formatedDate);
                Booth_List_list.clear();
                formattedDateasd=formatedDate;
                Booth_List_list.clear();
                new Get_ComingEvent_Dates_List().execute();
                new Get_ComingEvent_List_By_Date().execute();

                //Log.d("awe",date1);
            }
        });





        if (Comming_Events.this.isNetworkConnected()) {
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


        if (appPreferences.get_commu_color().equals("entretaiment")) {

            aboutbs.setTextColor(getResources().getColor(R.color.reject_color));
            twitter_l.setImageResource((R.drawable.passionate));

        }
        if (appPreferences.get_commu_color().equals("bussiretail")) {

            aboutbs.setTextColor(getResources().getColor(R.color.buss_oner_color));
            twitter_l.setImageResource((R.drawable.bussiness_owner));


        }
        if (appPreferences.get_commu_color().equals("service")) {

            aboutbs.setTextColor(getResources().getColor(R.color.supplier_color));
            twitter_l.setImageResource((R.drawable.supplir_image));
            twitter_l.setColorFilter(getResources().getColor(R.color.supplier_color));


        }

        if (appPreferences.get_commu_color().equals("food_be")) {

            aboutbs.setTextColor(getResources().getColor(R.color.fb_color));
            twitter_l.setImageResource((R.drawable.vispart));

            twitter_l.setColorFilter(getResources().getColor(R.color.fb_color));
        }
        if (appPreferences.get_commu_color().equals("envirome")) {

            aboutbs.setTextColor(getResources().getColor(R.color.enviro_color));
            twitter_l.setImageResource((R.drawable.vispart));
            twitter_l.setColorFilter(getResources().getColor(R.color.enviro_color));

            //     twitter_l.setColorFilter(getResources().getColor(R.color.enviro_color), android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        if (appPreferences.get_commu_color().equals("industrial")) {

            aboutbs.setTextColor(getResources().getColor(R.color.indus_color));
            twitter_l.setImageResource((R.drawable.vispart));

            twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));
        }
        if (appPreferences.get_commu_color().equals("technology")) {

            aboutbs.setTextColor(getResources().getColor(R.color.tq_color));
            twitter_l.setImageResource((R.drawable.vispart));

            twitter_l.setColorFilter(getResources().getColor(R.color.tq_color));
        }


        if (appPreferences.get_commu_color().equals("creative")) {

            aboutbs.setTextColor(getResources().getColor(R.color.vispart_color));
            twitter_l.setImageResource((R.drawable.vispart));

            // twitter_l.setColorFilter(getResources().getColor(R.color.indus_color));
        }
        calendarView. setDateSelected(CalendarDay.today(), true);

       /* calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                Calendar cal1 = day.getCalendar();
                Calendar cal2 = Calendar.getInstance();

                return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                        && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                        && cal1.get(Calendar.DAY_OF_YEAR) ==
                        cal2.get(Calendar.DAY_OF_YEAR));
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.setBackgroundDrawable(ContextCompat.getDrawable(Comming_Events.this,R.drawable.selector));
            }
        });*/



        android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
        calendarView.state().edit().setMinimumDate(calendar.getTime()).commit();
                calendarView = findViewById(R.id.calendarView);




    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private List<java.util.Calendar> getDisabledDays() {
        java.util.Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(java.util.Calendar.DAY_OF_MONTH, 2);

        java.util.Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(java.util.Calendar.DAY_OF_MONTH, 1);

        java.util.Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(java.util.Calendar.DAY_OF_MONTH, 18);

        List<java.util.Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private java.util.Calendar getRandomCalendar() {
        Random random = new Random();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));

        return calendar;
    }


    private void findid() {
        calendarView = findViewById(R.id.calendarView);

        kuwait_event = findViewById(R.id.kuwait_event);
        abcd = findViewById(R.id.abcd);
        rw_eventlist = findViewById(R.id.rw_eventlist);
        soso_event = findViewById(R.id.soso_event);
        abou_app = findViewById(R.id.abou_app);
        abou_app.setTypeface(typebold);
        soso_event.setTypeface(typebold);
        logoside = findViewById(R.id.logoside);
        aboutbs = findViewById(R.id.aboutbs);
        twitter_l = findViewById(R.id.twitter_l);
        aboutbs.setTypeface(typebold);
        logoside.setOnClickListener(this);
        kuwait_event.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Comming_Events.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.kuwait_event:

               /* eventback = true;
                startActivity(new Intent(Comming_Events.this, Booth_Event_Activity.class));
                finish();*/
                break;

        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = Comming_Events.this.getAssets().open("row");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ComingEvent_Dates_List','" + formattedDateasd + "','"+appPreferences.get_Community_srno()+"','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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


            //  progressDialog.dismiss();


         /*   response = loadJSONFromAsset();
            try {
                JSONArray jArray = new JSONArray(response);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    String StartDate = jsonObject.getString("Date");
                    Date date = simpleDateFormat.parse(StartDate);

                    Log.d("Date ", "" + date);
                    CalendarDay day = CalendarDay.from(date);
                    events.add(day);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            EventDecorator eventDecorator = new EventDecorator(Color.RED, events);
            calendarView.addDecorator(eventDecorator);*/


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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_ComingEvent_List_By_Date','" + formattedDateasd + "','"+appPreferences.get_Community_srno()+"','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
                event_adapter = new Event_Adapter(Comming_Events.this, onClickCall, Booth_List_list);
                rw_eventlist.setAdapter(event_adapter);

            } else {
                rw_eventlist.setAdapter(null);
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }


}