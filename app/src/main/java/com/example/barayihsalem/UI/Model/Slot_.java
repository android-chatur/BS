package com.example.barayihsalem.UI.Model;

import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.R;

public class Slot_ extends RecyclerView.ViewHolder {
    public ImageView img_logo;
    public LinearLayout lin_bs_news;
    public TextView Number, booth_name,zone_name;
    public EditText edit_price,description;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    AppController appController;

    public Slot_(@NonNull View itemView) {
        super(itemView);
        appController = new AppController();

        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        edit_price = itemView.findViewById(R.id.edit_price);
        description = itemView.findViewById(R.id.description);
        Number = itemView.findViewById(R.id.Number);
        booth_name = itemView.findViewById(R.id.booth_name);
        zone_name = itemView.findViewById(R.id.zone_name);



    }
}
