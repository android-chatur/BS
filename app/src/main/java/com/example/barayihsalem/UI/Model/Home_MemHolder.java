package com.example.barayihsalem.UI.Model;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.R;

public class Home_MemHolder extends RecyclerView.ViewHolder {
    public ImageView img_logo;
    public LinearLayout all_bacgrond;
    public TextView mem_name, txt_memproduct_name;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    AppController appController;

    public Home_MemHolder(@NonNull View itemView) {
        super(itemView);
        appController = new AppController();

        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        all_bacgrond = itemView.findViewById(R.id.all_bacgrond);
        img_logo = itemView.findViewById(R.id.img_logo);
        mem_name = itemView.findViewById(R.id.mem_name);
        txt_memproduct_name = itemView.findViewById(R.id.txt_memproduct_name);
        mem_name.setTypeface(typebold);
        txt_memproduct_name.setTypeface(typebold);
    }
}
