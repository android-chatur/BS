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

public class Home_Commm extends RecyclerView.ViewHolder {
    public ImageView img_logo;
    public LinearLayout lin_bs_news;
    public TextView tx_bs_news, see_all_news;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    AppController appController;

    public Home_Commm(@NonNull View itemView) {
        super(itemView);
        appController = new AppController();

        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        lin_bs_news = itemView.findViewById(R.id.lin_bs_news);
        img_logo = itemView.findViewById(R.id.img_logo);
        tx_bs_news = itemView.findViewById(R.id.tx_bs_news);
        see_all_news = itemView.findViewById(R.id.see_all_news);

        tx_bs_news.setTypeface(typebold);
        see_all_news.setTypeface(typebold);
    }
}
