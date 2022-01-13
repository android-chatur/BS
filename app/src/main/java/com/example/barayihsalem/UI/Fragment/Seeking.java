package com.example.barayihsalem.UI.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.APIClient;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Address_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Model.Accommodation_Seeking_Pojo;
import com.example.barayihsalem.UI.Model.Row;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.OnClickListener;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Seeking extends Fragment implements OnClickListener {
    SessionManager sessionManager;
    AppPreferences appPreferences;
    RecyclerView rw_allvision, rw_comminity;
    Address_Adapter address_adapter;
    TextView city_name, txt_gender_do, txt_Nationality, txt_room_type, txt_budget_range, txt_room_type_veg, txt_room_type_smoking, txt_sharing_type;
    AppController appController;
    RadioButton male_do, female_do;
    Button proceed_do;
    ProgressDialog progressDialog;
    EditText editTextrequest_box, editText_age_do, editText_mobile_do;
    RecyclerView rw_regi_servi, rw_additional;
    String Special_request = "";
    Addition_services_Adapter addition_services_adapter;
    View v;
    RadioGroup radio_gender;

    APIInterface apiInterface;
    String Nationality_Xcode = "";
    String XCode_gender, Xcode_male, Xcode_female, Gendercode;
    String RoomType1_Xcode = "";
    String Room_type_veg_Xcode = "";
    String Room_type_smoking_Xcode = "";
    String Budget_Range = "";
    String sharing_type_xcode = "";

    ArrayList<Row> RoomType_list1 = new ArrayList<Row>();
    Spinner sp_nationality, sp_room_type_relation, sp_room_type_veg, sp_room_type_smoking, sp_budget_range, sp_sharing_type;
    ArrayList<String> nationality_list = new ArrayList<>();
    Nationality_Adapter nationality_adapter;
    ArrayList<Row> Gender_list = new ArrayList<Row>();
    ArrayList<Row> RoomType_list2 = new ArrayList<Row>();
    ArrayList<Row> RoomType_list3 = new ArrayList<Row>();
    ArrayList<Row> sharing_type_List = new ArrayList<Row>();

    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    ArrayList<Row> Budget_Range_List = new ArrayList<Row>();

    AlertDialog alertDialog;
    TextView message, no, txt_request_box;
    RelativeLayout abcd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    BottomSheetDialog dialog;
    Button btnLogin, btnok;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        appPreferences = new AppPreferences(getActivity());
        sessionManager = new SessionManager(getActivity());

        // View v;

        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            v = inflater.inflate(R.layout.fragment_seeking, parent, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            v = inflater.inflate(R.layout.fragment_seeking_ar, parent, false);


        }


        return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findid(v);

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


        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (Seeking.this.isNetworkConnected()) {


            new Get_Gender_List().execute();
            new Get_Nationality_List().execute();
            new Get_RoomType_1_List().execute();
            new Get_RoomType_2_List().execute();
            new Get_RoomType_3_List().execute();
            new Get_Budget_Range_List().execute();
            new Get_Sharing_Type_List().execute();

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


        String fri = appPreferences.get_Color();
        if (fri.equals("passionate colr")) {


            btnLogin.setBackground(getResources().getDrawable(R.drawable.passionate_drawable));

        }
        if (appPreferences.get_Color().equals("bussiness colr")) {
            btnLogin.setBackground(getResources().getDrawable(R.drawable.bussine_drawable));


        }
        if (appPreferences.get_Color().equals("vp_Recident_VP")) {
            btnLogin.setBackground(getResources().getDrawable(R.drawable.residency_drawable));


        }

        if (appPreferences.get_Color().equals("vp colr")) {
            btnLogin.setBackground(getResources().getDrawable(R.drawable.visionpatner));


        }
        if (appPreferences.get_Color().equals("supp_registerd_services")) {
            btnLogin.setBackground(getResources().getDrawable(R.drawable.supplier));


        }

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid(View v) {
        radio_gender = v.findViewById(R.id.radio_gender);

        editTextrequest_box = v.findViewById(R.id.editTextrequest_box);
        city_name = v.findViewById(R.id.city_name);
        radio_gender = v.findViewById(R.id.radio_gender);
        sp_nationality = v.findViewById(R.id.sp_nationality);
        sp_room_type_relation = v.findViewById(R.id.sp_room_type_relation);
        sp_room_type_veg = v.findViewById(R.id.sp_room_type_veg);
        sp_room_type_smoking = v.findViewById(R.id.sp_room_type_smoking);
        sp_budget_range = v.findViewById(R.id.sp_budget_range);
        sp_sharing_type = v.findViewById(R.id.sp_sharing_type);

        txt_request_box = v.findViewById(R.id.txt_request_box);
        abcd = v.findViewById(R.id.abcd);
        txt_gender_do = v.findViewById(R.id.txt_gender_do);
        city_name = v.findViewById(R.id.city_name);
        txt_Nationality = v.findViewById(R.id.txt_Nationality);
        male_do = v.findViewById(R.id.male_do);
        txt_room_type = v.findViewById(R.id.txt_room_type);
        female_do = v.findViewById(R.id.female_do);
        txt_budget_range = v.findViewById(R.id.txt_budget_range);
        txt_room_type_veg = v.findViewById(R.id.txt_room_type_veg);
        txt_room_type_smoking = v.findViewById(R.id.txt_room_type_smoking);
        txt_sharing_type = v.findViewById(R.id.txt_sharing_type);
        txt_sharing_type = v.findViewById(R.id.txt_sharing_type);
        btnLogin = v.findViewById(R.id.btnLogin);
        txt_gender_do.setTypeface(typeRegular);
        city_name.setTypeface(typeRegular);
        city_name.setTypeface(typeRegular);
        male_do.setTypeface(typeRegular);
        editTextrequest_box.setTypeface(typeRegular);
        txt_request_box.setTypeface(typeRegular);
        female_do.setTypeface(typeRegular);
        txt_room_type.setTypeface(typeRegular);
        txt_room_type_veg.setTypeface(typeRegular);
        txt_room_type_smoking.setTypeface(typeRegular);
        txt_budget_range.setTypeface(typeRegular);
        txt_sharing_type.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        btnLogin.setOnClickListener(this);
        //  rw_additional = v.findViewById(R.id.rw_additional);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                if (Seeking.this.isNetworkConnected()) {
                    Special_request = editTextrequest_box.getText().toString();
                    if (!Nationality_Xcode.equals("") || !Nationality_Xcode.isEmpty()) {

                        showProgress();
                        Save_Accommodation_Seeking();
                    } else {
                        Toast.makeText(getContext(), "Please Select Nationality", Toast.LENGTH_SHORT).show();

                    }
                    // Showdialog();
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

    private void Save_Accommodation_Seeking() {
        Accommodation_Seeking_Pojo seeking_pojo = new Accommodation_Seeking_Pojo();

        seeking_pojo.setUserId(appPreferences.getuserid());
        seeking_pojo.setMem_SrNo(appPreferences.get_membership_srno());
        if (appPreferences.getCulturemode().equals("1")) {
            seeking_pojo.setArea("SALMIYA");

        } else {
            seeking_pojo.setArea("SALMIYA");

        }
        seeking_pojo.setGender(Gendercode);
        seeking_pojo.setNationality(Nationality_Xcode);
        seeking_pojo.setBudgetRange(Budget_Range);
        seeking_pojo.setRoomType1(RoomType1_Xcode);
        seeking_pojo.setRoomType2(Room_type_veg_Xcode);
        seeking_pojo.setRoomType3(Room_type_smoking_Xcode);
        seeking_pojo.setSharingType(sharing_type_xcode);
        seeking_pojo.setReq_Box(Special_request);
        seeking_pojo.setSource("Android");
        seeking_pojo.setActive(true);
        seeking_pojo.setDelete(false);
        seeking_pojo.setCorpcentreby(appPreferences.get_company_id());
        seeking_pojo.setIpAddress(sessionManager.GetIpAddress());
        seeking_pojo.setCommand("Save");


        Call<Accommodation_Seeking_Pojo> call2 = apiInterface.Save_Accommodation_Seeking(seeking_pojo);
        call2.enqueue(new Callback<Accommodation_Seeking_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Accommodation_Seeking_Pojo> call, Response<Accommodation_Seeking_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
                        editTextrequest_box.getText().clear();
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Showdialog();
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
            public void onFailure(Call<Accommodation_Seeking_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void Showdialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.seeking_dialog, viewGroup, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.seeking_dialog_ar, viewGroup, false);


        }
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        message = dialogView.findViewById(R.id.message);

        btnok = dialogView.findViewById(R.id.btnok);
        // message.setTypeface(typebold);

        btnok.setTypeface(typeSemibold);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
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
                sp_nationality.setAdapter(nationality_adapter);
                sp_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_nationality.setSelection(position);
                        Nationality_Xcode = Nationality_list.get(position).xCode;
                        Log.e("Nationality_Xcode", Nationality_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
             //   Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_RoomType_1_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_RoomType_1_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_RoomType_1_List", response);
            progressDialog.dismiss();
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                RoomType_list1.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, RoomType_list1, res);
                sp_room_type_relation.setAdapter(nationality_adapter);
                sp_room_type_relation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_room_type_relation.setSelection(position);
                        RoomType1_Xcode = RoomType_list1.get(position).xCode;
                        Log.e("RoomType1_Xcode", RoomType1_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
             //   Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_RoomType_2_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {

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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_RoomType_2_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_RoomType_2_List", response);
            progressDialog.dismiss();
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                RoomType_list2.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, RoomType_list2, res);
                sp_room_type_veg.setAdapter(nationality_adapter);
                sp_room_type_veg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_room_type_veg.setSelection(position);
                        Room_type_veg_Xcode = RoomType_list2.get(position).xCode;
                        Log.e("Room_type_veg_Xcode", Room_type_veg_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
             //   Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_RoomType_3_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_RoomType_3_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_RoomType_3_List", response);
            progressDialog.dismiss();
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                RoomType_list3.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, RoomType_list3, res);
                sp_room_type_smoking.setAdapter(nationality_adapter);
                sp_room_type_smoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_room_type_smoking.setSelection(position);
                        Room_type_smoking_Xcode = RoomType_list3.get(position).xCode;
                        Log.e("Room_type_smoking_Xcode", Room_type_smoking_Xcode);

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


    private class Get_Budget_Range_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Budget_Range_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Budget_Range_List", response);
            progressDialog.dismiss();
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Budget_Range_List.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, Budget_Range_List, res);
                sp_budget_range.setAdapter(nationality_adapter);
                sp_budget_range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_budget_range.setSelection(position);
                        Budget_Range = Budget_Range_List.get(position).xCode;
                        Log.e("Budget_Range", Budget_Range);

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

    private class Get_Sharing_Type_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Sharing_Type_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Sharing_Type_List", response);
            progressDialog.dismiss();
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                sharing_type_List.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(getActivity(), R.layout.countryspinner_country_new, sharing_type_List, res);
                sp_sharing_type.setAdapter(nationality_adapter);
                sp_sharing_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_sharing_type.setSelection(position);
                        sharing_type_xcode = sharing_type_List.get(position).xCode;
                        Log.e("sharing_type_xcode", sharing_type_xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            } else {
            //    Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

}


