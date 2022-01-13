package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolder_Booth extends RecyclerView.ViewHolder {
  public   ImageView down_arrow, up_arrow;
  public TextView txt_booth_A;
  public EditText editText_booth_a;

    public MyViewHolder_Booth(@NonNull View itemView) {
        super(itemView);

        txt_booth_A = itemView.findViewById(R.id.txt_booth_A);
        editText_booth_a = itemView.findViewById(R.id.editText_booth_a);


    }
}
