package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Address_Viewhoder;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import static com.example.barayihsalem.UI.Shopping_Category_page.colorred;

public class Address_Adapter extends RecyclerView.Adapter<Address_Viewhoder> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    ArrayList<Row> bottom_list = new ArrayList<>();
    int mSelectedPosition = 0;
    private int mCheckedPostion = -1;
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Address_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> bottom_list) {
        this.context = context;
        this.bottom_list = bottom_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Address_Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_details_ar, parent, false);

        }
        return new Address_Viewhoder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull Address_Viewhoder holder, final int position) {
        //tvName.setText(list.get(position).getxName());


        holder.tx_cate.setTypeface(typeHeader);
        holder.tx_cy.setTypeface(typeRegular);
        holder.tx_cancel.setTypeface(typeRegular);
        holder.tx_cate.setChecked(position == mCheckedPostion);
        holder.tx_cate.setText(bottom_list.get(position).getxName());
        //  tx_cy.setText(bottom_list.get(position).getxName());
        String srn0 = String.valueOf(bottom_list.get(position).getSr());
        if (appPreferences.getCulturemode().equals("1")) {
            if (position == 1) {
                holder.tx_cy.setText("Address" + " " + srn0);
            }
        } else {
            if (position == 1) {
                holder.tx_cy.setText("العنوان " + "" + srn0);
            }
        }
        int selectedPosition = 0;

        if (colorred == true) {
            holder.proi.setVisibility(View.GONE);
            holder.tx_cancel.setTextColor(context.getColor(R.color.reject_color));

        } else {
            holder.proi.setVisibility(View.VISIBLE);

            holder.tx_cancel.setTextColor(context.getColor(R.color.supplier_color));

        }
       if(holder.tx_cate.isChecked()){
           holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));
        }else {
           holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));
       }

        holder.tx_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mCheckedPostion) {
                    holder.tx_cate.setChecked(false);
                    //  lowest.username = "";
                    mCheckedPostion = -1;
                    String asd = bottom_list.get(position).getxCode();
                 //   holder.tx_cate.setTextColor(context.getResources().getColor(R.color.black));


                } else {
                   // filte = true;
                    mCheckedPostion = position;
                    String asd = bottom_list.get(position).getxCode();
                  //  holder.tx_cate.setTextColor(context.getResources().getColor(R.color.button_color_signup));

                    //   StringGen.username = holder.poll_option.getText().toString();
                    // Toast.makeText(mycontext, "select : "+position+holder.poll_option.getText(), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });




        /*holder.tx_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selectedPosition == position)
                    holder.tx_cate.setTextColor(Color.GREEN);
                else
                    holder.tx_cate.setTextColor(Color.BLACK);

                // bottom_list.get(position).setSelected(!bottom_list.get(position).isSelected());

               *//* if (bottom_list.get(position).isSelected()){
                    holder.tx_cate.setTextColor( bottom_list.get(position).isSelected() ? Color.GREEN);
                }else {
                    holder.tx_cate.setTextColor( Color.BLACK);

                }*//*
                //  holder.tx_cate.setTextColor(bottom_list.get(position).isSelected() ? Color.GREEN : Color.WHITE);
            }
        });*/


        holder.tx_cancel.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return bottom_list.size();
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


