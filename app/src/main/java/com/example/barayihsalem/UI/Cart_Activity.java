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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Shopping_Adapter;
import com.example.barayihsalem.UI.Model.PromoCode_Pojo;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Update_Or_Delete_Cart_All_Pojo;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.example.barayihsalem.UI.View.ProductHoldernew;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Cart_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, items, filter, settings, offer, notifi, reach_help, abou_app, oprated;
    AppController appController;
    ProgressDialog progressDialog;
    TextView sub_total, aatoorder, aatoorder_price, sub_total_value, shiping, shipping_value, sub_discount, discount_value;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    APIInterface apiInterface;

    AppPreferences appPreferences;
    EditText editText_mob;
    String Promo = "";
    ImageView logoside;
    Shopping_Adapter shopping_adapter;
    Button btnapplay;
    Cart_Adapter cart_adapter;
    RecyclerView rw_cart, rw_comminity;
    RelativeLayout lin_button, abcd;
    Cart_Adapter_ allMenu_adapter;
    ArrayList<Row> Cart_list = new ArrayList<Row>();
    Double SubTotal, Discount, Delivery_Charge, GrandTotal;
    String Currency;
    LinearLayoutManager layoutManager;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {
            //  Toast.makeText(appController, "hiiii", Toast.LENGTH_SHORT).show();

           /* if (position == 0) {
                appPreferences.save_showhompage("Vision partner");
                startActivity(new Intent(WellcomefamilyPage.this, VisionPartner.class));
                finish();
            }
         */

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
       /* if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }*/
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_cart_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_cart_ar);

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


        if (Cart_Activity.this.isNetworkConnected()) {
            new Get_Food_Store_Wise_Cart_List().execute();
            new SP_Get_Cart_Total_Food().execute();
            new Get_Food_Store_Wise_Cart_PromoDetails().execute();

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

    private void findid() {
        abcd = findViewById(R.id.abcd);
        rw_cart = findViewById(R.id.rv_list_resu);
        sub_total = findViewById(R.id.sub_total);
        lin_button = findViewById(R.id.lin_button);
        sub_total_value = findViewById(R.id.sub_total_value);
        shiping = findViewById(R.id.shiping);
        shipping_value = findViewById(R.id.shipping_value);
        sub_discount = findViewById(R.id.sub_discount);
        discount_value = findViewById(R.id.discount_value);
        logoside = findViewById(R.id.logoside);
        editText_mob = findViewById(R.id.editText_mob);
        btnapplay = findViewById(R.id.btnapplay);
        sub_total.setTypeface(normal);
        discount_value.setTypeface(normal);
        shiping.setTypeface(normal);
        shipping_value.setTypeface(normal);
        sub_discount.setTypeface(normal);
        sub_total_value.setTypeface(normal);
        lin_button.setOnClickListener(this);
        btnapplay.setOnClickListener(this);
        logoside.setOnClickListener(this);
        aatoorder = findViewById(R.id.aatoorder);
        aatoorder_price = findViewById(R.id.aatoorder_price);
        aatoorder.setTypeface(typeRegular);
        aatoorder_price.setTypeface(typeRegular);
        editText_mob.setTypeface(typeRegular);
        btnapplay.setTypeface(typeRegular);
      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_cart.setLayoutManager(gridLayoutManager);
        cart_adapter = new Cart_Adapter(Cart_Activity.this);
        rw_cart.setAdapter(cart_adapter);*/
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Cart_Activity.this, Shopping_Category_page.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_button:


                startActivity(new Intent(Cart_Activity.this, Checkout_Activity.class));
                finish();
                break;

            case R.id.logoside:
                onBackPressed();
                break;
            case R.id.btnapplay:

                if (Cart_Activity.this.isNetworkConnected()) {
                    if (validate()) {
                        showProgress();
                        Apply_PromoCode();
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

    private void Apply_PromoCode() {
        PromoCode_Pojo attribute_pojo = new PromoCode_Pojo();

        attribute_pojo.setStore_SrNo(appPreferences.geStore_SrNo());
        attribute_pojo.setPromoCode(Promo);
        attribute_pojo.setUserId(appPreferences.getuserid());
        attribute_pojo.setUniqueId(sessionManager.GetUniqueId());
        attribute_pojo.setIpAddress(sessionManager.GetIpAddress());
        attribute_pojo.setWebCountryCode("KW");
        attribute_pojo.setCompanyId(appPreferences.get_company_id());


        Call<PromoCode_Pojo> call2 = apiInterface.Apply_PromoCode(attribute_pojo);
        call2.enqueue(new Callback<PromoCode_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<PromoCode_Pojo> call, Response<PromoCode_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        new SP_Get_Cart_Total_Food().execute();

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();


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
            public void onFailure(Call<PromoCode_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private boolean validate() {
        Promo = editText_mob.getText().toString();

        if (TextUtils.isEmpty(editText_mob.getText().toString())) {
            editText_mob.setError("Please Enter Promocode");
            editText_mob.requestFocus();
            return false;
        }
        return true;
    }

    private void Update_Or_Delete_Cart_All(String cart_id, String del_Update, String qunt) {
        Update_Or_Delete_Cart_All_Pojo update_or_delete_cart_all_pojo = new Update_Or_Delete_Cart_All_Pojo();
        update_or_delete_cart_all_pojo.setCartId(cart_id);

        update_or_delete_cart_all_pojo.setUserId(appPreferences.getuserid());
        update_or_delete_cart_all_pojo.setStore_SrNo(appPreferences.geStore_SrNo());
        update_or_delete_cart_all_pojo.setQty(qunt);
        update_or_delete_cart_all_pojo.setCommand(del_Update);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Update_Or_Delete_Cart_All_Pojo> call2 = apiInterface.Update_Or_Delete_Cart_All(update_or_delete_cart_all_pojo);
        call2.enqueue(new Callback<Update_Or_Delete_Cart_All_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Update_Or_Delete_Cart_All_Pojo> call, Response<Update_Or_Delete_Cart_All_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(getIntent());


                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        new SP_Get_Cart_Total_Food().execute();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-6")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
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
            public void onFailure(Call<Update_Or_Delete_Cart_All_Pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Cart_Activity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class Cart_Adapter_ extends RecyclerView.Adapter<ProductHoldernew> {
        String xcode;
        SessionManager sessionManager;
        int cartcount, wishcnt;
        String Del_Update = "";

        APIInterface apiInterface;
        String cart_id;
        String userId = "0";
        int no_of_passe = 1;


        String cart_id_update = "";
        String command = "";
        AppPreferences appPreferences;
        ProgressDialog progressDialog;
        //  private AppController appController;
        ArrayList<Row> cart_list = new ArrayList<>();
        //   String image;
        //  private Global globalClass;
        //   private ArrayList<Row> subCategories, subCategories1;
        private Context context;
        private double price;
        private RelativeLayout relative_layout;
        //   private CarViewListner carviewListener;
        private float perhourcharge;
        private TextView txtCartCount = null;
        private Typeface typeSemibold, typeRegular, typeLight;

        public Cart_Adapter_(Cart_Activity context, ArrayList<Row> cart_list) {
            this.context = context;
            this.cart_list = cart_list;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public ProductHoldernew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();

            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_ar, parent, false);

            }


            ProductHoldernew rvh = new ProductHoldernew(view);
            return rvh;
        }

        @Override
        public void onBindViewHolder(@NonNull final ProductHoldernew holder, final int position) {
            //  final Row subCategory = subCategories.get(position);
            final int po = position;
            appPreferences = new AppPreferences(context);
            cartcount = appPreferences.getcartcnt();
            //  wishcnt = appPreferences.getwishcnt();
            sessionManager = new SessionManager(context);


            holder.prod_name.setText((cart_list.get(position).getxName()));
            String qunt = String.valueOf(cart_list.get(position).getQuantity());
            holder.txtQuantity.setText(qunt);

            holder.txtQuantity.setText(qunt);
            no_of_passe = cart_list.get(position).getQuantity();

            double changed_total = (cart_list.get(position).getPrice());
            holder.prod_price.setText(String.format("%.3f", changed_total) + " " + cart_list.get(position).getCurrency());


            Picasso.with(context)
                    .load(context.getResources().getString(R.string.ItemCard) + cart_list.get(position).getImage1())
                    .error(R.drawable.home)
                    .placeholder(R.drawable.home)
                    // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                    //  .fit()
                    .into(holder.itemImage);

            holder.delete_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String qunt = String.valueOf(cart_list.get(position).getQuantity());
                    Del_Update = "Delete";
                    showProgress();
                    Update_Or_Delete_Cart_All(String.valueOf(cart_list.get(position).getCartId()), Del_Update, qunt);

                }
            });


            holder.txtQuantity.setText(String.valueOf(no_of_passe));


            holder.imgPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    no_of_passe = Integer.parseInt(holder.txtQuantity.getText().toString());
                    if (no_of_passe != 100) {
                        no_of_passe++;
                        Del_Update = "Update";

                        holder.txtQuantity.setText(String.valueOf(no_of_passe));
                        Update_Or_Delete_Cart_All(String.valueOf(cart_list.get(position).getCartId()), Del_Update, String.valueOf(no_of_passe));


                    }

                }
            });


            holder.imgMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    no_of_passe = Integer.parseInt(holder.txtQuantity.getText().toString());
                    if (no_of_passe != 1) {
                        no_of_passe--;
                        Del_Update = "Update";

                        holder.txtQuantity.setText(String.valueOf(no_of_passe));

                        Update_Or_Delete_Cart_All(String.valueOf(cart_list.get(position).getCartId()), Del_Update, String.valueOf(no_of_passe));


                    }

                }
            });


        }


        @Override
        public int getItemCount() {
            return cart_list.size();
        }

    }

    private class Get_Food_Store_Wise_Cart_List extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Food_Store_Wise_Cart_List','" + appPreferences.geStore_SrNo() + "','" + sessionManager.GetUniqueId() + "','" + sessionManager.GetIpAddress() + "','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Food_Store_Wise_Cart_List", response);
            //  progressDialog.dismiss();


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                Cart_list.addAll(partnerPojo.row);

                allMenu_adapter = new Cart_Adapter_(Cart_Activity.this, Cart_list);
                layoutManager = new LinearLayoutManager(Cart_Activity.this, LinearLayoutManager.VERTICAL, false);
                rw_cart.setLayoutManager(layoutManager);
                rw_cart.setAdapter(allMenu_adapter);


            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }


        }
    }

    private class Get_Food_Store_Wise_Cart_PromoDetails extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Food_Store_Wise_Cart_PromoDetails','" + appPreferences.geStore_SrNo() + "','" + sessionManager.GetUniqueId() + "','" + sessionManager.GetIpAddress() + "','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("Get_Food_Store_Wise_Cart_PromoDetails", response);
            //  progressDialog.dismiss();


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String PromoCode = collegeData.getString("PromoCode");


                        editText_mob.setText(PromoCode);

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }

        }
    }

    private class SP_Get_Cart_Total_Food extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "SP_Get_Cart_Total_Food'" + appPreferences.geStore_SrNo() + "','" + appPreferences.getuserid() + "','" + sessionManager.GetUniqueId() + "','" + sessionManager.GetIpAddress() + "','KW','" + appPreferences.get_company_id() + "','" + appPreferences.getCulturemode() + "'");
                String query = builder.build().getEncodedQuery();
                //.d("awe",query);
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
            Log.d("KWt", response);


            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        SubTotal = collegeData.getDouble("SubTotal");
                        Discount = collegeData.getDouble("Discount");
                        Delivery_Charge = collegeData.getDouble("Delivery_Charge");
                        GrandTotal = collegeData.getDouble("GrandTotal");
                        Currency = collegeData.getString("Currency");


                        String Total = String.format("%.3f", SubTotal);
                        String Disco = String.format("%.3f", Discount);
                        String Delivery_Cha = String.format("%.3f", Delivery_Charge);
                        String GrandTota = String.format("%.3f", GrandTotal);

                        sub_total_value.setText(Total + " " + Currency);
                        shipping_value.setText(Disco + " " + Currency);
                        discount_value.setText(Delivery_Cha + " " + Currency);
                        aatoorder_price.setText(GrandTota + " " + Currency);


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }


        }
    }


}
