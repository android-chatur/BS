package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Search_Shopping;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Seach_Adapter extends RecyclerView.Adapter<Seach_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName, tv_name_sub, tv_price;
    ImageView ivImage;
    RelativeLayout relative_id;
    ArrayList<Row> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Seach_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta, ArrayList<Row> seach_list) {
        this.context = context;
         this.list = seach_list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Seach_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seach_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seach_list_ar, parent, false);

        }
        return new Seach_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Seach_Adapter.OffersProduct_ada_ad holder, final int position) {


        String Mem_Price = String.format("%.3f", list.get(position).getNewPrice());
        String KD = list.get(position).getCurrency();
        tv_price.setText(Mem_Price + " " + KD);


        tvName.setText(list.get(position).getxName());


        Picasso.with(context)
                .load(context.getResources().getString(R.string.ItemCard) + list.get(position).getImage1())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                //  .fit()
                .into(ivImage);

        //tvName.setText(list.get(position).getxName());
     /*   String image = list.get(position).getImage1();
        if (list.get(position).getImage1() != null)
            // Picasso.with(ivImage.getContext()).load("http://admin.livingkwt.com/Upload/SubCat/2/" + image).error(R.drawable.default_image).fit().transform(new CustomImageRoundCorner()).into(ivImage);


            Glide.with(context)
                    .load("http://admin.livingkwt.com/Upload/SubCat/2/" + image)

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
                    .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.default_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
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
            tvName = itemView.findViewById(R.id.tv_name);
            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            tv_price = itemView.findViewById(R.id.tv_price);
            tvName.setTypeface(typeHeader);
            tv_name_sub.setTypeface(typeRegular);
            tv_price.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.iv_image);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}


