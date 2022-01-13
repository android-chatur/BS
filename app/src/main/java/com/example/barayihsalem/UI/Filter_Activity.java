package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
import com.example.barayihsalem.UI.Model.FilterAdapter_MyView;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Filter_Activity extends AppCompatActivity implements View.OnClickListener /*implements View.OnClickListener*/ {
    public static boolean filte = false;
    TextView cancel, filter, clear, filter_by;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    TextView sortfilt;
    ImageView tick_low, tick_high, newly_tick;
    Spinner id_city, id_country;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_list_filter;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    LinearLayoutManager layoutManager;
    ArrayList<Row> filter_list = new ArrayList<Row>();

    OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

         /*   startActivity(new Intent(All_Food.this, Restaurant_activity.class));
            finish();
           *//* if (position == 0) {
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

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.buss_oner_color)); //status bar or the time bar at the top
        }
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            // setContentView(R.layout.activity_all__food);
            setContentView(R.layout.activity_filter_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_filter_ar);

        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

        if (Filter_Activity.this.isNetworkConnected()) {

            new Get_Filter_SortBy_list().execute();


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
        rv_list_filter = findViewById(R.id.rv_list_filter);
        cancel = findViewById(R.id.cancel);
        btnLogin = findViewById(R.id.btnLogin);
        filter = findViewById(R.id.filter);
        clear = findViewById(R.id.clear);
        tick_low = findViewById(R.id.tick_low);

        filter_by = findViewById(R.id.filter_by);


        filter_by.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        cancel.setTypeface(typeRegular);
        filter.setTypeface(typeRegular);

        clear.setTypeface(typeRegular);
        cancel.setOnClickListener(this);

        clear.setOnClickListener(this);

        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        filte = false;
        if (appPreferences.get_Bafilte().equals("Menu_Activity")) {
            startActivity(new Intent(Filter_Activity.this, Menu_Activity.class));
            finish();
        }
        if (appPreferences.get_Bafilte().equals("Restaurant_activity")) {
            startActivity(new Intent(Filter_Activity.this, Restaurant_activity.class));
            finish();
        }

        if (appPreferences.get_Bafilte().equals("Brand_Page_Activity")) {
            startActivity(new Intent(Filter_Activity.this, Brand_Page_Activity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.clear:
                new Get_Filter_SortBy_list().execute();


                break;
            case R.id.btnLogin:
                filte = true;
                if (appPreferences.get_Bafilte().equals("Menu_Activity")) {
                    startActivity(new Intent(Filter_Activity.this, Menu_Activity.class));
                    finish();
                }
                if (appPreferences.get_Bafilte().equals("Restaurant_activity")) {
                    startActivity(new Intent(Filter_Activity.this, Restaurant_activity.class));
                    finish();
                }

                if (appPreferences.get_Bafilte().equals("Brand_Page_Activity")) {
                    startActivity(new Intent(Filter_Activity.this, Brand_Page_Activity.class));
                    finish();
                }

                break;

            case R.id.cancel:
                filte = false;

                onBackPressed();
                break;


        }

    }


    private class Get_Filter_SortBy_list extends AsyncTask<String, String, String> {
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
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Filter_SortBy_list','','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
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
            Log.d("filter_List", response);
            //  progressDialog.dismiss();
            //lowest


            VisionspinnerPojo partnerPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (partnerPojo.success.equals(1)) {
                // Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();

                filter_list.addAll(partnerPojo.row);

                FilterAdapter filterAdapter = new FilterAdapter(Filter_Activity.this, filter_list);
                layoutManager = new LinearLayoutManager(Filter_Activity.this, LinearLayoutManager.VERTICAL, false);
                rv_list_filter.setLayoutManager(layoutManager);
                rv_list_filter.setAdapter(filterAdapter);

            } else {
                Toast.makeText(appController, partnerPojo.message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class FilterAdapter extends RecyclerView.Adapter<FilterAdapter_MyView> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
        TextView tvName;
        /* private static CheckBox lastChecked = null;
         private static int lastCheckedPos = 0;*/
        ImageView ivImage;
        ArrayList<Row> shopping_list = new ArrayList<>();
        AppPreferences appPreferences;
        LinearLayout card;
        private int mCheckedPostion = -1;
        private Context context;
        private OnItemClickListener.OnClickCallback onClickCallback;
        private int lastCheckedPosition = -1;

        public FilterAdapter(Context context, ArrayList<Row> shopping_list) {
            this.context = context;
            this.shopping_list = shopping_list;


        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public FilterAdapter_MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_list, parent, false);
            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_list, parent, false);

            }
            return new FilterAdapter_MyView(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull FilterAdapter_MyView holder, final int position) {

            holder.lowest.setText(shopping_list.get(position).getxName());

            //  holder.bind(shopping_list.get(position));
            if (position == 0) {
                holder.lowest.setChecked(true);
            }
            holder.lowest.setChecked(position == mCheckedPostion);
            holder.lowest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == mCheckedPostion) {
                        holder.lowest.setChecked(false);
                        //  lowest.username = "";
                        mCheckedPostion = -1;
                        String asd = shopping_list.get(position).getxCode();
                    } else {
                        filte = true;
                        mCheckedPostion = position;
                        String asd = shopping_list.get(position).getxCode();
                        appPreferences.save_filter_sort(asd);

                        //   StringGen.username = holder.poll_option.getText().toString();
                        // Toast.makeText(mycontext, "select : "+position+holder.poll_option.getText(), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return shopping_list.size();
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