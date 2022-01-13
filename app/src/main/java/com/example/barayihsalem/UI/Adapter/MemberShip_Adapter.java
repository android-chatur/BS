package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.barayihsalem.UI.Model.MemberShip_Viewholder;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class MemberShip_Adapter extends RecyclerView.Adapter<MemberShip_Viewholder> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    ArrayList<Row> order_Event_List = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public MemberShip_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> order_Event_List) {
        this.context = context;
        this.order_Event_List = order_Event_List;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MemberShip_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.membersh_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.membership_ar, parent, false);

        }
        return new MemberShip_Viewholder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MemberShip_Viewholder holder, final int position) {

        holder.email_num_.setTypeface(typeHeader);
        holder.editText_area.setTypeface(typeHeader);
        holder.mobi_num.setTypeface(typeHeader);
        holder.email_num.setTypeface(typeHeader);

        holder.email_.setTypeface(typeRegular);
        holder.email.setTypeface(typeRegular);
        holder.mobile.setTypeface(typeRegular);
        holder.name.setTypeface(typeRegular);

        holder.editText_area.setText(order_Event_List.get(position).getMemName());
        holder.mobi_num.setText(order_Event_List.get(position).getMemFinishDate());
        holder.email_num.setText(order_Event_List.get(position).getMembershipTermPaid());
        holder.email_num_.setText(order_Event_List.get(position).getMembershipTermRemaining());


        if (order_Event_List.get(position).getIsShowBtn().equals(1)) {
            holder.proceed_do.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.proceed_do.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);

        }


        holder.proceed_do.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return order_Event_List.size();
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

