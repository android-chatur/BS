package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class Address_Viewhoder extends RecyclerView.ViewHolder {



    public CheckBox tx_cate;
    public TextView  tx_cy, tx_cancel;
    public ImageView ivImage,proi;
    public RelativeLayout relative_id;
    public LinearLayout lin_select;

    public Address_Viewhoder(@NonNull View itemView) {
        super(itemView);

        tx_cate = itemView.findViewById(R.id.tx_cate);
        tx_cy = itemView.findViewById(R.id.tx_cy);
        tx_cancel = itemView.findViewById(R.id.tx_cancel);
        proi = itemView.findViewById(R.id.proi);

        ivImage = itemView.findViewById(R.id.iv_image);
        relative_id = itemView.findViewById(R.id.relative_id);
        lin_select = itemView.findViewById(R.id.lin_select);

    }
}
