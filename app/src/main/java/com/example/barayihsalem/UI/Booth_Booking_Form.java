package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Booth_Adapter;
import com.example.barayihsalem.UI.Adapter.Zone_A_Adapter_Details;
import com.example.barayihsalem.UI.Model.Get_BoothSlot_list_Pojo;
import com.example.barayihsalem.UI.Model.LiZone;
import com.example.barayihsalem.UI.Model.ListZone;
import com.example.barayihsalem.UI.Model.MyViewHoldeBB_zone;
import com.example.barayihsalem.UI.Model.MyViewHolderSlot;
import com.example.barayihsalem.UI.Model.MyViewHolderZone;
import com.example.barayihsalem.UI.Model.MyViewHolder_Eletric;
import com.example.barayihsalem.UI.Model.MyViewHolder_SelectBooth;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Save_Booth_BookingPojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.Model.Zone;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

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
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Booth_Booking_Form extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, aboutbs_selection, select_booth, view_image, tet_lic_need, txt_email, price, txt_phone, mem_price, txt_fr, txt_fro, txt_lic_n0, tet_lic_n0, view_image1, view_map_image, view_image2, view_image3, txt_rent_chair, txt_gender, txt_full_name, txt_, txt_frid, text_block, txt_rent, txt_booth_need, txt_provide_need, txt_fr_a, txt_provide_need_rent, txt_instagram_account, txt_identifi, txt_rent_pirple, txt_subject_avai;
    AppController appController;
    AppPreferences appPreferences;
    ArrayList<HashMap<String, String>> list_booth = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> list_slot = new ArrayList<HashMap<String, String>>();

    SessionManager sessionManager;
    ArrayList<ListZone> Zone_list_main = new ArrayList<>();
    ArrayList<Zone> Zone_list_sub = new ArrayList<>();
    ProgressDialog progressDialog;
    APIInterface apiInterface;
    AlertDialog alertDialog;
    TextView message, no, yes;
    TextView txt_booth_b, price_booth_b;
    ImageView logoside, booth_image, cross, map, logoside_sele;
    Button btn_select_booth_type, procee1d, proceed;
    RecyclerView rw_zonea, rw_zoneb, rw_electricity_list, rw_selectboot;
    ArrayList<Row> Electricity_list = new ArrayList<Row>();
    ArrayList<Row> Booth_list = new ArrayList<Row>();
    ArrayList<Row> Zone_list = new ArrayList<Row>();
    String Event_Name, Event_Duration, Currency;
    Double Per_ChairPrice = 0.0, Per_TablePrice = 0.0;
    Double Totalchairprice = 0.0;
    Double Totaltableprice = 0.0;
    String Map_Image = "";

    Eletricity_Adapter eletricity_adapter;
    Select_Boot_Adapter select_boot_adapter;
    // CheckBox booth_a, booth_b, chek_bring;
    RelativeLayout abcd;
    boolean compliment = false;
    CheckBox tems_cond;
    LinearLayout /*lin_booth_A, lin_zone_B, lin_booth_booking,*/ lin_booth_selected, lin_boot_a, lin_booth_booking;
    //  Zone_A_Adapter_New zoneAAdapter;
    Zone_A_Adapter_New zoneAAdapter;
    EditText editText_insta_acoo, editText_civil_n0, editText_type_here, editText_email, editText_lice_n0, editText_mobile, editText_nbuss, editText_pirple, editText_rent_chair, editText_last_name, editText_volun_name, editText_both_empty, editText_rent, editText_both;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    String zonadata = "";
    String Equpmentdata = "";
    String F_Name, L_Name, Pho_Num, Email, Nature_buss, Licence_Num, Civil_Num, Table_Num, chair_Num, Type_here, Insta_acc;


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


            setContentView(R.layout.activity_booth__booking__form);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_booth__booking__form_ar);


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


        if (Booth_Booking_Form.this.isNetworkConnected()) {
            new Get_BB_Event_Detail_BySrNo().execute();
            new Get_BB_Map_Image_By_SrNo().execute();
            new Get_BB_High_Electricity_List().execute();
            new Get_BB_BoothList_By_SrNo().execute();
            // new Get_BB_Created_BoothSlot_List().execute();

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


        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
      /*  gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_zonea.setLayoutManager(gridLayoutManager);
        zoneAAdapter = new Zone_A_Adapter_New(Booth_Booking_Form.this);
        rw_zonea.setAdapter(zoneAAdapter);*/

       /* if (booth_a.isChecked()) {
            lin_booth_A.setVisibility(View.VISIBLE);
            lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));
        } else {
            lin_booth_A.setVisibility(View.GONE);
            lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background));


        }
        if (chek_bring.isChecked()) {

            lin_boot_bring.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));
        } else {
            lin_boot_bring.setBackground(getResources().getDrawable(R.drawable.edittext_background));


        }*/

      /*  if (booth_b.isChecked()) {
            lin_zone_B.setVisibility(View.VISIBLE);
            lin_boot_b.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));

        } else {
            lin_zone_B.setVisibility(View.GONE);
            lin_boot_b.setBackground(getResources().getDrawable(R.drawable.edittext_background));

        }

        booth_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (booth_a.isChecked()) {
                    lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));

                    lin_booth_A.setVisibility(View.VISIBLE);
                } else {
                    lin_booth_A.setVisibility(View.GONE);
                    lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background));

                }
            }
        });


        booth_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (booth_b.isChecked()) {
                    lin_boot_b.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));

                    lin_zone_B.setVisibility(View.VISIBLE);

                } else {
                    lin_zone_B.setVisibility(View.GONE);
                    lin_boot_b.setBackground(getResources().getDrawable(R.drawable.edittext_background));

                }
            }
        });

        chek_bring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_bring.isChecked()) {

                    lin_boot_bring.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));
                } else {
                    lin_boot_bring.setBackground(getResources().getDrawable(R.drawable.edittext_background));


                }

            }
        });*/

/*
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_selectboot.setLayoutManager(gridLayoutManager);
        zoneAAdapter = new Zone_A_Adapter_New(Booth_Booking_Form.this);
        rw_zoneb.setAdapter(zoneAAdapter);*/

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Booth_Booking_Form.this, Booth_Event_Activity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
         /*   case R.id.btnLogin:
                startActivity(new Intent(Booth_Booking_Form.this, Booth_Booking_Form.class));
                finish();
                break;
*/
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.view_image:
                // Showimage(swe);
                break;

            case R.id.view_image1:
                // Showimage(swe);
                break;

            case R.id.view_image2:
                // Showimage(swe);
                break;

            case R.id.view_image3:
                // Showimage(swe);
                break;

            case R.id.view_map_image:

                Showimage_map();
                break;


            case R.id.btn_select_booth_type:
                lin_booth_booking.setVisibility(View.GONE);
                lin_booth_selected.setVisibility(View.VISIBLE);
                logoside_sele.setVisibility(View.VISIBLE);
                logoside.setVisibility(View.GONE);

                break;

            case R.id.proceed:
                lin_booth_booking.setVisibility(View.VISIBLE);
                lin_booth_selected.setVisibility(View.GONE);
                break;

            case R.id.logoside_sele:
                logoside.setVisibility(View.VISIBLE);
                lin_booth_booking.setVisibility(View.VISIBLE);
                lin_booth_selected.setVisibility(View.GONE);
                logoside_sele.setVisibility(View.GONE);
                break;


            case R.id.procee1d:
                if (Booth_Booking_Form.this.isNetworkConnected()) {

                    if (validate()) {
                        if (!(list_booth.size() == 0)) {
                            if (!(list_slot.size() == 0)) {
                                if (!(Equpmentdata.equals(""))) {
                                    if (compliment == true) {
                                        showProgress();
                                        Save_Booth_Booking();
                                    } else {
                                        Toast.makeText(appController, "Please Check Commitment", Toast.LENGTH_SHORT).show();

                                    }

                                } else {
                                    Toast.makeText(appController, "Please selec Equpment", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(appController, "Please select Booth Slot", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(appController, "Please select Booth", Toast.LENGTH_SHORT).show();
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
               /* startActivity(new Intent(Booth_Booking_Form.this, Booth_Booking_Success.class));
                finish();*/
                break;
        }
    }

    private boolean validate() {
        int totalchai = 0;
        int totaltable = 0;
        F_Name = editText_volun_name.getText().toString();
        L_Name = editText_last_name.getText().toString();
        Pho_Num = editText_mobile.getText().toString();
        Email = editText_email.getText().toString();
        Nature_buss = editText_nbuss.getText().toString();
        Licence_Num = editText_nbuss.getText().toString();
        Civil_Num = editText_civil_n0.getText().toString();
        Table_Num = editText_rent.getText().toString();
        chair_Num = editText_rent_chair.getText().toString();
        Insta_acc = editText_insta_acoo.getText().toString();
        Type_here = editText_type_here.getText().toString();

        if (!chair_Num.isEmpty() && !Table_Num.isEmpty()) {
            totalchai = Integer.parseInt(chair_Num);
            totaltable = Integer.parseInt(Table_Num);

            Totalchairprice = Per_ChairPrice * totalchai;
            Totaltableprice = Per_TablePrice * totaltable;
        }

        if (TextUtils.isEmpty(editText_volun_name.getText().toString())) {
            editText_volun_name.setError("Please Enter First Name");
            editText_volun_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_last_name.getText().toString())) {
            editText_last_name.setError("Please Enter Last Name");
            editText_last_name.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Please Enter Phone Number");
            editText_mobile.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your  E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_nbuss.getText().toString())) {
            editText_nbuss.setError("Please Enter Nature Of Bussiness");
            editText_nbuss.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_lice_n0.getText().toString())) {
            editText_lice_n0.setError("Please Enter Licence Number");
            editText_lice_n0.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_civil_n0.getText().toString())) {
            editText_civil_n0.setError("Please Enter Civil Number");
            editText_civil_n0.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_rent.getText().toString())) {
            editText_rent.setError("Please Enter Number of Table");
            editText_rent.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_rent_chair.getText().toString())) {
            editText_rent_chair.setError("Please Enter Number of Chair");
            editText_rent_chair.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_insta_acoo.getText().toString())) {
            editText_insta_acoo.setError("Please Enter bussiness Insagram");
            editText_insta_acoo.requestFocus();
            return false;
        }

        return true;
    }

    private void Save_Booth_Booking() {
        Save_Booth_BookingPojo save_booth_bookingPojo = new Save_Booth_BookingPojo();


        save_booth_bookingPojo.setUserId(appPreferences.getuserid());
        save_booth_bookingPojo.setEvent_SrNo(appPreferences.get_ComingEventSRNO());
        save_booth_bookingPojo.setFName(F_Name);
        save_booth_bookingPojo.setLName(L_Name);
        save_booth_bookingPojo.setPhone(Pho_Num);
        save_booth_bookingPojo.setEmail(Email);
        save_booth_bookingPojo.setCommitment(compliment);
        save_booth_bookingPojo.setBusiness_Nature(Nature_buss);
        save_booth_bookingPojo.setBusiness_License(Licence_Num);
        save_booth_bookingPojo.setCivilid(Civil_Num);
        save_booth_bookingPojo.setNoOfTables(Table_Num);
        save_booth_bookingPojo.setTablesPrice(String.valueOf(Per_TablePrice));
        save_booth_bookingPojo.setNoOfChairs(chair_Num);
        save_booth_bookingPojo.setChairsPrice(String.valueOf(Per_ChairPrice));
        save_booth_bookingPojo.setEquipment(Equpmentdata);
        save_booth_bookingPojo.setOtherEquipment(Type_here);
        save_booth_bookingPojo.setBusiness_Insta(Insta_acc);
        save_booth_bookingPojo.setTotalPrice("0.0");
        save_booth_bookingPojo.setBooth_Trx(list_booth);
        save_booth_bookingPojo.setBooth_Booking_Slot_Trx(list_slot);
        save_booth_bookingPojo.setSource("Android");
        save_booth_bookingPojo.setCorpcentreby(appPreferences.get_company_id());
        save_booth_bookingPojo.setIpAddress(sessionManager.GetIpAddress());
        save_booth_bookingPojo.setUniqueId(sessionManager.GetUniqueId());
        save_booth_bookingPojo.setCommand("Save");

        Call<Save_Booth_BookingPojo> call2 = apiInterface.Save_Booth_Booking(save_booth_bookingPojo);
        call2.enqueue(new Callback<Save_Booth_BookingPojo>() {
            @Override
            public void onResponse(Call<Save_Booth_BookingPojo> call, Response<Save_Booth_BookingPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        appPreferences.save_Attribute_Book(response.body().getAttribute1());
                        appPreferences.save_trackid_Book(response.body().getTrackId());


                        startActivity(new Intent(Booth_Booking_Form.this, BoothBookingPayment.class));
                        finish();
                        //   TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        //   TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-12")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        //   TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

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
            public void onFailure(Call<Save_Booth_BookingPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }


    private void showProgress() {
        progressDialog = new ProgressDialog(Booth_Booking_Form.this);
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
    private void Showimage(String swe) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image_ar, viewGroup, false);

        }

        cross = dialogView.findViewById(R.id.cross);
        booth_image = dialogView.findViewById(R.id.booth_image);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Picasso.with(this)
                .load("https://api.barayihsalem.com/Upload/Booth/" + swe)
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                .fit()
                .into(booth_image);


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void Showimage_map() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image_map, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image_map_ar, viewGroup, false);

        }


        cross = dialogView.findViewById(R.id.cross);
        map = dialogView.findViewById(R.id.map);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();


        Picasso.with(this)
                .load("https://api.barayihsalem.com/Upload/Event_Approval/Map/" + Map_Image)
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                .fit()
                .into(map);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    private void findid() {
        abcd = findViewById(R.id.abcd);
        tems_cond = findViewById(R.id.tems_cond);
        tems_cond.setTypeface(typeRegular);

        tems_cond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    tems_cond.setSelected(isChecked);
                    compliment = true;

                } else {
                    tems_cond.setSelected(false);
                    compliment = false;


                }
            }
        });

        lin_boot_a = findViewById(R.id.lin_boot_a);
        lin_booth_booking = findViewById(R.id.lin_booth_booking);
        lin_booth_selected = findViewById(R.id.lin_booth_selected);
        lin_booth_booking.setVisibility(View.VISIBLE);
        lin_booth_selected.setVisibility(View.GONE);

        view_image = findViewById(R.id.view_image);
        txt_booth_b = findViewById(R.id.txt_booth_b);
        price_booth_b = findViewById(R.id.price_booth_b);
        txt_booth_b.setTypeface(typeRegular);
        price_booth_b.setTypeface(typebold);
        rw_zonea = findViewById(R.id.rw_zonea);
        rw_electricity_list = findViewById(R.id.rw_electricity_list);
        rw_selectboot = findViewById(R.id.rw_selectboot);
        rw_zoneb = findViewById(R.id.rw_zoneb);
        txt_phone = findViewById(R.id.txt_phone);
        editText_lice_n0 = findViewById(R.id.editText_lice_n0);
        txt_fr = findViewById(R.id.txt_fr);
        view_image1 = findViewById(R.id.view_image1);
        view_map_image = findViewById(R.id.view_map_image);
        view_image2 = findViewById(R.id.view_image2);
        view_image3 = findViewById(R.id.view_image3);
        txt_rent_chair = findViewById(R.id.txt_rent_chair);
        txt_gender = findViewById(R.id.txt_gender);
        logoside = findViewById(R.id.logoside);
        logoside_sele = findViewById(R.id.logoside_sele);
        logoside_sele.setVisibility(View.GONE);
        procee1d = findViewById(R.id.procee1d);
        proceed = findViewById(R.id.proceed);
        btn_select_booth_type = findViewById(R.id.btn_select_booth_type);
        editText_insta_acoo = findViewById(R.id.editText_insta_acoo);
        editText_type_here = findViewById(R.id.editText_type_here);
        editText_civil_n0 = findViewById(R.id.editText_civil_n0);
        tet_lic_need = findViewById(R.id.tet_lic_need);
        editText_email = findViewById(R.id.editText_email);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_nbuss = findViewById(R.id.editText_nbuss);
        editText_pirple = findViewById(R.id.editText_pirple);
        aboutbs = findViewById(R.id.aboutbs);
        aboutbs_selection = findViewById(R.id.aboutbs_selection);
        select_booth = findViewById(R.id.select_booth);
        txt_instagram_account = findViewById(R.id.aboutbs);
        editText_rent_chair = findViewById(R.id.editText_rent_chair);
        editText_both_empty = findViewById(R.id.editText_both_empty);
        editText_rent = findViewById(R.id.editText_rent);
        txt_identifi = findViewById(R.id.txt_identifi);
        txt_rent_pirple = findViewById(R.id.txt_rent_pirple);
        txt_subject_avai = findViewById(R.id.txt_subject_avai);
        editText_both = findViewById(R.id.editText_both);
        txt_provide_need_rent = findViewById(R.id.txt_provide_need_rent);
        txt_provide_need = findViewById(R.id.txt_provide_need);
        txt_booth_need = findViewById(R.id.txt_booth_need);
        txt_fr_a = findViewById(R.id.txt_fr_a);
        txt_rent = findViewById(R.id.txt_rent);
        txt_frid = findViewById(R.id.txt_frid);
        txt_ = findViewById(R.id.txt_);
        txt_fro = findViewById(R.id.txt_fro);
        text_block = findViewById(R.id.text_block);
        txt_full_name = findViewById(R.id.txt_full_name);
        editText_volun_name = findViewById(R.id.editText_volun_name);
        editText_last_name = findViewById(R.id.editText_last_name);
        mem_price = findViewById(R.id.mem_price);
        txt_lic_n0 = findViewById(R.id.txt_lic_n0);
        txt_email = findViewById(R.id.txt_email);
        price = findViewById(R.id.price);
        tet_lic_n0 = findViewById(R.id.tet_lic_n0);
        aboutbs.setTypeface(typebold);
        aboutbs_selection.setTypeface(typebold);
        select_booth.setTypeface(typebold);
        procee1d.setTypeface(typeRegular);
        mem_price.setTypeface(typeRegular);
        btn_select_booth_type.setTypeface(typebold);
        txt_lic_n0.setTypeface(typeRegular);
        editText_nbuss.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        editText_civil_n0.setTypeface(typeRegular);
        tet_lic_need.setTypeface(typeRegular);

        proceed.setTypeface(typeRegular);
        txt_.setTypeface(typeRegular);
        txt_fro.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        editText_last_name.setTypeface(typeRegular);
        editText_lice_n0.setTypeface(typeRegular);
        tet_lic_n0.setTypeface(typeRegular);
        procee1d.setTypeface(typeRegular);
        txt_frid.setTypeface(typeRegular);
        txt_fr.setTypeface(typeRegular);
        txt_full_name.setTypeface(typeRegular);
        txt_phone.setTypeface(typeRegular);
        editText_both_empty.setTypeface(typeRegular);
        editText_volun_name.setTypeface(typeRegular);
        txt_booth_need.setTypeface(typeRegular);
        editText_both.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        txt_email.setTypeface(typeRegular);
        editText_rent.setTypeface(typeRegular);
        editText_rent.setTypeface(typeRegular);
        text_block.setTypeface(typeRegular);
        txt_subject_avai.setTypeface(typeRegular);
        editText_rent_chair.setTypeface(typeRegular);
        txt_rent_chair.setTypeface(typeRegular);
        txt_provide_need_rent.setTypeface(typeRegular);
        txt_fr_a.setTypeface(typeRegular);
        txt_provide_need.setTypeface(typeRegular);
        txt_rent.setTypeface(typeRegular);
        txt_rent_pirple.setTypeface(typeRegular);
        editText_pirple.setTypeface(typeRegular);
        editText_insta_acoo.setTypeface(typeRegular);
        txt_instagram_account.setTypeface(typeRegular);
        editText_type_here.setTypeface(typeRegular);
        txt_identifi.setTypeface(typeRegular);
        view_map_image.setTypeface(typeRegular);
        price.setTypeface(typebold);
        logoside.setOnClickListener(this);
        logoside_sele.setOnClickListener(this);
        view_image.setVisibility(View.GONE);
        view_image1.setVisibility(View.GONE);
        view_image2.setVisibility(View.GONE);
        view_image3.setVisibility(View.GONE);
        view_image3.setVisibility(View.GONE);

        // view_map_image.setText(Html.fromHtml(getString(R.string.your_string_here_)));
        if (appPreferences.getCulturemode().equals("1")) {

            view_image.setText(Html.fromHtml(getString(R.string.your_string_here)));
            view_image1.setText(Html.fromHtml(getString(R.string.your_string_here)));
            view_image2.setText(Html.fromHtml(getString(R.string.your_string_here)));
            view_image3.setText(Html.fromHtml(getString(R.string.your_string_here)));
            view_map_image.setText(Html.fromHtml("<u>View Event Map Here</u>"));

        } else {
            view_map_image.setText(Html.fromHtml("<u>عرض خريطة الفعالية هنا</u>"));

            view_image.setText(Html.fromHtml("<u>عرض الصورة</u>"));
            view_image1.setText(Html.fromHtml("<u>عرض الصورة</u>"));
            view_image2.setText(Html.fromHtml("<u>عرض الصورة</u>"));
            view_image3.setText(Html.fromHtml("<u>عرض الصورة</u>"));

        }
        view_image.setTypeface(typeRegular);
        view_image1.setTypeface(typeRegular);
        view_image2.setTypeface(typeRegular);
        view_image3.setTypeface(typeRegular);
        view_map_image.setTypeface(typeRegular);

        proceed.setOnClickListener(this);
        view_image.setOnClickListener(this);
        view_map_image.setOnClickListener(this);
        view_image1.setOnClickListener(this);
        view_image2.setOnClickListener(this);
        view_image3.setOnClickListener(this);
        procee1d.setOnClickListener(this);
        btn_select_booth_type.setOnClickListener(this);

    }

    private void Get_BoothSlot_list_ByBooth() {
        Get_BoothSlot_list_Pojo get_boothSlot_list_pojo = new Get_BoothSlot_list_Pojo();
        get_boothSlot_list_pojo.setValue(appPreferences.get_ComingEventSRNO());
        get_boothSlot_list_pojo.setValue1(zonadata);
        get_boothSlot_list_pojo.setUserId(appPreferences.getuserid());
        get_boothSlot_list_pojo.setCultureId(appPreferences.getCulturemode());
        get_boothSlot_list_pojo.setCorpcentreBy(appPreferences.get_company_id());


        Call<Get_BoothSlot_list_Pojo> call2 = apiInterface.Get_BoothSlot_list_ByBooth(get_boothSlot_list_pojo);
        call2.enqueue(new Callback<Get_BoothSlot_list_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Get_BoothSlot_list_Pojo> call, Response<Get_BoothSlot_list_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());

                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {


                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Zone_list_main.addAll(response.body().getList());


                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                        rw_zonea.setLayoutManager(gridLayoutManager);
                        zoneAAdapter = new Zone_A_Adapter_New(Booth_Booking_Form.this, Zone_list_main);
                        rw_zonea.setAdapter(zoneAAdapter);




                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {

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
            public void onFailure(Call<Get_BoothSlot_list_Pojo> call, Throwable t) {
                call.cancel();
                // hideProgress();
            }
        });


    }

    private class Get_BB_Event_Detail_BySrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BB_Event_Detail_BySrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("BB_Event_Detail_BySrNo", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        Event_Name = collegeData.getString("Event_Name");
                        Event_Duration = collegeData.getString("Event_Duration");
                        Currency = collegeData.getString("Currency");
                        Per_ChairPrice = collegeData.getDouble("Per_ChairPrice");
                        Per_TablePrice = collegeData.getDouble("Per_TablePrice");


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

    private class Get_BB_Map_Image_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BB_Map_Image_By_SrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("image", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        Map_Image = collegeData.getString("Event_NaMap_Imageme");


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

    private class Get_BB_High_Electricity_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BB_High_Electricity_List','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("BB_High_Electricity_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Electricity_list.addAll(partnerPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_electricity_list.setLayoutManager(gridLayoutManager);
                eletricity_adapter = new Eletricity_Adapter(Booth_Booking_Form.this, Electricity_list);
                rw_electricity_list.setAdapter(eletricity_adapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_BB_BoothList_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BB_BoothList_By_SrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("BoothList_By_SrNo", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Booth_list.addAll(partnerPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_selectboot.setLayoutManager(gridLayoutManager);
                select_boot_adapter = new Select_Boot_Adapter(Booth_Booking_Form.this, Booth_list);
                rw_selectboot.setAdapter(select_boot_adapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Get_BB_Created_BoothSlot_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_BB_Created_BoothSlot_List','" + appPreferences.get_ComingEventSRNO() + "','" + zonadata + "','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_BB_Created_BoothSlot_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Zone_list.addAll(partnerPojo.row);

              /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                rw_zonea.setLayoutManager(gridLayoutManager);
                zoneAAdapter = new Zone_A_Adapter_New(Booth_Booking_Form.this, Zone_list);
                rw_zonea.setAdapter(zoneAAdapter);*/


            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Eletricity_Adapter extends RecyclerView.Adapter<MyViewHolder_Eletric> {
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
        ArrayList<String> stringequp;
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Eletricity_Adapter(Context context, ArrayList<Row> listData) {
            this.context = context;
            this.listData = listData;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolder_Eletric onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();
            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;
            heding = appController.heding;
            typebold = appController.typebold;
            typeLight = appController.typeLight;
            // ArrayList<String> stringequp = new ArrayList<String>();

            // infalte the item Layout
            View v;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.electric_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.electric_list, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder_Eletric vh = new MyViewHolder_Eletric(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder_Eletric holder, int position) {
            stringequp = new ArrayList<String>();


            holder.Fridge.setTypeface(typeRegular);

            holder.Fridge.setText(listData.get(position).getxName());

            holder.Fridge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.Fridge.isChecked()) {
                        stringequp.add(listData.get(position).getxCode());
                    } else {
                        stringequp.remove(listData.get(position).getxCode());
                    }
                    String[] stringArray = stringequp.toArray(new String[stringequp.size()]);

                    Equpmentdata = TextUtils.join(",", stringArray);
                    Log.d("data", Equpmentdata);
                }
            });


            /* holder.Fridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                    if (b == true) {
                        stringequp.add(listData.get(position).getxCode());


                    } else {
                        stringequp.remove(listData.get(position).getxCode());

                    }

                    String[] stringArray = stringequp.toArray(new String[stringequp.size()]);

                    Equpmentdata = TextUtils.join(",", stringArray);
                }
            });
*/

            // zonadata = TextUtils.join(",", stringArray);


            // stringArrayList.remove(listData.get(position).getxCode());
            // final ZonePojo model = listData.get(position);

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            //  listData.size();
            return listData == null ? 0 : listData.size();
        }
    }

    private class Select_Boot_Adapter extends RecyclerView.Adapter<MyViewHolder_SelectBooth> {
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
        ArrayList<String> stringArrayList = new ArrayList<String>();

        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Select_Boot_Adapter(Context context, ArrayList<Row> listData) {
            this.context = context;
            this.listData = listData;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolder_SelectBooth onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectbootlist, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectbootlist_ar, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder_SelectBooth vh = new MyViewHolder_SelectBooth(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder_SelectBooth holder, int position) {

            ArrayList<String> abc = new ArrayList<>();

            holder.booth_a.setTypeface(typebold);

            holder.booth_a.setText(listData.get(position).getxName());
            ArrayList<String> list = new ArrayList();

            holder.booth_a.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    abc.add(listData.get(position).getxCode());

                } else {
                    abc.remove(listData.get(position).getxCode());

                }
            });

            holder.view_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String swe = listData.get(position).getImage1();
                    Showimage(swe);
                }
            });




           /* for (int i=0; i<jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
            }*/

//if you want your array

            zonadata = TextUtils.join(",", abc);
            Log.d("Select_abc", zonadata);
           /* holder.booth_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if ()
                   *//* if (holder.booth_a.isChecked()) {

                        abc.add(listData.get(position).getxCode());
                    } else {
                        abc.remove(listData.get(position).getxCode());

                    }*//*


                }
            });
*/

            holder.booth_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        abc.add(listData.get(position).getxCode());
                        stringArrayList.add(listData.get(position).getxCode()); //add to arraylist

                        holder.lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background_check_selection));


                        HashMap<String, String> hashmap = new HashMap();

                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put("Sr", String.valueOf(position + 1));
                        temp.put("BoothType", listData.get(position).getxCode());
                        list_booth.add(temp);


                    } else {
                        abc.remove(listData.get(position).getxCode());
                        stringArrayList.remove(listData.get(position).getxCode()); //add to arraylist

                        holder.lin_boot_a.setBackground(getResources().getDrawable(R.drawable.edittext_background));
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put("Sr", String.valueOf(position));
                        temp.put("BoothType", listData.get(position).getxCode());
                        list_booth.remove(temp);
                    }


                    String[] stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);

                    zonadata = TextUtils.join(",", stringArray);
                    Zone_list_main.clear();
                    rw_zonea.setAdapter(null);
                    Get_BoothSlot_list_ByBooth();
                    // new Get_BB_Created_BoothSlot_List().execute();
                }
            });
            /*if (holder.booth_a.isSelected()) {
                abc.add(listData.get(position).getxCode());
            }else {
                abc.remove(listData.get(position).getxCode());
            }
*/


            //asfaf
        }


        @Override
        public int getItemCount() {
            //  listData.size();
            return listData == null ? 0 : listData.size();
        }
    }

    private class Zone_A_Adapter_New extends RecyclerView.Adapter<MyViewHoldeBB_zone> {
        Context context;
        AppPreferences appPreferences;

        Zone_A_Adapter_Details_New zoneAAdapterDetails;
        LinearLayout lin_membackground;
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<ListZone> zone_list = new ArrayList<>();
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Zone_A_Adapter_New(Booth_Booking_Form booth_booking_form, ArrayList<ListZone> zone_list) {
            this.context = booth_booking_form;
            this.zone_list = zone_list;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHoldeBB_zone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list, parent, false);

            }


            MyViewHoldeBB_zone vh = new MyViewHoldeBB_zone(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoldeBB_zone holder, int position) {

            holder.booth_.setTypeface(typeRegular);
            holder.booth_.setText(zone_list.get(position).getBoothTypeName());
            holder.price.setText("");


          /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            holder.rw_zonelist.setLayoutManager(gridLayoutManager);
            zoneAAdapterDetails = new Zone_A_Adapter_Details_New(context, zone_list);
            holder.rw_zonelist.setAdapter(zoneAAdapterDetails);*/


            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            holder.rw_zonelist.setLayoutManager(gridLayoutManager);
            Zone_A_Adapter_Slot zoneAAdapterDetails = new Zone_A_Adapter_Slot(context, zone_list.get(position).getZone().getLiZone());
            holder.rw_zonelist.setAdapter(zoneAAdapterDetails);



//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            return zone_list.size();
        }


    }


    private class Zone_A_Adapter_Details_New extends RecyclerView.Adapter<MyViewHolderZone> {
        Context context;
        AppPreferences appPreferences;
        ImageView next;
        TextView date, date_;
        RecyclerView rw_zonelist;
        Zone_A_Adapter_Details zoneAAdapterDetails;
        LinearLayout lin_date;
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<ListZone> zone_list = new ArrayList<>();
        ArrayList<LiZone> zone_Slot = new ArrayList<>();
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;


        public Zone_A_Adapter_Details_New(Context context, ArrayList<ListZone> zone_list) {
            this.context = context;
            this.zone_list = zone_list;
        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolderZone onCreateViewHolder(@NonNull ViewGroup
                                                           parent, int viewType) {
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
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list_details, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list_details, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolderZone vh = new MyViewHolderZone(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolderZone holder, int position) {
            zone_Slot.addAll(zone_list.get(position).getZone().getLiZone());
            holder.date.setTypeface(typeRegular);


            holder.date.setText(zone_list.get(position).getZone().getZoneName());

           /* GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            holder.rw_zonelist_slot.setLayoutManager(gridLayoutManager);
            Zone_A_Adapter_Slot zoneAAdapterDetails = new Zone_A_Adapter_Slot(context, zone_list.get(position).getZone().getLiZone());
            holder.rw_zonelist_slot.setAdapter(zoneAAdapterDetails);*/

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            return zone_list.size();
        }


    }

    private class Zone_A_Adapter_Slot extends RecyclerView.Adapter<MyViewHolderSlot> {
        Context context;
        AppPreferences appPreferences;
        ImageView next;
        TextView date, date_;
        RecyclerView rw_zonelist;
        Zone_A_Adapter_Details zoneAAdapterDetails;
        LinearLayout lin_date;
        AppController appController;
        String Select_Zone = "";
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        List<LiZone> zone_list = new ArrayList<>();
        ArrayList<String> abc = new ArrayList<>();

        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;


        public Zone_A_Adapter_Slot(Context context, List<LiZone> zone_list) {
            this.context = context;
            this.zone_list = zone_list;
        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolderSlot onCreateViewHolder(@NonNull ViewGroup
                                                           parent, int viewType) {
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
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolderSlot vh = new MyViewHolderSlot(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolderSlot holder, int position) {
            String asc = String.valueOf(zone_list.get(position).getEventBoothTrxSr());
            String www = String.valueOf(zone_list.get(position).getBook());

            holder.date.setTypeface(typeRegular);
            holder.date.setText(asc);
            if (www.equals("false")) {
                holder.date.setClickable(true);
                holder.date.setFocusable(true);
            } else {
                holder.date.setBackgroundColor(context.getResources().getColor(R.color.thincolcor));
                holder.date.setClickable(false);
                holder.date.setFocusable(false);
            }


            holder.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String www = String.valueOf(zone_list.get(position).getBook());
                    if (www.equals("true")) {
                        holder.date.setClickable(false);
                        holder.date.setFocusable(false);
                        Toast.makeText(Booth_Booking_Form.this, "Alredy Booked", Toast.LENGTH_SHORT).show();
                    } else {
                        holder.date.setClickable(true);
                        holder.date.setFocusable(true);
                        zone_list.get(position).setSelected(!zone_list.get(position).isSelected());

                        if (zone_list.get(position).isSelected()) {
                            Select_Zone = zone_list.get(position).getEventBoothTrxSrNo();
                        }
                        if (zone_list.get(position).isSelected()) {
                            abc.add(Select_Zone);
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put("EventBoothTrx_SrNo", (zone_list.get(position).getEventBoothTrxSrNo()));
                            temp.put("EventBoothTrx_Sr", String.valueOf((zone_list.get(position).getEventBoothTrxSr())));
                            temp.put("Price", String.valueOf(zone_list.get(position).getPrice()));
                            temp.put("Commission", String.valueOf(zone_list.get(position).getCommission()));
                            temp.put("TotalPrice", String.valueOf(zone_list.get(position).getTotalPrice()));
                            list_slot.add(temp);
                        } else {
                            abc.remove(Select_Zone);
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put("EventBoothTrx_SrNo", (zone_list.get(position).getEventBoothTrxSrNo()));
                            temp.put("EventBoothTrx_Sr", String.valueOf((zone_list.get(position).getEventBoothTrxSr())));
                            temp.put("Price", String.valueOf(zone_list.get(position).getPrice()));
                            temp.put("Commission", String.valueOf(zone_list.get(position).getCommission()));
                            temp.put("TotalPrice", String.valueOf(zone_list.get(position).getTotalPrice()));
                            list_slot.remove(temp);

                        }
                        holder.date.setBackgroundColor(zone_list.get(position).isSelected() ? Color.GREEN : Color.WHITE);


                        String zonadata = TextUtils.join(",", abc);
                        //  holder.date.setBackgroundColor(listData.get(position).isSelected() ? R.drawable.edittext_select_zone : R.drawable.edittext_deselect_zone);
                        //holder.date.setBackground(context.getResources().getDrawable(R.drawable.edittext_select_zone));
                    }
                }
            });
            Log.d("Select_Zone12", String.valueOf(abc));


            // holder.date.setText(zone_list.get(position).getEventBoothTrxSr());


//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }


        @Override
        public int getItemCount() {
            return zone_list.size();
        }


    }

}