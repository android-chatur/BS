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
import com.example.barayihsalem.UI.Model.Row;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SlidingImage_Adapter_Food extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    //ArrayList<Banner> images;

    ArrayList<Row> productsArray = new ArrayList<>();

    public SlidingImage_Adapter_Food(Food_Activity productDetailPage, ArrayList<Row> productsArray) {
        this.context = productDetailPage;
        inflater = LayoutInflater.from(context);
        this.productsArray = productsArray;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return productsArray.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        RoundRectCornerImageView myImage = imageLayout.findViewById(R.id.image);


        Picasso.with(context)
                .load(context.getResources().getString(R.string.Banner_Category) + productsArray.get(position).getBannerCategoryImg())
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
