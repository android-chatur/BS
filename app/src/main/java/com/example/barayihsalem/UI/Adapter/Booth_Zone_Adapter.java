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
import com.example.barayihsalem.UI.Create_Booth_Slot;
import com.example.barayihsalem.UI.Select_MemberShip_Type;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class Booth_Zone_Adapter extends RecyclerView.Adapter<Booth_Zone_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, benefit, date1, benefit_list;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;


    public Booth_Zone_Adapter(Create_Booth_Slot bissiness_owner) {
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booth_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booth_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {
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
        return 1;
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
          /*  date = itemView.findViewById(R.id.date);
            date1 = itemView.findViewById(R.id.date1);
            benefit = itemView.findViewById(R.id.benefit);
            benefit_list = itemView.findViewById(R.id.benefit_list);
            //  lin_membackground = itemView.findViewById(R.id.lin_membackground);
            date.setTypeface(typeSemibold);
            date1.setTypeface(typeRegular);
            benefit_list.setTypeface(typeRegular);
            benefit.setTypeface(typebold);*/




        }
    }


}

