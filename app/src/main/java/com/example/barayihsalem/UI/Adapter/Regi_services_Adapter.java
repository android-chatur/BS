package com.example.barayihsalem.UI.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Regi_services_Adapter extends RecyclerView.Adapter<Regi_services_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView ti;
    TextView productname;
    LinearLayout platinum;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    BottomSheetDialog dialog;
    ArrayList<Row> addtyiona_service_List = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Regi_services_Adapter(Context shopping_activity, OnItemClickListener.OnClickCallback onClickCall, ArrayList<Row> addtyiona_service_List) {
        this.context = shopping_activity;
        this.onClickCallback_Benefi = onClickCall;
        this.addtyiona_service_List = addtyiona_service_List;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.regi_services_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.regi_services_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


        productname.setText(addtyiona_service_List.get(position).getxName());
        //ti.setColorFilter(context.getResources().getColor(R.color.reject_color));

        ti.setColorFilter(Color.parseColor(addtyiona_service_List.get(position).getColor()));
        platinum.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }

    private void showBottomSheetDialog() {
        View view;
        if (appPreferences.getCulturemode().equals("1")) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

        } else {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_dialog, null);

        }


        dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return addtyiona_service_List.size();
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
            productname = itemView.findViewById(R.id.productname);
            ti = itemView.findViewById(R.id.ti);
            platinum = itemView.findViewById(R.id.platinum);
            productname.setTypeface(typebold);


        }
    }


}
