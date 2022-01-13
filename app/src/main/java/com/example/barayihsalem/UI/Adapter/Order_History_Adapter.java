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

public class Order_History_Adapter extends RecyclerView.Adapter<Order_History_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView rate_order, rating, productname, price, date, restarunt_name;
    ImageView ivImage;
    RelativeLayout relative_id;
    ArrayList<Row> order_Event_List = new ArrayList<>();
    AppPreferences appPreferences;
    RatingBar rvratingbarptgym;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Order_History_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> order_Event_List) {
        this.context = context;
        this.order_Event_List = order_Event_List;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Order_History_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_ar, parent, false);

        }
        return new Order_History_Adapter.OffersProduct_ada_ad(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull Order_History_Adapter.OffersProduct_ada_ad holder, final int position) {
        Picasso.with(context)
                .load(context.getResources().getString(R.string.ItemCard) + order_Event_List.get(position).getImage1())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                .fit()
                .into(ivImage);


        productname.setText(order_Event_List.get(position).getItemName());
        date.setText(order_Event_List.get(position).getOrderDate());


        restarunt_name.setText(order_Event_List.get(position).getStoreName());
        double changed_total = (order_Event_List.get(position).getPrice());
        price.setText(String.format("%.3f", changed_total) + " " + order_Event_List.get(position).getCurrency());


        double as = order_Event_List.get(position).getRatingStar();
        String Mem_P_as = String.format("%.0f", as);


        String rating1 = String.valueOf(order_Event_List.get(position).getRatings());
        rating.setText(rating1);
        // rvratingbarptgym.setRating(shopping_list.get(position).getRatings());
        rvratingbarptgym.setRating((float) as);


        rate_order.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return order_Event_List.size();
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
            rate_order = itemView.findViewById(R.id.rate_order);
            productname = itemView.findViewById(R.id.productname);
            restarunt_name = itemView.findViewById(R.id.restarunt_name);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
            rating = itemView.findViewById(R.id.rating);
            rating = itemView.findViewById(R.id.rating);
            price.setTypeface(typeHeader);
            productname.setTypeface(typeHeader);
            rate_order.setTypeface(typeRegular);
            restarunt_name.setTypeface(typeRegular);
            rating.setTypeface(typeRegular);
            date.setTypeface(typeRegular);
            rating.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.icon);
            relative_id = itemView.findViewById(R.id.relative_id);
            rvratingbarptgym = itemView.findViewById(R.id.rvratingbarptgym);

        }
    }
}

