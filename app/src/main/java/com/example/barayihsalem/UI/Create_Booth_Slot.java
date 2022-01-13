package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Booth_Zone_Adapter;
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Save_Create_Booth_SlotPojo;
import com.example.barayihsalem.UI.Model.Slot_;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
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
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.UpcominiEvent_Details.my_acount;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Create_Booth_Slot extends AppCompatActivity implements View.OnClickListener {
    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    static final ArrayList<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
    static final ArrayList<HashMap<String, String>> list3 = new ArrayList<HashMap<String, String>>();
    TextView aboutbs, txt_name, txt_eventname, txt_booth_a, txt_booth_b, txt_booth_c, txt_both_c_value, txt_both_b_value, txt_both_a_value, txt_zone, txt_zone_value, transaction_id, transaction_id_value, payment_id, payment_id_value;
    AppController appController;
    AppPreferences appPreferences;
    int slotlistsize=0;
    ArrayList<List> slo_list = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    Slot_list_Adapter slot_list_adapter;
    String price1 = "";
    String price2 = "";
    String price3 = "";
    String Zone1 = "";
    String Zone2 = "";
    String Zone3 = "";

    String BoothType1 = "";
    String BoothType2 = "";
    String BoothType3 = "";

    String description1 = "";
    String description2 = "";
    String description3 = "";
    APIInterface apiInterface;
    SessionManager sessionManager;
    LinearLayout table_a, table_b, table_c, table_d, table_e, table_f, table_g, table_h, table_i, table_l, table_j, table_k;
    String Event_SrNo, Event_Name, BoothType_A_Name, BoothType_A_Size, BoothType_B_Name, BoothType_B_Size, BoothType_C_Name, BoothType_C_Size, Zone;
    ImageView logoside;
    Nationality_Adapter nationality_adapter;
    ArrayList<Row> Area_list = new ArrayList<Row>();
    ArrayList<Row> Boot_list = new ArrayList<Row>();
    String AreaA_Xcode = "";
    String AreaB_Xcode = "";
    String AreaC_Xcode = "";
    String AreaD_Xcode = "";
    String AreaE_Xcode = "";
    String AreaF_Xcode = "";
    String AreaG_Xcode = "";
    String AreaL_Xcode = "";
    String AreaH_Xcode = "";
    String AreaI_Xcode = "";
    String AreaJ_Xcode = "";
    String AreaK_Xcode = "";
    String BootA_Xcode = "";
    String BootB_Xcode = "";
    String BootC_Xcode = "";
    String BootD_Xcode = "";
    String BootE_Xcode = "";
    String BootF_Xcode = "";
    String BootG_Xcode = "";
    String BootH_Xcode = "";
    String BootI_Xcode = "";
    String BootJ_Xcode = "";
    String BootK_Xcode = "";
    String BootL_Xcode = "";
    int i = 1;

    Button proceed, addrow, skip, save;
    RecyclerView rw_list, rw_slot_list;
    Booth_Zone_Adapter zone_select_adapter;
    RelativeLayout abcd;
    String Boot_a, Boot_b, Boot_c, Boot_d, Boot_e, Boot_f, Boot_g, Boot_h, Boot_i, Boot_j, Boot_k, Boot_l;
    ProgressDialog progressDialog;
    TextView zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9, zone10, zone11, zone12;
    TextView zone_d_a, zone_d_b, zone_d_c, zone_d_d, zone_d_e, zone_d_f, zone_d_g, zone_d_h, zone_d_i, zone_d_j, zone_d_k, zone_d_l;
    EditText editText_boot_a, editText_boot_b, editText_boot_c, editText_boot_d, editText_boot_e, editText_boot_f, editText_boot_g, editText_boot_h, editText_boot_j, editText_boot_k, editText_boot_l, editText_boot_i;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Spinner spi_area_a, spi_area_b, spi_area_d, spi_area_e, spi_area_g, spi_area_f, spi_area_h, spi_area_j, spi_area_L, spi_area_k, spi_area_i, spi_area_c,
            spi_boot_a, spi_boot_b, spi_boot_c, spi_boot_d, spi_boot_e, spi_boot_f, spi_boot_g, spi_boot_h, spi_boot_i, spi_boot_j, spi_boot_k, spi_boot_l;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_create__time__slot);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_create__time__slot_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();
        list.clear();
        list2.clear();
        list3.clear();
        apiInterface = APIClient.getClient().create(APIInterface.class);


        if (Create_Booth_Slot.this.isNetworkConnected()) {
            new Get_Create_BoothSlot_Event_Detail_By_SrNo().execute();
            new Get_Create_BoothSlot_ZoneList_By_SrNo().execute();
            new Get_Create_BoothSlot_BoothList_By_SrNo().execute();

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

      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_list.setLayoutManager(gridLayoutManager);
        zone_select_adapter = new Booth_Zone_Adapter(Create_Booth_Slot.this);
        rw_list.setAdapter(zone_select_adapter);
*/

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid() {
        editText_boot_a = findViewById(R.id.editText_boot_a);
        editText_boot_b = findViewById(R.id.editText_boot_b);
        editText_boot_c = findViewById(R.id.editText_boot_c);
        editText_boot_d = findViewById(R.id.editText_boot_d);
        editText_boot_e = findViewById(R.id.editText_boot_e);
        editText_boot_f = findViewById(R.id.editText_boot_f);
        editText_boot_g = findViewById(R.id.editText_boot_g);
        editText_boot_h = findViewById(R.id.editText_boot_h);
        editText_boot_i = findViewById(R.id.editText_boot_i);
        editText_boot_j = findViewById(R.id.editText_boot_j);
        editText_boot_k = findViewById(R.id.editText_boot_k);
        editText_boot_l = findViewById(R.id.editText_boot_l);


        table_a = findViewById(R.id.table_a);
        table_b = findViewById(R.id.table_b);
        table_c = findViewById(R.id.table_c);
        table_d = findViewById(R.id.table_d);
        table_e = findViewById(R.id.table_e);
        table_f = findViewById(R.id.table_f);
        table_g = findViewById(R.id.table_g);
        table_h = findViewById(R.id.table_h);
        table_i = findViewById(R.id.table_i);
        table_j = findViewById(R.id.table_j);
        table_k = findViewById(R.id.table_k);
        table_l = findViewById(R.id.table_l);

        table_b.setVisibility(View.GONE);
        table_c.setVisibility(View.GONE);
        table_d.setVisibility(View.GONE);
        table_e.setVisibility(View.GONE);
        table_f.setVisibility(View.GONE);
        table_g.setVisibility(View.GONE);
        table_h.setVisibility(View.GONE);
        table_i.setVisibility(View.GONE);
        table_j.setVisibility(View.GONE);
        table_k.setVisibility(View.GONE);
        table_l.setVisibility(View.GONE);

        zone_d_a = findViewById(R.id.zone_d_a);
        zone_d_b = findViewById(R.id.zone_d_b);
        zone_d_c = findViewById(R.id.zone_d_c);
        zone_d_d = findViewById(R.id.zone_d_d);
        zone_d_e = findViewById(R.id.zone_d_e);
        zone_d_f = findViewById(R.id.zone_d_f);
        zone_d_g = findViewById(R.id.zone_d_g);
        zone_d_h = findViewById(R.id.zone_d_h);
        zone_d_i = findViewById(R.id.zone_d_i);
        zone_d_j = findViewById(R.id.zone_d_j);
        zone_d_k = findViewById(R.id.zone_d_k);
        zone_d_l = findViewById(R.id.zone_d_l);

        zone_d_j.setOnClickListener(this);
        zone_d_k.setOnClickListener(this);
        zone_d_l.setOnClickListener(this);
        zone_d_a.setOnClickListener(this);
        zone_d_b.setOnClickListener(this);
        zone_d_c.setOnClickListener(this);
        zone_d_d.setOnClickListener(this);
        zone_d_e.setOnClickListener(this);
        zone_d_f.setOnClickListener(this);
        zone_d_g.setOnClickListener(this);
        zone_d_h.setOnClickListener(this);
        zone_d_i.setOnClickListener(this);

        zone1 = findViewById(R.id.zone1);
        zone2 = findViewById(R.id.zone2);
        zone3 = findViewById(R.id.zone3);
        zone4 = findViewById(R.id.zone4);
        zone5 = findViewById(R.id.zone5);
        zone6 = findViewById(R.id.zone6);
        zone7 = findViewById(R.id.zone7);
        zone8 = findViewById(R.id.zone8);
        zone9 = findViewById(R.id.zone9);
        zone10 = findViewById(R.id.zone10);
        zone11 = findViewById(R.id.zone11);
        zone12 = findViewById(R.id.zone12);

        zone3.setTypeface(typeRegular);
        zone4.setTypeface(typeRegular);
        zone5.setTypeface(typeRegular);
        zone6.setTypeface(typeRegular);
        zone7.setTypeface(typeRegular);
        zone8.setTypeface(typeRegular);
        zone9.setTypeface(typeRegular);
        zone10.setTypeface(typeRegular);
        zone11.setTypeface(typeRegular);
        zone12.setTypeface(typeRegular);


        spi_boot_a = findViewById(R.id.spi_boot_a);
        spi_boot_b = findViewById(R.id.spi_boot_b);
        spi_boot_c = findViewById(R.id.spi_boot_c);
        spi_boot_d = findViewById(R.id.spi_boot_d);
        spi_boot_e = findViewById(R.id.spi_boot_e);
        spi_boot_f = findViewById(R.id.spi_boot_f);
        spi_boot_g = findViewById(R.id.spi_boot_g);
        spi_boot_i = findViewById(R.id.spi_boot_i);
        spi_boot_h = findViewById(R.id.spi_boot_h);
        spi_boot_j = findViewById(R.id.spi_boot_j);
        spi_boot_l = findViewById(R.id.spi_boot_l);
        spi_boot_k = findViewById(R.id.spi_boot_k);

        spi_area_a = findViewById(R.id.spi_area_a);
        spi_area_b = findViewById(R.id.spi_area_b);
        spi_area_c = findViewById(R.id.spi_area_c);
        spi_area_d = findViewById(R.id.spi_area_d);
        spi_area_e = findViewById(R.id.spi_area_e);
        spi_area_g = findViewById(R.id.spi_area_g);
        spi_area_f = findViewById(R.id.spi_area_f);
        spi_area_h = findViewById(R.id.spi_area_h);
        spi_area_j = findViewById(R.id.spi_area_j);
        spi_area_L = findViewById(R.id.spi_area_l);
        spi_area_k = findViewById(R.id.spi_area_k);
        spi_area_i = findViewById(R.id.spi_area_i);
        abcd = findViewById(R.id.abcd);
        proceed = findViewById(R.id.proceed);
        aboutbs = findViewById(R.id.aboutbs);
        txt_name = findViewById(R.id.txt_name);
        txt_eventname = findViewById(R.id.txt_eventname);
        txt_booth_a = findViewById(R.id.txt_booth_a);
        txt_booth_b = findViewById(R.id.txt_booth_b);
        txt_booth_c = findViewById(R.id.txt_booth_c);
        txt_both_a_value = findViewById(R.id.txt_both_a_value);
        txt_both_c_value = findViewById(R.id.txt_both_c_value);
        txt_both_b_value = findViewById(R.id.txt_both_b_value);
        txt_zone = findViewById(R.id.txt_zone);
        txt_zone_value = findViewById(R.id.txt_zone_value);
        addrow = findViewById(R.id.addrow);
        skip = findViewById(R.id.skip);
        save = findViewById(R.id.save);
        aboutbs.setTypeface(typebold);
        proceed.setTypeface(typeRegular);
        skip.setTypeface(typeRegular);
        addrow.setTypeface(typeRegular);
        txt_name.setTypeface(typeRegular);
        txt_eventname.setTypeface(typeRegular);
        txt_both_a_value.setTypeface(typeRegular);
        txt_both_b_value.setTypeface(typeRegular);
        txt_zone.setTypeface(typeRegular);
        txt_booth_a.setTypeface(typeRegular);
        txt_booth_c.setTypeface(typeRegular);
        txt_zone_value.setTypeface(typeRegular);
        zone1.setTypeface(typeRegular);
        save.setTypeface(typeRegular);
        zone2.setTypeface(typeRegular);
        zone3.setTypeface(typeRegular);
        rw_list = findViewById(R.id.rw_list);
        rw_slot_list = findViewById(R.id.rw_slot_list);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        proceed.setOnClickListener(this);
        addrow.setOnClickListener(this);
        skip.setOnClickListener(this);
        save.setOnClickListener(this);
        if (my_acount == true) {
            skip.setVisibility(View.GONE);
        }else {
            skip.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onBackPressed() {
        if (my_acount == true) {
            startActivity(new Intent(Create_Booth_Slot.this, UpcominiEvent_Details.class));
            finish();
            return;
        }
       /* if (back == true) {
            startActivity(new Intent(Create_Booth_Slot.this, Create_Events.class));
            finish();
        } else {
            startActivity(new Intent(Create_Booth_Slot.this, Book_Location_Events.class));
            finish();
        }*/

        startActivity(new Intent(Create_Booth_Slot.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.zone_d_b:
                i--;
                table_b.setVisibility(View.GONE);
                break;

            case R.id.zone_d_c:
                i--;

                table_c.setVisibility(View.GONE);
                break;

            case R.id.zone_d_e:
                i--;

                table_e.setVisibility(View.GONE);
                break;

            case R.id.zone_d_f:
                i--;

                table_f.setVisibility(View.GONE);
                break;

            case R.id.zone_d_g:
                i--;

                table_g.setVisibility(View.GONE);
                break;

            case R.id.zone_d_h:
                i--;

                table_h.setVisibility(View.GONE);
                break;

            case R.id.zone_d_d:
                i--;

                table_d.setVisibility(View.GONE);
                break;

            case R.id.zone_d_i:
                i--;
                table_i.setVisibility(View.GONE);
                break;

            case R.id.zone_d_j:
                i--;
                table_j.setVisibility(View.GONE);
                break;

            case R.id.zone_d_k:
                i--;
                table_k.setVisibility(View.GONE);
                break;

            case R.id.zone_d_l:
                i--;
                table_l.setVisibility(View.GONE);
                break;


            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.skip:
                startActivity(new Intent(Create_Booth_Slot.this, HomeActivity.class));
                finish();
                break;


            case R.id.addrow:


                if (i < 12) {
                    i++;

                }

                if (i == 2) {
                    table_b.setVisibility(View.VISIBLE);
                }

                if (i == 3) {
                    table_c.setVisibility(View.VISIBLE);
                }
                if (i == 4) {
                    table_d.setVisibility(View.VISIBLE);

                }

                if (i == 5) {
                    table_e.setVisibility(View.VISIBLE);

                }

                if (i == 6) {
                    table_f.setVisibility(View.VISIBLE);

                }


                if (i == 7) {
                    table_g.setVisibility(View.VISIBLE);

                }


                if (i == 8) {
                    table_h.setVisibility(View.VISIBLE);

                }

                if (i == 9) {
                    table_i.setVisibility(View.VISIBLE);

                }

                if (i == 10) {
                    table_j.setVisibility(View.VISIBLE);

                }

                if (i == 11) {
                    table_k.setVisibility(View.VISIBLE);

                }

                if (i == 12) {
                    table_l.setVisibility(View.VISIBLE);

                }


                break;

            case R.id.proceed:

                if (Create_Booth_Slot.this.isNetworkConnected()) {
                    if (validate()) {
                        showProgress();
                        Create_Booth_SlotAPI();
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

            case R.id.save:

                if (Create_Booth_Slot.this.isNetworkConnected()) {
                    if (validate_save()) {

                        int list2size=list2.size();
                        if ((slotlistsize ==list2size)) {
                            if (!price1.equals("")) {
                                showProgress();
                                Save_Create_Booth_Slot();
                            } else {
                                Toast.makeText(appController, "Please enter Price", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(appController, "Please fill all field", Toast.LENGTH_SHORT).show();

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

    private boolean validate_save() {


     /*   HashMap<String, String> temp_n = new HashMap<String, String>();
        temp_n.put("Sr", "1");
        temp_n.put("Zone", Zone1);
        temp_n.put("BoothType", BoothType2);
        temp_n.put("Ordering", "1");
        temp_n.put("Description", description1);

        temp_n.put("Price", price1);
        list2.add(temp_n);


        HashMap<String, String> temp_n1 = new HashMap<String, String>();
        temp_n1.put("Sr", "2");
        temp_n1.put("Zone", Zone2);
        temp_n1.put("BoothType", BoothType2);
        temp_n1.put("Ordering", "2");
        temp_n1.put("Description", description2);

        temp_n1.put("Price", price2);
        list2.add(temp_n1);
*/

       /* HashMap<String, String> temp_nlist3 = new HashMap<String, String>();
        temp_nlist3.put("Sr", "1");
        temp_nlist3.put("Zone", Zone1);
        temp_nlist3.put("BoothType", BoothType2);
        temp_nlist3.put("Qty", Boot_a);

        list3.add(temp_nlist3);


        HashMap<String, String> temp_list3 = new HashMap<String, String>();
        temp_list3.put("Sr", "2");
        temp_list3.put("Zone", Zone2);
        temp_list3.put("BoothType", BoothType2);
        temp_list3.put("Qty", Boot_b);

        list3.add(temp_list3);






*/



        /*HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("Sr", "1");
        temp.put("Zone", AreaA_Xcode);
        temp.put("Booth", BootA_Xcode);

        temp.put("Actual", Boot_a);
        list.add(temp);
        if (TextUtils.isEmpty(editText_boot_a.getText().toString())) {
            editText_boot_a.setError("Please Enter Mobile Number ");
            editText_boot_a.requestFocus();
            return false;
        }


        HashMap<String, String> temp1 = new HashMap<String, String>();
        temp1.put("Sr", "2");
        temp1.put("Zone", AreaB_Xcode);
        temp1.put("Booth", BootB_Xcode);

        temp1.put("Actual", Boot_b);*/

        return true;
    }

    private void Save_Create_Booth_Slot() {

        /*  ArrayList<String> uniqueList = new ArrayList<String>(set);*/
        Save_Create_Booth_SlotPojo save_create_booth_slotPojo = new Save_Create_Booth_SlotPojo();

        save_create_booth_slotPojo.setEvent_SrNo(appPreferences.get_Attribute1());
        save_create_booth_slotPojo.setEvents_Booth(list3);
        save_create_booth_slotPojo.setEvents_BoothTrx(list2);
        save_create_booth_slotPojo.setUserId(appPreferences.getuserid());
        save_create_booth_slotPojo.setCorpcentreby(appPreferences.get_company_id());
        save_create_booth_slotPojo.setIpAddress(sessionManager.GetIpAddress());
        save_create_booth_slotPojo.setCommand("Save");


        Call<Save_Create_Booth_SlotPojo> call2 = apiInterface.Save_Create_Booth_Slot(save_create_booth_slotPojo);
        call2.enqueue(new Callback<Save_Create_Booth_SlotPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Save_Create_Booth_SlotPojo> call, Response<Save_Create_Booth_SlotPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
                        list2.clear();
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Create_Booth_Slot.this, HomeActivity.class));
                        finish();
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //   list2.clear();

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-8")) {
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
            public void onFailure(Call<Save_Create_Booth_SlotPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate() {

        Boot_a = editText_boot_a.getText().toString();
        Boot_b = editText_boot_b.getText().toString();

        Boot_c = editText_boot_c.getText().toString();
        Boot_d = editText_boot_d.getText().toString();
        Boot_e = editText_boot_e.getText().toString();
        Boot_f = editText_boot_f.getText().toString();
        Boot_g = editText_boot_g.getText().toString();
        Boot_h = editText_boot_h.getText().toString();
        Boot_i = editText_boot_i.getText().toString();
        Boot_j = editText_boot_j.getText().toString();
        Boot_k = editText_boot_k.getText().toString();
        Boot_l = editText_boot_l.getText().toString();


        if (TextUtils.isEmpty(editText_boot_a.getText().toString())) {
            editText_boot_a.setError("Please Enter value ");
            editText_boot_a.requestFocus();
            return false;
        }

        if (!Boot_a.equals("")) {

            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("Sr", "1");
            temp.put("Zone", AreaA_Xcode);
            temp.put("Booth", BootA_Xcode);
            temp.put("Actual", Boot_a);
            list.add(temp);

            HashMap<String, String> temp_ = new HashMap<String, String>();
            temp_.put("Sr", "1");
            temp_.put("Zone", AreaA_Xcode);
            temp_.put("BoothType", BootA_Xcode);
            temp_.put("Qty", Boot_a);
            list3.add(temp_);


        }

        if (!Boot_b.equals("")) {

            HashMap<String, String> temp1 = new HashMap<String, String>();
            temp1.put("Sr", "2");
            temp1.put("Zone", AreaB_Xcode);
            temp1.put("Booth", BootB_Xcode);
            temp1.put("Actual", Boot_b);
            list.add(temp1);

            HashMap<String, String> temp_1 = new HashMap<String, String>();
            temp_1.put("Sr", "2");
            temp_1.put("Zone", AreaB_Xcode);
            temp_1.put("BoothType", BootB_Xcode);
            temp_1.put("Qty", Boot_b);
            list3.add(temp_1);
        }

        if (!Boot_c.equals("")) {

            HashMap<String, String> temp123 = new HashMap<String, String>();
            temp123.put("Sr", "3");
            temp123.put("Zone", AreaC_Xcode);
            temp123.put("Booth", BootC_Xcode);
            temp123.put("Actual", Boot_c);
            list.add(temp123);


            HashMap<String, String> temp_2 = new HashMap<String, String>();
            temp_2.put("Sr", "3");
            temp_2.put("Zone", AreaC_Xcode);
            temp_2.put("BoothType", BootC_Xcode);
            temp_2.put("Qty", Boot_c);
            list3.add(temp_2);
        }
        if (!Boot_d.equals("")) {

            HashMap<String, String> temp4 = new HashMap<String, String>();
            temp4.put("Sr", "4");
            temp4.put("Zone", AreaD_Xcode);
            temp4.put("Booth", BootD_Xcode);
            temp4.put("Actual", Boot_d);
            list.add(temp4);


            HashMap<String, String> temp_3 = new HashMap<String, String>();
            temp_3.put("Sr", "4");
            temp_3.put("Zone", AreaD_Xcode);
            temp_3.put("BoothType", BootD_Xcode);
            temp_3.put("Qty", Boot_d);
            list3.add(temp_3);
        }

        if (!Boot_e.equals("")) {

            HashMap<String, String> temp5 = new HashMap<String, String>();
            temp5.put("Sr", "5");
            temp5.put("Zone", AreaE_Xcode);
            temp5.put("Booth", BootE_Xcode);
            temp5.put("Actual", Boot_e);
            list.add(temp5);


            HashMap<String, String> temp_4 = new HashMap<String, String>();
            temp_4.put("Sr", "5");
            temp_4.put("Zone", AreaE_Xcode);
            temp_4.put("BoothType", BootE_Xcode);
            temp_4.put("Qty", Boot_e);
            list3.add(temp_4);
        }

        if (!Boot_f.equals("")) {

            HashMap<String, String> temp6 = new HashMap<String, String>();
            temp6.put("Sr", "6");
            temp6.put("Zone", AreaF_Xcode);
            temp6.put("Booth", BootF_Xcode);
            temp6.put("Actual", Boot_f);
            list.add(temp6);


            HashMap<String, String> temp_5 = new HashMap<String, String>();
            temp_5.put("Sr", "6");
            temp_5.put("Zone", AreaF_Xcode);
            temp_5.put("BoothType", BootF_Xcode);
            temp_5.put("Qty", Boot_f);
            list3.add(temp_5);
        }

        if (!Boot_g.equals("")) {

            HashMap<String, String> temp7 = new HashMap<String, String>();
            temp7.put("Sr", "7");
            temp7.put("Zone", AreaG_Xcode);
            temp7.put("Booth", BootG_Xcode);
            temp7.put("Actual", Boot_g);
            list.add(temp7);


            HashMap<String, String> temp_6 = new HashMap<String, String>();
            temp_6.put("Sr", "7");
            temp_6.put("Zone", AreaG_Xcode);
            temp_6.put("BoothType", BootG_Xcode);
            temp_6.put("Qty", Boot_g);
            list3.add(temp_6);
        }
        if (!Boot_h.equals("")) {

            HashMap<String, String> temp8 = new HashMap<String, String>();
            temp8.put("Sr", "8");
            temp8.put("Zone", AreaH_Xcode);
            temp8.put("Booth", BootH_Xcode);
            temp8.put("Actual", Boot_h);
            list.add(temp8);


            HashMap<String, String> temp_7 = new HashMap<String, String>();
            temp_7.put("Sr", "8");
            temp_7.put("Zone", AreaH_Xcode);
            temp_7.put("BoothType", BootH_Xcode);
            temp_7.put("Qty", Boot_h);
            list3.add(temp_7);
        }
        if (!Boot_i.equals("")) {

            HashMap<String, String> temp9 = new HashMap<String, String>();
            temp9.put("Sr", "9");
            temp9.put("Zone", AreaI_Xcode);
            temp9.put("Booth", BootI_Xcode);
            temp9.put("Actual", Boot_i);
            list.add(temp9);


            HashMap<String, String> temp_8 = new HashMap<String, String>();
            temp_8.put("Sr", "9");
            temp_8.put("Zone", AreaI_Xcode);
            temp_8.put("BoothType", BootI_Xcode);
            temp_8.put("Qty", Boot_i);
            list3.add(temp_8);
        }
        if (!Boot_j.equals("")) {

            HashMap<String, String> temp10 = new HashMap<String, String>();
            temp10.put("Sr", "10");
            temp10.put("Zone", AreaJ_Xcode);
            temp10.put("Booth", BootJ_Xcode);
            temp10.put("Actual", Boot_j);
            list.add(temp10);


            HashMap<String, String> temp_9 = new HashMap<String, String>();
            temp_9.put("Sr", "10");
            temp_9.put("Zone", AreaJ_Xcode);
            temp_9.put("BoothType", BootJ_Xcode);
            temp_9.put("Qty", Boot_j);
            list3.add(temp_9);
        }

        if (!Boot_k.equals("")) {

            HashMap<String, String> temp11 = new HashMap<String, String>();
            temp11.put("Sr", "11");
            temp11.put("Zone", AreaK_Xcode);
            temp11.put("Booth", BootK_Xcode);
            temp11.put("Actual", Boot_k);
            list.add(temp11);


            HashMap<String, String> temp_10 = new HashMap<String, String>();
            temp_10.put("Sr", "11");
            temp_10.put("Zone", AreaK_Xcode);
            temp_10.put("BoothType", BootK_Xcode);
            temp_10.put("Qty", Boot_k);
            list3.add(temp_10);
        }


        if (!Boot_l.equals("")) {
            HashMap<String, String> temp12 = new HashMap<String, String>();
            temp12.put("Sr", "12");
            temp12.put("Zone", AreaL_Xcode);
            temp12.put("Booth", BootL_Xcode);
            temp12.put("Actual", Boot_l);
            list.add(temp12);


            HashMap<String, String> temp_11 = new HashMap<String, String>();
            temp_11.put("Sr", "12");
            temp_11.put("Zone", AreaL_Xcode);
            temp_11.put("BoothType", BootL_Xcode);
            temp_11.put("Qty", Boot_l);
            list3.add(temp_11);

        }



      /*  list.add(temp1);
        if (TextUtils.isEmpty(editText_boot_a.getText().toString())) {
            editText_boot_a.setError("Please Enter Mobile Number ");
            editText_boot_a.requestFocus();
            return false;
        }*/
        return true;
    }

    private void Create_Booth_SlotAPI() {
        Create_Booth_SlotAPIPojo create_booth_slotAPIPojo = new Create_Booth_SlotAPIPojo();

        create_booth_slotAPIPojo.setBoothA_XCode(BootA_Xcode);
        create_booth_slotAPIPojo.setBoothB_XCode(BootB_Xcode);
        create_booth_slotAPIPojo.setBoothC_XCode(BootC_Xcode);
        create_booth_slotAPIPojo.setBoothA_Size(BoothType_A_Size);
        create_booth_slotAPIPojo.setBoothB_Size(BoothType_B_Size);
        create_booth_slotAPIPojo.setBoothC_Size(BoothType_C_Size);
        create_booth_slotAPIPojo.setLi(list);
        create_booth_slotAPIPojo.setCultureId(appPreferences.getCulturemode());
        create_booth_slotAPIPojo.setCorpcentreBy(appPreferences.get_company_id());


        Call<Create_Booth_SlotAPIPojo> call2 = apiInterface.Create_Booth_Slot(create_booth_slotAPIPojo);
        call2.enqueue(new Callback<Create_Booth_SlotAPIPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Create_Booth_SlotAPIPojo> call, Response<Create_Booth_SlotAPIPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
                        list.clear();
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        slo_list.addAll(response.body().getList());
                         slotlistsize=slo_list.size();
                       // Log.d("slotlistsize", String.valueOf(slotlistsize));


                        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                        rw_slot_list.setLayoutManager(gridLayoutManager);
                        slot_list_adapter = new Slot_list_Adapter(Create_Booth_Slot.this, slo_list);
                        rw_slot_list.setAdapter(slot_list_adapter);

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-6")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-8")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-10")) {
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-12")) {
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
            public void onFailure(Call<Create_Booth_SlotAPIPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Create_Booth_Slot.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private class Get_Create_BoothSlot_Event_Detail_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Create_BoothSlot_Event_Detail_By_SrNo','" + appPreferences.get_Attribute1() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                Log.d("event", query);
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
            Log.d("BoothSlot_Event_Detail_By_SrNo", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        Event_SrNo = collegeData.getString("Event_SrNo");
                        Event_Name = collegeData.getString("Event_Name");
                        BoothType_A_Name = collegeData.getString("BoothType_A_Name");
                        BoothType_A_Size = collegeData.getString("BoothType_A_Size");
                        BoothType_B_Name = collegeData.getString("BoothType_B_Name");
                        BoothType_B_Size = collegeData.getString("BoothType_B_Size");
                        BoothType_C_Name = collegeData.getString("BoothType_C_Name");
                        BoothType_C_Size = collegeData.getString("BoothType_C_Size");
                        Zone = collegeData.getString("Zone");


                        txt_eventname.setText(Event_Name);
                        txt_eventname.setText(Event_Name);
                        txt_booth_a.setText(BoothType_A_Name);
                        txt_booth_b.setText(BoothType_B_Name);
                        txt_booth_c.setText(BoothType_C_Name);
                        txt_both_a_value.setText(BoothType_A_Size);
                        txt_both_b_value.setText(BoothType_B_Size);
                        txt_both_c_value.setText(BoothType_C_Size);
                        txt_zone_value.setText(Zone);


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }


    private class Get_Create_BoothSlot_ZoneList_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Create_BoothSlot_ZoneList_By_SrNo','" + appPreferences.get_Attribute1() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                Log.d("asd", query);
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
            Log.d("Create_BoothSlot_ZoneList_By_SrNo", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                Area_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res);
                spi_area_a.setAdapter(nationality_adapter);
                spi_area_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_a.setSelection(position);
                        AreaA_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaA_Xcode", AreaA_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res1 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res1);
                spi_area_b.setAdapter(nationality_adapter);
                spi_area_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_b.setSelection(position);
                        AreaB_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaB_Xcode", AreaB_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res2 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res2);
                spi_area_c.setAdapter(nationality_adapter);
                spi_area_c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_c.setSelection(position);
                        AreaC_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaA_Xcode", AreaA_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res3 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res3);
                spi_area_d.setAdapter(nationality_adapter);
                spi_area_d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_d.setSelection(position);
                        AreaD_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaD_Xcode", AreaD_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res4 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res4);
                spi_area_e.setAdapter(nationality_adapter);
                spi_area_e.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_e.setSelection(position);
                        AreaE_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaE_Xcode", AreaE_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res5 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res5);
                spi_area_f.setAdapter(nationality_adapter);
                spi_area_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_f.setSelection(position);
                        AreaF_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaF_Xcode", AreaF_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res6 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res6);
                spi_area_g.setAdapter(nationality_adapter);
                spi_area_g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_g.setSelection(position);
                        AreaG_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaG_Xcode", AreaG_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res7 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res7);
                spi_area_h.setAdapter(nationality_adapter);
                spi_area_h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_h.setSelection(position);
                        AreaH_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaH_Xcode", AreaH_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res8 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res8);
                spi_area_i.setAdapter(nationality_adapter);
                spi_area_i.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_i.setSelection(position);
                        AreaI_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaI_Xcode", AreaI_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res9 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res9);
                spi_area_j.setAdapter(nationality_adapter);
                spi_area_j.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_j.setSelection(position);
                        AreaJ_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaJ_Xcode", AreaJ_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
                Resources res10 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res10);
                spi_area_k.setAdapter(nationality_adapter);
                spi_area_k.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_k.setSelection(position);
                        AreaK_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaK_Xcode", AreaK_Xcode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res11 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Area_list, res11);
                spi_area_L.setAdapter(nationality_adapter);
                spi_area_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_area_L.setSelection(position);
                        AreaL_Xcode = Area_list.get(position).xCode;
                        Log.e("AreaL_Xcode", AreaL_Xcode);

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

    private class Get_Create_BoothSlot_BoothList_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Create_BoothSlot_BoothList_By_SrNo','" + appPreferences.get_Attribute1() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("BoothSlot_BoothList_By_SrNo", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Boot_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res);
                spi_boot_a.setAdapter(nationality_adapter);
                spi_boot_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_a.setSelection(position);
                        BootA_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootA_Xcode", BootA_Xcode);
                        if (BootA_Xcode.equals("BT10000001")) {
                            zone1.setText(BoothType_A_Size);
                        }

                        if (BootA_Xcode.equals("BT10000002")) {
                            zone1.setText(BoothType_B_Size);
                        }

                        if (BootA_Xcode.equals("BT10000003")) {
                            zone1.setText(BoothType_C_Size);
                        }


                       /* zone2.setText(BoothType_A_Size);
                        zone3.setText(BoothType_A_Size);*/
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res1 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res1);
                spi_boot_b.setAdapter(nationality_adapter);
                spi_boot_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_b.setSelection(position);
                        BootB_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootB_Xcode", BootB_Xcode);
                        // zone1.setText(BoothType_B_Size);
                        //  zone2.setText(BoothType_B_Size);


                        if (BootB_Xcode.equals("BT10000001")) {
                            zone2.setText(BoothType_A_Size);
                        }

                        if (BootB_Xcode.equals("BT10000002")) {
                            zone2.setText(BoothType_B_Size);
                        }

                        if (BootB_Xcode.equals("BT10000003")) {
                            zone2.setText(BoothType_C_Size);
                        }


                        //   zone3.setText(BoothType_B_Size);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res2 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res2);
                spi_boot_c.setAdapter(nationality_adapter);
                spi_boot_c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_c.setSelection(position);
                        BootC_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootC_Xcode", BootC_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootC_Xcode.equals("BT10000001")) {
                            zone3.setText(BoothType_A_Size);
                        }

                        if (BootC_Xcode.equals("BT10000002")) {
                            zone3.setText(BoothType_B_Size);
                        }

                        if (BootC_Xcode.equals("BT10000003")) {
                            zone3.setText(BoothType_C_Size);
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });


                Resources res3 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res3);
                spi_boot_d.setAdapter(nationality_adapter);
                spi_boot_d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_d.setSelection(position);
                        BootD_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootD_Xcode", BootD_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootD_Xcode.equals("BT10000001")) {
                            zone4.setText(BoothType_A_Size);
                        }

                        if (BootD_Xcode.equals("BT10000002")) {
                            zone4.setText(BoothType_B_Size);
                        }

                        if (BootD_Xcode.equals("BT10000003")) {
                            zone4.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res4 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res4);
                spi_boot_e.setAdapter(nationality_adapter);
                spi_boot_e.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_e.setSelection(position);
                        BootE_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootE_Xcode", BootE_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootE_Xcode.equals("BT10000001")) {
                            zone5.setText(BoothType_A_Size);
                        }

                        if (BootE_Xcode.equals("BT10000002")) {
                            zone5.setText(BoothType_B_Size);
                        }

                        if (BootE_Xcode.equals("BT10000003")) {
                            zone5.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res5 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res5);
                spi_boot_f.setAdapter(nationality_adapter);
                spi_boot_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_f.setSelection(position);
                        BootF_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootF_Xcode", BootF_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootF_Xcode.equals("BT10000001")) {
                            zone6.setText(BoothType_A_Size);
                        }

                        if (BootF_Xcode.equals("BT10000002")) {
                            zone6.setText(BoothType_B_Size);
                        }

                        if (BootF_Xcode.equals("BT10000003")) {
                            zone6.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res6 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res6);
                spi_boot_g.setAdapter(nationality_adapter);
                spi_boot_g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_g.setSelection(position);
                        BootG_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootG_Xcode", BootG_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootG_Xcode.equals("BT10000001")) {
                            zone7.setText(BoothType_A_Size);
                        }

                        if (BootG_Xcode.equals("BT10000002")) {
                            zone7.setText(BoothType_B_Size);
                        }

                        if (BootG_Xcode.equals("BT10000003")) {
                            zone7.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res7 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res7);
                spi_boot_h.setAdapter(nationality_adapter);
                spi_boot_h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_h.setSelection(position);
                        BootH_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootH_Xcode", BootH_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootH_Xcode.equals("BT10000001")) {
                            zone8.setText(BoothType_A_Size);
                        }

                        if (BootH_Xcode.equals("BT10000002")) {
                            zone8.setText(BoothType_B_Size);
                        }

                        if (BootH_Xcode.equals("BT10000003")) {
                            zone8.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res8 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res8);
                spi_boot_i.setAdapter(nationality_adapter);
                spi_boot_i.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_i.setSelection(position);
                        BootI_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootI_Xcode", BootI_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootI_Xcode.equals("BT10000001")) {
                            zone9.setText(BoothType_A_Size);
                        }

                        if (BootI_Xcode.equals("BT10000002")) {
                            zone9.setText(BoothType_B_Size);
                        }

                        if (BootI_Xcode.equals("BT10000003")) {
                            zone9.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res9 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res9);
                spi_boot_j.setAdapter(nationality_adapter);
                spi_boot_j.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_j.setSelection(position);
                        BootJ_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootJ_Xcode", BootJ_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootJ_Xcode.equals("BT10000001")) {
                            zone10.setText(BoothType_A_Size);
                        }

                        if (BootJ_Xcode.equals("BT10000002")) {
                            zone10.setText(BoothType_B_Size);
                        }

                        if (BootJ_Xcode.equals("BT10000003")) {
                            zone10.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
                //-------

                Resources res10 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res10);
                spi_boot_l.setAdapter(nationality_adapter);
                spi_boot_l.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_l.setSelection(position);
                        BootL_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootL_Xcode", BootL_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootL_Xcode.equals("BT10000001")) {
                            zone11.setText(BoothType_A_Size);
                        }

                        if (BootL_Xcode.equals("BT10000002")) {
                            zone11.setText(BoothType_B_Size);
                        }

                        if (BootL_Xcode.equals("BT10000003")) {
                            zone11.setText(BoothType_C_Size);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                Resources res11 = getResources();
                nationality_adapter = new Nationality_Adapter(Create_Booth_Slot.this, R.layout.countryspinner_country_new, Boot_list, res11);
                spi_boot_k.setAdapter(nationality_adapter);
                spi_boot_k.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        spi_boot_k.setSelection(position);
                        BootK_Xcode = Boot_list.get(position).xCode;
                        Log.e("BootC_Xcode", BootK_Xcode);
                     /*   zone1.setText(BoothType_C_Size);
                        zone2.setText(BoothType_C_Size);*/
                        if (BootK_Xcode.equals("BT10000001")) {
                            zone12.setText(BoothType_A_Size);
                        }

                        if (BootK_Xcode.equals("BT10000002")) {
                            zone12.setText(BoothType_B_Size);
                        }

                        if (BootK_Xcode.equals("BT10000003")) {
                            zone12.setText(BoothType_C_Size);
                        }

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

    private class Slot_list_Adapter extends RecyclerView.Adapter<Slot_> {

        Context context;
        AppPreferences appPreferences;

        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<List> Common_list = new ArrayList<>();
        private OnItemClickListener.OnClickCallback onClickCall;


        public Slot_list_Adapter(Create_Booth_Slot create_booth_slot, ArrayList<List> slo_list) {

            this.context = create_booth_slot;
            this.Common_list = slo_list;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public Slot_ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();

            // infalte the item Layout
            View v;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_list, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            Slot_ vh = new Slot_(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull Slot_ holder, int position) {


            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;
            heding = appController.heding;
            typebold = appController.typebold;
            typeLight = appController.typeLight;

            holder.edit_price.setTypeface(typeRegular);
            holder.description.setTypeface(typeRegular);
            holder.Number.setTypeface(typeRegular);
            holder.booth_name.setTypeface(typeRegular);
            holder.zone_name.setTypeface(typeRegular);


            holder.zone_name.setText(Common_list.get(position).getZoneName());
            holder.booth_name.setText(Common_list.get(position).getBoothName());
            holder.Number.setText(Common_list.get(position).getNo());
          /*  holder.description.setText(Common_list.get(position).getDescription());
            holder.edit_price.setText(Common_list.get(position).getPrice());*/

            BoothType1 = Common_list.get(position).getBooth();

            holder.description.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    HashMap<String, String> temp_n1 = new HashMap<String, String>();
                    description1 = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {


                    // return;
                }
            });

            holder.edit_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    price1 = s.toString();
                    HashMap<String, String> temp_n1 = new HashMap<String, String>();
                    int sr = Common_list.get(position).getSr();
                    boolean isExit = false;

                    for (HashMap<String, String> k : list2) {

                        if (k.get("Id") == Common_list.get(position).getId()) {
                            k.put("Description", description1);
                            k.put("Price", price1);
                            isExit = true;
                        }

                    }

                    if (isExit == false) {
                        temp_n1.put("Id", Common_list.get(position).getId());
                        temp_n1.put("Sr", String.valueOf(sr));
                        temp_n1.put("Zone", Common_list.get(position).getZone());
                        temp_n1.put("BoothType", Common_list.get(position).getBooth());
                        temp_n1.put("Ordering", String.valueOf(Common_list.get(position).getNo()));
                        temp_n1.put("Description", description1);
                        temp_n1.put("Price", price1);
                        list2.add(temp_n1);
                    }
                    //  return;

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            // list2.add(temp_n1);







  /*          if (position == 0) {
                BoothType1 = Common_list.get(position).getBooth();
                // Zone1 = Common_list.get(position).getZoneName();
                Zone1 = Common_list.get(position).getZone();
                // price1 = holder.edit_price.getText().toString();

                holder.edit_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        price1 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.description.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        description1 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }
            if (position == 1) {
                BoothType2 = Common_list.get(position).getBooth();

                Zone2 = Common_list.get(position).getZone();
                // price2 = holder.edit_price.getText().toString();
                // description2 = holder.description.getText().toString();


                holder.edit_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        price2 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.description.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        description2 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
            if (position == 2) {
                BoothType3 = Common_list.get(position).getBooth();
                //   BoothType3 = Common_list.get(position).getb();

                Zone3 = Common_list.get(position).getZone();
              *//*  price3 = holder.edit_price.getText().toString();
                description3 = holder.description.getText().toString();
                *//*
                holder.edit_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        price3 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.description.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        description3 = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }*/


        }


        @Override
        public int getItemCount() {
            return Common_list.size();
        }
    }

}

