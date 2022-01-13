package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.barayihsalem.UI.Adapter.Extras_Adapter;
import com.example.barayihsalem.UI.Adapter.Menu_Adapter;
import com.example.barayihsalem.UI.Adapter.Pattern_Adapter;
import com.example.barayihsalem.UI.Adapter.Produtdeta_Adapter_Food;
import com.example.barayihsalem.UI.Adapter.ReasturantAdapter;
import com.example.barayihsalem.UI.Adapter.Size_Adapter;
import com.example.barayihsalem.UI.Adapter.Style_Adapter;
import com.example.barayihsalem.UI.Model.Add_to_Cart_Pojo;
import com.example.barayihsalem.UI.Model.Attribute_Pojo;
import com.example.barayihsalem.UI.Model.Color_Adapter;
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

import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Retail_Dish_Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    private static ViewPager mPager;
    private static int currentPage = 0;
    AppController appController;
    AppPreferences appPreferences;
    boolean conforn = false;
    int counter_Color = 0;
    int counter_Size = 0;
    int counter_pattern = 0;
    int counter_style = 0;

    boolean counter_clr = false;
    ProgressDialog progressDialog;
    Color_Adapter color_adapter;
    ArrayList<com.example.barayihsalem.UI.Model.Color> Color_list = new ArrayList<>();
    ArrayList<com.example.barayihsalem.UI.Model.AttrProdDetails> attribute_list = new ArrayList<>();
    ArrayList<com.example.barayihsalem.UI.Model.Size> Size_list = new ArrayList<>();
    ArrayList<com.example.barayihsalem.UI.Model.Pattern> pattern_list = new ArrayList<>();
    ArrayList<com.example.barayihsalem.UI.Model.Style> style_list = new ArrayList<>();
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    String XCode, XName, Image1, Image2, Image3, Currency, Description, Ingredients1;
    Double NewPrice;
    APIInterface apiInterface;
    boolean charge = false;
    boolean IsSet_Color = false;
    boolean IsSet_Size = false;
    boolean IsSet_pattern = false;
    boolean IsSet_style = false;
    String
            color_Xcode = "",
            Size_Xcode = "",
            pattent_Xcode = "",
            stylet_Xcode = "",
            Size = "",
            Color_ = "",
            Pattern = "",
            Style = "";
    Double productPrice = 0.0;
    String stockqty = "0";
    int no_of_passe = 0;
    SessionManager sessionManager;
    TextView qnt_text, item_name, item_price, item_info, Ingredients, qnt_value, aatoorder, aatoorder_price, item, Indre_value, entras;
    ImageView logoside, imgPlus, imgMinus;
    LinearLayout sortandfilter;
    Spinner id_color, id_size, id_pattern, id_style;
    Size_Adapter size_adapter;
    Style_Adapter style_adapter;
    Pattern_Adapter pattern_adapter;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    LinearLayout color_line, size_line, color_pattern, style;
    Timer swipeTimer = null;
    RelativeLayout abcd;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout lin_button;
    ReasturantAdapter all_food_adapter;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;
    RecyclerView rv_extrash_list;
    Menu_Adapter menu_adapter;
    Extras_Adapter allMenu_adapter;
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
            setContentView(R.layout.activity_retail__dish__detail_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_retail__dish__detail_ar);


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







      /*  allMenu_adapter = new Extras_Adapter(Retail_Dish_Detail_Activity.this, onClickCall_All);
        layoutManager = new LinearLayoutManager(Retail_Dish_Detail_Activity.this, LinearLayoutManager.VERTICAL, false);
        rv_extrash_list.setLayoutManager(layoutManager);
        rv_extrash_list.setAdapter(allMenu_adapter);*/
        if (Retail_Dish_Detail_Activity.this.isNetworkConnected()) {
            new Get_Food_Store_Wise_ItemDetails_By_SrNo().execute();
            showProgress();

            Get_Attribute_List();


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
        id_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {

                int get_Position = 0;
                boolean IsExists = false;
                Object item = parentView.getItemAtPosition(position);
                if (position == 0) {
                    // Call when dropdown is selected and call first time
                    if (counter_Color != 0 && counter_Color != 2) {
                        counter_Color = 0;
                        Color_ = "";
                        id_color.setSelection(0);
                        showProgress();

                        Get_Attribute_List();
                        return;
                    } else {
                        counter_Color = counter_Color + 1;
                        for (int i = 0; i < Color_list.size(); i++) {
                            if (Color_list.get(i).getSelected().equals(true)) {
                                get_Position = i;
                                IsExists = true;
                            }
                        }
                        if (IsExists == false) {
                            IsSet_Color = true;
                        }
                        id_color.setSelection(get_Position);
                    }


                } else if (v != null && position != 0) {
                    // Call when dropdown is selected
                    counter_Color = counter_Color + 1;

                    color_Xcode = Color_list.get(position).getxCode();
                    Color_ = color_Xcode;

                    //Check Fade item click
                    if (Color_list.get(position).getFade()) {
                        Size = "";
                        Pattern = "";
                        Style = "";
                        counter_Color = 0;
                        showProgress();

                        Get_Attribute_List();
                        return;
                    }
                    id_color.setSelection(position);

                    if (counter_Color == 3) {
                        IsSet_Color = true;
                    }

                    if (counter_Color == 4) {
                        counter_Color = 0;
                        //counter_clr=true;
                    }

                    if (IsSet_Color == true) {
                        charge = true;
                        // Color_list.clear();
                        //  color_adapter.notifyDataSetChanged();
                        counter_Color = 0;
                        showProgress();

                        Get_Attribute_List();
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        id_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {


                int get_Position = 0;
                boolean IsExists = false;

                if (position == 0) {
                    // Call when dropdown is selected and call first time

                    counter_Size = counter_Size + 1;
                    for (int i = 0; i < Size_list.size(); i++) {
                        if (Size_list.get(i).getSelected().equals(true)) {
                            get_Position = i;
                            IsExists = true;
                        }
                    }
                    if (IsExists == false) {
                        IsSet_Size = true;
                    }
                    id_size.setSelection(get_Position);
                } else if (v != null && position != 0) {
                    // Call when dropdown is selected
                    counter_Size = counter_Size + 1;

                    Size_Xcode = Size_list.get(position).getxCode();
                    Size = Size_Xcode;

                    //Check Fade item click
                    if (Size_list.get(position).getFade()) {
                        Color_ = "";
                        Pattern = "";
                        Style = "";

                        counter_Size = 0;
                        showProgress();

                        Get_Attribute_List();
                        return;
                    }
                    id_size.setSelection(position);

                    if (counter_Size == 3) {
                        IsSet_Size = true;
                    }
                    if (counter_Size == 4) {
                        counter_Size = 0;
                    }

                    if (IsSet_Size == true) {
                        charge = true;
                        //Size_list.clear();
                        //   size_adapter.notifyDataSetChanged();
                        counter_Size = 0;
                        showProgress();

                        Get_Attribute_List();

                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        id_pattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                int get_Position = 0;
                boolean IsExists = false;

                if (position == 0) {
                    // Call when dropdown is selected and call first time

                    counter_pattern = counter_pattern + 1;
                    for (int i = 0; i < pattern_list.size(); i++) {
                        if (pattern_list.get(i).getSelected().equals(true)) {
                            get_Position = i;
                            IsExists = true;
                        }
                    }
                    if (IsExists == false) {
                        IsSet_pattern = true;
                    }
                    id_pattern.setSelection(get_Position);
                } else if (v != null && position != 0) {
                    // Call when dropdown is selected
                    counter_pattern = counter_pattern + 1;

                    pattent_Xcode = pattern_list.get(position).getxCode();
                    Pattern = pattent_Xcode;

                    //Check Fade item click
                    if (pattern_list.get(position).getFade()) {
                        Color_ = "";
                        Size = "";
                        Style = "";

                        counter_pattern = 0;
                        showProgress();

                        Get_Attribute_List();
                        return;
                    }
                    id_pattern.setSelection(position);

                    if (counter_pattern == 3) {
                        IsSet_pattern = true;
                    }
                    if (counter_pattern == 4) {
                        counter_pattern = 0;
                    }

                    if (IsSet_pattern == true) {
                        charge = true;

                        counter_pattern = 0;
                        showProgress();

                        Get_Attribute_List();
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        id_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                int get_Position = 0;
                boolean IsExists = false;

                if (position == 0) {
                    // Call when dropdown is selected and call first time

                    counter_style = counter_style + 1;
                    for (int i = 0; i < style_list.size(); i++) {
                        if (style_list.get(i).getSelected().equals(true)) {
                            get_Position = i;
                            IsExists = true;
                        }
                    }
                    if (IsExists == false) {
                        IsSet_style = true;
                    }
                    id_style.setSelection(get_Position);
                } else if (v != null && position != 0) {
                    // Call when dropdown is selected
                    counter_style = counter_style + 1;

                    stylet_Xcode = style_list.get(position).getxCode();
                    Style = stylet_Xcode;

                    //Check Fade item click
                    if (style_list.get(position).getFade()) {
                        Color_ = "";
                        Size = "";
                        Pattern = "";


                        counter_style = 0;

                        showProgress();
                        Get_Attribute_List();
                        return;

                    }
                    id_style.setSelection(position);

                    if (counter_style == 3) {
                        IsSet_style = true;
                    }
                    if (counter_style == 4) {
                        counter_style = 0;
                    }

                    if (IsSet_style == true) {
                        charge = true;
                        counter_style = 0;
                        showProgress();

                        Get_Attribute_List();
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


    }

    private void Get_Attribute_List() {
        Attribute_Pojo attribute_pojo = new Attribute_Pojo();

        attribute_pojo.setChange(charge);
        //attribute_pojo.setChange(true);
        attribute_pojo.setItemXCode(appPreferences.getItem_xcode());
        attribute_pojo.setItemType(appPreferences.geShoppingSrno());

        attribute_pojo.setColor(Color_);
        attribute_pojo.setSize(Size);
        attribute_pojo.setPattern(Pattern);
        attribute_pojo.setStyle(Style);

        attribute_pojo.setValue1("");
        attribute_pojo.setValue2("");
        attribute_pojo.setUserId(appPreferences.getuserid());
        attribute_pojo.setCultureId(appPreferences.getCulturemode());
        attribute_pojo.setCorpcentreBy(appPreferences.get_company_id());


        Call<Attribute_Pojo> call2 = apiInterface.Get_Attribute_List(attribute_pojo);
        call2.enqueue(new Callback<Attribute_Pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Attribute_Pojo> call, Response<Attribute_Pojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                //  hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        //Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//color list

                        IsSet_Color = false;
                        IsSet_Size = false;
                        IsSet_pattern = false;
                        IsSet_style = false;
                       /* Color_list.clear();

                        Size_list.clear();
                        pattern_list.clear();
                        style_list.clear();*/
                        id_color.setAdapter(null);

                        id_size.setAdapter(null);
                        id_style.setAdapter(null);
                        id_pattern.setAdapter(null);
                        Color_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Color worldpop = new com.example.barayihsalem.UI.Model.Color();
                        Color_list = new ArrayList<>();
                        worldpop = new com.example.barayihsalem.UI.Model.Color();
                        worldpop.setOrdering(0);
                        worldpop.setFade(false);
                        worldpop.setxCode("");
                        worldpop.setSelected(false);
                          if (appPreferences.getCulturemode().equals("1")) {
                              worldpop.setxName("Select Color");
                          }else {
                              worldpop.setxName("لون");

                          }
                        Color_list.add(worldpop);


                        // size list
                        Size_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Size worldpop_si = new com.example.barayihsalem.UI.Model.Size();
                        Size_list = new ArrayList<>();
                        worldpop_si = new com.example.barayihsalem.UI.Model.Size();
                        worldpop_si.setxCode("");
                        worldpop_si.setOrdering(0);
                        worldpop_si.setFade(false);
                        worldpop_si.setxCode("");
                        worldpop_si.setSelected(false);
                          if (appPreferences.getCulturemode().equals("1")) {
                              worldpop_si.setxName("Select Size");
                          }else {
                              worldpop_si.setxName("مقاس");

                          }
                        Size_list.add(worldpop_si);


                        // pattern list
                        pattern_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Pattern worldpop_pattern = new com.example.barayihsalem.UI.Model.Pattern();
                        pattern_list = new ArrayList<>();
                        worldpop_pattern = new com.example.barayihsalem.UI.Model.Pattern();
                        worldpop_pattern.setxCode("");
                        worldpop_pattern.setOrdering(0);
                        worldpop_pattern.setFade(false);
                        worldpop_pattern.setxCode("");
                        worldpop_pattern.setSelected(false);
                         if (appPreferences.getCulturemode().equals("1")) {
                             worldpop_pattern.setxName("" + " Pattern");
                         }else {
                             worldpop_pattern.setxName("نموذج");

                         }
                        pattern_list.add(worldpop_pattern);


                        // pattern style
                        style_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Style worldpop_style = new com.example.barayihsalem.UI.Model.Style();
                        style_list = new ArrayList<>();
                        worldpop_style = new com.example.barayihsalem.UI.Model.Style();
                        worldpop_style.setxCode("");
                        worldpop_style.setOrdering(0);
                        worldpop_style.setFade(false);
                        worldpop_style.setxCode("");
                        worldpop_style.setSelected(false);
                        if (appPreferences.getCulturemode().equals("1")) {
                            worldpop_style.setxName("Select Style");
                        } else {
                            worldpop_style.setxName("نموذج");

                        }
                        style_list.add(worldpop_style);

                        Color_list.addAll(response.body().getColorList());


                        Size_list.addAll(response.body().getSizeList());
                        pattern_list.addAll(response.body().getPatternList());
                        style_list.addAll(response.body().getStyleList());
                        //   stockqty= String.valueOf(response.body().getAttrProdDetails().getStock());
                        hideProgress();

                        if (!(Color_list.size() == 0)) {
                            color_line.setVisibility(View.VISIBLE);
                        } else {
                            color_line.setVisibility(View.GONE);

                        }
                        lin_button.setVisibility(View.GONE);

                        if (!(Size_list.size() == 0)) {
                            size_line.setVisibility(View.VISIBLE);

                        } else {
                            size_line.setVisibility(View.GONE);


                        }
                        if (!(pattern_list.size() == 0)) {
                            size_line.setVisibility(View.VISIBLE);

                        } else {
                            size_line.setVisibility(View.GONE);

                        }

                        if (!(style_list.size() == 0)) {
                            style.setVisibility(View.VISIBLE);
                        } else {
                            style.setVisibility(View.GONE);

                        }
                        Resources res1 = getResources();
                        color_adapter = new Color_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, Color_list, res1);
                        id_color.setAdapter(color_adapter);
                        // color_adapter.notifyDataSetChanged();


                        Resources res = getResources();
                        size_adapter = new Size_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, Size_list, res);
                        id_size.setAdapter(size_adapter);

                        Resources res_pattern = getResources();
                        pattern_adapter = new Pattern_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, pattern_list, res_pattern);
                        id_pattern.setAdapter(pattern_adapter);

                        Resources res_style = getResources();
                        style_adapter = new Style_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, style_list, res_style);
                        id_style.setAdapter(style_adapter);


                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {
                        lin_button.setVisibility(View.VISIBLE);

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        ArrayList<String> arrayList = new ArrayList<>();

                        stockqty = String.valueOf(response.body().getAttrProdDetails().getStock());
                        Image1 = (response.body().getAttrProdDetails().getImage1());
                        Image2 = (response.body().getAttrProdDetails().getImage2());
                        Image3 = (response.body().getAttrProdDetails().getImage3());
                        NewPrice = (response.body().getAttrProdDetails().getNewPrice());
                        productPrice = (response.body().getAttrProdDetails().getNewPrice());

                        if (!Image1.equals("Default.jpg")) {
                            arrayList.add(Image1);

                        }
                        if (!Image2.equals("Default.jpg")) {
                            arrayList.add(Image2);

                        }
                        if (!Image3.equals("Default.jpg")) {
                            arrayList.add(Image3);
                        }
                        String Mem_Price = String.format("%.3f", NewPrice);


                        item_price.setText(Mem_Price + " " + "KD");
                        aatoorder_price.setText(Mem_Price + " " + "KD");

                        init(arrayList);
                        //    stockqty=    attribute_list.addAll(response.body().getAttrProdDetails().getStock());
                        hideProgress();


                        IsSet_Color = false;
                        IsSet_Size = false;
                        IsSet_pattern = false;
                        IsSet_style = false;
                        Color_list.clear();

                        Size_list.clear();
                        pattern_list.clear();
                        style_list.clear();
                      /*  id_color.setAdapter(null);

                        id_size.setAdapter(null);
                        id_style.setAdapter(null);
                        id_pattern.setAdapter(null);*/
                        Color_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Color worldpop = new com.example.barayihsalem.UI.Model.Color();
                        Color_list = new ArrayList<>();
                        worldpop = new com.example.barayihsalem.UI.Model.Color();
                        worldpop.setOrdering(0);
                        worldpop.setFade(false);
                        worldpop.setxCode("");
                        worldpop.setSelected(false);
                        //  if (appPreferences.getCulturemode().equals("1")) {
                        if (appPreferences.getCulturemode().equals("1")) {
                            worldpop.setxName("Select Color");
                        }else {
                            worldpop.setxName("لون");

                        }
                        Color_list.add(worldpop);


                        // size list
                        Size_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Size worldpop_si = new com.example.barayihsalem.UI.Model.Size();
                        Size_list = new ArrayList<>();
                        worldpop_si = new com.example.barayihsalem.UI.Model.Size();
                        worldpop_si.setxCode("");
                        worldpop_si.setOrdering(0);
                        worldpop_si.setFade(false);
                        worldpop_si.setxCode("");
                        worldpop_si.setSelected(false);
                        //  if (appPreferences.getCulturemode().equals("1")) {
                        if (appPreferences.getCulturemode().equals("1")) {
                            worldpop_si.setxName("Select Size");
                        }else {
                            worldpop_si.setxName("مقاس");

                        }
                        Size_list.add(worldpop_si);


                        // pattern list
                        pattern_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Pattern worldpop_pattern = new com.example.barayihsalem.UI.Model.Pattern();
                        pattern_list = new ArrayList<>();
                        worldpop_pattern = new com.example.barayihsalem.UI.Model.Pattern();
                        worldpop_pattern.setxCode("");
                        worldpop_pattern.setOrdering(0);
                        worldpop_pattern.setFade(false);
                        worldpop_pattern.setxCode("");
                        worldpop_pattern.setSelected(false);
                        //  if (appPreferences.getCulturemode().equals("1")) {
                        if (appPreferences.getCulturemode().equals("1")) {
                            worldpop_pattern.setxName("" + " Pattern");
                        }else {
                            worldpop_pattern.setxName("نموذج");

                        }
                        pattern_list.add(worldpop_pattern);


                        // pattern style
                        style_list = new ArrayList<>();
                        com.example.barayihsalem.UI.Model.Style worldpop_style = new com.example.barayihsalem.UI.Model.Style();
                        style_list = new ArrayList<>();
                        worldpop_style = new com.example.barayihsalem.UI.Model.Style();
                        worldpop_style.setxCode("");
                        worldpop_style.setOrdering(0);
                        worldpop_style.setFade(false);
                        worldpop_style.setxCode("");
                        worldpop_style.setSelected(false);
                        //  if (appPreferences.getCulturemode().equals("1")) {
                        if (appPreferences.getCulturemode().equals("1")) {
                            worldpop_style.setxName("Select Style");
                        } else {
                            worldpop_style.setxName("نموذج");

                        }
                        style_list.add(worldpop_style);

                        Color_list.addAll(response.body().getColorList());


                        Size_list.addAll(response.body().getSizeList());
                        pattern_list.addAll(response.body().getPatternList());
                        style_list.addAll(response.body().getStyleList());
                        //   stockqty= String.valueOf(response.body().getAttrProdDetails().getStock());


                        if (!(Color_list.size() == 0)) {
                            color_line.setVisibility(View.VISIBLE);
                        } else {
                            color_line.setVisibility(View.GONE);

                        }

                        if (!(Size_list.size() == 0)) {
                            size_line.setVisibility(View.VISIBLE);

                        } else {
                            size_line.setVisibility(View.GONE);


                        }
                        if (!(pattern_list.size() == 0)) {
                            size_line.setVisibility(View.VISIBLE);

                        } else {
                            size_line.setVisibility(View.GONE);

                        }

                        if (!(style_list.size() == 0)) {
                            style.setVisibility(View.VISIBLE);
                        } else {
                            style.setVisibility(View.GONE);

                        }


                        Resources res1 = getResources();
                        color_adapter = new Color_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, Color_list, res1);
                        id_color.setAdapter(color_adapter);
                        // color_adapter.notifyDataSetChanged();


                        Resources res = getResources();
                        size_adapter = new Size_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, Size_list, res);
                        id_size.setAdapter(size_adapter);

                        Resources res_pattern = getResources();
                        pattern_adapter = new Pattern_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, pattern_list, res_pattern);
                        id_pattern.setAdapter(pattern_adapter);

                        Resources res_style = getResources();
                        style_adapter = new Style_Adapter(Retail_Dish_Detail_Activity.this, R.layout.countryspinner_country_new, style_list, res_style);
                        id_style.setAdapter(style_adapter);


                        // size_adapter.notifyDataSetChanged();


                      /*  pattern_adapter = new Pattern_Adapter(Retail_Dish_Detail_Activity.this, pattern_list);
                        id_pattern.setAdapter(pattern_adapter);
                        //   pattern_adapter.notifyDataSetChanged();
                        id_pattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                                int get_Position = 0;
                                boolean IsExists = false;

                                if (position == 0) {
                                    // Call when dropdown is selected and call first time

                                    counter_Size = counter_Size + 1;
                                    for (int i = 0; i < pattern_list.size(); i++) {
                                        if (pattern_list.get(i).getSelected().equals(true)) {
                                            get_Position = i;
                                            IsExists = true;
                                        }
                                    }
                                    if (IsExists == false) {
                                        IsSet_pattern = true;
                                    }
                                    id_pattern.setSelection(get_Position);
                                }
                                else if (v != null && position != 0) {
                                    // Call when dropdown is selected
                                    counter_Size = counter_Size + 1;

                                    pattent_Xcode = pattern_list.get(position).getxCode();
                                    Pattern = pattent_Xcode;

                                    //Check Fade item click
                                    if (pattern_list.get(position).getFade()) {
                                        Color_ = "";
                                        Size = "";
                                        Style = "";
                                    }
                                    id_pattern.setSelection(position);

                                    if(counter_Size == 3)
                                    {
                                        IsSet_pattern = true;
                                    }

                                    if (IsSet_pattern == true) {
                                        charge = true;
                                        Get_Attribute_List();
                                        counter_Size = 0;
                                    }




                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                            }
                        });

                        style_adapter = new Style_Adapter(Retail_Dish_Detail_Activity.this, style_list);
                        id_style.setAdapter(style_adapter);
                        id_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                                int get_Position = 0;
                                boolean IsExists = false;

                                if (position == 0) {
                                    // Call when dropdown is selected and call first time

                                    counter_Size = counter_Size + 1;
                                    for (int i = 0; i < style_list.size(); i++) {
                                        if (style_list.get(i).getSelected().equals(true)) {
                                            get_Position = i;
                                            IsExists = true;
                                        }
                                    }
                                    if (IsExists == false) {
                                        IsSet_style = true;
                                    }
                                    id_style.setSelection(get_Position);
                                }
                                else if (v != null && position != 0) {
                                    // Call when dropdown is selected
                                    counter_Size = counter_Size + 1;

                                    stylet_Xcode = style_list.get(position).getxCode();
                                    Style = stylet_Xcode;

                                    //Check Fade item click
                                    if (style_list.get(position).getFade()) {
                                        Color_ = "";
                                        Size = "";
                                        Pattern = "";

                                    }
                                    id_style.setSelection(position);

                                    if(counter_Size == 3)
                                    {
                                        IsSet_style = true;
                                    }

                                    if (IsSet_style == true) {
                                        charge = true;
                                        Get_Attribute_List();
                                        counter_Size = 0;
                                    }




                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                            }
                        });

*/
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
            public void onFailure(Call<Attribute_Pojo> call, Throwable t) {
                call.cancel();
                // hideProgress();
            }
        });


    }


    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void findid() {
        color_line = findViewById(R.id.color_line);
        size_line = findViewById(R.id.size_line);
        color_pattern = findViewById(R.id.color_pattern);
        style = findViewById(R.id.style);

        id_color = findViewById(R.id.id_color);
        id_size = findViewById(R.id.id_size);
        id_pattern = findViewById(R.id.id_pattern);
        id_style = findViewById(R.id.id_style);
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
        logoside = findViewById(R.id.logoside);
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
        lin_button.setVisibility(View.GONE);
        logoside.setOnClickListener(this);
        imgPlus.setOnClickListener(this);
        imgMinus.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Retail_Dish_Detail_Activity.this, Retails_Details.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_button:

                if (Retail_Dish_Detail_Activity.this.isNetworkConnected()) {
                    if (!stockqty.equals("0")) {
                        showProgress();
                        Add_to_Cart_All();
                    } else {
                        Toast.makeText(appController, "Stack quntity Not Available...!", Toast.LENGTH_SHORT).show();
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

               /* startActivity(new Intent(Retail_Dish_Detail_Activity.this, Cart_Activity.class));
                finish();*/
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
        progressDialog = new ProgressDialog(Retail_Dish_Detail_Activity.this);
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
                        startActivity(new Intent(appController, Cart_Activity.class));
                        finish();

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {

                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(appController, Cart_Activity.class));
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


    private void init(ArrayList<String> arrayList) {
        ArrayList<String> Add_list_deatal = new ArrayList<>();
        mPager = findViewById(R.id.pager);


        for (int i = 0; i < arrayList.size(); i++)
            Add_list_deatal.add(arrayList.get(i));


        mPager.setAdapter(new Produtdeta_Adapter_Food(Retail_Dish_Detail_Activity.this, arrayList));
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
                Log.d("aew", query);
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

}