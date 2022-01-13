package com.example.barayihsalem.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
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
import com.example.barayihsalem.UI.Adapter.Nationality_Adapter;
import com.example.barayihsalem.UI.Model.ResidentrPojo;
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
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.BecomeResident_member.hasPermissions;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class BecomePassionate_Visitor extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE_camera = 0;
    private static int RESULT_LOAD_IMAGE = 1;
    final Calendar myCalendar = Calendar.getInstance();
    TextView txtlable, txt_fdate, view_image, txt_date, txtlable1, mobile_num, email, txt_gender_do, title, txt_Nationality, job_title, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    APIInterface apiInterface;

    TextView message, no, yes;
    String myFormat;
    String myFormat2;
    String gaha;
    String gahaw;
    String uniqueidimage;
    Bitmap bitmap = null;
    String Age = "";
    String From_date = "";
    String To_date = "";
    String gaha_dob;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    String City_Name, Property_num, Address, Mobile, Email;

    ProgressDialog progressDialog;
    ArrayList<Row> Gender_list = new ArrayList<Row>();
    ArrayList<Row> Nationality_list = new ArrayList<Row>();
    String XCode_gender, Xcode_male, Xcode_female, Gendercode;
    String gahaw_dob;
    String Nationality_Xcode = "";
    RadioButton male_do, female_do;
    ImageView logoside, image_iron_plate, cross;
    Spinner sp_nationality;
    Button btnLogin, male, female;
    Nationality_Adapter nationality_adapter;
    RecyclerView rw_vision, rw_comminity;
    RelativeLayout abcd;
    ArrayList<String> nationality_list = new ArrayList<>();
    int PERMISSION_ALL = 1;
    EditText editText_work, editText_title, editText_job, editText_mobile, editText_email;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
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
    private byte[] byteArray;
    private String encoded_passport_frant = "";
    private String imageName_passport_frant = "";
    private String imageName = "", Concate = "";

    private void updateLabel() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        myFormat2 = "dd MMMM yyyy"; //In which you need put here
        //In which you need put here
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


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.reject_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.reject_color)); //status bar or the time bar at the top
        }
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_become_passionate__visitor);

        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_become_passionate__visitor_ar);


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

        apiInterface = APIClient.getClient().create(APIInterface.class);

        findid();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        String formattedDate = df.format(c);
        SimpleDateFormat dfbak = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDateasd = dfbak.format(c);
        txt_fdate.setText(formattedDate);
        //  txt_todate.setText(formattedDate);
        From_date = formattedDateasd;

        if (appPreferences.getCulturemode().equals("1")) {

            view_image.setText(Html.fromHtml(getString(R.string.your_string_here)));
        } else {

            view_image.setText(Html.fromHtml("<u>عرض الصورة</u>"));
        }

        if (BecomePassionate_Visitor.this.isNetworkConnected()) {


            new Get_Gender_List().execute();
            new Get_Nationality_List().execute();

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


        Resources res = getResources();
        nationality_adapter = new Nationality_Adapter(BecomePassionate_Visitor.this, R.layout.countryspinner_country_new, nationality_list, res);
        sp_nationality.setAdapter(nationality_adapter);
        sp_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                sp_nationality.setSelection(position);


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


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BecomePassionate_Visitor.this, Passionate_Visitor.class));
        finish();
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid() {
        view_image = findViewById(R.id.view_image);
        image_iron_plate = findViewById(R.id.image_iron_plate);
        txt_gender_do = findViewById(R.id.txt_gender_do);
        sp_nationality = findViewById(R.id.sp_nationality);
        btnLogin = findViewById(R.id.btnLogin);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        txtlable = findViewById(R.id.txtlable);
        txtlable1 = findViewById(R.id.txtlable1);
        txt_fdate = findViewById(R.id.txt_fdate);
        txt_date = findViewById(R.id.txt_date);
        female_do = findViewById(R.id.female_do);
        male_do = findViewById(R.id.male_do);
        title = findViewById(R.id.title);
        mobile_num = findViewById(R.id.mobile_num);
        email = findViewById(R.id.email);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_email = findViewById(R.id.editText_email);
        job_title = findViewById(R.id.job_title);
        txt_Nationality = findViewById(R.id.txt_Nationality);
        editText_work = findViewById(R.id.editText_work);
        editText_title = findViewById(R.id.editText_title);
        editText_job = findViewById(R.id.editText_job);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        image_iron_plate.setOnClickListener(this);
        txt_fdate.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        male.setTypeface(typeRegular);
        female.setTypeface(typeRegular);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        view_image.setOnClickListener(this);
        male_do.setTypeface(typeRegular);
        female_do.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        txt_fdate.setTypeface(typeHeader);
        txt_date.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        txt_gender_do.setTypeface(typeRegular);
        txt_Nationality.setTypeface(typeRegular);
        mobile_num.setTypeface(typeRegular);
        email.setTypeface(typeRegular);
        editText_job.setTypeface(typeHeader);
        editText_title.setTypeface(typeHeader);
        editText_work.setTypeface(typeHeader);
        editText_mobile.setTypeface(typeHeader);
        txtlable.setTypeface(typebold);
        txtlable1.setTypeface(normal);
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
            case R.id.txt_fdate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);

                //following line to restrict future date selection
                //  datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.btnLogin:
                //  exitByBackKey();
                if (BecomePassionate_Visitor.this.isNetworkConnected()) {


                    if (Validate()) {
                        if (!Nationality_Xcode.equals("") || !Nationality_Xcode.isEmpty()) {
                            if (!From_date.equals("") || !From_date.isEmpty()) {

                                showProgress();
                                Save_Passionate();
                            } else {
                                Toast.makeText(appController, "Please Select DOB", Toast.LENGTH_SHORT).show();

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

            case R.id.image_iron_plate:
                chose_image();

                break;
            case R.id.view_image:
                View_Image();

                break;

            case R.id.yes:
                startActivity(new Intent(BecomePassionate_Visitor.this, WellcomefamilyPage.class));
                finish();
                break;
            case R.id.no:
                startActivity(new Intent(BecomePassionate_Visitor.this, HomeActivity.class));
                finish();
                break;


            case R.id.male:
                male.setBackground(getResources().getDrawable(R.drawable.male_drawable));
                female.setBackground(getResources().getDrawable(R.drawable.female_drawable));
                female.setTextColor(getResources().getColor(R.color.black));
                male.setTextColor(getResources().getColor(R.color.white));
                Gendercode = Xcode_male;
                break;

            case R.id.female:
                female.setBackground(getResources().getDrawable(R.drawable.male_drawable));
                male.setBackground(getResources().getDrawable(R.drawable.female_drawable));
                female.setTextColor(getResources().getColor(R.color.white));
                male.setTextColor(getResources().getColor(R.color.black));
                Gendercode = Xcode_female;
                break;
        }
    }

    private void Save_Passionate() {
        ResidentrPojo residentrPojo = new ResidentrPojo();
        residentrPojo.setUserId(appPreferences.getuserid());
        residentrPojo.setMem_SrNo(appPreferences.get_membership_srno());
        residentrPojo.setUniqueId(sessionManager.GetUniqueId());
        residentrPojo.setCorpcentreby(appPreferences.get_company_id());
        residentrPojo.setIpAddress(sessionManager.GetIpAddress());
        residentrPojo.setSource("Android");
        residentrPojo.setCommand("Save");
        residentrPojo.setCity(City_Name);
        residentrPojo.setLic_Civil_No(Property_num);
        residentrPojo.setAddress(Address);
        residentrPojo.setRes_Mobile(Mobile);
        residentrPojo.setRes_Email(Email);
        residentrPojo.setRes_Nationality(Nationality_Xcode);
        residentrPojo.setRes_DOB(From_date);
        residentrPojo.setRes_Gender(Gendercode);


        Call<ResidentrPojo> call2 = apiInterface.Save_Passionate(residentrPojo);
        call2.enqueue(new Callback<ResidentrPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<ResidentrPojo> call, Response<ResidentrPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //exitByBackKey();

                        Membership_Dialog start_dialog = new Membership_Dialog(BecomePassionate_Visitor.this);
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
            public void onFailure(Call<ResidentrPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(BecomePassionate_Visitor.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private boolean Validate() {

        City_Name = editText_work.getText().toString();
        Property_num = editText_title.getText().toString();
        Address = editText_job.getText().toString();
        Mobile = editText_mobile.getText().toString();
        Email = editText_email.getText().toString();

        if (TextUtils.isEmpty(editText_work.getText().toString())) {
            editText_work.setError("Please Enter City");
            editText_work.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_title.getText().toString())) {
            editText_title.setError("Please Enter Local Property Number");
            editText_title.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_job.getText().toString())) {
            editText_job.setError("Please Enter Address");
            editText_job.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(editText_mobile.getText().toString())) {
            editText_mobile.setError("Please Enter Mobile Number");
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
                image_iron_plate.setImageBitmap(imageBitmap);

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
                image_iron_plate.setImageURI(selected);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void View_Image() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image_iron, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image_iron_ar, viewGroup, false);

        }

        cross = dialogView.findViewById(R.id.cross);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
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
        message = dialogView.findViewById(R.id.message);
        no = dialogView.findViewById(R.id.no);
        yes = dialogView.findViewById(R.id.yes);
        message.setTypeface(typeRegular);
        no.setTypeface(typeRegular);
        yes.setTypeface(typeRegular);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Get_Gender_List extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(BecomePassionate_Visitor.this);
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
            //  progressDialog.show();
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
            //   progressDialog.dismiss();
            Nationality_list = new ArrayList<>();
            Row worldpop = new Row();
            worldpop.setxCode("");
              if (appPreferences.getCulturemode().equals("1")) {
            worldpop.setxName("Select Nationality");
           } else {
                worldpop.setxName("حدد الجنسية ");
            }
            // worldpop.setXname("Select Country");
            //  worldpop.setXname("Kuwait");
            Nationality_list.add(worldpop);

            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Nationality_list.addAll(partnerPojo.row);

                Resources res = getResources();
                nationality_adapter = new Nationality_Adapter(BecomePassionate_Visitor.this, R.layout.countryspinner_country_new, Nationality_list, res);
                sp_nationality.setAdapter(nationality_adapter);
                sp_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                        sp_nationality.setSelection(position);
                        Nationality_Xcode = Nationality_list.get(position).xCode;
                        Log.e("Nationality_Xcode", Nationality_Xcode);

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