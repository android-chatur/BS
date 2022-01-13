package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolder_SelectBooth extends RecyclerView.ViewHolder {

    public EditText editText_booth_a;
    public CheckBox booth_a;
    public TextView view_image;
    public LinearLayout lin_boot_a;

    public MyViewHolder_SelectBooth(@NonNull View itemView) {
        super(itemView);

        view_image = itemView.findViewById(R.id.view_image);
        booth_a = itemView.findViewById(R.id.booth_a);
        lin_boot_a = itemView.findViewById(R.id.lin_boot_a);


    }
}
