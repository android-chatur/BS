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
import com.example.barayihsalem.UI.Filter_Activity;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SubFoodAdapter extends RecyclerView.Adapter<SubFoodAdapter.OffersProduct_ada_ad> {
  AppController appController;
  Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, LoraNormal;
  TextView tvName;
  ImageView ivImage;
  ArrayList<Row> shopping_list = new ArrayList<>();
  AppPreferences appPreferences;
  LinearLayout card;
  private Context context;
  private OnItemClickListener.OnClickCallback onClickCallback;

  public SubFoodAdapter(Context context, OnItemClickListener.OnClickCallback onClickCall, ArrayList<Row> shopping_list) {
    this.context = context;
    this.shopping_list = shopping_list;
    this.onClickCallback = onClickCall;

  }

  public SubFoodAdapter(Filter_Activity filter_activity, ArrayList<Row> filter_list) {
  }


  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  @NonNull
  @Override
  public SubFoodAdapter.OffersProduct_ada_ad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
      itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_sub_list, parent, false);
    } else {
      ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

      itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_sub_list_ar, parent, false);

    }
    return new SubFoodAdapter.OffersProduct_ada_ad(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull SubFoodAdapter.OffersProduct_ada_ad holder, final int position) {

    tvName.setText(shopping_list.get(position).getxName());
    tvName.bringToFront();

    Picasso.with(context)
            .load(context.getResources().getString(R.string.Imageurl_Cuisines) + shopping_list.get(position).getImage())
            .error(R.drawable.home)
            .placeholder(R.drawable.home)
            // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
            //  .fit()
            .into(ivImage);

    card.setOnClickListener(new OnItemClickListener(position, onClickCallback, "ITEM"));


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
      card = itemView.findViewById(R.id.card);
      tvName.setTypeface(typeSemibold);
      ivImage = itemView.findViewById(R.id.iv_image);
      tvName.setTypeface(typebold);

    }
  }
}
