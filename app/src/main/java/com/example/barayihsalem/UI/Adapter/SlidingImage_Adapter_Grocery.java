package com.example.barayihsalem.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.RoundRectCornerImageView;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Food_Activity;
import com.example.barayihsalem.UI.Grocery_Activity;
import com.example.barayihsalem.UI.Model.Row;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SlidingImage_Adapter_Grocery extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    //ArrayList<Banner> images;


/* public SlidingImage_Adapter_Food(HomeActivity productDetailPage, ArrayList<Integer> images) {
    this.context = productDetailPage;
    inflater = LayoutInflater.from(context);
    this.images = images;

}*/
ArrayList<Row> banner_list=new ArrayList<>();
    public SlidingImage_Adapter_Grocery(Context productDetailPage, ArrayList<Row> banner_list) {
        this.context = productDetailPage;
        inflater = LayoutInflater.from(context);
        this.banner_list = banner_list;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return banner_list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        RoundRectCornerImageView myImage = imageLayout.findViewById(R.id.image);

        String abc = String.valueOf(banner_list);
        Picasso.with(context)
                .load(context.getResources().getString(R.string.Banner_Category) + banner_list.get(position).getBannerCategoryImg())
                .error(R.drawable.home)
                .placeholder(R.drawable.home)
                // .transform(new CircleTransform((int) mcontext.getResources().getDimension(R.dimen.Px20), 0))
                //  .fit()
                .into(myImage);

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
