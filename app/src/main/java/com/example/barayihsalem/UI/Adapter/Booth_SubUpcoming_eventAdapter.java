package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.UpcominiEvent_Download;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Booth_SubUpcoming_eventAdapter extends RecyclerView.Adapter<Booth_SubUpcoming_eventAdapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, date_;
    Button proceed;

    RecyclerView rw_zonelist;
    Zone_A_Adapter_Details zoneAAdapterDetails;
    LinearLayout lin_date;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    ArrayList<Row> booth_In_UpComing_Event_List = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Booth_SubUpcoming_eventAdapter(Context context, OnItemClickListener.OnClickCallback onClickCallback_Benefi, ArrayList<Row> booth_In_UpComing_Event_List) {
        this.context = context;
        this.onClickCallback_Benefi = onClickCallback_Benefi;
        this.booth_In_UpComing_Event_List = booth_In_UpComing_Event_List;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boothupcomin_sub_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boothupcomin_sub_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


        date.setText(booth_In_UpComing_Event_List.get(position).getEventName() + ",\n" +booth_In_UpComing_Event_List.get(position).getCommunityName() + " " + booth_In_UpComing_Event_List.get(position).getDate());
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appPreferences.SaveEvent_SRNo(booth_In_UpComing_Event_List.get(position).getEventSrNo());
                context.startActivity(new Intent(context, UpcominiEvent_Download.class));
                ((Activity)context).finish();
            }
        });
        lin_date.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
        return booth_In_UpComing_Event_List.size();
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
            date = itemView.findViewById(R.id.date);
            lin_date = itemView.findViewById(R.id.lin_date);
            date.setTypeface(typeSemibold);
            proceed = itemView.findViewById(R.id.proceed);


        }
    }


}

