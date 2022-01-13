package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class FilterAdapter_MyView extends RecyclerView.ViewHolder {

    public CheckBox lowest;


    public FilterAdapter_MyView(@NonNull View itemView) {
        super(itemView);

        lowest = itemView.findViewById(R.id.lowest);


    }
}