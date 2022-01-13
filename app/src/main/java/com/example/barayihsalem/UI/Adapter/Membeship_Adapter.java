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
import com.example.barayihsalem.UI.Model.LiMem;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.example.barayihsalem.UI.WellcomefamilyPage;

import java.util.ArrayList;

public class Membeship_Adapter extends RecyclerView.Adapter<Membeship_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView date, date_;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<LiMem> Membership_list = new ArrayList<com.example.barayihsalem.UI.Model.LiMem>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Membeship_Adapter(WellcomefamilyPage exchage_list, ArrayList<LiMem> Membership_list, OnItemClickListener.OnClickCallback onClickCallback_Benefi) {
        this.context = exchage_list;
        this.Membership_list = Membership_list;
        this.onClickCallback_Benefi = onClickCallback_Benefi;
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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {
        date.setText(Membership_list.get(position).membershipName);
        if (position == 0) {
            // lin_membackground.setVisibility(View.GONE);
            if ((Membership_list.get(position).isDisable)) {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.visionpatner));
                lin_membackground.setAlpha((float) 0.5);
                lin_membackground.setClickable(false);
                lin_membackground.setFocusable(false);

            } else {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.visionpatner));
                lin_membackground.setClickable(true);
                lin_membackground.setFocusable(true);
            }


        }

        if (position == 1) {
            // lin_membackground.setVisibility(View.GONE);
            if ((Membership_list.get(position).isDisable)) {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.bussinedd_owner));
                lin_membackground.setAlpha((float) 0.5);
                lin_membackground.setClickable(false);
                lin_membackground.setFocusable(false);

            } else {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.bussinedd_owner));
                lin_membackground.setClickable(true);
                lin_membackground.setFocusable(true);
            }


        }

        if (position == 2) {
            // lin_membackground.setVisibility(View.GONE);
            if ((Membership_list.get(position).isDisable)) {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.resident));
                lin_membackground.setAlpha((float) 0.5);
                lin_membackground.setClickable(false);
                lin_membackground.setFocusable(false);

            } else {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.resident));
                lin_membackground.setClickable(true);
                lin_membackground.setFocusable(true);
            }


        }


        if (position == 3) {
            // lin_membackground.setVisibility(View.GONE);
            if ((Membership_list.get(position).isDisable)) {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.passionate_visitor));
                lin_membackground.setAlpha((float) 0.5);
                lin_membackground.setClickable(false);
                lin_membackground.setFocusable(false);

            } else {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.passionate_visitor));
                lin_membackground.setClickable(true);
                lin_membackground.setFocusable(true);
            }


        }

        if (position == 4) {
            // lin_membackground.setVisibility(View.GONE);
            if ((Membership_list.get(position).isDisable)) {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.supplier));
                lin_membackground.setAlpha((float) 0.5);
                lin_membackground.setClickable(false);
                lin_membackground.setFocusable(false);

            } else {
                lin_membackground.setBackground(context.getResources().getDrawable(R.drawable.supplier));
                lin_membackground.setClickable(true);
                lin_membackground.setFocusable(true);
            }


        }


        lin_membackground.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return Membership_list.size();
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
            lin_membackground = itemView.findViewById(R.id.lin_membackground);
            date.setTypeface(typebold);



          /*  bene_bank_name = itemView.findViewById(R.id.bene_bank_name);
            benefi_name = itemView.findViewById(R.id.benefi_name);

*/
           /* benefi_name.setTypeface(typeSemibold);

            bene_bank_name.setTypeface(typeLight);*/

        }
    }


}
