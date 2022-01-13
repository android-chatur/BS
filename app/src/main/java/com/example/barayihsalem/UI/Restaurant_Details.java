package com.example.barayihsalem.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
import com.example.barayihsalem.UI.Adapter.Restaurant_AllMenu_Adapter;
import com.example.barayihsalem.UI.Adapter.Restaurant_Detail_Adapter;
import com.example.barayihsalem.UI.Adapter.Timing_Adapter;
import com.example.barayihsalem.UI.Model.Get_Store_Details_Pojo;
import com.example.barayihsalem.UI.Model.StoreFeaturedItem;
import com.example.barayihsalem.UI.Model.StoreMenu;
import com.example.barayihsalem.UI.Model.StoreTimeInfo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.Filter_Activity.filte;
import static com.example.barayihsalem.UI.Food_Activity.iffood;
import static com.example.barayihsalem.UI.Retails_Details.retail;

public class Restaurant_Details extends AppCompatActivity implements View.OnClickListener {
    public static boolean back = false;
    TextView txtlab, rating, offers_, mim_or_price, mim_oder, delevary_price, delivary_oder, min_price, time, featured, all_menu, kilo, info;
    AppController appController;
    AppPreferences appPreferences;
    RatingBar rvratingbarptgym;
    APIInterface apiInterface;
    ArrayList<StoreFeaturedItem> StoreFeatur_list = new ArrayList<>();
    ArrayList<StoreMenu> Storemenu_list = new ArrayList<>();
    ArrayList<StoreTimeInfo> Storetiming_list = new ArrayList<>();
    String phone_nu;
    SessionManager sessionManager;
    TextView lne, dd, phone_number;
    ImageView logoside, seach, phone;
    LinearLayout sortandfilter;
    ProgressDialog progressDialog;
    RelativeLayout abcd;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_listallmenu, rv_list_resu, rv_list_timing;
    Restaurant_Detail_Adapter resta_food_adapter;
    Restaurant_AllMenu_Adapter allMenu_adapter;
    Timing_Adapter timing_adapter;
    LinearLayoutManager layoutManager;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            //Toast.makeText(appController, "hiiii", Toast.LENGTH_SHORT).show();
            if (StoreFeatur_list != null && StoreFeatur_list.size() > 0) {
                appPreferences.SaveItem_xcode(StoreFeatur_list.get(position).getxCode());
                back = true;
                startActivity(new Intent(Restaurant_Details.this, Dish_Detail_Activity.class));
                finish();
            }

        }

    };

    OnItemClickListener.OnClickCallback onClickCall_All = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            if (Storemenu_list != null && Storemenu_list.size() > 0) {
                filte = false;
                filte = false;
                appPreferences.SaveMenu_xcode(Storemenu_list.get(position).getxCode());
                appPreferences.SaveMenu_xName(Storemenu_list.get(position).getxName());
                startActivity(new Intent(Restaurant_Details.this, Menu_Activity.class));
                finish();

            }
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
            setContentView(R.layout.activity_restaurant__details);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_restaurant__details_ar);

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


        if (Restaurant_Details.this.isNetworkConnected()) {
            showProgress();
            Get_Store_Details();

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

    private void Get_Store_Details() {
        Get_Store_Details_Pojo getStoreDetailsPojo = new Get_Store_Details_Pojo();
        getStoreDetailsPojo.setUserId(appPreferences.getuserid());
        getStoreDetailsPojo.setValue(appPreferences.geStore_SrNo());
        getStoreDetailsPojo.setValue1(appPreferences.geShoppingSrno());
        getStoreDetailsPojo.setValue2("");
        getStoreDetailsPojo.setCorpcentreBy(appPreferences.get_company_id());
        getStoreDetailsPojo.setCultureId(appPreferences.getCulturemode());


        Call<Get_Store_Details_Pojo> call2 = apiInterface.Get_Store_Details(getStoreDetailsPojo);
        call2.enqueue(new Callback<Get_Store_Details_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Get_Store_Details_Pojo> call, Response<Get_Store_Details_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        String Store_SrNo = response.body().getStoreTag().getStoreTag();
                        String Stotxtlabre_ = response.body().getStoreDetails().getStoreName();


                        String mim_or_price1 = String.format("%.3f", response.body().getStoreDetails().getStoreMinOrder());
                        String delevary_price1 = String.format("%.3f", response.body().getStoreDetails().getStoreDeliveryCharge());

                        mim_or_price.setText(mim_or_price1 + " " + response.body().getStoreDetails().getCurrency());
                        delevary_price.setText(delevary_price1 + " " + response.body().getStoreDetails().getCurrency());
                        String min = String.valueOf(response.body().getStoreDetails().getStoreDeliveryTime());
                        min_price.setText(min);
                        txtlab.setText(Stotxtlabre_);
                        time.setText(response.body().getStoreDetails().getTimeInWords());


                        double as = response.body().getStoreDetails().getRatingStar();
                        String Mem_P_as = String.format("%.0f", as);

                        String rating1 = String.valueOf(response.body().getStoreDetails().getRatings());
                        // rating.setText(rating1);
                        // rvratingbarptgym.setRating(shopping_list.get(position).getRatings());
                        rvratingbarptgym.setRating((float) as);


                        if (appPreferences.getCulturemode().equals("1")) {
                            rating.setText(rating1 + " " + "Rating");

                        } else {
                            rating.setText(rating1 + " " + "تقييم");

                        }

                        lne.setText(response.body().getStoreDetails().getStoreAddress() + " " + response.body().getStoreDetails().getStoreArea());
                        dd.setText(response.body().getStoreDetails().getStoreCountry());
                        offers_.setText(Store_SrNo);
                        phone_number.setText(response.body().getStoreDetails().getStoreMobile());

                         phone_nu=response.body().getStoreDetails().getStoreMobile();
                        StoreFeatur_list.addAll(response.body().getStoreFeaturedItemList());
                        Storemenu_list.addAll(response.body().getStoreMenuList());
                        Storetiming_list.addAll(response.body().getStoreTimeInfo());
                      /*  Community_list.addAll(response.body().list2);
                        Common_list.addAll(response.body().commonList);
                        Add_list.addAll(response.body().adsDetails);*/

                        StoreFeatur_list.addAll(response.body().getStoreFeaturedItemList());
                        if (!(StoreFeatur_list.size() == 0)) {
                            featured.setVisibility(View.VISIBLE);
                            resta_food_adapter = new Restaurant_Detail_Adapter(Restaurant_Details.this, onClickCall, StoreFeatur_list);
                            layoutManager = new LinearLayoutManager(Restaurant_Details.this, LinearLayoutManager.HORIZONTAL, false);
                            rv_list_resu.setLayoutManager(layoutManager);
                            rv_list_resu.setAdapter(resta_food_adapter);
                        } else {
                            featured.setVisibility(View.GONE);
                        }
                        if (!(Storemenu_list.size() == 0)) {
                            all_menu.setVisibility(View.VISIBLE);
                            allMenu_adapter = new Restaurant_AllMenu_Adapter(Restaurant_Details.this, onClickCall_All, Storemenu_list);
                            layoutManager = new LinearLayoutManager(Restaurant_Details.this, LinearLayoutManager.VERTICAL, false);
                            rv_listallmenu.setLayoutManager(layoutManager);
                            rv_listallmenu.setAdapter(allMenu_adapter);
                        } else {
                            all_menu.setVisibility(View.GONE);

                        }

                        timing_adapter = new Timing_Adapter(Restaurant_Details.this, Storetiming_list);
                        layoutManager = new LinearLayoutManager(Restaurant_Details.this, LinearLayoutManager.VERTICAL, false);
                        rv_list_timing.setLayoutManager(layoutManager);
                        rv_list_timing.setAdapter(timing_adapter);
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
            public void onFailure(Call<Get_Store_Details_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Restaurant_Details.this);
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

    private void findid() {
        rv_list_timing = findViewById(R.id.rv_list_timing);
        rv_list_resu = findViewById(R.id.rv_list_resu);
        rv_listallmenu = findViewById(R.id.rv_listallmenu);
        logoside = findViewById(R.id.logoside);
        txtlab = findViewById(R.id.txtlab);
        rvratingbarptgym = findViewById(R.id.rvratingbarptgym);
        offers_ = findViewById(R.id.offers_);
        rating = findViewById(R.id.rating);
        mim_or_price = findViewById(R.id.mim_or_price);
        mim_oder = findViewById(R.id.mim_oder);
        delevary_price = findViewById(R.id.delevary_price);
        delivary_oder = findViewById(R.id.delivary_oder);
        time = findViewById(R.id.time);
        seach = findViewById(R.id.seach);
        phone = findViewById(R.id.phone);

        seach.setOnClickListener(this);
        phone.setOnClickListener(this);

        featured = findViewById(R.id.featured);
        min_price = findViewById(R.id.min_price);
        all_menu = findViewById(R.id.all_menu);
        info = findViewById(R.id.info);
        kilo = findViewById(R.id.kilo);

        lne = findViewById(R.id.lne);
        phone_number = findViewById(R.id.phone_number);
        dd = findViewById(R.id.dd);
        logoside.setOnClickListener(this);
        txtlab.setTypeface(typebold);
        offers_.setTypeface(typeRegular);
        mim_or_price.setTypeface(typeRegular);
        dd.setTypeface(typeRegular);
        kilo.setTypeface(typeRegular);
        lne.setTypeface(typeRegular);
        phone_number.setTypeface(typeRegular);

        mim_oder.setTypeface(typeRegular);
        rating.setTypeface(typeRegular);
        delevary_price.setTypeface(typeRegular);
        delivary_oder.setTypeface(typeRegular);
        min_price.setTypeface(typeRegular);
        time.setTypeface(typeRegular);
        featured.setTypeface(typebold);
        all_menu.setTypeface(typebold);
        info.setTypeface(typebold);


    }

    @Override
    public void onBackPressed() {

        if (iffood == true) {
            startActivity(new Intent(Restaurant_Details.this, Grocery_Activity.class));
            finish();
        } else {
            startActivity(new Intent(Restaurant_Details.this, Restaurant_activity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoside:
                onBackPressed();
                break;


            case R.id.seach:
                retail=false;
                appPreferences.save_AddBackSeach("Restaurant_Details");

                startActivity(new Intent(Restaurant_Details.this, Search_Shopping.class));
                finish();
                break;

            case R.id.phone:
                if (Build.VERSION.SDK_INT > 22) {

                    if (ActivityCompat.checkSelfPermission(Restaurant_Details.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(Restaurant_Details.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                        return;
                    }
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phone_nu));
                    startActivity(callIntent);
                } else {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phone_nu));
                    startActivity(callIntent);
                }

                break;
        }
    }


}