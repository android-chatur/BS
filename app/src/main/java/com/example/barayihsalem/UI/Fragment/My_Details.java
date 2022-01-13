package com.example.barayihsalem.UI.Fragment;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.APIClient;
import com.example.barayihsalem.UI.Adapter.Booth_Past_Event_Adapter;
import com.example.barayihsalem.UI.Adapter.Booth_UpcommingEvent_Adapter;
import com.example.barayihsalem.UI.Adapter.PastEvent_Adapter;
import com.example.barayihsalem.UI.Adapter.UpcommingEvent_Adapter;
import com.example.barayihsalem.UI.Booth_PastiEvent_Details;
import com.example.barayihsalem.UI.Booth_UpcominiEvent_Details;
import com.example.barayihsalem.UI.Enter_Address;
import com.example.barayihsalem.UI.Model.Address_Viewhoder;
import com.example.barayihsalem.UI.Model.DeleteAddressPojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.UpcominiEvent_Details;
import com.example.barayihsalem.UI.UpcominiPastEvent_Details;
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

import static com.example.barayihsalem.UI.Shopping_Category_page.back_cate;
import static com.example.barayihsalem.UI.Shopping_Category_page.colorred;
import static com.example.barayihsalem.UI.Shopping_Category_page.isedit;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class My_Details extends Fragment {
    SessionManager sessionManager;
    AppPreferences appPreferences;
    RecyclerView rw_allvision, rw_up_comingevent, rw_booth_comingevent, rw_booth_past_comingevent, rw_past_comingevent;
    Address_Adapter_inner address_adapter;
    UpcommingEvent_Adapter upcommingEventAdapter;
    ProgressDialog progressDialog;
    APIInterface apiInterface;

    RelativeLayout abcd;
    Booth_UpcommingEvent_Adapter booth_upcommingEvent_adapter;
    Booth_Past_Event_Adapter booth_past_event_adapter;
    PastEvent_Adapter pastEvent_adapter;
    TextView name, addnewaddress, mobile, email, aboutbs_address, aboutbs, aboutbs_past_event, aboutbs_up_event, aboutbs_booth_up_event;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    AppController appController;
    TextView mobi_num, editText_area, email_num;
    View v;
    ArrayList<Row> bottom_list = new ArrayList<Row>();
    ArrayList<Row> UpComming_list = new ArrayList<Row>();
    ArrayList<Row> PastUpComming_list = new ArrayList<Row>();
    ArrayList<Row> Booth_In_UpComing_Event_List = new ArrayList<Row>();
    ArrayList<Row> Booth_In_Past_Event_List = new ArrayList<Row>();
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutManager;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            back_cate = false;
            isedit = true;
            appPreferences.AddressBack("Acount_Info");

            appPreferences.SaveAddressXcode(bottom_list.get(position).getxCode());
            startActivity(new Intent(getActivity(), Enter_Address.class));
            getActivity().finish();

        }

    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall_UpcomingEvent = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            appPreferences.SaveEvent_SRNo(UpComming_list.get(position).getEventSrNo());
            startActivity(new Intent(getActivity(), UpcominiEvent_Details.class));
            getActivity().finish();

        }

    };
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall_boothUpcomingEvent = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

            appPreferences.SaveEvent_SRNo(Booth_In_UpComing_Event_List.get(position).getEventSrNo());

            startActivity(new Intent(getActivity(), Booth_UpcominiEvent_Details.class));
            getActivity().finish();

        }

    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall_boothinpastEvent = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
           // appPreferences.SaveEvent_SRNo(PastUpComming_list.get(position).getEventSrNo());
            appPreferences.SaveEvent_SRNo(Booth_In_Past_Event_List.get(position).getEventSrNo());

            startActivity(new Intent(getActivity(), Booth_PastiEvent_Details.class));
            getActivity().finish();

        }

    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    OnItemClickListener.OnClickCallback onClickCall_pastEvent = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            // PastUpComming_list
            appPreferences.SaveEvent_SRNo(PastUpComming_list.get(position).getEventSrNo());
            startActivity(new Intent(getActivity(), UpcominiPastEvent_Details.class));
            getActivity().finish();
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        appPreferences = new AppPreferences(getActivity());
        sessionManager = new SessionManager(getActivity());

        // View v;

        if (appPreferences.getCulturemode().equals("1")) {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            v = inflater.inflate(R.layout.fragment_mydetails, parent, false);


        } else {
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            v = inflater.inflate(R.layout.fragment_mydetails_ar, parent, false);


        }

        // apiInterface = APIClient.getClient().create(APIInterface.class);

        return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appController = (AppController) getActivity().getApplicationContext();
        appPreferences = new AppPreferences(getActivity());
        sessionManager = new SessionManager(getActivity());
        findid(v);


        apiInterface = APIClient.getClient().create(APIInterface.class);


        if (My_Details.this.isNetworkConnected()) {
            new Get_My_Details().execute();
            new Get_My_UpComing_Event_List().execute();
            new Get_My_Past_Event_List().execute();
            new Get_My_Booth_In_UpComing_Event_List().execute();
            new Get_My_Booth_In_Past_Event_List().execute();
            new Get_Address_List_For_Category().execute();


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
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    private void findid(View v) {
        abcd = v.findViewById(R.id.abcd);
        rw_booth_past_comingevent = v.findViewById(R.id.rw_booth_past_comingevent);
        rw_past_comingevent = v.findViewById(R.id.rw_past_comingevent);
        aboutbs_past_event = v.findViewById(R.id.aboutbs_past_event);
        rw_up_comingevent = v.findViewById(R.id.rw_up_comingevent);
        rw_booth_comingevent = v.findViewById(R.id.rw_booth_comingevent);
        rw_allvision = v.findViewById(R.id.rw_allvision);
        aboutbs_address = v.findViewById(R.id.aboutbs_address);
        aboutbs = v.findViewById(R.id.aboutbs);
        aboutbs_up_event = v.findViewById(R.id.aboutbs_up_event);
        aboutbs_booth_up_event = v.findViewById(R.id.aboutbs_booth_up_event);
        name = v.findViewById(R.id.name);
        addnewaddress = v.findViewById(R.id.addnewaddress);
        mobile = v.findViewById(R.id.mobile);
        email = v.findViewById(R.id.email);
        editText_area = v.findViewById(R.id.editText_area);
        mobi_num = v.findViewById(R.id.mobi_num);
        email_num = v.findViewById(R.id.email_num);


        email_num.setTypeface(typeRegular);
        name.setTypeface(typeRegular);
        email.setTypeface(typeRegular);
        mobile.setTypeface(typeRegular);
        mobi_num.setTypeface(typeRegular);
        editText_area.setTypeface(typeRegular);
        aboutbs_address.setTypeface(typebold);
        aboutbs.setTypeface(typebold);
        aboutbs_up_event.setTypeface(typebold);
        aboutbs_past_event.setTypeface(typebold);
        aboutbs_booth_up_event.setTypeface(typebold);
        addnewaddress.setTypeface(typebold);
        addnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_cate = false;
                appPreferences.AddressBack("Acount_Info");

                startActivity(new Intent(getActivity(), Enter_Address.class));
                getActivity().finish();
            }
        });

    }

    private void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void Save_UserShippingAddress(String asd) {
        DeleteAddressPojo shippingAddressPojo = new DeleteAddressPojo();

        shippingAddressPojo.setSrNo(asd);
        shippingAddressPojo.setUserId(appPreferences.getuserid());
        shippingAddressPojo.setActive(false);
        shippingAddressPojo.setDelete(true);

        shippingAddressPojo.setSource("Android");
        shippingAddressPojo.setCorpcentreby(appPreferences.get_company_id());
        shippingAddressPojo.setIpAddress(sessionManager.GetIpAddress());
        shippingAddressPojo.setCommand("Delete");


        Call<DeleteAddressPojo> call2 = apiInterface.Save_UserShippingAddress(shippingAddressPojo);
        call2.enqueue(new Callback<DeleteAddressPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<DeleteAddressPojo> call, Response<DeleteAddressPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("6")) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        new Get_Address_List_For_Category().execute();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-6")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-10")) {

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
            public void onFailure(Call<DeleteAddressPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private class Get_My_Details extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_Details','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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

            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        String Name = collegeData.getString("Name");
                        String Mobile = collegeData.getString("Mobile");
                        String Email = collegeData.getString("Email");
                        editText_area.setText(Name);
                        mobi_num.setText(Mobile);
                        email_num.setText(Email);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }


        }
    }

    private class Get_My_UpComing_Event_List extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_UpComing_Event_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


                String query = builder.build().getEncodedQuery();
                Log.d("upcoomig_re", query);
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
            Log.d("upcoming", response);
            //  progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                UpComming_list.addAll(partnerPojo.row);

                gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                upcommingEventAdapter = new UpcommingEvent_Adapter(getActivity(), onClickCall_UpcomingEvent, UpComming_list);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rw_up_comingevent.setLayoutManager(layoutManager);
                rw_up_comingevent.setAdapter(upcommingEventAdapter);
            } else {
                //Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Get_My_Past_Event_List extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_Past_Event_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("ast_Event_List", response);
            //  progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                PastUpComming_list.addAll(partnerPojo.row);


                gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                pastEvent_adapter = new PastEvent_Adapter(getActivity(), onClickCall_pastEvent, PastUpComming_list);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rw_past_comingevent.setLayoutManager(layoutManager);
                rw_past_comingevent.setAdapter(pastEvent_adapter);


            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Get_My_Booth_In_UpComing_Event_List extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_Booth_In_UpComing_Event_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("Booth_In_UpComing_Event_List", response);
            //  progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Booth_In_UpComing_Event_List.addAll(partnerPojo.row);


                gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                booth_upcommingEvent_adapter = new Booth_UpcommingEvent_Adapter(getActivity(), onClickCall_boothUpcomingEvent, Booth_In_UpComing_Event_List);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rw_booth_comingevent.setLayoutManager(layoutManager);
                rw_booth_comingevent.setAdapter(booth_upcommingEvent_adapter);

            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Get_My_Booth_In_Past_Event_List extends AsyncTask<String, String, String> {
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

                builder.appendQueryParameter("StrQuery", "Sp_MyAccount_App'Get_My_Booth_In_Past_Event_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");


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
            Log.d("Booth_In_UpComing_Event_List", response);
            //  progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Booth_In_Past_Event_List.addAll(partnerPojo.row);

                gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                booth_past_event_adapter = new Booth_Past_Event_Adapter(getActivity(), onClickCall_boothinpastEvent, Booth_In_Past_Event_List);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rw_booth_past_comingevent.setLayoutManager(layoutManager);
                rw_booth_past_comingevent.setAdapter(booth_past_event_adapter);


            } else {
               // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Get_Address_List_For_Category extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Address_List_For_Category','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Address_List_For_Category", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                bottom_list.addAll(partnerPojo.row);
                colorred = true;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                address_adapter = new Address_Adapter_inner(getContext(), onClickCall, bottom_list);

                rw_allvision.setLayoutManager(layoutManager);
                colorred = false;
                rw_allvision.setAdapter(address_adapter);

            } else {
              //  Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Address_Adapter_inner extends RecyclerView.Adapter<Address_Viewhoder> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
        ArrayList<Row> bottom_list = new ArrayList<>();
        int mSelectedPosition = 0;
        AppPreferences appPreferences;
        private int mCheckedPostion = -1;
        private Context context;
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Address_Adapter_inner(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> bottom_list) {
            this.context = context;
            this.bottom_list = bottom_list;
            this.onClickCallback_Benefi = onClickCall_resta;

        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public Address_Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details, parent, false);
            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details_ar, parent, false);

            }
            return new Address_Viewhoder(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull Address_Viewhoder holder, final int position) {
            //tvName.setText(list.get(position).getxName());


            holder.tx_cate.setTypeface(typeHeader);
            holder.tx_cy.setTypeface(typeRegular);
            holder.tx_cancel.setTypeface(typeRegular);
            holder.tx_cate.setChecked(position == mCheckedPostion);
            holder.tx_cate.setText(bottom_list.get(position).getxName());
            //  tx_cy.setText(bottom_list.get(position).getxName());
            String srn0 = String.valueOf(bottom_list.get(position).getSr());
            if (appPreferences.getCulturemode().equals("1")) {
                if (position == 1) {
                    holder.tx_cy.setText("Address" + " " + srn0);
                }
            } else {
                if (position == 1) {
                    holder.tx_cy.setText("العنوان " + "" + srn0);
                }
            }
            int selectedPosition = 0;

            if (colorred == true) {
                holder.proi.setVisibility(View.GONE);
                holder.tx_cancel.setTextColor(context.getColor(R.color.reject_color));

            } else {
                holder.proi.setVisibility(View.VISIBLE);

                holder.tx_cancel.setTextColor(context.getColor(R.color.supplier_color));

            }
            if (holder.tx_cate.isChecked()) {
                holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));
            } else {
                holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));
            }

            holder.proi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String asd = bottom_list.get(position).getxCode();
                    showProgress();
                    Save_UserShippingAddress(asd);
                }
            });


            holder.tx_cate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == mCheckedPostion) {
                        holder.tx_cate.setChecked(false);
                        //  lowest.username = "";
                        mCheckedPostion = -1;
                        String asd = bottom_list.get(position).getxCode();
                        //   holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));


                    } else {
                        // filte = true;
                        mCheckedPostion = position;
                      /*  AddressName = bottom_list.get(position).getxName();
                        AddressXcode=bottom_list.get(position).getxCode();*/
                        //  holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));

                        //   StringGen.username = holder.poll_option.getText().toString();
                        // Toast.makeText(mycontext, "select : "+position+holder.poll_option.getText(), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }
            });




        /*holder.tx_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selectedPosition == position)
                    holder.tx_cate.setTextColor(Color.GREEN);
                else
                    holder.tx_cate.setTextColor(Color.BLACK);

                // bottom_list.get(position).setSelected(!bottom_list.get(position).isSelected());

               *//* if (bottom_list.get(position).isSelected()){
                    holder.tx_cate.setTextColor( bottom_list.get(position).isSelected() ? Color.GREEN);
                }else {
                    holder.tx_cate.setTextColor( Color.BLACK);

                }*//*
                //  holder.tx_cate.setTextColor(bottom_list.get(position).isSelected() ? Color.GREEN : Color.WHITE);
            }
        });*/


            holder.tx_cancel.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

        }

        @Override
        public int getItemCount() {
            return bottom_list.size();
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

