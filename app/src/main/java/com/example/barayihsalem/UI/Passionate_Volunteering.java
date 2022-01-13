package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Adapter.VolunteerIn_Adapter;
import com.example.barayihsalem.UI.Model.Confirm_Membership_Community_Flow_Pojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Valunteering_Pojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Passionate_Volunteering extends AppCompatActivity implements View.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    TextView aboutbs, txt_todate_, txt_todate, txt_fdate_, txt_fdate, txt_full_name, txt_gender, txt_nationality, txt_age, txt_voluntee, txt_phone;
    AppController appController;
    AppPreferences appPreferences;
    RadioGroup radio_gender;
    SessionManager sessionManager;
    APIInterface apiInterface;
    AlertDialog alertDialog;
    String XCode_gender, Xcode_male, Xcode_female, Gendercode;
    ArrayList<Row> Gender_list = new ArrayList<Row>();
    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    ArrayList<Row> VolunteerIn_List = new ArrayList<Row>();
    RadioButton male, female;
    Nationality_Adapter nationality_adapter;
    VolunteerIn_Adapter volunteerIn_adapter;
    ProgressDialog progressDialog;
    RelativeLayout abcd;
    Spinner id_nationality, id_valunteer;
    String Nationality_Xcode = "";
    String Valunteering_Xcode = "";
    TextView f_name, txt_phone_cu;
    String Parent_Name = "";
    String Parent_Mobile = "";
    EditText editText_fa_name, editText_mobile_cu;
    String myFormat;
    String myFormat2;
    String gaha;
    String gahaw;
    String Age = "";
    String Volun_name = "";
    String Mobile = "";
    String From_date = "";
    String To_date = "";
    String gaha_dob;
    String gahaw_dob;
    BottomSheetDialog dialog;
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
            updateLabel_dob();
            // updateLabel_buss();
        }

    };
    TextView message, no, yes;
    ImageView logoside;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button proceed_cus, proceed;
    EditText editText_volun_name, editText_age, editText_mobile;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    //  RecyclerView rw_regi_servi, rw_additional;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel_dob() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        myFormat2 = "dd/MM/yyyy"; //In which you need put here
        android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat(myFormat, Locale.US);
        android.icu.text.SimpleDateFormat sdff = new SimpleDateFormat(myFormat2, Locale.US);


        gaha_dob = sdf.format(myCalendar.getTime());
        gahaw_dob = sdff.format(myCalendar.getTime());
        To_date = gaha_dob;
        txt_todate.setText(gahaw_dob);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        myFormat2 = "dd/MM/yyyy"; //In which you need put here
        android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat(myFormat, Locale.US);
        android.icu.text.SimpleDateFormat sdff = new SimpleDateFormat(myFormat2, Locale.US);


        gaha = sdf.format(myCalendar.getTime());
        gahaw = sdff.format(myCalendar.getTime());
        From_date = gaha;
        txt_fdate.setText(gahaw);
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

            setContentView(R.layout.activity_passionate__volunteering);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_passionate__volunteering_ar);


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

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String formattedDate = df.format(c);
        SimpleDateFormat dfbak = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDateasd = dfbak.format(c);
        txt_fdate.setText(formattedDate);
        txt_todate.setText(formattedDate);
        From_date = formattedDateasd;
        To_date = formattedDateasd;
        Log.d("formattedDate", formattedDate);
        Log.d("formattedDateasd", formattedDateasd);
        if (Passionate_Volunteering.this.isNetworkConnected()) {


            new Get_Gender_List().execute();
            new Get_Nationality_List().execute();
            new Get_VolunteerIn_List().execute();

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
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {


                int childCount = group.getChildCount();
                String gender = null;
                for (int x = 0; x < childCount; x++) {
                    RadioButton btn = (RadioButton) group.getChildAt(x);


                    if (btn.getId() == R.id.male) {
                        Gendercode = Xcode_male;
                    } else {

                        Gendercode = Xcode_female;

                    }
                    if (btn.getId() == i) {

                        gender = Gendercode;// here gender will contain M or F.

                    }

                }

                Log.e("Gender", gender);

            }
        });

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private void findid() {
        id_valunteer = findViewById(R.id.id_valunteer);
        id_nationality = findViewById(R.id.id_nationality);
        radio_gender = findViewById(R.id.radio_gender);
        aboutbs = findViewById(R.id.aboutbs);
        male = findViewById(R.id.male);
        abcd = findViewById(R.id.abcd);


        female = findViewById(R.id.female);
        txt_full_name = findViewById(R.id.txt_full_name);
        txt_gender = findViewById(R.id.txt_gender);
        editText_volun_name = findViewById(R.id.editText_volun_name);
        logoside = findViewById(R.id.logoside);

        txt_age = findViewById(R.id.txt_age);
        txt_voluntee = findViewById(R.id.txt_voluntee);
        txt_nationality = findViewById(R.id.txt_nationality);

        editText_age = findViewById(R.id.editText_age);
        editText_mobile = findViewById(R.id.editText_mobile);
        txt_phone = findViewById(R.id.txt_phone);

        proceed = findViewById(R.id.proceed);

        txt_full_name.setTypeface(typeRegular);

        male.setTypeface(typeRegular);

        female.setTypeface(typeRegular);
        txt_age.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        txt_voluntee.setTypeface(typeRegular);
        txt_phone.setTypeface(typeRegular);
        txt_nationality.setTypeface(typeRegular);
        editText_volun_name.setTypeface(typeRegular);
        editText_age.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        aboutbs.setTypeface(typebold);

        logoside.setOnClickListener(this);

        txt_fdate_ = findViewById(R.id.txt_fdate_);
        txt_todate_ = findViewById(R.id.txt_todate_);
        txt_todate = findViewById(R.id.txt_todate);
        txt_fdate = findViewById(R.id.txt_fdate);
        txt_fdate.setTypeface(typeRegular);
        txt_todate_.setTypeface(typeRegular);
        txt_fdate_.setTypeface(typeRegular);
        txt_todate.setTypeface(typeRegular);
        logoside.setOnClickListener(this);
        proceed.setTypeface(typeRegular);
        txt_fdate.setOnClickListener(this);

        txt_fdate.setOnClickListener(this);
        txt_todate.setOnClickListener(this);
        proceed.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Passionate_Volunteering.this, HomeActivity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.txt_fdate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.txt_todate:
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, dateListener_dob, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog1.show();
                break;

            case R.id.proceed:
                if (Passionate_Volunteering.this.isNetworkConnected()) {


                    if (validate()) {

                        if (!Nationality_Xcode.equals("") || !Nationality_Xcode.isEmpty()) {
                            if (!Valunteering_Xcode.equals("") || !Valunteering_Xcode.isEmpty()) {


                                Double aaa = Double.valueOf((Age));
                                if (aaa < 18) {
                                    showDialog_Custom();
                                    return;
                                } else {
                                    showProgress();
                                    Save_Volunteering();
                                }


                            } else {
                                Toast.makeText(appController, "Please Select Volunteer in", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(appController, "Please Select Nationality", Toast.LENGTH_SHORT).show();
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

                break;

        }
    }

    private void Save_Volunteering() {
        Valunteering_Pojo valunteering_pojo = new Valunteering_Pojo();
        valunteering_pojo.setUserId(appPreferences.getuserid());

        valunteering_pojo.setMem_SrNo(appPreferences.get_membership_srno());
        valunteering_pojo.setFullName(Volun_name);
        valunteering_pojo.setGender(Gendercode);
        valunteering_pojo.setNationality(Nationality_Xcode);
        valunteering_pojo.setFromDate(From_date);
        valunteering_pojo.setToDate(To_date);
        valunteering_pojo.setAge(Age);
        valunteering_pojo.setParent_Name(Parent_Name);
        valunteering_pojo.setParent_Mobile(Parent_Mobile);
        valunteering_pojo.setVolunteerIn(Valunteering_Xcode);
        valunteering_pojo.setMobile(Mobile);
        valunteering_pojo.setSource("Android");
        valunteering_pojo.setCorpcentreby(appPreferences.get_company_id());
        valunteering_pojo.setIpAddress(sessionManager.GetIpAddress());
        valunteering_pojo.setCommand("Save");


        Call<Valunteering_Pojo> call2 = apiInterface.Save_Volunteering(valunteering_pojo);
        call2.enqueue(new Callback<Valunteering_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Valunteering_Pojo> call, Response<Valunteering_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name.getText().clear();
                        editText_age.getText().clear();
                        editText_mobile.getText().clear();
                       /* Intent intent= getIntent();
                        finish();
                        startActivity(intent);*/

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

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
            public void onFailure(Call<Valunteering_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Passionate_Volunteering.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showDialog_Custom() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.custom_list, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.custom_list_ar, viewGroup, false);


        }

        f_name = dialogView.findViewById(R.id.f_name);
        editText_fa_name = dialogView.findViewById(R.id.editText_fa_name);
        txt_phone_cu = dialogView.findViewById(R.id.txt_phone_cu);
        editText_mobile_cu = dialogView.findViewById(R.id.editText_mobile_cu);
        proceed_cus = dialogView.findViewById(R.id.proceed_cus);

        f_name.setTypeface(typebold);
        editText_mobile_cu.setTypeface(typeRegular);
        txt_phone_cu.setTypeface(typeRegular);
        editText_fa_name.setTypeface(typeRegular);
        proceed_cus.setTypeface(typeRegular);
        proceed_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Vadidate_dialog()) {

                    showProgress();
                    Save_Volunteering();
                    alertDialog.dismiss();
                }
            }
        });


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        //  alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

       /* cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });*/
    }

    private boolean Vadidate_dialog() {

        Parent_Name = editText_fa_name.getText().toString();
        Parent_Mobile = editText_mobile_cu.getText().toString();

        if (TextUtils.isEmpty(editText_fa_name.getText().toString())) {
            editText_fa_name.setError("Enter Father Name");
            editText_fa_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_mobile_cu.getText().toString())) {
            editText_mobile_cu.setError("Enter Father Mobile Numbe");
            editText_mobile_cu.requestFocus();
            return false;
        }
        return true;
    }


    private boolean validate() {
        Volun_name = editText_volun_name.getText().toString();
        if (TextUtils.isEmpty(editText_volun_name.getText().toString())) {
            editText_volun_name.setError("Enter Authorized Person Name ");
            editText_volun_name.requestFocus();
            return false;
        }
        Age = (editText_age.getText().toString());
        if (TextUtils.isEmpty(editText_age.getText().toString())) {
            editText_age.setError("Enter Age");
            editText_age.requestFocus();
            return false;
        }
        Mobile = (editText_mobile.getText().toString());
        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Enter Phone Number");
            editText_mobile.requestFocus();
            return false;
        }

        return true;
    }

    private class Get_Gender_List extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Passionate_Volunteering.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Gender_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("gender_list", response);
            progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {


                Gender_list.addAll(partnerPojo.row);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String emailresponse = jsonObject.getString("success");
                    if (emailresponse.equals("0")) {

                    } else {

                        JSONArray result = jsonObject.getJSONArray("row");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject collegeData = result.getJSONObject(i);


                            XCode_gender = collegeData.getString("XCode");

                            if (XCode_gender.equals("Gndr01")) {
                                String XName_male = collegeData.getString("XName");
                                Xcode_male = collegeData.getString("XCode");
                                Gendercode = Xcode_male;

                                male.setText(XName_male);
                            } else {
                                String XName_female = collegeData.getString("XName");
                                Xcode_female = collegeData.getString("XCode");
                                female.setText(XName_female);
                            }
                        }
                    }
                } catch (JSONException e) {


                }


            }
        }

    }


    private class Get_Nationality_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* progressDialog = new ProgressDialog(BecomeResident_member.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);*/
            progressDialog.show();
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Nationality_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Nationality_List", response);
            progressDialog.dismiss();
            Nationality_list = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
            //  if (appPreferences.getCulturemode().equals("1")) {
            worldpop.setxName("Select Nationality");
         /*   } else {
                worldpop.setXname("البلد");
            }*/
            // worldpop.setXname("Select Country");
            //  worldpop.setXname("Kuwait");
            Nationality_list.add(worldpop);

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Nationality_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Passionate_Volunteering.this, R.layout.countryspinner_country_new, Nationality_list, res);
                id_nationality.setAdapter(nationality_adapter);
                id_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_nationality.setSelection(position);
                        Nationality_Xcode = Nationality_list.get(position).xCode;
                        Log.e("Nationality_Xcode", Nationality_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
           //     Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_VolunteerIn_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* progressDialog = new ProgressDialog(BecomeResident_member.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);*/
            progressDialog.show();
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_VolunteerIn_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("_List", response);
            progressDialog.dismiss();
            VolunteerIn_List = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
            //  if (appPreferences.getCulturemode().equals("1")) {
            worldpop.setxName("VolunteerIn");
         /*   } else {
                worldpop.setXname("البلد");
            }*/
            // worldpop.setXname("Select Country");
            //  worldpop.setXname("Kuwait");
            VolunteerIn_List.add(worldpop);

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                VolunteerIn_List.addAll(partnerPojo.row);

                Resources res = getResources();
                volunteerIn_adapter = new VolunteerIn_Adapter(Passionate_Volunteering.this, R.layout.countryspinner_country_new, VolunteerIn_List, res);
                id_valunteer.setAdapter(volunteerIn_adapter);
                id_valunteer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_valunteer.setSelection(position);
                        Valunteering_Xcode = VolunteerIn_List.get(position).xCode;
                        Log.e("Valunteering_Xcode", Valunteering_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
               // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }
}