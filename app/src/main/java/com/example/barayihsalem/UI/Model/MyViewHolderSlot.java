package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MyViewHolderSlot extends RecyclerView.ViewHolder {

        public TextView date;
        public RecyclerView rw_zonelist_slot;

        public LinearLayout lin_date;

    public MyViewHolderSlot(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);




    }
}
