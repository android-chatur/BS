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
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class All_Food_adapter extends RecyclerView.Adapter<All_Food_adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName,tx_cate;
    ImageView ivImage;
    RelativeLayout relative_id;
    //ArrayList<SubCategoryDTO> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    ArrayList<Row> shopping_list=new ArrayList<>();

    public All_Food_adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> shopping_list) {
        this.context = context;
          this.shopping_list = shopping_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public All_Food_adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_food_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_food_list_ar, parent, false);

        }
        return new All_Food_adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull All_Food_adapter.OffersProduct_ada_ad holder, final int position) {
        tvName.setText(shopping_list.get(position).getxName());
        tvName.bringToFront();

        Picasso.with(context)
                .load(context.getResources().getString(R.string.Imageurl_Cuisines) + shopping_list.get(position).getImage())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                //  .fit()
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
            tvName = itemView.findViewById(R.id.tv_name);
            tx_cate = itemView.findViewById(R.id.tx_cate);
            tx_cate.setTypeface(typeRegular);
            tvName.setTypeface(typeHeader);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}

