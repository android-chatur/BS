package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.StoreMenu;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Restaurant_AllMenu_Adapter extends RecyclerView.Adapter<Restaurant_AllMenu_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName, shushi;
    ImageView ivImage;
    RelativeLayout relative_id;
    //ArrayList<SubCategoryDTO> list = new ArrayList<>();
    AppPreferences appPreferences;
    ArrayList<StoreMenu> Storemenu_list = new ArrayList<>();
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;


    public Restaurant_AllMenu_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<StoreMenu> Storemenu_list) {
        this.context = context;
        this.Storemenu_list = Storemenu_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Restaurant_AllMenu_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmenu_list_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmenu_list_list_ar, parent, false);

        }
        return new Restaurant_AllMenu_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurant_AllMenu_Adapter.OffersProduct_ada_ad holder, final int position) {
        tvName.setText(Storemenu_list.get(position).getxName());

        String asd1= String.valueOf(Storemenu_list.get(position).getItemCount());
        shushi.setText(asd1);


        relative_id.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return Storemenu_list.size();
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
            tvName = itemView.findViewById(R.id.newly);
            shushi = itemView.findViewById(R.id.shushi);
            tvName.setTypeface(typeRegular);
            shushi.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.newly_tick);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}


