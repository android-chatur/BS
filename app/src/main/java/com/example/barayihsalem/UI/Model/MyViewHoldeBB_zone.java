package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHoldeBB_zone extends RecyclerView.ViewHolder {

    public TextView price,date,booth_;
    public RecyclerView rw_zonelist;
    //public LinearLayout lin_date;

    public MyViewHoldeBB_zone(@NonNull View itemView) {
        super(itemView);

        rw_zonelist = itemView.findViewById(R.id.rw_zonelist);
      //  date = itemView.findViewById(R.id.date);
        price = itemView.findViewById(R.id.price);
        booth_ = itemView.findViewById(R.id.booth_);
       // lin_date = itemView.findViewById(R.id.lin_date);

    }
}
