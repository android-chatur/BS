package com.example.barayihsalem.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.VisionPartnerAdapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionPartnerGovernmentOnlyPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerPublicPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerSponserPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerland_loadsPojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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

import static com.example.barayihsalem.UI.BecomeResident_member.hasPermissions;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class BecomeVisPart extends AppCompatActivity implements View.OnClickListener {

    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private static int RESULT_LOAD_IMAGE_camera = 0;
    private static int RESULT_LOAD_IMAGE = 1;
    JSONArray jsonArr = new JSONArray();
    Bitmap bitmap = null;
    String Age = "";
    String From_date = "";
    String To_date = "";
    String uniqueidimage;
    TextView txtlabl_, txtlable, txtlable1, title, job_title, pass_txt, text_sigup, munici_corp, supported, oprated;
    TextView public_txt_association, public_txt_representive, public_txt_em_reprentative, main_sponser_corporate_name, main_sponser_representive, main_sponser_txt_dona_amount;
    AppController appController;
    AppPreferences appPreferences;
    APIInterface apiInterface;
    SessionManager sessionManager;
    TextView main_sponser_txt_mobile_nuber, main_sponser_txt_email, land_loads_bulding1, land_loads_bulding2, land_loads_txt_mobile_nuber;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    ProgressDialog progressDialog;
    Button btnLogin, btn_uplod_landload;
    JSONArray jsonArray;
    int i = 2;
    List<String> data;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    Button btn_uploadlogo, btn_add_morebulding, btn_uplod_sponse, btn_uplod_goverment, btn_uplod_public;
    Spinner id_slect_vp;
    String work_name_gove, Title_gove, Job_Title_gove, Short_Desc_gove;
    String Bulding_num_landloads, Bulding_num_landloads2 = "", Mobile_landloads, Descri_landloads;
    String Bulding_num_landloads3 = "", Bulding_num_landloads4 = "", Bulding_num_landloads5 = "", Bulding_num_landloads6 = "", Bulding_num_landloads7 = "", Bulding_num_landloads8 = "", Bulding_num_landloads9 = "", Bulding_num_landloads10 = "";
    String Association_Name_public, Reprentative_name_public, Reprentative_email_public, short_desc_public;
    String Corp_name_sponser, repren_name_sponser, Done_ammou_sponser, Mobile_sponser, Email_sponser, Description_sponser;
    LinearLayout lin_goverment, lin_public, lin_main_sponser, lin_land_loads;
    EditText main_sponser_editText_mobile_number, main_sponser_editText_email, land_loads_editText_bulding_number1, land_loads_editText_bulding_number2, land_loads_editText_mobile_number;
    EditText editText_work, editText_title, editText_job, main_sponser_editText_corporate_name, main_sponser_editText_representative, main_sponser_editText_dona_amount;
    EditText editText_des_main_public, public_editText_assocition, public_editText_representative, editText_des_main_goverment, public_editText_emai_reprentative, editText_des_land_load, editText_des_main_sponser;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    VisionPartnerAdapter adapter;
    String type = "";
    String asd = "";
    List arrayList;
    JSONArray jsonArray1;
    String changedString;
    TextView land_loads_bulding3, land_loads_bulding4, land_loads_bulding5, land_loads_bulding6, land_loads_bulding7, land_loads_bulding8, land_loads_bulding9, land_loads_bulding10;
    EditText land_loads_editText_bulding_number3, land_loads_editText_bulding_number4, land_loads_editText_bulding_number5, land_loads_editText_bulding_number6, land_loads_editText_bulding_number7,
            land_loads_editText_bulding_number8, land_loads_editText_bulding_number9, land_loads_editText_bulding_number10;
    LinearLayout lin_bulding_3, lin_bulding_4, lin_bulding_5, lin_bulding_6, lin_bulding_7, lin_bulding_8, lin_bulding_9, lin_bulding_10;
    String Vision_Partner_Type = "";
    ArrayList<Row> spinner_list = new ArrayList<Row>();
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    private byte[] byteArray;
    private String encoded_passport_frant = "";
    private String imageName_passport_frant = "";
    private String imageName = "", Concate = "";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

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
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_become_vis_part);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_become_vis_part_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        findid();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (BecomeVisPart.this.isNetworkConnected()) {


            new Get_VP_Type_List().execute();

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

        lin_goverment.setVisibility(View.VISIBLE);
        lin_public.setVisibility(View.GONE);
        lin_main_sponser.setVisibility(View.GONE);
        lin_land_loads.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(BecomeVisPart.this, VisionPartner.class));
        finish();
    }

    private void findid() {

        land_loads_editText_bulding_number3 = findViewById(R.id.land_loads_editText_bulding_number3);
        land_loads_editText_bulding_number4 = findViewById(R.id.land_loads_editText_bulding_number4);
        land_loads_editText_bulding_number5 = findViewById(R.id.land_loads_editText_bulding_number5);
        land_loads_editText_bulding_number6 = findViewById(R.id.land_loads_editText_bulding_number6);
        land_loads_editText_bulding_number7 = findViewById(R.id.land_loads_editText_bulding_number7);
        land_loads_editText_bulding_number8 = findViewById(R.id.land_loads_editText_bulding_number8);
        land_loads_editText_bulding_number9 = findViewById(R.id.land_loads_editText_bulding_number9);
        land_loads_editText_bulding_number10 = findViewById(R.id.land_loads_editText_bulding_number10);
        land_loads_editText_bulding_number3.setTypeface(typeRegular);
        land_loads_editText_bulding_number4.setTypeface(typeRegular);
        land_loads_editText_bulding_number5.setTypeface(typeRegular);
        land_loads_editText_bulding_number6.setTypeface(typeRegular);
        land_loads_editText_bulding_number7.setTypeface(typeRegular);
        land_loads_editText_bulding_number8.setTypeface(typeRegular);
        land_loads_editText_bulding_number9.setTypeface(typeRegular);
        land_loads_editText_bulding_number10.setTypeface(typeRegular);
        land_loads_editText_bulding_number3.setTypeface(typeRegular);

        land_loads_bulding3 = findViewById(R.id.land_loads_bulding3);
        land_loads_bulding4 = findViewById(R.id.land_loads_bulding4);
        land_loads_bulding5 = findViewById(R.id.land_loads_bulding5);
        land_loads_bulding6 = findViewById(R.id.land_loads_bulding6);
        land_loads_bulding7 = findViewById(R.id.land_loads_bulding7);
        land_loads_bulding8 = findViewById(R.id.land_loads_bulding8);
        land_loads_bulding9 = findViewById(R.id.land_loads_bulding9);
        land_loads_bulding10 = findViewById(R.id.land_loads_bulding10);
        land_loads_bulding3.setTypeface(typeRegular);
        land_loads_bulding4.setTypeface(typeRegular);
        land_loads_bulding5.setTypeface(typeRegular);
        land_loads_bulding6.setTypeface(typeRegular);
        land_loads_bulding7.setTypeface(typeRegular);
        land_loads_bulding8.setTypeface(typeRegular);
        land_loads_bulding9.setTypeface(typeRegular);
        land_loads_bulding10.setTypeface(typeRegular);

        lin_bulding_3 = findViewById(R.id.lin_bulding_3);
        lin_bulding_4 = findViewById(R.id.lin_bulding_4);
        lin_bulding_5 = findViewById(R.id.lin_bulding_5);
        lin_bulding_6 = findViewById(R.id.lin_bulding_6);
        lin_bulding_7 = findViewById(R.id.lin_bulding_7);
        lin_bulding_8 = findViewById(R.id.lin_bulding_8);
        lin_bulding_9 = findViewById(R.id.lin_bulding_9);
        lin_bulding_10 = findViewById(R.id.lin_bulding10);
        lin_bulding_3.setVisibility(View.GONE);
        lin_bulding_4.setVisibility(View.GONE);
        lin_bulding_5.setVisibility(View.GONE);
        lin_bulding_6.setVisibility(View.GONE);
        lin_bulding_7.setVisibility(View.GONE);
        lin_bulding_8.setVisibility(View.GONE);
        lin_bulding_9.setVisibility(View.GONE);
        lin_bulding_10.setVisibility(View.GONE);

        abcd = findViewById(R.id.abcd);
        main_sponser_editText_email = findViewById(R.id.main_sponser_editText_email);
        lin_goverment = findViewById(R.id.lin_goverment);
        btn_uplod_sponse = findViewById(R.id.btn_uplod_sponse);
        btn_uplod_goverment = findViewById(R.id.btn_uplod_goverment);
        btn_uplod_public = findViewById(R.id.btn_uplod_public);
        lin_public = findViewById(R.id.lin_public);
        lin_main_sponser = findViewById(R.id.lin_main_sponser);
        lin_land_loads = findViewById(R.id.lin_land_loads);
        main_sponser_editText_mobile_number = findViewById(R.id.main_sponser_editText_mobile_number);
        public_txt_association = findViewById(R.id.public_txt_association);
        public_txt_representive = findViewById(R.id.public_txt_representive);
        public_editText_assocition = findViewById(R.id.public_editText_assocition);
        public_editText_representative = findViewById(R.id.public_editText_representative);
        public_editText_emai_reprentative = findViewById(R.id.public_editText_emai_reprentative);
        public_txt_em_reprentative = findViewById(R.id.public_txt_em_reprentative);
        main_sponser_corporate_name = findViewById(R.id.main_sponser_corporate_name);
        main_sponser_representive = findViewById(R.id.main_sponser_representive);
        id_slect_vp = findViewById(R.id.id_slect_vp);
        btnLogin = findViewById(R.id.btnLogin);
        btn_uplod_landload = findViewById(R.id.btn_uplod_landload);
        txtlable = findViewById(R.id.txtlable);
        txtlabl_ = findViewById(R.id.txtlabl_);
        txtlable1 = findViewById(R.id.txtlable1);
        title = findViewById(R.id.title);
        job_title = findViewById(R.id.job_title);
        editText_work = findViewById(R.id.editText_work);
        editText_title = findViewById(R.id.editText_title);
        editText_job = findViewById(R.id.editText_job);
        main_sponser_txt_email = findViewById(R.id.main_sponser_txt_email);
        land_loads_bulding1 = findViewById(R.id.land_loads_bulding1);
        land_loads_bulding2 = findViewById(R.id.land_loads_bulding2);
        logoside = findViewById(R.id.logoside);
        main_sponser_txt_dona_amount = findViewById(R.id.main_sponser_txt_dona_amount);
        main_sponser_editText_corporate_name = findViewById(R.id.main_sponser_editText_corporate_name);
        main_sponser_editText_representative = findViewById(R.id.main_sponser_editText_representative);
        main_sponser_editText_dona_amount = findViewById(R.id.main_sponser_editText_dona_amount);
        main_sponser_txt_mobile_nuber = findViewById(R.id.main_sponser_txt_mobile_nuber);
        land_loads_editText_bulding_number1 = findViewById(R.id.land_loads_editText_bulding_number1);
        land_loads_editText_bulding_number2 = findViewById(R.id.land_loads_editText_bulding_number2);
        land_loads_editText_mobile_number = findViewById(R.id.land_loads_editText_mobile_number);
        btn_uploadlogo = findViewById(R.id.btn_uploadlogo);
        btn_uploadlogo.setVisibility(View.GONE);
        land_loads_txt_mobile_nuber = findViewById(R.id.land_loads_txt_mobile_nuber);
        btn_add_morebulding = findViewById(R.id.btn_add_morebulding);
        editText_des_land_load = findViewById(R.id.editText_des_land_load);
        editText_des_main_sponser = findViewById(R.id.editText_des_main_sponser);
        editText_des_main_goverment = findViewById(R.id.editText_des_main_goverment);
        editText_des_main_public = findViewById(R.id.editText_des_main_public);
        btn_add_morebulding.setOnClickListener(this);
        btn_uplod_public.setOnClickListener(this);
        btn_uplod_sponse.setOnClickListener(this);
        logoside.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btn_uplod_landload.setOnClickListener(this);
        btn_uplod_goverment.setOnClickListener(this);
        btn_uplod_public.setTypeface(typeRegular);
        editText_des_main_sponser.setTypeface(typeRegular);
        editText_des_main_public.setTypeface(typeRegular);
        btn_uplod_goverment.setTypeface(typeRegular);
        editText_des_main_goverment.setTypeface(typeRegular);
        land_loads_editText_mobile_number.setTypeface(typeRegular);
        main_sponser_txt_email.setTypeface(typeRegular);
        land_loads_bulding2.setTypeface(typeRegular);
        main_sponser_editText_mobile_number.setTypeface(typeRegular);
        main_sponser_txt_dona_amount.setTypeface(typeRegular);
        main_sponser_editText_dona_amount.setTypeface(typeRegular);
        main_sponser_txt_mobile_nuber.setTypeface(typeRegular);
        btn_uploadlogo.setTypeface(typeSemibold);
        main_sponser_editText_email.setTypeface(typeRegular);
        public_txt_representive.setTypeface(typeRegular);
        public_txt_em_reprentative.setTypeface(typeRegular);
        land_loads_txt_mobile_nuber.setTypeface(typeRegular);
        btn_add_morebulding.setTypeface(typeSemibold);
        public_txt_association.setTypeface(typeRegular);
        public_editText_assocition.setTypeface(typeRegular);
        public_editText_representative.setTypeface(typeRegular);
        public_editText_emai_reprentative.setTypeface(typeRegular);
        main_sponser_corporate_name.setTypeface(typeRegular);
        land_loads_editText_bulding_number2.setTypeface(typeRegular);
        main_sponser_editText_corporate_name.setTypeface(typeRegular);
        main_sponser_editText_representative.setTypeface(typeRegular);
        main_sponser_representive.setTypeface(typeRegular);
        land_loads_bulding1.setTypeface(typeRegular);
        land_loads_editText_bulding_number1.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        btn_uplod_landload.setTypeface(typeRegular);
        btn_uplod_sponse.setTypeface(typeRegular);
        editText_job.setTypeface(typeRegular);
        editText_title.setTypeface(typeRegular);
        editText_work.setTypeface(typeRegular);
        editText_des_land_load.setTypeface(typeRegular);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(normal);
        txtlabl_.setTypeface(normal);
        title.setTypeface(normal);
        job_title.setTypeface(normal);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.btnLogin:
                if (BecomeVisPart.this.isNetworkConnected()) {

                    if (type == "land_loads") {


                        // filterOb_gym();

                        if (validate_landload()) {
                            showProgress();
                            Save_VisionPartner_land_loads();
                        }
                    }
                    if (type == "sponser") {


                        if (validate_sponser()) {
                            showProgress();
                            Save_VisionPartner_sponser();
                        }
                    }

                    if (type == "public") {

                        // Vision_Partner_Type = "Save Vision Partner - Public";

                        if (validate_public()) {
                            showProgress();

                            Save_VisionPartner_public();
                        }
                    }
                    if (type == "goverment") {
                        // Vision_Partner_Type = "Save Vision Partner - Government Only";

                        if (validate()) {
                            showProgress();
                            Save_VisionPartner_Government();
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
                //     exitByBackKey();
                break;


            case R.id.btn_add_morebulding:

                i++;


                if (i == 3) {
                    lin_bulding_3.setVisibility(View.VISIBLE);
                }
                if (i == 4) {
                    lin_bulding_4.setVisibility(View.VISIBLE);

                }

                if (i == 5) {
                    lin_bulding_5.setVisibility(View.VISIBLE);

                }

                if (i == 6) {
                    lin_bulding_6.setVisibility(View.VISIBLE);

                }


                if (i == 7) {
                    lin_bulding_7.setVisibility(View.VISIBLE);

                }


                if (i == 8) {
                    lin_bulding_8.setVisibility(View.VISIBLE);

                }


                if (i == 9) {
                    lin_bulding_9.setVisibility(View.VISIBLE);

                }

                if (i == 10) {
                    lin_bulding_10.setVisibility(View.VISIBLE);

                }



               /* LinearLayout rootLayout = findViewById(R.id.root_layout);
                rootLayout.setOrientation(LinearLayout.VERTICAL);
//this layout still needs to be vertical to hold the children.
                for (int i = 0; i < 1; i++) {

                    //make a new horizontal LinearLayout each time to hold the children.
                    LinearLayout temp = new LinearLayout(this);
                    temp.layout(0, 10, 0, 10);

                    temp.setOrientation(LinearLayout.VERTICAL);
                    temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    if (appPreferences.getCulturemode().equals("1")) {

                        int abc = i;
                        textView.setText("Bulding Number" + " ");

                    } else {
                        textView.setText(" رقم المبنى");

                    }
                    textView.layout(0, 10, 0, 10);
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(16);
                    textView.setTypeface(typeRegular);

                    temp.addView(textView); //add them to this temporary layout.

                    EditText editText = new EditText(this);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    editText.layout(0, 10, 0, 10);
                    editText.setTextColor(Color.BLACK);
                    editText.setTextSize(16);
                    editText.setTypeface(typeRegular);
                    if (appPreferences.getCulturemode().equals("1")) {

                        editText.setHint("Enter Bulding Number");
                    } else {
                        editText.setHint("أدخِل رقم المبنى");

                    }
                    temp.addView(editText);

                 *//*   TextView addTextView = new TextView(this);
                    addTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    addTextView.setText("Additional Text");
                    //  temp.addView(addTextViewtextView);*//*

                    rootLayout.addView(temp);
                    String asew = editText.getText().toString();

                }*/
                break;


            case R.id.yes:
                startActivity(new Intent(BecomeVisPart.this, WellcomefamilyPage.class));
                finish();
                break;
            case R.id.no:
                startActivity(new Intent(BecomeVisPart.this, HomeActivity.class));
                finish();
                break;

            case R.id.btn_uplod_goverment:
                chose_image();
                break;


            case R.id.btn_uplod_landload:
                chose_image();
                break;

            case R.id.btn_uplod_sponse:
                chose_image();
                break;

            case R.id.btn_uplod_public:
                chose_image();
                break;
        }
    }

    private boolean validate_landload() {
        Bulding_num_landloads = land_loads_editText_bulding_number1.getText().toString();
        Bulding_num_landloads2 = land_loads_editText_bulding_number2.getText().toString();


        Bulding_num_landloads3 = land_loads_editText_bulding_number3.getText().toString();
        Bulding_num_landloads4 = land_loads_editText_bulding_number4.getText().toString();
        Bulding_num_landloads5 = land_loads_editText_bulding_number5.getText().toString();
        Bulding_num_landloads6 = land_loads_editText_bulding_number6.getText().toString();
        Bulding_num_landloads7 = land_loads_editText_bulding_number7.getText().toString();
        Bulding_num_landloads8 = land_loads_editText_bulding_number8.getText().toString();
        Bulding_num_landloads9 = land_loads_editText_bulding_number9.getText().toString();
        Bulding_num_landloads10 = land_loads_editText_bulding_number10.getText().toString();


        Mobile_landloads = land_loads_editText_mobile_number.getText().toString();
        Descri_landloads = editText_des_land_load.getText().toString();
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();


        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("Sr", "1");
        temp.put("Build_CivilId", Bulding_num_landloads);
        temp.put("Mobile", Mobile_landloads);
        list.add(temp);

        if (!Bulding_num_landloads2.equals("")) {
            HashMap<String, String> temp1 = new HashMap<String, String>();
            temp1.put("Sr", "2");
            temp1.put("Build_CivilId", Bulding_num_landloads2);
            temp1.put("Mobile", Mobile_landloads);
            list.add(temp1);
        }

        if (!Bulding_num_landloads3.equals("")) {

            HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put("Sr", "3");
            temp2.put("Build_CivilId", Bulding_num_landloads3);
            temp2.put("Mobile", Mobile_landloads);


            list.add(temp2);

        }

        if (!Bulding_num_landloads4.equals("")) {


            HashMap<String, String> temp3 = new HashMap<String, String>();
            temp3.put("Sr", "4");
            temp3.put("Build_CivilId", Bulding_num_landloads4);
            temp3.put("Mobile", Mobile_landloads);


            list.add(temp3);
        }

        if (!Bulding_num_landloads5.equals("")) {


            HashMap<String, String> temp4 = new HashMap<String, String>();
            temp4.put("Sr", "5");
            temp4.put("Build_CivilId", Bulding_num_landloads5);
            temp4.put("Mobile", Mobile_landloads);


            list.add(temp4);
        }

        if (!Bulding_num_landloads6.equals("")) {


            HashMap<String, String> temp5 = new HashMap<String, String>();
            temp5.put("Sr", "6");
            temp5.put("Build_CivilId", Bulding_num_landloads6);
            temp5.put("Mobile", Mobile_landloads);


            list.add(temp5);
        }

        if (!Bulding_num_landloads7.equals("")) {


            HashMap<String, String> temp6 = new HashMap<String, String>();
            temp6.put("Sr", "7");
            temp6.put("Build_CivilId", Bulding_num_landloads7);
            temp6.put("Mobile", Mobile_landloads);


            list.add(temp6);

        }


        if (!Bulding_num_landloads7.equals("")) {

            HashMap<String, String> temp7 = new HashMap<String, String>();
            temp7.put("Sr", "8");
            temp7.put("Build_CivilId", Bulding_num_landloads7);
            temp7.put("Mobile", Mobile_landloads);


            list.add(temp7);
        }

        if (!Bulding_num_landloads9.equals("")) {


            HashMap<String, String> temp8 = new HashMap<String, String>();
            temp8.put("Sr", "9");
            temp8.put("Build_CivilId", Bulding_num_landloads9);
            temp8.put("Mobile", Mobile_landloads);


            list.add(temp8);

        }
        if (!Bulding_num_landloads10.equals("")) {

            HashMap<String, String> temp9 = new HashMap<String, String>();
            temp9.put("Sr", "10");
            temp9.put("Build_CivilId", Bulding_num_landloads10);
            temp9.put("Mobile", Mobile_landloads);


            list.add(temp9);


        }


        if (TextUtils.isEmpty(land_loads_editText_bulding_number1.getText().toString())) {
            land_loads_editText_bulding_number1.setError("Please Enter Building Number ");
            land_loads_editText_bulding_number1.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(land_loads_txt_mobile_nuber.getText().toString())) {
            land_loads_txt_mobile_nuber.setError("Please Enter Mobile Number ");
            land_loads_txt_mobile_nuber.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_des_land_load.getText().toString())) {
            editText_des_land_load.setError("Please Enter Description ");
            editText_des_land_load.requestFocus();
            return false;
        }
        return true;
    }

    private JSONArray filterOb_gym() {
        JSONArray jObjFilter = new JSONArray();
        JSONObject jObjFilter1 = new JSONObject();
        try {


            jObjFilter1.put("Sr", "1");
            jObjFilter1.put("miBuild_CivilIdn_price", "3543543544");
            jObjFilter1.put("Mobile", "25474424");


            JSONObject student2 = new JSONObject();
            try {
                student2.put("Sr", "2");
                student2.put("Build_CivilId", "3543543544");
                student2.put("Mobile", "25474424");


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jObjFilter.put((jObjFilter1));
            jObjFilter.put((student2));

            Log.i(getClass().getName(), "=========== jObjFilter: " + jObjFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObjFilter;
    }

    private void Save_VisionPartner_land_loads() {
        VisionPartnerland_loadsPojo loadsPojo = new VisionPartnerland_loadsPojo();
        loadsPojo.setUserId(appPreferences.getuserid());
        loadsPojo.setMem_SrNo(appPreferences.get_membership_srno());
        loadsPojo.setUniqueId(sessionManager.GetUniqueId());
        loadsPojo.setCorpcentreby(appPreferences.get_company_id());
        loadsPojo.setIpAddress(sessionManager.GetIpAddress());
        loadsPojo.setSource("Android");
        loadsPojo.setVision_Partner_Type(Vision_Partner_Type);
        loadsPojo.setObjlist(list);
        loadsPojo.setLogo_Image(imageName_passport_frant);
        loadsPojo.setLogo_Image_Base64_String(encoded_passport_frant);
        loadsPojo.setShort_Desc(Descri_landloads);


        Call<VisionPartnerland_loadsPojo> call2 = apiInterface.Save_VisionPartner_landload(loadsPojo);
        call2.enqueue(new Callback<VisionPartnerland_loadsPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<VisionPartnerland_loadsPojo> call, Response<VisionPartnerland_loadsPojo> response) {
                Log.i(getClass().getName(), "response_landload: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Membership_Dialog start_dialog = new Membership_Dialog(BecomeVisPart.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(BecomeVisPart.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(BecomeVisPart.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(BecomeVisPart.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<VisionPartnerland_loadsPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate_public() {

        Association_Name_public = public_editText_assocition.getText().toString();
        Reprentative_name_public = public_editText_representative.getText().toString();
        Reprentative_email_public = public_editText_emai_reprentative.getText().toString();
        short_desc_public = editText_des_main_public.getText().toString();

        if (TextUtils.isEmpty(public_editText_assocition.getText().toString())) {
            public_editText_assocition.setError("Please Enter Association Name ");
            public_editText_assocition.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(public_editText_representative.getText().toString())) {
            public_editText_representative.setError("Enter Reprentative Name");
            public_editText_representative.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(public_editText_emai_reprentative.getText().toString())) {
            public_editText_emai_reprentative.setError("Enter Your Reprentative E-mail");
            public_editText_emai_reprentative.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(public_editText_emai_reprentative.getText().toString())) {
            public_editText_emai_reprentative.setError("Invalid Email Address");
            public_editText_emai_reprentative.requestFocus();
            return false;


        }

        if (TextUtils.isEmpty(editText_des_main_public.getText().toString())) {
            editText_des_main_public.setError("Enter Description");
            editText_des_main_public.requestFocus();
            return false;
        }


        return true;
    }

    JSONObject vendoeOb_gym(String Build_CivilId, String Mobile) {
        JSONObject jObjFilter = new JSONObject();
        try {


            jObjFilter.put("Build_CivilId", Build_CivilId);


            jObjFilter.put("Mobile", Mobile);

            jsonArr.put(jObjFilter);

            Log.i(getClass().getName(), "=========== jObjbildingr: " + jObjFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObjFilter;
    }

    private void Save_VisionPartner_public() {
        VisionPartnerPublicPojo publicPojo = new VisionPartnerPublicPojo();
        publicPojo.setUserId(appPreferences.getuserid());
        publicPojo.setMem_SrNo(appPreferences.get_membership_srno());
        publicPojo.setUniqueId(sessionManager.GetUniqueId());
        publicPojo.setCorpcentreby(appPreferences.get_company_id());
        publicPojo.setIpAddress(sessionManager.GetIpAddress());
        publicPojo.setSource("Android");
        publicPojo.setVision_Partner_Type(Vision_Partner_Type);
        publicPojo.setVP_AssociationName(Association_Name_public);
        publicPojo.setVP_RepresentativeName(Reprentative_name_public);
        publicPojo.setVP_Representative_Email(Reprentative_email_public);
        publicPojo.setLogo_Image(imageName_passport_frant);
        publicPojo.setLogo_Image_Base64_String(encoded_passport_frant);
        publicPojo.setShort_Desc(short_desc_public);


        Call<VisionPartnerPublicPojo> call2 = apiInterface.Save_VisionPartner_publc(publicPojo);
        call2.enqueue(new Callback<VisionPartnerPublicPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<VisionPartnerPublicPojo> call, Response<VisionPartnerPublicPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Membership_Dialog start_dialog = new Membership_Dialog(BecomeVisPart.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(BecomeVisPart.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(BecomeVisPart.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(BecomeVisPart.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<VisionPartnerPublicPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate_sponser() {

        Corp_name_sponser = main_sponser_editText_corporate_name.getText().toString();
        repren_name_sponser = main_sponser_editText_representative.getText().toString();
        Done_ammou_sponser = main_sponser_editText_dona_amount.getText().toString();
        Mobile_sponser = main_sponser_editText_mobile_number.getText().toString();
        Email_sponser = main_sponser_editText_email.getText().toString();
        Description_sponser = editText_des_main_sponser.getText().toString();

        if (TextUtils.isEmpty(main_sponser_editText_corporate_name.getText().toString())) {
            main_sponser_editText_corporate_name.setError("Please Enter Corporate Name ");
            main_sponser_editText_corporate_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(main_sponser_editText_representative.getText().toString())) {
            main_sponser_editText_representative.setError("Please Enter Reprentative Name ");
            main_sponser_editText_representative.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(main_sponser_editText_dona_amount.getText().toString())) {
            main_sponser_editText_dona_amount.setError("Please Enter Donation Amount ");
            main_sponser_editText_dona_amount.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(main_sponser_editText_mobile_number.getText().toString())) {
            main_sponser_editText_mobile_number.setError("Please Enter Donation Amount ");
            main_sponser_editText_mobile_number.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(main_sponser_editText_email.getText().toString())) {
            main_sponser_editText_email.setError("Enter Your E-mail");
            main_sponser_editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(main_sponser_editText_email.getText().toString())) {
            main_sponser_editText_email.setError("Invalid Email Address");
            main_sponser_editText_email.requestFocus();
            return false;


        }

        if (TextUtils.isEmpty(editText_des_main_sponser.getText().toString())) {
            editText_des_main_sponser.setError("Please Enter Description ");
            editText_des_main_sponser.requestFocus();
            return false;
        }
        return true;
    }

    private void Save_VisionPartner_sponser() {
        VisionPartnerSponserPojo partnerSponserPojo = new VisionPartnerSponserPojo();
        partnerSponserPojo.setUserId(appPreferences.getuserid());
        partnerSponserPojo.setMem_SrNo(appPreferences.get_membership_srno());
        partnerSponserPojo.setUniqueId(sessionManager.GetUniqueId());
        partnerSponserPojo.setCorpcentreby(appPreferences.get_company_id());
        partnerSponserPojo.setIpAddress(sessionManager.GetIpAddress());
        partnerSponserPojo.setSource("Android");
        partnerSponserPojo.setVision_Partner_Type(Vision_Partner_Type);
        partnerSponserPojo.setVP_Corporate_Name(Corp_name_sponser);
        partnerSponserPojo.setVP_Representative_Name(repren_name_sponser);
        partnerSponserPojo.setVP_Company_Logo(encoded_passport_frant);
        partnerSponserPojo.setVP_Donation_Amount(Done_ammou_sponser);
        partnerSponserPojo.setVP_Mobile_Number(Mobile_sponser);
        partnerSponserPojo.setVP_Email(Email_sponser);
        partnerSponserPojo.setShort_Desc(Description_sponser);


        Call<VisionPartnerSponserPojo> call2 = apiInterface.Save_VisionPartner_sponser(partnerSponserPojo);
        call2.enqueue(new Callback<VisionPartnerSponserPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<VisionPartnerSponserPojo> call, Response<VisionPartnerSponserPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Membership_Dialog start_dialog = new Membership_Dialog(BecomeVisPart.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(BecomeVisPart.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(BecomeVisPart.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(BecomeVisPart.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<VisionPartnerSponserPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate() {
        work_name_gove = editText_work.getText().toString();
        Title_gove = editText_title.getText().toString();
        Job_Title_gove = editText_job.getText().toString();
        Short_Desc_gove = editText_des_main_goverment.getText().toString();

        if (TextUtils.isEmpty(editText_work.getText().toString())) {

            editText_work.setError("Please Enter What government do you work for");
            editText_work.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_title.getText().toString())) {

            editText_title.setError("Please Enter Title");
            editText_title.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_job.getText().toString())) {

            editText_job.setError("Please Enter Job Title");
            editText_job.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_des_main_goverment.getText().toString())) {

            editText_des_main_goverment.setError("Please Enter Description");
            editText_des_main_goverment.requestFocus();
            return false;
        }


        return true;
    }

    private void chose_image() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your Civil Id Front picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                  /*  Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);*/
                    Intent camera_passport_front = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //IMAGE CAPTURE CODE
                    startActivityForResult(camera_passport_front, RESULT_LOAD_IMAGE_camera);

                } else if (options[item].equals("Choose from Gallery")) {
                 /*   Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);*/

                    Intent i_gallery__civil_frant = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i_gallery__civil_frant, RESULT_LOAD_IMAGE);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE_camera && resultCode == RESULT_OK) {
            try {


                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
                encoded_passport_frant = Base64.encodeToString(byteArray, Base64.DEFAULT);
              /*  Uri selectedImageUri = data.getData();
                imageName = getRealPathFromURI(selectedImageUri);*/
               /* File f = new File(picturePath);
                imageName = f.getName();*/
                //image_name.setText(imageName);

                String filename = "image";
                String fileNameExtension = ".jpg";
                File sdCard = Environment.getExternalStorageDirectory();
                String imageStorageFolder = File.separator + "Your application Folder" + File.separator;
                imageName_passport_frant = filename + fileNameExtension;
                Concate = uniqueidimage + "" + imageName_passport_frant;

                //   image_front.setText(imageName_passport_frant);

                Log.d("imageName", imageName);
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // image_iron_plate.setImageBitmap(imageBitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.d("picturePath", picturePath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
                //      encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encoded_passport_frant = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Uri selectedImageUri = data.getData();

                //String base64String = Base64.encodeBase64String(bytes);
                // byte[] backToBytes = Base64.decodeBase64(base64String);
                //Log.e("image",encoded);
                //encoded=Base64.encodeToString(byteArray, Base64.NO_WRAP | Base64.URL_SAFE);
                File f = new File(picturePath);
                imageName_passport_frant = f.getName();
                //image_name.setText(imageName);
                Concate = uniqueidimage + "" + imageName_passport_frant;

                Uri selected = data.getData();
                //   image_iron_plate.setImageURI(selected);
                //   image_front.setText(imageName_passport_frant);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }

    private void Save_VisionPartner_Government() {
        VisionPartnerGovernmentOnlyPojo governmentOnlyPojo = new VisionPartnerGovernmentOnlyPojo();
        governmentOnlyPojo.setUserId(appPreferences.getuserid());
        governmentOnlyPojo.setMem_SrNo(appPreferences.get_membership_srno());
        governmentOnlyPojo.setUniqueId(sessionManager.GetUniqueId());
        governmentOnlyPojo.setCorpcentreby(appPreferences.get_company_id());
        governmentOnlyPojo.setIpAddress(sessionManager.GetIpAddress());
        governmentOnlyPojo.setSource("Android");
        governmentOnlyPojo.setVision_Partner_Type(Vision_Partner_Type);
        governmentOnlyPojo.setGovernment_Name(work_name_gove);
        governmentOnlyPojo.setTitle(Title_gove);
        governmentOnlyPojo.setJob_Title(Job_Title_gove);
        governmentOnlyPojo.setLogo_Image(imageName_passport_frant);
        governmentOnlyPojo.setLogo_Image_Base64_String(encoded_passport_frant);
        governmentOnlyPojo.setShort_Desc(Short_Desc_gove);


        Call<VisionPartnerGovernmentOnlyPojo> call2 = apiInterface.Save_VisionPartner_govement(governmentOnlyPojo);
        call2.enqueue(new Callback<VisionPartnerGovernmentOnlyPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<VisionPartnerGovernmentOnlyPojo> call, Response<VisionPartnerGovernmentOnlyPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //  exitByBackKey();
                        Membership_Dialog start_dialog = new Membership_Dialog(BecomeVisPart.this);
                        //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);


                        start_dialog.setCanceledOnTouchOutside(false);
                        start_dialog.show();
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(BecomeVisPart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(BecomeVisPart.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(BecomeVisPart.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(BecomeVisPart.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<VisionPartnerGovernmentOnlyPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(BecomeVisPart.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
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
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private class Get_VP_Type_List extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(BecomeVisPart.this);
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_VP_Type_List','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("contry_list", response);
            progressDialog.dismiss();

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                spinner_list.addAll(partnerPojo.row);

                Resources res = getResources();
                adapter = new VisionPartnerAdapter(BecomeVisPart.this, R.layout.countryspinner_country_new, spinner_list, res);
                id_slect_vp.setAdapter(adapter);
                id_slect_vp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        id_slect_vp.setSelection(position);

                        Vision_Partner_Type = spinner_list.get(position).xCode;
                        Log.e("contrycode", Vision_Partner_Type);
                      /*  Row w = (Row) id_slect_vp.getSelectedItem();
                        if (!w.xCode().equals("0")) {
                          String  contrycode = w.xCode();

                            Log.e("contrycode", contrycode);

                            //     getcitylist(countryid);
                        }
*/
                        if (spinner_list.get(position).xCode.equals("VP_LndLrd")) {
                            type = "land_loads";
                            i = 2;

                            if (i == 2) {
                                lin_bulding_3.setVisibility(View.GONE);
                                lin_bulding_4.setVisibility(View.GONE);
                                lin_bulding_5.setVisibility(View.GONE);
                                lin_bulding_6.setVisibility(View.GONE);
                                lin_bulding_7.setVisibility(View.GONE);
                                lin_bulding_8.setVisibility(View.GONE);
                                lin_bulding_9.setVisibility(View.GONE);
                                lin_bulding_10.setVisibility(View.GONE);
                            }

                            lin_goverment.setVisibility(View.GONE);
                            lin_public.setVisibility(View.GONE);
                            lin_main_sponser.setVisibility(View.GONE);
                            lin_land_loads.setVisibility(View.VISIBLE);
                        }

                        if (spinner_list.get(position).xCode.equals("VP_MSpnsr")) {
                            type = "sponser";

                            lin_goverment.setVisibility(View.GONE);
                            lin_public.setVisibility(View.GONE);
                            lin_main_sponser.setVisibility(View.VISIBLE);
                            lin_land_loads.setVisibility(View.GONE);
                        }

                        if (spinner_list.get(position).xCode.equals("VP_Pblc")) {
                            type = "public";

                            lin_goverment.setVisibility(View.GONE);
                            lin_public.setVisibility(View.VISIBLE);
                            lin_main_sponser.setVisibility(View.GONE);
                            lin_land_loads.setVisibility(View.GONE);
                        }
                        if (spinner_list.get(position).xCode.equals("VP_Grnmnt")) {
                            type = "goverment";

                            lin_goverment.setVisibility(View.VISIBLE);
                            lin_public.setVisibility(View.GONE);
                            lin_main_sponser.setVisibility(View.GONE);
                            lin_land_loads.setVisibility(View.GONE);
                        }


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
}
