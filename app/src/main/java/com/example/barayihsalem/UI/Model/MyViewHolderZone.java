package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolderZone extends RecyclerView.ViewHolder {

    public TextView date;
    public RecyclerView rw_zonelist_slot;

    public LinearLayout lin_date;

    public MyViewHolderZone(@NonNull View itemView) {
        super(itemView);
        rw_zonelist_slot = itemView.findViewById(R.id.rw_zonelist_slot);
        date = itemView.findViewById(R.id.date);
        lin_date = itemView.findViewById(R.id.lin_date);
        date = itemView.findViewById(R.id.date);


    }
}