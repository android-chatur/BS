package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class DishDetail_Viewholder extends RecyclerView.ViewHolder {

    public TextView shushi,tvName;
    public ImageView newly_tick;
   public   CheckBox tems_cond;
    public RelativeLayout relative_id;


    public DishDetail_Viewholder(@NonNull View itemView) {
        super(itemView);



        tems_cond = itemView.findViewById(R.id.tems_cond);
        tvName = itemView.findViewById(R.id.newly);
        shushi = itemView.findViewById(R.id.shushi);
        newly_tick = itemView.findViewById(R.id.newly_tick);
        //ivImage = itemView.findViewById(R.id.newly_tick);
        relative_id = itemView.findViewById(R.id.relative_id);


    }
}