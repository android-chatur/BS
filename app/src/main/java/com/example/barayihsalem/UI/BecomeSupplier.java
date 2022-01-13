package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Categoty_BO_Adapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.SupplierPojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.google.android.material.snackbar.Snackbar;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class BecomeSupplier extends AppCompatActivity implements View.OnClickListener {
    TextView txtlable, txtlable1, title, job_title, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    ProgressDialog progressDialog;
    Button btnLogin;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    EditText editText_work, editText_title, editText_job;
    EditText editText_city, editText_area, editText_address, editText_liceacti, editText_commodity, editText_service;
    EditText editText_email_address, editText_full_name, editText_phone_number;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    TextView area, city, address, licence_activity, commondity, service;
    TextView txt_email_address, txt_fullname, txt_phone_num;
    CheckBox tems_cond;
    Categoty_BO_Adapter categoty_bo_adapter;
    APIInterface apiInterface;
    String Category_Xcode = "";
    boolean adb = false;
    Spinner id_slect_cate;
    ArrayList<Row> Category_list = new ArrayList<Row>();
    String City_Name, Licence_num, Address, Comm_licActivity, Company_name, Person_Name, Phone_number, Email;

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

            setContentView(R.layout.activity_become_supplier);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_become_supplier_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();


        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (BecomeSupplier.this.isNetworkConnected()) {


            new Get_Category_List().execute();

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
        startActivity(new Intent(BecomeSupplier.this, Supplier_.class));
        finish();
    }

    private void findid() {
        btnLogin = findViewById(R.id.btnLogin);
        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        title = findViewById(R.id.title);
        job_title = findViewById(R.id.job_title);
        editText_work = findViewById(R.id.editText_work);
        editText_title = findViewById(R.id.editText_title);
        editText_job = findViewById(R.id.editText_job);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnLogin.setTypeface(typeRegular);
        editText_job.setTypeface(typeRegular);
        editText_title.setTypeface(typeRegular);
        editText_work.setTypeface(typeRegular);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(normal);
        title.setTypeface(normal);
        job_title.setTypeface(normal);
        id_slect_cate = findViewById(R.id.id_slect_cate);
        btnLogin = findViewById(R.id.btnLogin);
        txtlable = findViewById(R.id.txtlable);
        txt_email_address = findViewById(R.id.txt_email_address);
        txt_fullname = findViewById(R.id.txt_fullname);
        txt_phone_num = findViewById(R.id.txt_phone_num);
        city = findViewById(R.id.city);
        area = findViewById(R.id.area);
        commondity = findViewById(R.id.commondity);
        licence_activity = findViewById(R.id.licence_activity);
        address = findViewById(R.id.address);
        service = findViewById(R.id.service);
        editText_city = findViewById(R.id.editText_city);
        editText_email_address = findViewById(R.id.editText_email_address);
        editText_area = findViewById(R.id.editText_area);
        editText_address = findViewById(R.id.editText_address);
        editText_liceacti = findViewById(R.id.editText_liceacti);
        editText_commodity = findViewById(R.id.editText_commodity);
        editText_service = findViewById(R.id.editText_service);
        logoside = findViewById(R.id.logoside);
        editText_full_name = findViewById(R.id.editText_full_name);
        editText_phone_number = findViewById(R.id.editText_phone_number);
        logoside.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnLogin.setTypeface(typeRegular);
        editText_phone_number.setTypeface(typeRegular);

        editText_email_address.setTypeface(typeRegular);
        txt_phone_num.setTypeface(typeRegular);
        txt_phone_num.setTypeface(typeRegular);
        txt_fullname.setTypeface(typeRegular);
        editText_full_name.setTypeface(typeRegular);
        editText_city.setTypeface(typeRegular);
        editText_commodity.setTypeface(typeRegular);
        editText_area.setTypeface(typeRegular);
        editText_address.setTypeface(typeRegular);
        editText_liceacti.setTypeface(typeRegular);
        editText_service.setTypeface(typeRegular);
        txt_email_address.setTypeface(typeRegular);
        txtlable.setTypeface(typebold);
        area.setTypeface(normal);
        city.setTypeface(normal);
        tems_cond = findViewById(R.id.tems_cond);
        licence_activity.setTypeface(normal);
        tems_cond.setTypeface(typeRegular);
        commondity.setTypeface(normal);
        service.setTypeface(normal);

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btnLogin:
                if (BecomeSupplier.this.isNetworkConnected()) {
                    if (validate()) {
                        if (!Category_Xcode.isEmpty() || !Category_Xcode.equals("")) {
                            if (adb == true) {
                                showProgress();
                                Save_Supplier();
                            } else {

                                Toast.makeText(appController, "Please Agree Terms and Conditions ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(appController, "Please Select Category", Toast.LENGTH_SHORT).show();
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

                //   exitByBackKey();


                break;

            case R.id.yes:
                startActivity(new Intent(BecomeSupplier.this, WellcomefamilyPage.class));
                finish();
                break;
            case R.id.no:
                startActivity(new Intent(BecomeSupplier.this, HomeActivity.class));
                finish();
                break;
        }
    }

    private void Save_Supplier() {
        SupplierPojo supplierPojo = new SupplierPojo();
        supplierPojo.setUserId(appPreferences.getuserid());
        supplierPojo.setMem_SrNo(appPreferences.get_membership_srno());
        supplierPojo.setUniqueId(sessionManager.GetUniqueId());
        supplierPojo.setCorpcentreby(appPreferences.get_company_id());
        supplierPojo.setIpAddress(sessionManager.GetIpAddress());
        supplierPojo.setSource("Android");
        supplierPojo.setCommand("Save");
        supplierPojo.setCity(City_Name);
        supplierPojo.setLic_Civil_No(Licence_num);
        supplierPojo.setAddress(Address);
        supplierPojo.setCommercial_Lic_Activity(Comm_licActivity);
        supplierPojo.setCommodity(Category_Xcode);
        supplierPojo.setCompany_Name(Company_name);
        supplierPojo.setAuthorize_Per_Name(Person_Name);
        supplierPojo.setRes_Mobile(Phone_number);
        supplierPojo.setRes_Email(Email);


        Call<SupplierPojo> call2 = apiInterface.Save_Supplier(supplierPojo);
        call2.enqueue(new Callback<SupplierPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SupplierPojo> call, Response<SupplierPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //exitByBackKey();

                        Membership_Dialog start_dialog = new Membership_Dialog(BecomeSupplier.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();

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
            public void onFailure(Call<SupplierPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate() {

        City_Name = editText_city.getText().toString();
        Licence_num = editText_area.getText().toString();
        Address = editText_address.getText().toString();
        Comm_licActivity = editText_liceacti.getText().toString();
        Company_name = editText_service.getText().toString();
        Person_Name = editText_full_name.getText().toString();
        Phone_number = editText_phone_number.getText().toString();
        Email = editText_email_address.getText().toString();


        if (TextUtils.isEmpty(editText_city.getText().toString())) {
            editText_city.setError("Please Enter City");
            editText_city.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_area.getText().toString())) {
            editText_area.setError("Please Enter Local License Number");
            editText_area.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_address.getText().toString())) {
            editText_address.setError("Please Enter Address");
            editText_address.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_liceacti.getText().toString())) {
            editText_liceacti.setError("Please Enter Commercial License Activity");
            editText_liceacti.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_service.getText().toString())) {
            editText_service.setError("Please Enter Company Name");
            editText_service.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_full_name.getText().toString())) {
            editText_full_name.setError("Please Authorized Person Name");
            editText_full_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_phone_number.getText().toString())) {
            editText_phone_number.setError("Please Enter Phone Number");
            editText_phone_number.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_email_address.getText().toString())) {
            editText_email_address.setError("Enter Your  E-mail");
            editText_email_address.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email_address.getText().toString())) {
            editText_email_address.setError("Invalid Email Address");
            editText_email_address.requestFocus();
            return false;


        }


        return true;
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(BecomeSupplier.this);
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
    private void exitByBackKey() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_ar, viewGroup, false);


        }
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        message = dialogView.findViewById(R.id.message);
        no = dialogView.findViewById(R.id.no);
        yes = dialogView.findViewById(R.id.yes);
        message.setTypeface(typeRegular);
        no.setTypeface(typeRegular);
        yes.setTypeface(typeRegular);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        //Now we need an AlertDialog.Builder object/
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();


    }

    private class Get_Category_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Category_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("category_list", response);
            hideProgress();
            Category_list = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
            //  if (appPreferences.getCulturemode().equals("1")) {
            worldpop.setxCode("");
            if (appPreferences.getCulturemode().equals("1")) {
                worldpop.setxName("Select Category");
            } else {
                worldpop.setxName("حدد الفئة ");
            }
         /*   } else {
                worldpop.setXname("البلد");
            }*/
            // worldpop.setXname("Select Country");
            //  worldpop.setXname("Kuwait");
            Category_list.add(worldpop);
            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Category_list.addAll(partnerPojo.row);


                Resources res = getResources();
                categoty_bo_adapter = new Categoty_BO_Adapter(BecomeSupplier.this, R.layout.countryspinner_country_new, Category_list, res);
                id_slect_cate.setAdapter(categoty_bo_adapter);
                id_slect_cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_slect_cate.setSelection(position);
                        Category_Xcode = Category_list.get(position).xCode;
                        Log.e("Category_Xcode", Category_Xcode);

                        //  CountryModel w = (CountryModel) id_slect_vp.getSelectedItem();
               /* if (!w.getXcode().equals("0")) {
                    currency_code = w.getXcode();
                    currency_name = w.getXname();
                    Log.e("currency_code", currency_code);
                    appPreferences.save_Currency_code(currency_code);


                }*/
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

}