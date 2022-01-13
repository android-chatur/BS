package com.example.barayihsalem.UI;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Model.Confirm_Membership_Community_Flow_Pojo;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Community_Dialog extends Dialog implements View.OnClickListener {
    AppPreferences appPreferences;
    APIInterface apiInterface;
    SessionManager sessionManager;
    TextView message, no, yes;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ProgressDialog progressDialog;
    private Object Activity;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Community_Dialog(Context context) {
        super(context);
        appPreferences = new AppPreferences(context);
        appPreferences = new AppPreferences(context);
        sessionManager = new SessionManager(context);

        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.dialog_commu);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.dialog_commu_ar);


        }
        apiInterface = APIClient.getClient().create(APIInterface.class);

        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        message = findViewById(R.id.message);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);
        message.setTypeface(heding);
        no.setTypeface(heding);
        yes.setTypeface(heding);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.no:

                // showProgress();
                new Verify_PaymentType_Page_IsShow().execute();
                // Confirm_Membership_Community_Flow();

                break;
            case R.id.yes:
                Intent intent = new Intent(getContext(), WellcomefamilyPage.class);
                getContext().startActivity(intent);
                /* ((Activity))).finish();*/
            /*    startActivity(new Intent(getContext(), WellcomefamilyPage.class));
                finish();*/
                break;
        }
    }

    private void Confirm_Membership_Community_Flow() {
        Confirm_Membership_Community_Flow_Pojo confirm_membership_community_flow_pojo = new Confirm_Membership_Community_Flow_Pojo();
        confirm_membership_community_flow_pojo.setUserId(appPreferences.getuserid());

        confirm_membership_community_flow_pojo.setUniqueId(sessionManager.GetUniqueId());
        confirm_membership_community_flow_pojo.setCorpcentreby(appPreferences.get_company_id());
        confirm_membership_community_flow_pojo.setIpAddress(sessionManager.GetIpAddress());


        Call<Confirm_Membership_Community_Flow_Pojo> call2 = apiInterface.Confirm_Membership_Community_Flow(confirm_membership_community_flow_pojo);
        call2.enqueue(new Callback<Confirm_Membership_Community_Flow_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Confirm_Membership_Community_Flow_Pojo> call, Response<Confirm_Membership_Community_Flow_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sessionManager.SetUniqueId("");

                        appPreferences.saveHomepage(true);
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        getContext().startActivity(intent);

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(getContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Confirm_Membership_Community_Flow_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
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


    private class Verify_PaymentType_Page_IsShow extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(getContext().getResources().getString(R.string.GetMainurl));

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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Verify_PaymentType_Page_IsShow','','','','','" + sessionManager.GetUniqueId() + "','" + sessionManager.GetIpAddress() + "','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("page_show", response);
            hideProgress();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String VDone = collegeData.getString("VDone");

                        if (VDone.equals("2")) {

                            getContext().startActivity(new Intent(getContext(), Select_Payment_Type.class));


                        } else {
                            showProgress();
                            Confirm_Membership_Community_Flow();
                        }

                    }
                }
            } catch (JSONException e) {


            }
        }

    }
}
