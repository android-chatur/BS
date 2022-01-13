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
import com.example.barayihsalem.UI.Booth_Booking_Form;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Zone_A_Adapter extends RecyclerView.Adapter<Zone_A_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, date_;
    RecyclerView rw_zonelist;
    Zone_A_Adapter_Details zoneAAdapterDetails;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Zone_A_Adapter(Booth_Booking_Form bissiness_owner) {
        this.context = bissiness_owner;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {


        if (appPreferences.getCulturemode().equals("1")) {
            if (position == 0) {
                date.setText("Area A");
            }
            if (position == 1) {
                date.setText("Area B");

            }
        }else {
            if (position == 0) {
                date.setText("منطقة أ");
            }
            if (position == 1) {
                date.setText("منطقة ب");

            }
        }



        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rw_zonelist.setLayoutManager(gridLayoutManager);
        zoneAAdapterDetails = new Zone_A_Adapter_Details(context);
        rw_zonelist.setAdapter(zoneAAdapterDetails);
      /*  date.setText(exchange_list.get(position));
        if (position == 0) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.entertaiment));

        }
        if (position == 1) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.bussiness_reatils));

        }
        if (position == 3) {
            lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.foodaandbev));

        }
*/

//        next.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
        return 2;
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
            date = itemView.findViewById(R.id.date);
            rw_zonelist = itemView.findViewById(R.id.rw_zonelist);
            //  lin_membackground = itemView.findViewById(R.id.lin_membackground);
            date.setTypeface(typeSemibold);


        }
    }


}

