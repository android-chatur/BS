package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Cart_Activity;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView imgMinus, imgPlus;
    TextView productname, price, countnolable;
    LinearLayout lin_qnt;
    String stockqty = "12";
    int no_of_passe = 1;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    BottomSheetDialog dialog;
    /*
        public Cart_Adapter(Cart_Activity exchage_list) {


        }*/
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Cart_Adapter(Cart_Activity shopping_activity, OnItemClickListener.OnClickCallback onClickCall) {
        this.context = shopping_activity;
        this.onClickCallback_Benefi = onClickCall;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
        return 2;
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

            lin_qnt = itemView.findViewById(R.id.lin_qnt);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            productname = itemView.findViewById(R.id.auther);
            price = itemView.findViewById(R.id.price);
            countnolable = itemView.findViewById(R.id.countnolable);
            countnolable.setTypeface(typeRegular);
            price.setTypeface(typeHeader);
            countnolable.setTypeface(typeRegular);
            imgPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    {

                        //stockqty = cart_arrList.get(position).getAvailableQuantity();
                        no_of_passe = Integer.parseInt(countnolable.getText().toString());
                        if (no_of_passe != Integer.parseInt(stockqty)) {

                            //   if (no_of_passe <= Integer.parseInt(stockqty)) {

                            if (stockqty.equals("0")) {
                                if (appPreferences.getCulturemode().equals("1")) {
                                    // TastyToast.makeText(CartPage.this, "Stock Not Available", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                                } else {
                                    //  TastyToast.makeText(CartPage.this, "المخزون غير متاح", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                                }
                            } else {
                                no_of_passe++;
                              /*  cart_id_update = card_details.getCartID();
                                new Sp_Update_Cart_Apps().execute();*/
                                countnolable.setText(String.valueOf(no_of_passe));
                            }


                        }


                    }
                }
            });

            imgMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    no_of_passe = Integer.parseInt(countnolable.getText().toString());
                    if (no_of_passe != 1) {
                        no_of_passe--;
                     /*   cart_id_update = card_details.getCartID();
                        new Sp_Update_Cart_Apps().execute();*/
                        countnolable.setText(String.valueOf(no_of_passe));

                    }


                }
            });


        }
    }


}
