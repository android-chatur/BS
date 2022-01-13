package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.MyViewHolder;
import com.example.barayihsalem.UI.Model.MyViewHolder_Zone;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.ZonePojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Zone_Select_Adapter extends RecyclerView.Adapter<MyViewHolder_Zone> {
    Context context;
    AppPreferences appPreferences;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    ArrayList<Row> listData=new ArrayList<>();
    public Zone_Select_Adapter(Context context, ArrayList<Row> listData) {
        this.context = context;
        this.listData = listData;
    }

   /* public Zone_Select_Adapter(Create_Events create_events, List<ZonePojo> listData) {
    }
*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder_Zone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_select_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_select_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_Zone vh = new MyViewHolder_Zone(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Zone holder, int position) {

        holder.date.setText(listData.get(position).getxName());

        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.get(position).setSelected(!listData.get(position).isSelected());
              //  holder.lin_date.setBackgroundColor(listData.get(position).isSelected() ? Color.CYAN : Color.WHITE);
                holder.date.setBackground(context.getResources().getDrawable(R.drawable.edittext_select_zone));

            }
        });

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
      //  listData.size();
        return listData == null ? 0 : listData.size();
    }



}

