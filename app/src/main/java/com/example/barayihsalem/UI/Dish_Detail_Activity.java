package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Menu_Adapter;
import com.example.barayihsalem.UI.Adapter.Produtdeta_Adapter_Food;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
import com.example.barayihsalem.UI.Model.Add_to_Cart_Pojo;
import com.example.barayihsalem.UI.Model.DishDetail_Viewholder;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;

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
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.Filter_Activity.filte;
import static com.example.barayihsalem.UI.Restaurant_Details.back;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Dish_Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    private static ViewPager mPager;
    private static int currentPage = 0;
    AppController appController;
    boolean conforn = false;
    AppPreferences appPreferences;
    ArrayList<HashMap<String, String>> AL_route_bus_collection_a = new ArrayList<HashMap<String, String>>();
    String stockqty = "12";
    int no_of_passe = 1;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String XCode, XName, Image1, Image2, Image3, Currency, Description, Ingredients1;
    Double NewPrice;
    APIInterface apiInterface;

    String
            Size = "",
            Color_ = "",
            Pattern = "",
            Style = "";

    Double productPrice = 0.0;
    TextView qnt_text, item_name, item_price, item_info, Ingredients, qnt_value, aatoorder, aatoorder_price, item, Indre_value, entras;
    ImageView logoside, imgPlus, imgMinus;
    LinearLayout sortandfilter;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    Timer swipeTimer = null;

    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout lin_button, abcd;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_extrash_list;
    Menu_Adapter menu_adapter;
    ArrayList<Row> Extras_list = new ArrayList<Row>();

    // Extras_Adapter allMenu_adapter;
    Extras_Adapter_InnerClass allMenu_adapter;
    LinearLayoutManager layoutManager;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall_All = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {


        }

    };

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
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            // setContentView(R.layout.activity_all__food);
            setContentView(R.layout.activity_dish__detail_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_dish__detail_ar);

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

        if (Dish_Detail_Activity.this.isNetworkConnected()) {
            new Get_Food_Store_Wise_ItemDetails_By_SrNo().execute();
            new Get_Food_Store_Wise_ItemExtra_List().execute();


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

    private void init(ArrayList<String> arrayList) {
        ArrayList<String> Add_list_deatal = new ArrayList<>();
        mPager = findViewById(R.id.pager);


        for (int i = 0; i < arrayList.size(); i++)
            Add_list_deatal.add(arrayList.get(i));


        mPager.setAdapter(new Produtdeta_Adapter_Food(Dish_Detail_Activity.this, arrayList));
        PageIndicatorView indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setSelectedColor(Color.BLACK);
        indicator.setUnselectedColor(Color.GRAY);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == arrayList.size()) {
                    currentPage = 0;

                }
                mPager.setCurrentItem(currentPage++, true);

            }
        };
        swipeTimer = new Timer();
        // Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(Update);
                                }

                            },
                4000, 4000);


    }

    private void findid() {
        lin_button = findViewById(R.id.lin_button);
        abcd = findViewById(R.id.abcd);
        entras = findViewById(R.id.entras);
        rv_extrash_list = findViewById(R.id.rv_extrash_list);
        item_name = findViewById(R.id.item_name);
        qnt_text = findViewById(R.id.qnt_text);
        item_info = findViewById(R.id.item_info);
        item_price = findViewById(R.id.item_price);
        Ingredients = findViewById(R.id.Ingredients);
        Indre_value = findViewById(R.id.Indre_value);
        qnt_value = findViewById(R.id.qnt_value);
        item = findViewById(R.id.item);
        imgPlus = findViewById(R.id.imgPlus);
        aatoorder = findViewById(R.id.aatoorder);
        imgMinus = findViewById(R.id.imgMinus);
        aatoorder_price = findViewById(R.id.aatoorder_price);
        item_name.setTypeface(typebold);
        entras.setTypeface(typebold);
        qnt_text.setTypeface(typebold);
        item_price.setTypeface(typeRegular);
        qnt_value.setTypeface(typeRegular);
        item_info.setTypeface(typeRegular);
        Ingredients.setTypeface(typeRegular);
        Indre_value.setTypeface(typeRegular);
        item.setTypeface(typeRegular);
        aatoorder.setTypeface(typeRegular);
        aatoorder_price.setTypeface(typeRegular);
        lin_button.setOnClickListener(this);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        imgPlus.setOnClickListener(this);
        imgMinus.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        filte = false;

        if (back == false) {
            startActivity(new Intent(Dish_Detail_Activity.this, Menu_Activity.class));
            finish();
        } else {
            startActivity(new Intent(Dish_Detail_Activity.this, Restaurant_Details.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_button:

                if (Dish_Detail_Activity.this.isNetworkConnected()) {

                    showProgress();
                    Add_to_Cart_All();
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
//                startActivity(new Intent(Dish_Detail_Activity.this, Cart_Activity.class));
//                finish();
                break;

            case R.id.imgPlus:

                //  stockqty = cart_arrList.get(position).getAvailableQuantity();
                no_of_passe = Integer.parseInt(qnt_value.getText().toString());
                if (no_of_passe != Integer.parseInt(stockqty)) {


                    if (stockqty.equals("0")) {
                        if (appPreferences.getCulturemode().equals("1")) {
                            //    TastyToast.makeText(CartPage.this, "Stock Not Available", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            Toast.makeText(appController, "Stock Not Available", Toast.LENGTH_SHORT).show();

                        } else {
                            //  TastyToast.makeText(CartPage.this, "المخزون غير متاح", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                        }
                    } else {
                        no_of_passe++;

                        qnt_value.setText(String.valueOf(no_of_passe));

                        NewPrice = productPrice * no_of_passe;

                        String Mem_Price = String.format("%.3f", NewPrice);
                        aatoorder_price.setText(Mem_Price + " " + Currency);

                    }
                }
                break;

            case R.id.imgMinus:

                no_of_passe = Integer.parseInt(qnt_value.getText().toString());
                if (no_of_passe != 1) {
                    no_of_passe--;
                   /* cart_id_update = card_details.getCartID();
                    new Sp_Update_Cart_Apps().execute();*/
                    qnt_value.setText(String.valueOf(no_of_passe));

                    NewPrice = productPrice * no_of_passe;

                    String Mem_Price = String.format("%.3f", NewPrice);
                    aatoorder_price.setText(Mem_Price + " " + Currency);

                }
                break;


            case R.id.logoside:
                onBackPressed();
                break;
        }
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Dish_Detail_Activity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void Add_to_Cart_All() {
        Add_to_Cart_Pojo add_to_cart_pojo = new Add_to_Cart_Pojo();

        add_to_cart_pojo.setCategory(appPreferences.geShoppingSrno());
        add_to_cart_pojo.setUserId(appPreferences.getuserid());
        add_to_cart_pojo.setStore_SrNo(appPreferences.geStore_SrNo());

        add_to_cart_pojo.setProductCode(XCode);
        add_to_cart_pojo.setPrice(String.valueOf(productPrice));
        add_to_cart_pojo.setQuantity(String.valueOf(no_of_passe));
        add_to_cart_pojo.setSize(Size);
        add_to_cart_pojo.setColor(Color_);
        add_to_cart_pojo.setPattern(Pattern);
        add_to_cart_pojo.setStyle(Style);
        add_to_cart_pojo.setCart_Extra(list);
        add_to_cart_pojo.setUniqueId(sessionManager.GetUniqueId());
        add_to_cart_pojo.setCorpcentreby(appPreferences.get_company_id());
        add_to_cart_pojo.setIpAddress(sessionManager.GetIpAddress());
        add_to_cart_pojo.setActive(true);
        add_to_cart_pojo.setDeleted(false);
        add_to_cart_pojo.setConfirm(conforn);


        Call<Add_to_Cart_Pojo> call2 = apiInterface.Add_to_Cart_All(add_to_cart_pojo);
        call2.enqueue(new Callback<Add_to_Cart_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Add_to_Cart_Pojo> call, Response<Add_to_Cart_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Dish_Detail_Activity.this, Cart_Activity.class));
                        finish();

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Dish_Detail_Activity.this, Cart_Activity.class));
                        finish();

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-10")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        exitByBackKey(response.body().getMessage());


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
            public void onFailure(Call<Add_to_Cart_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void exitByBackKey(String message) {

        AlertDialog alertbox = new AlertDialog.Builder(this)

                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        conforn = true;
                        showProgress();
                        Add_to_Cart_All();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //DoNothing
                    }
                })
                .setTitle("Exit")
                .setIcon(R.drawable.ic_launcher_background)
                .setCancelable(false)
                .show();

    }


    private class Get_Food_Store_Wise_ItemDetails_By_SrNo extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Food_Store_Wise_ItemDetails_By_SrNo','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','" + appPreferences.getItem_xcode() + "','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("item_List", response);
            //  progressDialog.dismiss();
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        XCode = collegeData.getString("XCode");
                        XName = collegeData.getString("XName");
                        Image1 = collegeData.getString("Image1");
                        Image2 = collegeData.getString("Image2");
                        Image3 = collegeData.getString("Image3");
                        Currency = collegeData.getString("Currency");
                        Description = collegeData.getString("Description");
                        Ingredients1 = collegeData.getString("Ingredients");
                        NewPrice = collegeData.getDouble("NewPrice");
                        productPrice = collegeData.getDouble("NewPrice");
                        if (!Image1.equals("Default.jpg")) {
                            arrayList.add(Image1);

                        }
                        if (!Image2.equals("Default.jpg")) {
                            arrayList.add(Image2);

                        }
                        if (!Image3.equals("Default.jpg")) {
                            arrayList.add(Image3);
                        }


                        item_name.setText(XName);
                        item_info.setText(Description);
                        Indre_value.setText(Ingredients1);
                        String Mem_Price = String.format("%.3f", NewPrice);


                        item_price.setText(Mem_Price + " " + Currency);
                        aatoorder_price.setText(Mem_Price + " " + Currency);


                    }
                    init(arrayList);
                }
            } catch (JSONException e) {


            }


        }
    }

    private class Get_Food_Store_Wise_ItemExtra_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Food_Store_Wise_ItemExtra_List','" + appPreferences.geStore_SrNo() + "','" + appPreferences.geShoppingSrno() + "','" + appPreferences.getItem_xcode() + "','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Food_Store_Wise_ItemExtra_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                Extras_list.addAll(partnerPojo.row);

                allMenu_adapter = new Extras_Adapter_InnerClass(Dish_Detail_Activity.this, Extras_list);
                layoutManager = new LinearLayoutManager(Dish_Detail_Activity.this, LinearLayoutManager.VERTICAL, false);
                rv_extrash_list.setLayoutManager(layoutManager);
                rv_extrash_list.setAdapter(allMenu_adapter);

            } else {
               // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }


        }
    }

    private class Extras_Adapter_InnerClass extends RecyclerView.Adapter<DishDetail_Viewholder> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;

        //ArrayList<SubCategoryDTO> list = new ArrayList<>();
        AppPreferences appPreferences;
        ArrayList<Row> Extras = new ArrayList<>();
        private Context context;
        private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

        public Extras_Adapter_InnerClass(Context context, ArrayList<Row> Extras) {
            this.context = context;
            this.Extras = Extras;


        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public DishDetail_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extras_list, parent, false);
            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extras_list_ar, parent, false);

            }
            return new DishDetail_Viewholder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull DishDetail_Viewholder holder, final int position) {
            holder.tvName.setText(Extras.get(position).getExtraName());
            String mim_or_price1 = String.format("%.3f", Extras.get(position).getPrice());

            holder.shushi.setText(mim_or_price1 + " " + "KD");

            Extras.get(position).setSelected(!Extras.get(position).isSelected());

            if (Extras.get(position).isSelected()) {
                // Select_Zone = Extras.get(position).getxCode();
            }
            holder.newly_tick.setVisibility(View.GONE);

            holder.tems_cond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        holder.tems_cond.setSelected(isChecked);

                        NewPrice = NewPrice + Extras.get(position).getPrice();

                        String Mem_Price = String.format("%.3f", NewPrice);
                        aatoorder_price.setText(Mem_Price + " " + Currency);


                        HashMap<String, String> hashmap = new HashMap();

                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put("ProductCode", XCode);
                        temp.put("ItemExtraCode", Extras.get(position).getxCode());
                        double asd = Extras.get(position).getPrice();
                        temp.put("Price", String.valueOf(asd));
                        list.add(temp);


                    } else {
                        holder.tems_cond.setSelected(false);
                        NewPrice = NewPrice - Extras.get(position).getPrice();

                        String Mem_Price = String.format("%.3f", NewPrice);
                        aatoorder_price.setText(Mem_Price + " " + Currency);

                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put("ProductCode", XCode);
                        temp.put("ItemExtraCode", Extras.get(position).getxCode());
                        double asd = Extras.get(position).getPrice();
                        temp.put("Price", String.valueOf(asd));
                        list.remove(temp);

                    }
                }
            });


//
        }

        @Override
        public int getItemCount() {
            return Extras.size();
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