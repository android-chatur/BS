package com.example.barayihsalem.UI.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.APIClient;
import com.example.barayihsalem.UI.Adapter.Address_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Adapter.VolunteerIn_Adapter;
import com.example.barayihsalem.UI.Model.Donation_Pojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Donation extends Fragment implements View.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    SessionManager sessionManager;
    ArrayList<String> nationality_list = new ArrayList<>();
    AppPreferences appPreferences;
    RecyclerView rw_allvision, rw_comminity;
    Spinner id_nationality_do, id_valunteer_do;
    Address_Adapter address_adapter;
    Nationality_Adapter nationality_adapter;
    String Nationality_Xcode = "";
    String Valunteering_Xcode = "";
    VolunteerIn_Adapter volunteerIn_adapter;

    TextView txt_todate_, txt_todate, txt_fdate_, txt_fdate, txt_full_name_do, txt_age_do, txt_nationality_do, txt_voluntee_do, txt_phone_do, txt_donate;
    AppController appController;
    // RadioButton male_do, female_do;
    Button proceed_do, proceed_cus;
    EditText editText_volun_name_do, editText_age_do, editText_mobile_do, editText_donate_do;
    String myFormat;
    String myFormat2;
    RadioGroup radio_gender;
    RadioButton male_do, female_do;
    APIInterface apiInterface;

    String gaha;
    String XCode_gender, Xcode_male, Xcode_female, Gendercode;
    ArrayList<Row> Gender_list = new ArrayList<Row>();
    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    ArrayList<Row> VolunteerIn_List = new ArrayList<Row>();
    String gahaw;
    ProgressDialog progressDialog;
    String Age = "";
    String Mobile = "";
    String Donate = "";
    String Name = "";
    String Parent_Name = "";
    String Parent_Mobile = "";
    String From_date = "";
    String To_date = "";
    String gaha_dob;
    String gahaw_dob;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
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
    RelativeLayout abcd;
    TextView editText_area, mobi_num, email_num, email_num_;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    TextView f_name, txt_phone_cu;
    EditText editText_fa_name, editText_mobile_cu;
    View v;
    LinearLayoutManager layoutManager;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        appPreferences = new AppPreferences(getActivity());
        sessionManager = new SessionManager(getActivity());

        // View v;

        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            v = inflater.inflate(R.layout.fragment_donation, parent, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            v = inflater.inflate(R.layout.fragment_donation_ar, parent, false);


        }

        // apiInterface = APIClient.getClient().create(APIInterface.class);

        return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findid(v);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (Donation.this.isNetworkConnected()) {

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
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private void findid(View v) {
        male_do = v.findViewById(R.id.male);
        female_do = v.findViewById(R.id.female);
        radio_gender = v.findViewById(R.id.radio_gender);
        id_nationality_do = v.findViewById(R.id.id_nationality_do);
        id_valunteer_do = v.findViewById(R.id.id_valunteer_do);

        editText_volun_name_do = v.findViewById(R.id.editText_volun_name_do);
        editText_age_do = v.findViewById(R.id.editText_age_do);
        editText_mobile_do = v.findViewById(R.id.editText_mobile_do);
        female_do.setTypeface(typeRegular);
        male_do.setTypeface(typeRegular);


        proceed_do = v.findViewById(R.id.proceed_do);
        txt_full_name_do = v.findViewById(R.id.txt_full_name_do);
        txt_age_do = v.findViewById(R.id.txt_age_do);
        txt_voluntee_do = v.findViewById(R.id.txt_voluntee_do);
        //  txt_gender_do = v.findViewById(R.id.txt_gender_do);
        txt_nationality_do = v.findViewById(R.id.txt_nationality_do);
        txt_phone_do = v.findViewById(R.id.txt_phone_do);
        txt_donate = v.findViewById(R.id.txt_donate);

        editText_donate_do = v.findViewById(R.id.editText_donate_do);
        txt_fdate_ = v.findViewById(R.id.txt_fdate_);
        txt_todate_ = v.findViewById(R.id.txt_todate_);
        txt_todate = v.findViewById(R.id.txt_todate);
        txt_fdate = v.findViewById(R.id.txt_fdate);
        txt_fdate.setTypeface(typeRegular);
        txt_todate_.setTypeface(typeRegular);
        editText_donate_do.setTypeface(typeRegular);
        txt_fdate_.setTypeface(typeRegular);
        txt_todate.setTypeface(typeRegular);
        editText_mobile_do.setTypeface(typeRegular);
        //   male_do.setTypeface(typeRegular);
        txt_voluntee_do.setTypeface(typeRegular);
        // female_do.setTypeface(typeRegular);
        txt_phone_do.setTypeface(typeRegular);
        txt_full_name_do.setTypeface(typeRegular);
        txt_donate.setTypeface(typeRegular);
        txt_age_do.setTypeface(typeRegular);
        editText_age_do.setTypeface(typeRegular);
        txt_nationality_do.setTypeface(typeRegular);
        //  txt_gender_do.setTypeface(typeRegular);
        editText_volun_name_do.setTypeface(typeRegular);
        proceed_do.setTypeface(typeRegular);
        txt_fdate.setOnClickListener(this);

        txt_fdate.setOnClickListener(this);
        txt_todate.setOnClickListener(this);
        proceed_do.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_fdate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.txt_todate:
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), dateListener_dob, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog1.show();
                break;

            case R.id.proceed_do:
                    if (Donation.this.isNetworkConnected()) {


                    if (validate()) {

                        if (!Nationality_Xcode.equals("") || !Nationality_Xcode.isEmpty()) {
                            if (!Valunteering_Xcode.equals("") || !Valunteering_Xcode.isEmpty()) {


                                Double aaa = Double.valueOf((Age));
                                if (aaa < 18) {
                                    showDialog_Custom();
                                    return;
                                } else {
                                    showProgress();
                                    Save_Donation();
                                }


                            } else {
                                Toast.makeText(getActivity(), "Please Select Volunteer in", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(getActivity(), "Please Select Nationality", Toast.LENGTH_SHORT).show();
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

    private void Save_Donation() {
        Donation_Pojo valunteering_pojo = new Donation_Pojo();
        valunteering_pojo.setUserId(appPreferences.getuserid());

        valunteering_pojo.setMem_SrNo(appPreferences.get_membership_srno());
        valunteering_pojo.setFullName(Name);
        valunteering_pojo.setGender(Gendercode);
        valunteering_pojo.setNationality(Nationality_Xcode);
        valunteering_pojo.setAge(Age);
        valunteering_pojo.setParent_Name(Parent_Name);
        valunteering_pojo.setParent_Mobile(Parent_Mobile);
        valunteering_pojo.setVolunteerIn(Valunteering_Xcode);
        valunteering_pojo.setMobile(Mobile);
        valunteering_pojo.setDonate(Donate);
        valunteering_pojo.setSource("Android");
        valunteering_pojo.setCorpcentreby(appPreferences.get_company_id());
        valunteering_pojo.setIpAddress(sessionManager.GetIpAddress());
        valunteering_pojo.setCommand("Save");


        Call<Donation_Pojo> call2 = apiInterface.Save_Donation(valunteering_pojo);
        call2.enqueue(new Callback<Donation_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Donation_Pojo> call, Response<Donation_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {

                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        editText_volun_name_do.getText().clear();
                        editText_age_do.getText().clear();
                        editText_mobile_do.getText().clear();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(),
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(getContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Donation_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
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
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_list, viewGroup, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_list_ar, viewGroup, false);


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
                    Save_Donation();
                    alertDialog.dismiss();
                }

            }
        });


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.OurAlertDialogStyle);

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

        Name = (editText_volun_name_do.getText().toString());
        Age = (editText_age_do.getText().toString());
        Mobile = (editText_mobile_do.getText().toString());

        Donate = (editText_donate_do.getText().toString());

        if (TextUtils.isEmpty(editText_volun_name_do.getText().toString())) {
            editText_volun_name_do.setError("Enter Authorized Person Name");
            editText_volun_name_do.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_age_do.getText().toString())) {
            editText_age_do.setError("Enter Age");
            editText_age_do.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_mobile_do.getText().toString())) {
            editText_mobile_do.setError("Enter Mobile Number");
            editText_mobile_do.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_donate_do.getText().toString())) {
            editText_donate_do.setError("Enter Donate");
            editText_donate_do.requestFocus();
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
            progressDialog = new ProgressDialog(getActivity());
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
            Log.d("gender_list_do", response);
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

                                male_do.setText(XName_male);
                            } else {
                                String XName_female = collegeData.getString("XName");
                                Xcode_female = collegeData.getString("XCode");
                                female_do.setText(XName_female);
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
            //   progressDialog.show();
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
            // progressDialog.dismiss();
            Nationality_list = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
            //  if (appPreferences.getCulturemode().equals("1")) {
            if (appPreferences.getCulturemode().equals("1")) {
                worldpop.setxName("Select Nationality");
            } else {
                worldpop.setxName("حدد الجنسية ");
            }
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
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, Nationality_list, res);
                id_nationality_do.setAdapter(nationality_adapter);
                id_nationality_do.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_nationality_do.setSelection(position);
                        Nationality_Xcode = Nationality_list.get(position).xCode;
                        Log.e("Nationality_Xcode", Nationality_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
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
            //   progressDialog.dismiss();
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
                volunteerIn_adapter = new VolunteerIn_Adapter(getActivity(), R.layout.countryspinner_country_new, VolunteerIn_List, res);
                id_valunteer_do.setAdapter(volunteerIn_adapter);
                id_valunteer_do.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_valunteer_do.setSelection(position);
                        Valunteering_Xcode = VolunteerIn_List.get(position).xCode;
                        Log.e("Valunteering_Xcode", Valunteering_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }
}



