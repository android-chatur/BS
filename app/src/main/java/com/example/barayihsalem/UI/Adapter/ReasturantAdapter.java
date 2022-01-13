package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReasturantAdapter extends RecyclerView.Adapter<ReasturantAdapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName,rating,tv_delivary_time,tv_name_sub;
    ImageView ivImage;
    RatingBar rvratingbarptgym;
    RelativeLayout relative_id;
    ArrayList<Row> shopping_list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public ReasturantAdapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> shopping_list) {
        this.context = context;
        this.shopping_list = shopping_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public ReasturantAdapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restarunt_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restarunt_list_ar, parent, false);

        }
        return new ReasturantAdapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReasturantAdapter.OffersProduct_ada_ad holder, final int position) {
        tvName.setText(shopping_list.get(position).getStoreName());




        String Mem_Price_ = String.format("%.3f", shopping_list.get(position).getStoreMinOrder());
        String Mem_P_ = String.format("%.0f", shopping_list.get(position).getRatingStar());
       // String Installment_Price_ = String.format("%.3f", Mem_Price_);
        String KD = shopping_list.get(position).getCurrency();
        tv_name_sub.setText(Mem_Price_ + " " +KD);
        Double as= shopping_list.get(position).getRatingStar();
        rating.setText(Mem_P_);
        rvratingbarptgym.setRating(shopping_list.get(position).getRatings());



        tv_delivary_time.setText(shopping_list.get(position).getStoreDeliveryTime() +" "+ shopping_list.get(position).getTimeInWords());


        Picasso.with(context)
                .load(context.getResources().getString(R.string.Store) + shopping_list.get(position).getStoreLogo())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                .into(ivImage);

        relative_id.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return shopping_list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class OffersProduct_ada_ad extends RecyclerView.ViewHolder {
        public OffersProduct_ada_ad(@NonNull View itemView) {
            super(itemView);
            tv_delivary_time = itemView.findViewById(R.id.tv_delivary_time);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            rating = itemView.findViewById(R.id.rating);
            rvratingbarptgym = itemView.findViewById(R.id.rvratingbarptgym);


            rating.setTypeface(typeRegular);
            tv_delivary_time.setTypeface(typeRegular);
            tv_name_sub.setTypeface(typeRegular);
            tvName.setTypeface(typeHeader);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}

