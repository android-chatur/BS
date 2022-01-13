package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.BO_BootYourSales;
import com.example.barayihsalem.UI.Shopping_Activity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Alredy_post_Adapter extends RecyclerView.Adapter<Alredy_post_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView productname, price,countnolable;
    LinearLayout lin_qnt;
    Button btnLogin;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
  //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    BottomSheetDialog dialog;
/*
    public Cart_Adapter(Cart_Activity exchage_list) {


    }*/

    public Alredy_post_Adapter(BO_BootYourSales shopping_activity) {
        this.context = shopping_activity;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alredypost_list, parent, false);

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


      /*  if (position==0){
            lin_qnt.setVisibility(View.GONE);
            lin_atocard.setVisibility(View.VISIBLE);
        }

        if (position==1){
            lin_qnt.setVisibility(View.VISIBLE);
            lin_atocard.setVisibility(View.GONE);
        }*/
     //   lin_membackground.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
       /* aatocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();

            }
        });*/
      /*  date.setText(exchange_list.get(position));
        if (position == 0) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.entertaiment));

        }
        if (position == 1) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.bussiness_reatils));

        }
        if (position == 3) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.foodaandbev));

        }
*/

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }

    private void showBottomSheetDialog() {
        View view;
        if (appPreferences.getCulturemode().equals("1")) {
            view =  LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

        } else {
            view =  LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

        }
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
        return 1;
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
          /*  lin_atocard = itemView.findViewById(R.id.lin_atocard);
            aatocard = itemView.findViewById(R.id.aatocard);*/
            btnLogin = itemView.findViewById(R.id.btnLogin);
            lin_qnt = itemView.findViewById(R.id.lin_qnt);
            productname = itemView.findViewById(R.id.auther);
            price = itemView.findViewById(R.id.price);
            countnolable = itemView.findViewById(R.id.countnolable);
            countnolable.setTypeface(typeRegular);
            price.setTypeface(heding);
          //  aatocard.setTypeface(heding);
            countnolable.setTypeface(typeRegular);
            btnLogin.setTypeface(typeRegular);

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
