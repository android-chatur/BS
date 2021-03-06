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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
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
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Retails_Adapter extends RecyclerView.Adapter<Retails_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName, tv_deli_time, tv_name_sub, rating_value;
    ImageView ivImage;
    RatingBar rvratingbarptgym;
    RelativeLayout relative_id;
    ArrayList<Row> shopping_list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Retails_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> shopping_list) {
        this.context = context;
        this.shopping_list = shopping_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Retails_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.retail_seeall_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.retail_seeall_list_ar, parent, false);

        }
        return new Retails_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Retails_Adapter.OffersProduct_ada_ad holder, final int position) {
        tvName.setText(shopping_list.get(position).getStoreName());
        double changed_total = (shopping_list.get(position).getStoreMinOrder());
        tv_name_sub.setText(String.format("%.3f", changed_total) + " " + shopping_list.get(position).getCurrency());


        double as= shopping_list.get(position).getRatingStar();
        String Mem_P_as = String.format("%.0f",as);
        String Mem_P_ = String.format("%.0f", shopping_list.get(position).getRatingStar());

        String rating= String.valueOf(shopping_list.get(position).getRatings());
        rating_value.setText(rating);
       // rvratingbarptgym.setRating(shopping_list.get(position).getRatings());
        rvratingbarptgym.setRating((float) as);

        if (appPreferences.getCulturemode().equals("1")) {

            tv_deli_time.setText(shopping_list.get(position).getStoreDeliveryTime() + " " + shopping_list.get(position).getTimeInWords() + " " + "Delivery");


        }else {
            tv_deli_time.setText(shopping_list.get(position).getStoreDeliveryTime() + " " + shopping_list.get(position).getTimeInWords() + " " + "??????????????");

        }
        String image = shopping_list.get(position).getStoreLogo();
        if (shopping_list.get(position).getStoreLogo() != null)
            // Picasso.with(ivImage.getContext()).load("http://admin.livingkwt.com/Upload/SubCat/2/" + image).error(R.drawable.default_image).fit().transform(new CustomImageRoundCorner()).into(ivImage);

            Picasso.with(context)
                    .load(context.getResources().getString(R.string.Store) + shopping_list.get(position).getStoreLogo())
                    .error(R.drawable.home)
                    .placeholder(R.drawable.home)
                    // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                      .fit()
                    .into(ivImage);

        /*
            Glide.with(context)
                    .load(context.getResources().getString(R.string.Store) + shopping_list.get(position).getStoreLogo())

                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .transition(withCrossFade())
                    .apply(new RequestOptions().transform(new RoundedCorners(10)).error(R.drawable.home).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(ivImage);
*/

        relative_id.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return shopping_list.size();
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
            tvName = itemView.findViewById(R.id.tv_name);
            rvratingbarptgym = itemView.findViewById(R.id.rvratingbarptgym);
            tv_deli_time = itemView.findViewById(R.id.tv_deli_time);
            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            rating_value = itemView.findViewById(R.id.rating_value);
            tvName.setTypeface(typebold);
            tv_deli_time.setTypeface(typeRegular);
            tv_name_sub.setTypeface(typeRegular);
            rating_value.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}

