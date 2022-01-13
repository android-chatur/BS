package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import com.example.barayihsalem.UI.BSNews_Activity;
import com.example.barayihsalem.UI.Bussiness_Owner;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.MyViewHolder;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Bussiness_Owner_Adapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, date_;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

    ArrayList<List> Vision_list = new ArrayList<List>();

    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    public Bussiness_Owner_Adapter(Bussiness_Owner bussiness_owner, ArrayList<List> Vision_list) {
        this.context = bussiness_owner;
        this.Vision_list = Vision_list;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buss_onner_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buss_onner_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder. date.setTypeface(typeHeader);
        holder.date1.setTypeface(typeRegular);
        holder.date.setText(Vision_list.get(position).title);
        holder.date1.setText(Vision_list.get(position).getDescription());
        holder.up_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.up_arrow.setVisibility(View.GONE);
                holder.date1.setVisibility(View.GONE);
                holder.down_arrow.setVisibility(View.VISIBLE);
            }
        });
        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Url = Vision_list.get(position).getUrl();

                if (!Url.equals("NULL")) {
                    if (Url.equals("BsNews")) {
                        appPreferences.save_Bs_newsBack("well");
                        context.startActivity(new Intent(context, BSNews_Activity.class));
                        ((Activity)context).finish();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                        context.startActivity(intent);
                    }
                }
            }
        });
        holder. down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.down_arrow.setVisibility(View.GONE);
                holder.up_arrow.setVisibility(View.VISIBLE);
                holder.date1.setVisibility(View.VISIBLE);



            }
        });





    }


    @Override
    public int getItemCount() {
        return Vision_list.size();
    }




}
