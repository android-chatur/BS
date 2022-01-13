package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolder_Eletric extends RecyclerView.ViewHolder {

    public EditText editText_booth_a;
    public CheckBox Fridge;

    public MyViewHolder_Eletric(@NonNull View itemView) {
        super(itemView);

        Fridge = itemView.findViewById(R.id.Fridge);


    }
}
