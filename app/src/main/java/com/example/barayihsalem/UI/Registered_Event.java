package com.example.barayihsalem.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Addition_services_Adapter;
import com.example.barayihsalem.UI.Adapter.Regi_services_Adapter;
import com.example.barayihsalem.UI.Adapter.TermsAndConditionListAdapter;
import com.example.barayihsalem.UI.Model.Events_PlannerPojo;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.TemsandCond;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Registered_Event extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE_camera = 0;
    private static int RESULT_LOAD_IMAGE = 1;
    TextView aboutbs, txt_full_name, txt_date, txt_gender, txt_nationality, txt_phone, txt_ptem_con, txt_Event_Size;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    BottomSheetDialog dialog;
    ProgressDialog progressDialog;
    TextView message, no, yes;
    ImageView logoside, twitter_l;
    EditText editText_lice;
    Regi_services_Adapter regi_services_adapter;
    Addition_services_Adapter addition_services_adapter;
    Button btnLogin, proceed;
    RecyclerView rw_vision, rw_checkbox;

    boolean adb = false;

    int termscodsize;
    int checkvalue=0;
    TermsAndConditionListAdapterBO termsAndConditionListAdapter;
    LinearLayoutManager layoutManager;

    ArrayList<List> Vision_list = new ArrayList<com.example.barayihsalem.UI.Model.List>();
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
    //  RecyclerView rw_regi_servi, rw_additional;
    EditText editText_age, editText_name, editText_mobile, editText_email;
    Bitmap bitmap = null;
    RelativeLayout abcd;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    APIInterface apiInterface;

    String uniqueidimage;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    String Company_Name, Name, mobile, Email;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_registered__event);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            setContentView(R.layout.activity_registered__event_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();
        new Get_Terms_Conditions_List().execute();

    }

    private void findid() {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        editText_lice = findViewById(R.id.editText_lice);
        abcd = findViewById(R.id.abcd);
        aboutbs = findViewById(R.id.aboutbs);
        txt_full_name = findViewById(R.id.txt_full_name);
        txt_gender = findViewById(R.id.txt_gender);
        logoside = findViewById(R.id.logoside);
        txt_nationality = findViewById(R.id.txt_nationality);
        editText_age = findViewById(R.id.editText_age);
        editText_name = findViewById(R.id.editText_name);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_email = findViewById(R.id.editText_email);
        txt_phone = findViewById(R.id.txt_phone);
        txt_ptem_con = findViewById(R.id.txt_ptem_con);
        txt_Event_Size = findViewById(R.id.txt_Event_Size);
        proceed = findViewById(R.id.proceed);
        twitter_l = findViewById(R.id.twitter_l);
        rw_checkbox = findViewById(R.id.rw_checkbox);
        txt_date = findViewById(R.id.txt_date);
        txt_full_name.setTypeface(typeRegular);
        txt_date.setTypeface(typeHeader);
        editText_email.setTypeface(typeRegular);
        editText_lice.setTypeface(typeRegular);
        txt_Event_Size.setTypeface(typeRegular);
        txt_gender.setTypeface(typeRegular);
        txt_phone.setTypeface(typeRegular);
        txt_nationality.setTypeface(typeRegular);
        editText_age.setTypeface(typeRegular);
        editText_mobile.setTypeface(typeRegular);
        editText_name.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        txt_ptem_con.setTypeface(typeRegular);
        aboutbs.setTypeface(typebold);
        logoside.setOnClickListener(this);
        proceed.setOnClickListener(this);
        editText_lice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    chose_image();
                    // Do what you want
                    return true;
                }



                return false;
            }
        });


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
                    dialog.dismiss();

                } else if (options[item].equals("Choose from Gallery")) {
                 /*   Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);*/

                    Intent i_gallery__civil_frant = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i_gallery__civil_frant, RESULT_LOAD_IMAGE);
                    dialog.dismiss();

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

                editText_lice.setText(imageName_passport_frant);

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
                editText_lice.setText(imageName_passport_frant);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Registered_Event.this, Events_activity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.proceed:
                if (Registered_Event.this.isNetworkConnected()) {
                    if (validate()) {
                        showProgress();
                        Save_Events_Planner();
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

    private void Save_Events_Planner() {
        Events_PlannerPojo loginPojo = new Events_PlannerPojo();


        loginPojo.setUserId(appPreferences.getuserid());
        loginPojo.setComp_Name(Company_Name);
        loginPojo.setComp_License(imageName_passport_frant);
        loginPojo.setComp_License_Base64_String(encoded_passport_frant);
        loginPojo.setName(Name);
        loginPojo.setMobile(mobile);
        loginPojo.setEmail(Email);
        loginPojo.setActive(true);
        loginPojo.setDelete(false);
        loginPojo.setCorpcentreby(appPreferences.get_company_id());
        loginPojo.setIpAddress(sessionManager.GetIpAddress());
        loginPojo.setCommand("Save");


        Call<Events_PlannerPojo> call2 = apiInterface.Save_Events_Planner(loginPojo);
        call2.enqueue(new Callback<Events_PlannerPojo>() {
            @Override
            public void onResponse(Call<Events_PlannerPojo> call, Response<Events_PlannerPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {


                        editText_age.getText().clear();
                        editText_lice.getText().clear();
                        editText_name.getText().clear();
                        editText_mobile.getText().clear();
                        editText_email.getText().clear();

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
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
            public void onFailure(Call<Events_PlannerPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Registered_Event.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private boolean validate() {

        Company_Name = editText_age.getText().toString();
        imageName_passport_frant = editText_lice.getText().toString();
        Name = editText_name.getText().toString();
        mobile = editText_mobile.getText().toString();
        Email = editText_email.getText().toString();


        if (TextUtils.isEmpty(editText_age.getText().toString())) {
            editText_age.setError("Please Enter Company Name");
            editText_age.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_name.getText().toString())) {
            editText_name.setError("Please Enter Name");
            editText_name.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Please Enter Mobile");
            editText_mobile.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your Register E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;


        }

        return true;
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
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

                termsAndConditionListAdapter = new TermsAndConditionListAdapterBO("create", Registered_Event.this, Terms_list);
                layoutManager = new LinearLayoutManager(Registered_Event.this, LinearLayoutManager.VERTICAL, false);
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
                      //  tems_cond.setSelected(isChecked);
                        // adb = true;
                        checkvalue++;

                    } else {
                       // tems_cond.setSelected(false);
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