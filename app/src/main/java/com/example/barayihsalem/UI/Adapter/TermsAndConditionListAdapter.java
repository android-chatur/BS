package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.DialogTermsAndConditions;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class TermsAndConditionListAdapter extends RecyclerView.Adapter<TermsAndConditionListAdapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    CheckBox aboutbs, tv_price;
    TextView txtlable;
    ImageView ivImage;
    LinearLayout mainid;
    ArrayList<Row> menu_list = new ArrayList<>();
    AppPreferences appPreferences;
    String resi;
    private DialogTermsAndConditions dialogTermsAndConditions;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public TermsAndConditionListAdapter(String resi, Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> menu_list) {
        this.resi = resi;
        this.context = context;
        this.menu_list = menu_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public TermsAndConditionListAdapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tems_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tems_list, parent, false);

        }
        return new TermsAndConditionListAdapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAndConditionListAdapter.OffersProduct_ada_ad holder, int position) {

        if (resi.equals("resi")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.recident_color));

        }

        if (resi.equals("bussi")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
            aboutbs.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
        }
        if (resi.equals("passi")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.reject_color));
            aboutbs.setTextColor(context.getResources().getColor(R.color.reject_color));
        }
        if (resi.equals("suppli")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.supplier_color));
            aboutbs.setTextColor(context.getResources().getColor(R.color.supplier_color));
        }
        if (resi.equals("sales")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.supplier_color));
            aboutbs.setTextColor(context.getResources().getColor(R.color.supplier_color));
        }

    if (resi.equals("create")) {
            txtlable.setTextColor(context.getResources().getColor(R.color.black));
            aboutbs.setTextColor(context.getResources().getColor(R.color.black));
        }

        txtlable.setText(menu_list.get(position).getxName());


        aboutbs.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
        txtlable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (dialogTermsAndConditions != null && dialogTermsAndConditions.isShowing())
                    dialogTermsAndConditions.dismiss();
                dialogTermsAndConditions = new DialogTermsAndConditions(context, menu_list.get(position).getxLink(), menu_list.get(position).getxCode());
                dialogTermsAndConditions.show();


                //  new Get_ContentPage_Data(context,menu_list.get(position).getxLink(),menu_list.get(position).getxCode()).execute();
            }
        });


    }


    @Override
    public int getItemCount() {
        return menu_list.size();
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
            aboutbs = itemView.findViewById(R.id.tems_cond);
            txtlable = itemView.findViewById(R.id.txtlable);
            mainid = itemView.findViewById(R.id.mainid);

            aboutbs.setTypeface(typebold);


        }
    }
}


