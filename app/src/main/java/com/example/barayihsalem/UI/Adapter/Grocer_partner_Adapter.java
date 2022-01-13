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

public class Grocer_partner_Adapter extends RecyclerView.Adapter<Grocer_partner_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView rating_value,tvName, tv_deli_time, tv_name_sub;
    ImageView ivImage;
    RatingBar rvratingbarptgym;
    RelativeLayout relative_id;
    ArrayList<Row> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Grocer_partner_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> shopping_list) {
        this.context = context;
        this.list = shopping_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Grocer_partner_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_seeall_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_seeall_list, parent, false);

        }
        return new Grocer_partner_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Grocer_partner_Adapter.OffersProduct_ada_ad holder, final int position) {


        Picasso.with(context)
                .load(context.getResources().getString(R.string.Store) + list.get(position).getStoreLogo())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                  .fit()
                .into(ivImage);


        tvName.setText(list.get(position).getxName());


        tvName.setText(list.get(position).getStoreName());
        double changed_total = (list.get(position).getStoreMinOrder());
        tv_name_sub.setText(String.format("%.3f", changed_total) + " " + list.get(position).getCurrency());


        double as= list.get(position).getRatingStar();
        String Mem_P_as = String.format("%.0f",as);
        String Mem_P_ = String.format("%.0f", list.get(position).getRatingStar());

        String rating= String.valueOf(list.get(position).getRatings());
        rating_value.setText(rating);
        // rvratingbarptgym.setRating(shopping_list.get(position).getRatings());
        rvratingbarptgym.setRating((float) as);

        if (appPreferences.getCulturemode().equals("1")) {

            tv_deli_time.setText(list.get(position).getStoreDeliveryTime() + " " + list.get(position).getTimeInWords() + " " + "Delivery");


        }else {
            tv_deli_time.setText(list.get(position).getStoreDeliveryTime() + " " + list.get(position).getTimeInWords() + " " + "التسليم");
        }



        relative_id.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return list.size();
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
            tvName = itemView.findViewById(R.id.tv_name);
            tv_deli_time = itemView.findViewById(R.id.tv_deli_time);
            rvratingbarptgym = itemView.findViewById(R.id.rvratingbarptgym);
            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            rating_value = itemView.findViewById(R.id.rating);
            tvName.setTypeface(typebold);
            tv_deli_time.setTypeface(typeRegular);
            tv_name_sub.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}
