package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Food_Activity;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

    public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.OffersProduct_ada_ad> {
        AppController appController;
        Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
        TextView tvHeader;
        TextView ivAll;
        LinearLayout llNoProducts,lin_header,asd;
        RecyclerView rvList;
      //  ArrayList<Row> list = new ArrayList<>();
        AppPreferences appPreferences;
      //  SubFoodAdapter subCategoryAdapter;
        LinearLayoutManager horizontalLayoutManager;
        private Context context;

        public AllFoodAdapter(Context context) {
            this.context = context;
           // this.list = list;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @NonNull
        @Override
        public AllFoodAdapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list, parent, false);

            } else {
                ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list, parent, false);

            }
            return new AllFoodAdapter.OffersProduct_ada_ad(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AllFoodAdapter.OffersProduct_ada_ad holder, final int position) {
          //  tvHeader.setText(list.get(position).getxName());

           /* if (list.get(position).getSubCategoryList() != null && list.get(position).getSubCategoryList().size() < 1) {
                rvList.setVisibility(View.GONE);
                lin_header.setVisibility(View.GONE);
                llNoProducts.setVisibility(View.GONE);
                asd.setVisibility(View.GONE);
            } else {*/
               // asd.setVisibility(View.VISIBLE);
                rvList.setVisibility(View.VISIBLE);
             //   lin_header.setVisibility(View.VISIBLE);
              //  llNoProducts.setVisibility(View.GONE);
          /*  SubFoodAdapter subCategoryAdapter = new SubFoodAdapter(context);
            horizontalLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            rvList.setLayoutManager(horizontalLayoutManager);
            rvList.setAdapter(subCategoryAdapter);*/
           // }

            ivAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 /*   String xname = list.get(position).getxName();
                    appPreferences.saveCategory_Name(xname);
                    appPreferences.saveCategory(list.get(position).getxCode());
                    context.startActivity(new Intent(context, AllItems.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
                    ((Activity) context).finish();*/

                }
            });

        }

        @Override
        public int getItemCount() {
            return 2;
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
                tvHeader = itemView.findViewById(R.id.tv_header);
                rvList = itemView.findViewById(R.id.rv_list);
                lin_header = itemView.findViewById(R.id.lin_header);
                asd = itemView.findViewById(R.id.asd);
                ivAll = itemView.findViewById(R.id.iv_all);
               // llNoProducts = itemView.findViewById(R.id.ll_no_products);
                tvHeader.setTypeface(heding);
            }
        }
    }
