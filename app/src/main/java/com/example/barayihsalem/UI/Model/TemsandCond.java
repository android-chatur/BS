package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class TemsandCond extends RecyclerView.ViewHolder {



    public CheckBox aboutbs, tv_price;
    public TextView txtlable;
    ImageView ivImage;
   public LinearLayout mainid;

    public TemsandCond(@NonNull View itemView) {
        super(itemView);

        aboutbs = itemView.findViewById(R.id.tems_cond);
        txtlable = itemView.findViewById(R.id.txtlable);
        mainid = itemView.findViewById(R.id.mainid);

    }
}
