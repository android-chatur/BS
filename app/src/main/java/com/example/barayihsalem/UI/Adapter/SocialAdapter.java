package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Booth_Event_Activity;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView face;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<Row> Membership_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;


    public SocialAdapter(Booth_Event_Activity booth_event_activity, ArrayList<Row> social_list) {
        this.context = booth_event_activity;
        this.Membership_list = social_list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public SocialAdapter.MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        SocialAdapter.MyViewHolder_procedBene vh = new SocialAdapter.MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SocialAdapter.MyViewHolder_procedBene holder, int position) {


        if (Membership_list.get(position).getTitle().equals("Fcbk")) {
            face.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.facebook));
        }
        if (Membership_list.get(position).getTitle().equals("Twit")) {
            face.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.twitter));
        }

        if (Membership_list.get(position).getTitle().equals("Insta")) {
            face.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.search));
        }
        if (Membership_list.get(position).getTitle().equals("Pntrst")) {
            face.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.search));
        }

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Membership_list.get(position).getUrl()));
                context.startActivity(i);
            }
        });
       // face.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
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

            lin_membackground = itemView.findViewById(R.id.lin_membackground);
            face = itemView.findViewById(R.id.face);




          /*  bene_bank_name = itemView.findViewById(R.id.bene_bank_name);
            benefi_name = itemView.findViewById(R.id.benefi_name);

*/
           /* benefi_name.setTypeface(typeSemibold);

            bene_bank_name.setTypeface(typeLight);*/

        }
    }


}
