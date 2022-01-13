package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Opportunity_ForYou;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Oppertunities_for_You_Adapter extends RecyclerView.Adapter<Oppertunities_for_You_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView twitter_l, ti;
    TextView auther, see_all_news, auther1;
    LinearLayout lin_membackground;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<Row> news_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Oppertunities_for_You_Adapter(Opportunity_ForYou exchage_list, ArrayList<Row> news_list, OnItemClickListener.OnClickCallback onClickCall) {
        this.context = exchage_list;
        this.news_list = news_list;
        this.onClickCallback_Benefi = onClickCall;

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
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opportuni_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opportuni_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {

        if (appPreferences.get_commu_color().equals("entretaiment")) {

            see_all_news.setTextColor(context.getResources().getColor(R.color.reject_color));
            auther1.setTextColor(context.getResources().getColor(R.color.reject_color));

            twitter_l.setColorFilter(context.getResources().getColor(R.color.reject_color)/*, android.graphics.PorterDuff.Mode.MULTIPLY*/);

        }
        if (appPreferences.get_commu_color().equals("bussiretail")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
            auther1.setTextColor(context.getResources().getColor(R.color.buss_oner_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.buss_oner_color));


        }

        if (appPreferences.get_commu_color().equals("service")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.black));
            auther1.setTextColor(context.getResources().getColor(R.color.black));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.black));


        }
        if (appPreferences.get_commu_color().equals("food_be")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.fb_color));
            auther1.setTextColor(context.getResources().getColor(R.color.fb_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.fb_color));
        }
        if (appPreferences.get_commu_color().equals("envirome")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.enviro_color));
            auther1.setTextColor(context.getResources().getColor(R.color.enviro_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.enviro_color));
        }
        if (appPreferences.get_commu_color().equals("industrial")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.indus_color));
            auther1.setTextColor(context.getResources().getColor(R.color.indus_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.indus_color));
        }

        if (appPreferences.get_commu_color().equals("creative")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.vispart_color));
            auther1.setTextColor(context.getResources().getColor(R.color.vispart_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.vispart_color));
        }

        if (appPreferences.get_commu_color().equals("technology")) {
            see_all_news.setTextColor(context.getResources().getColor(R.color.tq_color));
            auther1.setTextColor(context.getResources().getColor(R.color.tq_color));
            twitter_l.setColorFilter(context.getResources().getColor(R.color.tq_color));
        }
        see_all_news.setText(news_list.get(position).getHeader());
        auther1.setText(news_list.get(position).getDescriptionShort());
        if (appPreferences.getCulturemode().equals("1")) {


            auther.setText("Author : " + news_list.get(position).getAuthor() + ",  " + "Date : " + news_list.get(position).getNewsDate());
        } else {
            auther.setText("تأليف : " + news_list.get(position).getAuthor() + ",  " + "تاريخ  : " + news_list.get(position).getNewsDate());

        }

        Glide.with(context)
                .load(context.getResources().getString(R.string.Imageurl_bsnews) + news_list.get(position).getImage())

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
                .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(ti);


        twitter_l.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));
    }


    @Override
    public int getItemCount() {
        return news_list.size();
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
            auther = itemView.findViewById(R.id.auther);
            see_all_news = itemView.findViewById(R.id.see_all_news);
            auther1 = itemView.findViewById(R.id.auther1);
            twitter_l = itemView.findViewById(R.id.twitter_l);
            ti = itemView.findViewById(R.id.ti);
            //  lin_membackground = itemView.findViewById(R.id.lin_membackground);
            auther.setTypeface(typeSemibold);
            see_all_news.setTypeface(heding);
            auther1.setTypeface(typeRegular);



          /*  bene_bank_name = itemView.findViewById(R.id.bene_bank_name);
            benefi_name = itemView.findViewById(R.id.benefi_name);

*/
           /* benefi_name.setTypeface(typeSemibold);

            bene_bank_name.setTypeface(typeLight);*/

        }
    }


}
