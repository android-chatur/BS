package com.example.barayihsalem.UI.Model;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.R;

public class MyViewHolder_NewUser extends RecyclerView.ViewHolder {

    AppController appController;
public TextView description, vspart_name;
public Button btn_Accept, btn_reject;
    public MyViewHolder_NewUser(@NonNull View itemView) {

        super(itemView);
        vspart_name = itemView.findViewById(R.id.vspart_name);
        description = itemView.findViewById(R.id.description);
        btn_Accept = itemView.findViewById(R.id.btn_Accept);
        btn_reject = itemView.findViewById(R.id.btn_reject);


    }
}
