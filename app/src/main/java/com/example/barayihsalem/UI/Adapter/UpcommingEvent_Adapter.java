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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class UpcommingEvent_Adapter extends RecyclerView.Adapter<UpcommingEvent_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView event_name, tx_cy, tx_cancel;
    ImageView ivImage, proi;
    LinearLayout lin_id_main;
    Upcoming_eventAdapter upcoming_eventAdapter;
    RecyclerView rw_eventlist;
    ArrayList<Row> upComming_list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public UpcommingEvent_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_UpcomingEvent, ArrayList<Row> upComming_list) {
        this.context = context;
        this.onClickCallback_Benefi = onClickCall_UpcomingEvent;
        this.upComming_list = upComming_list;
        //  this.list = list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingevent_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingevent_list_ar, parent, false);

        }
        return new OffersProduct_ada_ad(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull OffersProduct_ada_ad holder, final int position) {
        //tvName.setText(list.get(position).getxName());


        if (appPreferences.getCulturemode().equals("1")) {

            event_name.setText("Event" + " " + upComming_list.get(position).getSr());


        } else {


            event_name.setText("الأحداث " + " " + upComming_list.get(position).getSr());


        }


        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_eventlist.setLayoutManager(gridLayoutManager);
        upcoming_eventAdapter = new Upcoming_eventAdapter(context, onClickCallback_Benefi, upComming_list);
        rw_eventlist.setAdapter(upcoming_eventAdapter);

        lin_id_main.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return upComming_list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class OffersProduct_ada_ad extends RecyclerView.ViewHolder {
        public OffersProduct_ada_ad(@NonNull View itemView) {
            super(itemView);
            event_name = itemView.findViewById(R.id.event_name);
            lin_id_main = itemView.findViewById(R.id.lin_id_main);
            rw_eventlist = itemView.findViewById(R.id.rw_eventlist);
            event_name.setTypeface(typeRegular);

           /* event_name = itemView.findViewById(R.id.event_name);
            tx_cy = itemView.findViewById(R.id.tx_cy);
            tx_cancel = itemView.findViewById(R.id.tx_cancel);
            proi = itemView.findViewById(R.id.proi);
            tx_cate.setTypeface(typeHeader);
            tx_cy.setTypeface(typeRegular);
            tx_cancel.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);*/

        }
    }
}



