package com.example.barayihsalem.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.barayihsalem.Helper.RoundRectCornerImageView;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.HomeActivity;
import com.example.barayihsalem.UI.Model.AdsDetail;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SlidingImage_Adapter_home extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<AdsDetail> images;


   /* public SlidingImage_Adapter_home(HomeActivity productDetailPage, ArrayList<Integer> images) {
        this.context = productDetailPage;
        inflater = LayoutInflater.from(context);
        this.images = images;

    }*/

    public SlidingImage_Adapter_home(HomeActivity productDetailPage, ArrayList<AdsDetail> productsArray) {
        this.context = productDetailPage;
        inflater = LayoutInflater.from(context);
        this.images = productsArray;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        RoundRectCornerImageView myImage = imageLayout.findViewById(R.id.image);


        //appPreferences.saveCategory(list.get(position).getxCode());


        Glide.with(context)
                .load("https://api.barayihsalem.com/Upload/AdminEntries/" + images.get(position).getxName())

                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


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
                .apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.home).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(myImage);



        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
