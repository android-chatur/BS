package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.barayihsalem.UI.Checkout_Activity;
import com.example.barayihsalem.UI.Checkout_Shopping;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Shopping_Activity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Checkout_Adapter extends RecyclerView.Adapter<Checkout_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView ti;
    TextView productname, price,qnt_text,qnt_value;
//    LinearLayout lin_qnt;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
  //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    BottomSheetDialog dialog;
    ArrayList<Row> cart_list=new ArrayList<>();
/*
    public Cart_Adapter(Cart_Activity exchage_list) {


    }*/

    public Checkout_Adapter(Checkout_Activity shopping_activity, ArrayList<Row> cart_list) {
        this.context = shopping_activity;
        this.cart_list = cart_list;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {
        productname.setText(cart_list.get(position).getxName());
        String qnt= String.valueOf(cart_list.get(position).getQuantity());
        qnt_value.setText(qnt);
        double changed_total = (cart_list.get(position).getPrice());
        price.setText(String.format("%.3f", changed_total) + " " + cart_list.get(position).getCurrency());


        Picasso.with(context)
                .load(context.getResources().getString(R.string.ItemCard) + cart_list.get(position).getImage1())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                //  .fit()
                .into(ti);
      /* if (position==0){
            lin_qnt.setVisibility(View.GONE);
            lin_atocard.setVisibility(View.VISIBLE);
        }
*/

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }



    @Override
    public int getItemCount() {
        return cart_list.size();
    }

    public class MyViewHolder_procedBene extends RecyclerView.ViewHolder {
        public MyViewHolder_procedBene(@NonNull View itemView) {
            super(itemView);
            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;
            heding = appController.heding;
            typebold = appController.typebold;
            typeLight = appController.typeLight;
            ti = itemView.findViewById(R.id.ti);
            productname = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.price);
            qnt_value = itemView.findViewById(R.id.qnt_value);
            qnt_text = itemView.findViewById(R.id.qnt_text);
            qnt_text.setTypeface(typeRegular);
            productname.setTypeface(typeRegular);
            price.setTypeface(typeRegular);
          //  aatocard.setTypeface(heding);
            qnt_value.setTypeface(typeRegular);



        }
    }


}
