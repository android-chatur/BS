package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.UpcominiEvent_Download;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

public class EqupmentBoothAdapter extends RecyclerView.Adapter<EqupmentBoothAdapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    TextView name, name_value;
    RecyclerView rw_zonelist;
    Zone_A_Adapter_Details zoneAAdapterDetails;
    LinearLayout lin_date;

    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<Row> upComming_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;



    public EqupmentBoothAdapter(UpcominiEvent_Download context, ArrayList<Row> event_list) {
        this.context = context;
        this.upComming_list = event_list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public EqupmentBoothAdapter.MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.equp_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.equp_list, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EqupmentBoothAdapter.MyViewHolder_procedBene vh = new EqupmentBoothAdapter.MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EqupmentBoothAdapter.MyViewHolder_procedBene holder, int position) {


        name.setText(upComming_list.get(position).getxName());
        String count= String.valueOf(upComming_list.get(position).getCount());
        name_value.setText(count);



    }


    @Override
    public int getItemCount() {
        return upComming_list.size();
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
            name = itemView.findViewById(R.id.name);
            lin_date = itemView.findViewById(R.id.lin_date);
            name_value = itemView.findViewById(R.id.name_value);
            name.setTypeface(typebold);
            name_value.setTypeface(typebold);



        }
    }


}


