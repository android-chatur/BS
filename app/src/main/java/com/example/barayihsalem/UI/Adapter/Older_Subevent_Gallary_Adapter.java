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
import com.example.barayihsalem.UI.Model.EventGallery;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Older_Subevent_Gallary_Adapter extends RecyclerView.Adapter<Older_Subevent_Gallary_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView time;
    TextView aboutbs, price, countnolable;
    LinearLayout lin_qnt;
    RecyclerView rw_sub_gallary;
    //  Button btnLogin;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    //  private OnItemClickListener.OnClickCallback onClickCallback_Benefi;
    BottomSheetDialog dialog;
    /*
        public Cart_Adapter(Cart_Activity exchage_list) {


        }*/
    List<EventGallery> eventGalleryList = new LinkedList<>();

    public Older_Subevent_Gallary_Adapter(Context shopping_activity, List<EventGallery> eventGalleryList) {
        this.context = shopping_activity;
        this.eventGalleryList = eventGalleryList;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Older_Subevent_Gallary_Adapter.MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.older_event_gallary_list_sub, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.older_event_gallary_list_sub, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Older_Subevent_Gallary_Adapter.MyViewHolder_procedBene vh = new Older_Subevent_Gallary_Adapter.MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Older_Subevent_Gallary_Adapter.MyViewHolder_procedBene holder, int position) {




        Picasso.with(context)
                .load(context.getResources().getString(R.string.Events_Gallery) + eventGalleryList.get(position).getImage1())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                .fit()
                .into(time);

    }


    @Override
    public int getItemCount() {
        return eventGalleryList.size();
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

            time = itemView.findViewById(R.id.time);

        }
    }


}
