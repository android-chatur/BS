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
import com.example.barayihsalem.UI.Model.NewPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class BoothSize_Adapter extends RecyclerView.Adapter<BoothSize_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView event_boota, booth_a_value, tx_cancel;
    ImageView ivImage, proi;
    LinearLayout lin_id_main;
    Upcoming_eventAdapter upcoming_eventAdapter;
    RecyclerView rw_eventlist;
    //ArrayList<SubCategoryDTO> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    ArrayList<NewPojo> newPojoArrayList=new ArrayList<>();
    public BoothSize_Adapter(Context context, ArrayList<NewPojo> newPojoArrayList) {
        this.context = context;
        this.newPojoArrayList = newPojoArrayList;

        //  this.list = list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boothsize_adapter, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boothsize_adapter_ar, parent, false);

        }
        return new OffersProduct_ada_ad(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull OffersProduct_ada_ad holder, final int position) {
        //tvName.setText(list.get(position).getxName());


                event_boota.setText(newPojoArrayList.get(position).getBoothTypeName());
                booth_a_value.setText(newPojoArrayList.get(position).getBoothtype_A_Size());





        /*   lin_id_main.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));*/

    }

    @Override
    public int getItemCount() {
        return newPojoArrayList.size();
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
            event_boota = itemView.findViewById(R.id.event_boota);
            booth_a_value = itemView.findViewById(R.id.booth_a_value);

            event_boota.setTypeface(typeHeader);
            booth_a_value.setTypeface(typeHeader);

           /* event_name = itemView.findViewById(R.id.event_name);
            tx_cy = itemView.findViewById(R.id.tx_cy);
            tx_cancel = itemView.findViewById(R.id.tx_cancel);
            proi = itemView.findViewById(R.id.proi);
            tx_cate.setTypeface(typeHeader);
            tx_cy.setTypeface(typeRegular);
            tx_cancel.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);*/

        }
    }
}



