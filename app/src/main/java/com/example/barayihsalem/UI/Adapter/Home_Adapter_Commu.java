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
import com.example.barayihsalem.UI.HomeActivity;
import com.example.barayihsalem.UI.Model.Home_MemHolder;
import com.example.barayihsalem.UI.Model.List2;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Home_Adapter_Commu extends RecyclerView.Adapter<Home_MemHolder> {

    Context context;
    AppPreferences appPreferences;

    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<List2> community_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCall;

    public Home_Adapter_Commu(HomeActivity homeActivity, ArrayList<List2> community_list, OnItemClickListener.OnClickCallback onClickCall) {
        this.context = homeActivity;
        this.community_list = community_list;
        this.onClickCall = onClickCall;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Home_MemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberhome_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberhome_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Home_MemHolder vh = new Home_MemHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Home_MemHolder holder, int position) {


        holder.mem_name.setText(community_list.get(position).getCommName());
        holder.txt_memproduct_name.setText(community_list.get(position).getxName());
        holder.mem_name.setTextColor(Color.parseColor(community_list.get(position).getCommColor()));
        holder.txt_memproduct_name.setTextColor(Color.parseColor(community_list.get(position).getCommColor()));


        holder.img_logo.setColorFilter(Color.parseColor(community_list.get(position).getLogoColor()));

        String clor_back = community_list.get(position).getLogoColor();
        if (clor_back.equals("#f93822")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_entertan_commu));
        }


        if (clor_back.equals("#0057b8")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.commu));
        }

        if (clor_back.equals("#833ded")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_service_commu_new));
        }

        if (clor_back.equals("#ff9800")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_food_vege));
        }

        if (clor_back.equals("#97d700")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_enviro_drawable));
        }

        if (clor_back.equals("#88828d")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_industrial_drawable));
        }

        if (clor_back.equals("#41b6e6")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_creative_drawable));
        }

        if (clor_back.equals("#2ad2c9")) {
            holder.all_bacgrond.setBackground(context.getResources().getDrawable(R.drawable.home_tech_drawable));
        }


        holder.all_bacgrond.setOnClickListener(new OnItemClickListener(position, onClickCall, "ITEM"));


    }


    @Override
    public int getItemCount() {
        return community_list.size();
    }
}
