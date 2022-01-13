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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class PastEvent_Adapter extends RecyclerView.Adapter<PastEvent_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView event_name, tx_cy, tx_cancel;
    ImageView ivImage, proi;
    LinearLayout lin_main;
    Past_sub_eventAdapter past_sub_eventAdapter;
    RecyclerView rw_eventlist;
    ArrayList<Row> pastUpComming_list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public PastEvent_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_pastEvent, ArrayList<Row> pastUpComming_list) {
        this.context = context;
        this.onClickCallback_Benefi = onClickCall_pastEvent;
        this.pastUpComming_list = pastUpComming_list;

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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastevent_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastevent_list_ar, parent, false);

        }
        return new OffersProduct_ada_ad(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull OffersProduct_ada_ad holder, final int position) {
        //tvName.setText(list.get(position).getxName());

       /* if (position == 1) {
            event_name.setText("Event 2");

        }
*/

        if (appPreferences.getCulturemode().equals("1")) {

            event_name.setText("Event" + " " + pastUpComming_list.get(position).getSr());


        } else {


            event_name.setText("الأحداث " + " " + pastUpComming_list.get(position).getSr());


        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_eventlist.setLayoutManager(gridLayoutManager);
        past_sub_eventAdapter = new Past_sub_eventAdapter(context, onClickCallback_Benefi,pastUpComming_list);
        rw_eventlist.setAdapter(past_sub_eventAdapter);
        lin_main.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return pastUpComming_list.size();
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
            event_name = itemView.findViewById(R.id.event_name);
            rw_eventlist = itemView.findViewById(R.id.rw_eventlist);
            lin_main = itemView.findViewById(R.id.lin_main);
            event_name.setTypeface(typeRegular);

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


