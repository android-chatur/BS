package com.example.barayihsalem.UI;

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


public class Resta_food_Adapter extends RecyclerView.Adapter<Resta_food_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName, tv_name_sub, rating;
    ImageView ivImage;
    RelativeLayout relative_id;
    RatingBar rvratingbarptgym;
    ArrayList<Row> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Resta_food_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> feature_list) {
        this.context = context;
          this.list = feature_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Resta_food_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resta_sub_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resta_sub_list_ar, parent, false);

        }
        return new Resta_food_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Resta_food_Adapter.OffersProduct_ada_ad holder, final int position) {
        tvName.setText(list.get(position).getStoreName());
        double changed_total = (list.get(position).getStoreMinOrder());
        tv_name_sub.setText(list.get(position).getStoreType());


        double as= list.get(position).getRatingStar();
        String Mem_P_as = String.format("%.0f",as);
        String Mem_P_ = String.format("%.0f", list.get(position).getRatingStar());

        String rating1= String.valueOf(list.get(position).getRatings());
        rating.setText(rating1);
        // rvratingbarptgym.setRating(shopping_list.get(position).getRatings());
        rvratingbarptgym.setRating((float) as);


        String image = list.get(position).getStoreLogo();
        if (list.get(position).getStoreLogo() != null)
            // Picasso.with(ivImage.getContext()).load("http://admin.livingkwt.com/Upload/SubCat/2/" + image).error(R.drawable.default_image).fit().transform(new CustomImageRoundCorner()).into(ivImage);



            Picasso.with(context)
                    .load(context.getResources().getString(R.string.Store) + list.get(position).getStoreLogo())
                    .error(R.drawable.home)
                    .placeholder(R.drawable.home)
                    // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                    .fit()
                    .into(ivImage);




          /*  Glide.with(context)
                    .load(context.getResources().getString(R.string.Store) + image)

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
                    .into(ivImage);*/


        relative_id.setOnClickListener(new OnItemClickListener(position, onClickCallback_Benefi, "ITEM"));

    }

    @Override
    public int getItemCount() {
        return list.size();
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
            rating = itemView.findViewById(R.id.rating);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            tvName.setTypeface(typeHeader);
            tv_name_sub.setTypeface(typeRegular);
            rating.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            rvratingbarptgym = itemView.findViewById(R.id.rvratingbarptgym);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}
