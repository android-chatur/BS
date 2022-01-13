package com.example.barayihsalem.UI.Model;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
  public   ImageView down_arrow, up_arrow;
  public TextView date, date1;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
        date1 = itemView.findViewById(R.id.date1);
        down_arrow = itemView.findViewById(R.id.down_arrow);
        up_arrow = itemView.findViewById(R.id.up_arrow);

    }
}
