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
import com.example.barayihsalem.UI.Model.List;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Older_event_Gallary_Adapter extends RecyclerView.Adapter<Older_event_Gallary_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView aboutbs, price, countnolable;
    LinearLayout lin_qnt;
    RecyclerView rw_sub_gallary;
    Older_Subevent_Gallary_Adapter older_event_gallary_adapter;
    //  Button btnLogin;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    BottomSheetDialog dialog;
    /*
        public Cart_Adapter(Cart_Activity exchage_list) {


        }*/
    ArrayList<List> Gallary_list = new ArrayList<>();

    public Older_event_Gallary_Adapter(Context shopping_activity, ArrayList<List> Gallary_list) {
        this.context = shopping_activity;
        this.Gallary_list = Gallary_list;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.older_event_gallary_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.older_event_gallary_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


        aboutbs.setText(Gallary_list.get(position).getEventList().getEventName());




        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_sub_gallary.setLayoutManager(gridLayoutManager);
        older_event_gallary_adapter = new Older_Subevent_Gallary_Adapter(context,Gallary_list.get(position).getEventGalleryList());
        rw_sub_gallary.setAdapter(older_event_gallary_adapter);

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
        return Gallary_list.size();
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

            aboutbs = itemView.findViewById(R.id.aboutbs);
            rw_sub_gallary = itemView.findViewById(R.id.rw_sub_gallary);
            aboutbs.setTypeface(typebold);
        }
    }


}
