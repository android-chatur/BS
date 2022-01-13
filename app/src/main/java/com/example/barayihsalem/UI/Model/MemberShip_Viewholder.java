package com.example.barayihsalem.UI.Model;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class MemberShip_Viewholder extends RecyclerView.ViewHolder {



    public Button proceed_do;
    public View view;
    public TextView tx_cy, tx_cancel;
    public     TextView name, editText_area, mobile, mobi_num, email, email_num,email_,email_num_;

    public RelativeLayout relative_id;
    public LinearLayout lin_select;

    public MemberShip_Viewholder(@NonNull View itemView) {
        super(itemView);

        view = itemView.findViewById(R.id.view);
        proceed_do = itemView.findViewById(R.id.proceed_do);
        email_num_ = itemView.findViewById(R.id.email_num_);
        name = itemView.findViewById(R.id.name);
        email_num = itemView.findViewById(R.id.email_num);
        editText_area = itemView.findViewById(R.id.editText_area);
        mobile = itemView.findViewById(R.id.mobile);
        mobi_num = itemView.findViewById(R.id.mobi_num);
        email = itemView.findViewById(R.id.email);
        email_ = itemView.findViewById(R.id.email_);

    }
}
