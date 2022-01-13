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
import com.example.barayihsalem.UI.Enviromental_commu;
import com.example.barayihsalem.UI.Food_Beverages;
import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.MyViewHolder;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;


public class Environmetal_Adapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, date_;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    ArrayList<List> enviroment_list=new ArrayList<>();

    public Environmetal_Adapter(Enviromental_commu enviromental_commu, ArrayList<List> enviroment_list) {
        this.context = enviromental_commu;
        this.enviroment_list = enviroment_list;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.envime_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.envime_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setTypeface(typebold);
        holder.date1.setTypeface(typeRegular);
        holder.date.setText(enviroment_list.get(position).title);
        holder.date1.setText(enviroment_list.get(position).getDescription());
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

                String Url = enviroment_list.get(position).getUrl();

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

        holder.down_arrow.setOnClickListener(new View.OnClickListener() {
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
        return enviroment_list.size();
    }



}
