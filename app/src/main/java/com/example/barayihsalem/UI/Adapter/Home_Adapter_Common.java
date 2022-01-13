package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.HomeActivity;
import com.example.barayihsalem.UI.Model.Common;
import com.example.barayihsalem.UI.Model.Home_Commm;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Home_Adapter_Common extends RecyclerView.Adapter<Home_Commm> {

    Context context;
    AppPreferences appPreferences;

    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<Common> Common_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCall;

    public Home_Adapter_Common(HomeActivity homeActivity, ArrayList<Common> Common_list, OnItemClickListener.OnClickCallback onClickCall) {
        this.context = homeActivity;
        this.Common_list = Common_list;
        this.onClickCall = onClickCall;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull

    @Override
    public Home_Commm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        appController = (AppController) context.getApplicationContext();

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecommon_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecommon_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Home_Commm vh = new Home_Commm(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Home_Commm holder, int position) {


        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        holder.tx_bs_news.setTypeface(typebold);
        holder.see_all_news.setTypeface(typebold);

        holder.tx_bs_news.bringToFront();
        holder.tx_bs_news.setText(Common_list.get(position).getxName());


        holder.see_all_news.setOnClickListener(new OnItemClickListener(position, onClickCall, "ITEM"));
        holder.lin_bs_news.setOnClickListener(new OnItemClickListener(position, onClickCall, "ITEM"));


        if (Common_list.get(position).getxCode().equals("BsNews")) {


            Glide.with(context)
                    .load("https://api.barayihsalem.com/Upload/BS_News/" + Common_list.get(position).getImage())

                    //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }


                    })
                    .transition(withCrossFade())
                    .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.home).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(holder.img_logo);

        }
        if (Common_list.get(position).getxCode().equals("BookEvent")) {


            Glide.with(context)
                    .load("https://api.barayihsalem.com/Upload/Event_Approval/"+ Common_list.get(position).getImage())

                    //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }


                    })
                    .transition(withCrossFade())
                    .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.home).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(holder.img_logo);

        }

        if (Common_list.get(position).getxCode().equals("Shpng")) {


            Glide.with(context)
                    .load("https://api.barayihsalem.com/Upload/Store/" + Common_list.get(position).getImage())

                    //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }


                    })
                    .transition(withCrossFade())
                    .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.home).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(holder.img_logo);

        }

    }


    @Override
    public int getItemCount() {
        return Common_list.size();
    }
}
