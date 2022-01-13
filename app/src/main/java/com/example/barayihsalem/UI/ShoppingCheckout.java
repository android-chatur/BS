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
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;

public class ShoppingCheckout extends AppCompatActivity {
    TextView aboutbs, items, filter, settings, offer, notifi, reach_help, abou_app, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView txtlable, no, yes;
    ImageView logoside;
    Cart_Adapter cart_adapter;
    Button btnLogin;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    Spinner id_city, id_country;
    EditText editText_area, edit_block, editText_street, editText_bulg_n0, editText_landmark, editText_apprtment_n0;

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

            setContentView(R.layout.activity_shopping_checkout);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_shopping_checkout);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        findid();

    }

    private void findid() {
        id_city = findViewById(R.id.id_city);
        logoside = findViewById(R.id.logoside);
        id_country = findViewById(R.id.id_country);
        txtlable = findViewById(R.id.txtlable);
        editText_area = findViewById(R.id.editText_area);
        editText_street = findViewById(R.id.editText_street);
        edit_block = findViewById(R.id.edit_block);
        editText_street = findViewById(R.id.editText_street);
        editText_bulg_n0 = findViewById(R.id.editText_bulg_n0);
        editText_landmark = findViewById(R.id.editText_landmark);
        editText_apprtment_n0 = findViewById(R.id.editText_apprtment_n0);
        btnLogin = findViewById(R.id.btnLogin);
        editText_area.setTypeface(typeRegular);
        edit_block.setTypeface(typeRegular);
        editText_street.setTypeface(typeRegular);
        editText_bulg_n0.setTypeface(typeRegular);
        editText_landmark.setTypeface(typeRegular);
        editText_apprtment_n0.setTypeface(typeRegular);
        txtlable.setTypeface(normal);
        btnLogin.setTypeface(typeRegular);
        logoside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        }); btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCheckout.this, Checkout_Shopping.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShoppingCheckout.this, Shopping_Activity.class));
        finish();
    }
}