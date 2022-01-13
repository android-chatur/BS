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
import com.example.barayihsalem.UI.View.OnItemClickListener;

public class Extras_Adapter extends RecyclerView.Adapter<Extras_Adapter.OffersProduct_ada_ad> {
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
    TextView tvName,shushi;
    ImageView ivImage;
    RelativeLayout relative_id;
    //ArrayList<SubCategoryDTO> list = new ArrayList<>();
    AppPreferences appPreferences;
    private Context context;
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Extras_Adapter(Context context, OnItemClickListener.OnClickCallback onClickCall_resta) {
        this.context = context;
        //  this.list = list;
        this.onClickCallback_Benefi = onClickCall_resta;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Extras_Adapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extras_list, parent, false);
        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extras_list_ar, parent, false);

        }
        return new Extras_Adapter.OffersProduct_ada_ad(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Extras_Adapter.OffersProduct_ada_ad holder, final int position) {
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
        return 2;
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
            tvName = itemView.findViewById(R.id.newly);
            shushi = itemView.findViewById(R.id.shushi);
            tvName.setTypeface(typeRegular);
            shushi.setTypeface(typeRegular);
            ivImage = itemView.findViewById(R.id.newly_tick);
            relative_id = itemView.findViewById(R.id.relative_id);

        }
    }
}



