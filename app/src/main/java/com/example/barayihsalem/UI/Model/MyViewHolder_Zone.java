package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolder_Zone extends RecyclerView.ViewHolder {

    public TextView date;
    public LinearLayout lin_date;

    public MyViewHolder_Zone(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
        lin_date = itemView.findViewById(R.id.lin_date);

    }
}
