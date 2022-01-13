package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class Shopping_ViewHolder extends RecyclerView.ViewHolder {
    public ImageView proimage2;
    public TextView tx_grocery;
    public RelativeLayout relative_id;

    public Shopping_ViewHolder(@NonNull View itemView) {
        super(itemView);

        tx_grocery = itemView.findViewById(R.id.tx_grocery);
        proimage2 = itemView.findViewById(R.id.proimage2);
        relative_id = itemView.findViewById(R.id.relative_id);

    }
}
