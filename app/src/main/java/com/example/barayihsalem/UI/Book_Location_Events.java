package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Booth_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Adapter.TermsAndConditionListAdapter;
import com.example.barayihsalem.UI.Model.Create_Event_Pojo;
import com.example.barayihsalem.UI.Model.MyViewHolder_Booth;
import com.example.barayihsalem.UI.Model.MyViewHolder_Zone;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.TemsandCond;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.Model.ZonePojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.Create_Events.back;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Book_Location_Events extends AppCompatActivity implements View.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    TextView aboutbs, txt_full_name, txt_date, txt_gender, txt_nationality, txt_age, txt_voluntee, txt_phone, txt_ptem_con, txt_Event_Size;
    AppController appController;
    AppPreferences appPreferences;
    public static boolean back_event=false;
    SessionManager sessionManager;
    APIInterface apiInterface;
    String myFormat;
    ArrayList<Row> Event_list = new ArrayList<Row>();
    ArrayList<Row> Booth_List_list = new ArrayList<Row>();
    ArrayList<Row> Zone_List = new ArrayList<Row>();
    Nationality_Adapter nationality_adapter;
    ProgressDialog progressDialog;
    String myFormat2;
    String gaha;
    String gahaw;
    Spinner spi_event_type;
    String Age = "";
    String Event_Xcode = "";
    String From_date = "";
    String To_date = "";
    String gaha_dob;
    String gahaw_dob;
    AlertDialog alertDialog;
    RadioButton male, female;
    BottomSheetDialog dialog;
    TextView message, no, yes;
    ImageView logoside, twitter_l;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    RelativeLayout abcd;
    String Select_Zone = "";
    String Events_days = "";
    String Events_Mobile = "";
    String Events_Email = "";
    double Event_Price = 0.0;
    LinearLayoutManager layoutManager;
    RecyclerView rw_vision, rw_checkbox;
    boolean adb = false;

    int termscodsize;
    int checkvalue=0;
    TermsAndConditionListAdapterBO termsAndConditionListAdapter;

    ArrayList<com.example.barayihsalem.UI.Model.List> Vision_list = new ArrayList<com.example.barayihsalem.UI.Model.List>();
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
    String Events_IBAN = "";
    String Editext_booth_A = "", Editext_booth_C = "", Editext_booth_B = "";
    String xcode_booth_A = "", xcode_booth_C = "", xcode_booth_B = "";
    RecyclerView rw_selectzone, rw_Booth;
    Zone_Select_Adapter_Event zone_select_adapter;
    Booth_Adapter_Event booth_adapter;
    TextView txt_todate, txt_fdate, txt_select_zones, txt_end_date, txt_mobile_num, txt_email, _instalment_price, mont_instalment;
    EditText editText_events_days, editText_mobile_num, editText_email;

    //  RecyclerView rw_regi_servi, rw_additional;

    EditText editText_age, editText_mobile;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            // updateLabel_buss();
        }

    };
    DatePickerDialog.OnDateSetListener dateListener_dob = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            // updateLabel_dob();
            // updateLabel_buss();
        }

    };
    ArrayList<String> abc = new ArrayList<>();
    String zonadata = "";
    private List<ZonePojo> mModelList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel_dob() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        myFormat2 = "dd/MM/yyyy"; //In which you need put here
        android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat(myFormat, Locale.US);
        android.icu.text.SimpleDateFormat sdff = new SimpleDateFormat(myFormat2, Locale.US);


        gaha_dob = sdf.format(myCalendar.getTime());
        gahaw_dob = sdff.format(myCalendar.getTime());
        From_date = gaha_dob;
        txt_todate.setText(gahaw_dob);


        new Get_Event_Price_ByDates().execute();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        myFormat2 = "dd/MM/yyyy"; //In which you need put here
        android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat(myFormat, Locale.US);
        android.icu.text.SimpleDateFormat sdff = new SimpleDateFormat(myFormat2, Locale.US);


        gaha = sdf.format(myCalendar.getTime());
        gahaw = sdff.format(myCalendar.getTime());
        To_date = gaha;
        //   From_date = gaha;

        String outputDateStr_ = "";
        DateFormat inputFormat_ = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        DateFormat outputFormat_ = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
        String inputDateStr12 = To_date;
        Date date11 = null;
        try {
            date11 = inputFormat_.parse(inputDateStr12);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        outputDateStr_ = outputFormat_.format(date11);


        txt_fdate.setText(outputDateStr_);


        String date = To_date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        Date parseDate = null;
        try {
            parseDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate);
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(Events_days) - 1);
        //newDate.setText(format.format(cal.getTime()));
        Log.d("number", format.format(cal.getTime()));

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
        String inputDateStr = format.format(cal.getTime());
        Date date1 = null;
        try {
            date1 = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date1);


        if (Events_days.equals("1")) {
            From_date = To_date;
            txt_todate.setText(outputDateStr_);
        } else {
            From_date = format.format(cal.getTime());
            txt_todate.setText(outputDateStr);
        }


        new Get_Event_Price_ByDates().execute();

    }

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

            setContentView(R.layout.activity_book__location__events);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_book__location__events_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();


        editText_events_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Events_days = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Events_Mobile = appPreferences.get_mobe();
        Events_Email = appPreferences.get_email();
        editText_mobile_num.setText(Events_Mobile);
        editText_email.setText(Events_Email);
        apiInterface = APIClient.getClient().create(APIInterface.class);


        if (Book_Location_Events.this.isNetworkConnected()) {

            new Get_Event_Type_List_UserWise().execute();
            new Get_Booth_List().execute();
            new Get_Zone_List().execute();
            new Get_Event_Price_ByDates().execute();
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


    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private void findid() {

        spi_event_type = findViewById(R.id.spi_event_type);
        abcd = findViewById(R.id.abcd);
        txt_todate = findViewById(R.id.txt_todate);
        txt_fdate = findViewById(R.id.txt_fdate);
        rw_checkbox = findViewById(R.id.rw_checkbox);

        txt_select_zones = findViewById(R.id.txt_select_zones);
        editText_events_days = findViewById(R.id.editText_events_days);
        editText_mobile_num = findViewById(R.id.editText_mobile_num);
        editText_email = findViewById(R.id.editText_email);
        txt_email = findViewById(R.id.txt_email);
        mont_instalment = findViewById(R.id.mont_instalment);
        _instalment_price = findViewById(R.id._instalment_price);
        mont_instalment.setTypeface(typeRegular);
        _instalment_price.setTypeface(typeRegular);
        editText_mobile_num.setTypeface(typeRegular);
        txt_email.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        txt_todate.setTypeface(typeRegular);
        txt_fdate.setTypeface(typeRegular);

        editText_events_days.setTypeface(typebold);

        txt_fdate.setOnClickListener(this);
        txt_todate.setOnClickListener(this);
        aboutbs = findViewById(R.id.aboutbs);
        male = findViewById(R.id.male);
        rw_selectzone = findViewById(R.id.rw_selectzone);
        rw_Booth = findViewById(R.id.rw_Booth);
        female = findViewById(R.id.female);
        txt_full_name = findViewById(R.id.txt_full_name);
        txt_gender = findViewById(R.id.txt_gender);
        logoside = findViewById(R.id.logoside);
        txt_age = findViewById(R.id.txt_age);
        txt_voluntee = findViewById(R.id.txt_voluntee);
        txt_nationality = findViewById(R.id.txt_nationality);
        editText_age = findViewById(R.id.editText_age);
        editText_mobile = findViewById(R.id.editText_mobile);
        txt_phone = findViewById(R.id.txt_phone);
        txt_ptem_con = findViewById(R.id.txt_ptem_con);
        txt_Event_Size = findViewById(R.id.txt_Event_Size);
        proceed = findViewById(R.id.proceed);
        twitter_l = findViewById(R.id.twitter_l);
        txt_date = findViewById(R.id.txt_date);
        txt_full_name.setTypeface(typeRegular);
        male.setTypeface(typeRegular);
        txt_date.setTypeface(typeRegular);
        txt_Event_Size.setTypeface(typeRegular);
        female.setTypeface(typeRegular);
        txt_age.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        txt_voluntee.setTypeface(typeRegular);
        txt_phone.setTypeface(typeRegular);
        txt_nationality.setTypeface(typeRegular);

        editText_age.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        txt_ptem_con.setTypeface(typeRegular);
        aboutbs.setTypeface(typebold);

        logoside.setOnClickListener(this);
        proceed.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Book_Location_Events.this, Events_activity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.proceed:
                back = false;
                if (Book_Location_Events.this.isNetworkConnected()) {
                    if (validate()) {
                        if (!zonadata.equals("") || !zonadata.isEmpty()) {
                            if (!To_date.equals("") || !To_date.isEmpty()) {

                                if (adb == true) {
                                    showProgress();
                                    Save_Create_Event();
                                }else {
                                    Toast.makeText(appController, "Please Agree Terms and Conditions ", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(appController, "Please Select start date", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(appController, "Please Select Area of Event Size", Toast.LENGTH_SHORT).show();

                        }
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


               /* startActivity(new Intent(this, Commu_Success.class));
                finish();*/
                break;


            case R.id.txt_fdate:

                if (!Events_days.isEmpty() || !Events_days.equals("") || !Events_days.equals("")) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                    //following line to restrict future date selection
                    //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
                } else {
                    Toast.makeText(appController, "First enter event of days", Toast.LENGTH_SHORT).show();

                }

                break;


            case R.id.txt_todate:
               /* DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, dateListener_dob, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog1.show();*/
                break;

        }
    }

    private boolean validate() {
        Events_days = editText_events_days.getText().toString();
        Events_Mobile = editText_mobile_num.getText().toString();
        Events_Email = editText_email.getText().toString();
        Events_IBAN = editText_mobile.getText().toString();

        if (Editext_booth_A.equals("") || Editext_booth_A.isEmpty()) {
            Toast.makeText(appController, "Please Enter Value of Booth A", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Editext_booth_B.equals("") || Editext_booth_B.isEmpty()) {
            Toast.makeText(appController, "Please Enter Value of Booth B", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Editext_booth_C.equals("") || Editext_booth_C.isEmpty()) {
            Toast.makeText(appController, "Please Enter Value of Booth Bring my own Booth", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editText_mobile_num.getText().toString())) {
            editText_mobile_num.setError("Please Enter Event Planner Phone Number");
            editText_mobile_num.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_events_days.getText().toString())) {
            editText_events_days.setError("Please Enter Event day");

            editText_events_days.requestFocus();
            return false;
        }


        if (editText_events_days.getText().toString().equals("0")) {
            /*  if(editText_mobile_num.getText().toString().equals("0")){*/
            editText_events_days.setError("Please Enter correct value");
            //    }

            editText_events_days.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your Event Planner E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;


        }
        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Please Enter IBAN Number");
            editText_mobile.requestFocus();
            return false;
        }
        return true;
    }

    private void Save_Create_Event() {
        Create_Event_Pojo create_event_pojo = new Create_Event_Pojo();

        create_event_pojo.setUserId(appPreferences.getuserid());
        create_event_pojo.setComm_SrNo(Event_Xcode);
        create_event_pojo.setEventType(Event_Xcode);
        create_event_pojo.setBoothType_A(xcode_booth_A);
        create_event_pojo.setBoothType_A_Size(Editext_booth_A);
        create_event_pojo.setBoothType_B(xcode_booth_B);
        create_event_pojo.setBoothType_B_Size(Editext_booth_B);
        create_event_pojo.setBoothType_C(xcode_booth_C);
        create_event_pojo.setBoothType_C_Size(Editext_booth_C);
        create_event_pojo.setEventSize_Zone(zonadata);
        create_event_pojo.setDays(Events_days);
        create_event_pojo.setSDate(To_date);
        create_event_pojo.setEDate(From_date);
        create_event_pojo.setEventPlanner_Mobile(Events_Mobile);
        create_event_pojo.setEventPlanner_Email(Events_Email);
        create_event_pojo.setIBAN(Events_IBAN);
        create_event_pojo.setPrice(Event_Price);
        create_event_pojo.setSource("Android");
        create_event_pojo.setCorpcentreby(appPreferences.get_company_id());
        create_event_pojo.setIpAddress(sessionManager.GetIpAddress());
        create_event_pojo.setCommand("Save");


        Call<Create_Event_Pojo> call2 = apiInterface.Save_Create_Event(create_event_pojo);
        call2.enqueue(new Callback<Create_Event_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Create_Event_Pojo> call, Response<Create_Event_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        back_event=false;
                        String attribute = String.valueOf(response.body().getAttribute1());
                        String track = String.valueOf(response.body().getTrackId());


                        appPreferences.save_Attribute1(attribute);
                        appPreferences.save_TrackEvent(track);
                        Log.d("track",track);


                        startActivity(new Intent(appController, Payment_Activity.class));
                        finish();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

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
            public void onFailure(Call<Create_Event_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Book_Location_Events.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_Event_Type_List_UserWise extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         /*   progressDialog = new ProgressDialog(Book_Location_Events.this);
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Event_Type_List_UserWise','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Event_Type_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Event_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Book_Location_Events.this, R.layout.countryspinner_country_new, Event_list, res);
                spi_event_type.setAdapter(nationality_adapter);
                spi_event_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_event_type.setSelection(position);
                        Event_Xcode = Event_list.get(position).xCode;
                        Log.e("Event_Xcode", Event_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Booth_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Booth_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Booth_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Booth_List_list.addAll(partnerPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_Booth.setLayoutManager(gridLayoutManager);
                booth_adapter = new Booth_Adapter_Event(Book_Location_Events.this, Booth_List_list);
                rw_Booth.setAdapter(booth_adapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Zone_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Zone_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Zone_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Zone_List.addAll(partnerPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_selectzone.setLayoutManager(gridLayoutManager);
                zone_select_adapter = new Zone_Select_Adapter_Event(Book_Location_Events.this, Zone_List);
                rw_selectzone.setAdapter(zone_select_adapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_Event_Price_ByDates extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Event_Price_ByDates','" + To_date + "','" + From_date + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("price_", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        Event_Price = collegeData.getDouble("Event_Price");
                        String Currency = collegeData.getString("Currency");


                        String Mem_Price_ = String.format("%.3f", Event_Price);

                        _instalment_price.setText(Mem_Price_ + " " + Currency);


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }


    private class Zone_Select_Adapter_Event extends RecyclerView.Adapter<MyViewHolder_Zone> {
        Context context;
        AppPreferences appPreferences;
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<String> exchange_list = new ArrayList<>();
        ArrayList<Row> listData = new ArrayList<>();
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Zone_Select_Adapter_Event(Context context, ArrayList<Row> listData) {
            this.context = context;
            this.listData = listData;
        }

        /* public Zone_Select_Adapter(Create_Events create_events, List<ZonePojo> listData) {
         }
     */
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolder_Zone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();

            // infalte the item Layout
            View v;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_select_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_select_list_ar, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder_Zone vh = new MyViewHolder_Zone(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder_Zone holder, int position) {

            holder.date.setText(listData.get(position).getxName());

            holder.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listData.get(position).setSelected(!listData.get(position).isSelected());

                    if (listData.get(position).isSelected()) {
                        Select_Zone = listData.get(position).getxCode();
                    }
                    if (listData.get(position).isSelected()) {
                        abc.add(Select_Zone);
                    } else {
                        abc.remove(Select_Zone);

                    }
                    holder.date.setBackgroundColor(listData.get(position).isSelected() ? Color.GREEN : Color.WHITE);


                    zonadata = TextUtils.join(",", abc);
                    //  holder.date.setBackgroundColor(listData.get(position).isSelected() ? R.drawable.edittext_select_zone : R.drawable.edittext_deselect_zone);
                    //holder.date.setBackground(context.getResources().getDrawable(R.drawable.edittext_select_zone));

                }
            });
            //   zonadata = TextUtils.join(",", abc);

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            //  listData.size();
            return listData == null ? 0 : listData.size();
        }


    }


    private class Booth_Adapter_Event extends RecyclerView.Adapter<MyViewHolder_Booth> {
        Context context;
        AppPreferences appPreferences;
        ImageView next;
        TextView date, date_;
        RecyclerView rw_zonelist;
        Booth_Adapter zoneAAdapterDetails;
        LinearLayout lin_date;
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<String> exchange_list = new ArrayList<>();
        ArrayList<Row> listData = new ArrayList<>();
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Booth_Adapter_Event(Context context, ArrayList<Row> listData) {
            this.context = context;
            this.listData = listData;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolder_Booth onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();
            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;
            heding = appController.heding;
            typebold = appController.typebold;
            typeLight = appController.typeLight;
            // infalte the item Layout
            View v;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boooth_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boooth_list_ar, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder_Booth vh = new MyViewHolder_Booth(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder_Booth holder, int position) {

            holder.txt_booth_A.setTypeface(typebold);
            holder.editText_booth_a.setTypeface(typeRegular);

            holder.txt_booth_A.setText(listData.get(position).getxCode1());
            if (position == 0) {


                holder.editText_booth_a.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        Editext_booth_A = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                xcode_booth_A = listData.get(position).getxCode();

            }

            if (position == 1) {
                //  Editext_booth_B = holder.editText_booth_a.getText().toString();
                xcode_booth_B = listData.get(position).getxCode();

                holder.editText_booth_a.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        Editext_booth_B = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }
            if (position == 2) {
                //  Editext_booth_C = holder.editText_booth_a.getText().toString();
                xcode_booth_C = listData.get(position).getxCode();


                holder.editText_booth_a.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        Editext_booth_C = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
            // final ZonePojo model = listData.get(position);

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            //  listData.size();
            return listData == null ? 0 : listData.size();
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

                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Terms_Conditions_List','','CrtEvnts','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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

                termsAndConditionListAdapter = new TermsAndConditionListAdapterBO("create", Book_Location_Events.this, Terms_list);
                layoutManager = new LinearLayoutManager(Book_Location_Events.this, LinearLayoutManager.VERTICAL, false);
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
                     //   tems_cond.setSelected(isChecked);
                        // adb = true;
                        checkvalue++;

                    } else {
                   //     tems_cond.setSelected(false);
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