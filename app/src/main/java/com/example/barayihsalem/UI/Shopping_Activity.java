package com.example.barayihsalem.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.Cart_Adapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import static com.example.barayihsalem.UI.Shopping_Category_page.back_cate;
import static com.example.barayihsalem.UI.Shopping_Category_page.isedit;

public class Shopping_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView hi, abou, notifi, reach_help, abou_app, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    AlertDialog alertDialog;
    TextView message, no, yes;
    // LinearLayout lin_filter;
    ImageView logoside;
    Shopping_Adapter_ shopping_adapter;
    Cart_Adapter cart_adapter;
    Button proceed;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rw_shopping, rw_cart;
    RelativeLayout abcd;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

      /*  if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }*/
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_shopping_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_shopping_ar);
        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_shopping.setLayoutManager(gridLayoutManager);
        shopping_adapter = new Shopping_Adapter_(Shopping_Activity.this);
        rw_shopping.setAdapter(shopping_adapter);*/
    }

    private void findid() {


        hi = findViewById(R.id.hi);
        abou = findViewById(R.id.abou);
        proceed = findViewById(R.id.proceed);
        //lin_filter = findViewById(R.id.lin_filter);
        logoside = findViewById(R.id.logoside);
        logoside.setOnClickListener(this);
        hi.setTypeface(typebold);
        abou.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        proceed.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.proceed:
                back_cate = true;
                isedit=false;
                appPreferences.AddressBack("Shopping_Activity");

                startActivity(new Intent(Shopping_Activity.this, Enter_Address.class));
                finish();
                break;

            case R.id.logoside:

                onBackPressed();

                break;


        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Shopping_Activity.this, HomeActivity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void ShowFilter() {
        View view;
        if (appPreferences.getCulturemode().equals("1")) {
            view = LayoutInflater.from(this).inflate(R.layout.fragment_bottom_filter, null);

        } else {
            view = LayoutInflater.from(this).inflate(R.layout.fragment_bottom_filter, null);

        }


        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private class Shopping_Adapter_ extends RecyclerView.Adapter<Shopping_Adapter_.MyViewHolder_procedBene> {
        Context context;
        AppPreferences appPreferences;
        ImageView next;
        TextView productname, price, countnolable, aatocard;
        LinearLayout lin_atocard, lin_qnt;
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
        ArrayList<String> exchange_list = new ArrayList<>();
        //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
        BottomSheetDialog dialog;

        public Shopping_Adapter_(Shopping_Activity exchage_list) {
            this.context = exchage_list;

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            appPreferences = new AppPreferences(context);
            appController = (AppController) context.getApplicationContext();
            // infalte the item Layout
            View v;
            if (appPreferences.getCulturemode().equals("1")) {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vision_list_ar, parent, false);

            }

            //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


            if (position == 0) {
                lin_qnt.setVisibility(View.GONE);
                lin_atocard.setVisibility(View.VISIBLE);
            }

            if (position == 1) {
                lin_qnt.setVisibility(View.VISIBLE);
                lin_atocard.setVisibility(View.GONE);
            }
            //   lin_membackground.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
            aatocard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    showBottomSheetDialog();

                }
            });


//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        }

        private void showBottomSheetDialog() {
            View view;
            if (appPreferences.getCulturemode().equals("1")) {
                view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

            } else {
                view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

            }

            rw_cart = view.findViewById(R.id.rw_cart);
            proceed = view.findViewById(R.id.proceed);

           /* GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            rw_cart.setLayoutManager(gridLayoutManager);
            cart_adapter = new Cart_Adapter(Shopping_Activity.this);
            rw_cart.setAdapter(cart_adapter);*/
            proceed.setTypeface(typeHeader);
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Shopping_Activity.this, ShoppingCheckout.class));
                }
            });
            //   dialog = new BottomSheetDialog(context,R.style.OurAlertDialogStyle);
            //  AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

      /*  _lin_requ_moneey = view.findViewById(R.id._lin_requ_moneey);
        withrawtobank = view.findViewById(R.id.withrawtobank);
        WithDraw = view.findViewById(R.id.WithDraw);
        req_money = view.findViewById(R.id.req_money);
        req_money.setTypeface(typebold);
        WithDraw.setTypeface(typebold);
        cancel_dia = view.findViewById(R.id.cancel_dia);
        cancel_dia.setTypeface(typebold);

        cancel_lin = view.findViewById(R.id.cancel_lin);
        _lin_requ_moneey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Request_money.class));
                finish();
            }
        });
        cancel_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cancel_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == cancel_dia) {
                    cancel_lin.performClick();
                    dialog.dismiss();
                }
            }
        });
        withrawtobank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, WithDrawtoBank.class));
                finish();
            }
        });*/
            dialog = new BottomSheetDialog(context);
            dialog.setContentView(view);
            dialog.show();
        }


        @Override
        public int getItemCount() {
            return 3;
        }

        public class MyViewHolder_procedBene extends RecyclerView.ViewHolder {
            public MyViewHolder_procedBene(@NonNull View itemView) {
                super(itemView);
                typeSemibold = appController.typeSemibold;
                typeRegular = appController.typeNormal;
                typeHeader = appController.typeNavFont;
                normal = appController.typeNormal;
                heding = appController.heding;
                typebold = appController.typebold;
                typeLight = appController.typeLight;
                lin_atocard = itemView.findViewById(R.id.lin_atocard);
                aatocard = itemView.findViewById(R.id.aatocard);
                lin_qnt = itemView.findViewById(R.id.lin_qnt);
                productname = itemView.findViewById(R.id.auther);
                price = itemView.findViewById(R.id.price);
                countnolable = itemView.findViewById(R.id.countnolable);
                countnolable.setTypeface(typeRegular);
                price.setTypeface(heding);
                aatocard.setTypeface(heding);
                countnolable.setTypeface(typeRegular);

                //  lin_membackground = itemView.findViewById(R.id.lin_membackground);
          /*  productname.setTypeface(heding);
            price.setTypeface(heding);
            countnolable.setTypeface(typeRegular);*/



          /*  bene_bank_name = itemView.findViewById(R.id.bene_bank_name);
            benefi_name = itemView.findViewById(R.id.benefi_name);

*/
           /* benefi_name.setTypeface(typeSemibold);

            bene_bank_name.setTypeface(typeLight);*/

            }
        }


    }

}