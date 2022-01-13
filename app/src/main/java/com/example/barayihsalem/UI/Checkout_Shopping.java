package com.example.barayihsalem.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.example.barayihsalem.UI.Adapter.Checkout_Adapter;
import com.example.barayihsalem.UI.Adapter.PayMode_Adapter;

public class Checkout_Shopping extends AppCompatActivity implements View.OnClickListener {
    TextView sub_total, sub_total_value, shiping, shipping_value, sub_discount, discount_value, total, total_value, proceed;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    ImageView logoside;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    Checkout_Adapter checkout_adapter;
    PayMode_Adapter payMode_adapter;
    RecyclerView rw_checkout, rw_pay_mode;
    RelativeLayout abcd;
    TextView txtlable, pay_txt;
    Spinner id_city, id_country;
    EditText edit_fullname, editText_mob_num, editText_email, editText_bulg_n0, editText_landmark, editText_apprtment_n0;

    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

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

            setContentView(R.layout.activity_checkout__shopping);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_checkout__shopping);

        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
     /*   rw_checkout.setLayoutManager(gridLayoutManager);
        checkout_adapter = new Checkout_Adapter(Checkout_Shopping.this);
        rw_checkout.setAdapter(checkout_adapter);*/

        //Paymode
        /*gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_pay_mode.setLayoutManager(gridLayoutManager);
        payMode_adapter = new PayMode_Adapter(Checkout_Shopping.this);
        rw_pay_mode.setAdapter(payMode_adapter);*/

    }

    private void findid() {
        sub_total = findViewById(R.id.sub_total);
        sub_total_value = findViewById(R.id.sub_total_value);
        shiping = findViewById(R.id.shiping);
        shipping_value = findViewById(R.id.shipping_value);
        sub_discount = findViewById(R.id.sub_discount);
        discount_value = findViewById(R.id.discount_value);
        total = findViewById(R.id.total);
        proceed = findViewById(R.id.proceed);
        total_value = findViewById(R.id.total_value);
        rw_checkout = findViewById(R.id.rw_checkout);
        logoside = findViewById(R.id.logoside);
        rw_pay_mode = findViewById(R.id.rw_pay_mode);
        edit_fullname = findViewById(R.id.edit_fullname);
        txtlable = findViewById(R.id.txtlable);
        pay_txt = findViewById(R.id.pay_txt);
        editText_mob_num = findViewById(R.id.editText_mob_num);
        editText_email = findViewById(R.id.editText_email);
        txtlable.setTypeface(normal);
        pay_txt.setTypeface(normal);
        sub_total.setTypeface(normal);
        discount_value.setTypeface(normal);
        shiping.setTypeface(normal);
        shipping_value.setTypeface(normal);
        sub_discount.setTypeface(normal);
        sub_total_value.setTypeface(normal);
        edit_fullname.setTypeface(typeRegular);
        editText_mob_num.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        total.setTypeface(typeHeader);
        total_value.setTypeface(typeHeader);
        proceed.setTypeface(typeHeader);
        proceed.setOnClickListener(this);
        logoside.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Checkout_Shopping.this, ShoppingCheckout.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.proceed:
                startActivity(new Intent(Checkout_Shopping.this, Success_Shopping_Page.class));
                finish();
                break;

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }
}