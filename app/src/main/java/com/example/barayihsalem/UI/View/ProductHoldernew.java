package com.example.barayihsalem.UI.View;

import android.inputmethodservice.Keyboard;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.R;

public class ProductHoldernew extends RecyclerView.ViewHolder {
    public TextView prod_price, prod_name, txtQuantity, brand, oldprice;
    public ImageView itemImage, delete_cart;
    public ImageView img_adv_sub;
    public ImageView imgMinus, imgPlus, wishlist;
    Keyboard.Row gridItem;
    RelativeLayout relativeLayout;
    Button addcart, buy;

    public ProductHoldernew(View itemView) {
        super(itemView);

        //itemView.setOnClickListener(this);
        itemImage = (ImageView) itemView.findViewById(R.id.icon);
        delete_cart = (ImageView) itemView.findViewById(R.id.delete);
        // img_adv_sub = (ImageView) itemView.findViewById(R.id.img_adv_sub);
        prod_price = (TextView) itemView.findViewById(R.id.price);
        prod_name = (TextView) itemView.findViewById(R.id.productname);
        txtQuantity = (TextView) itemView.findViewById(R.id.countnolable);
        //relativeLayout=(RelativeLayout) itemView.findViewById(R.id.relative_adapter);
        imgMinus = (ImageView) itemView.findViewById(R.id.imgMinus);
        imgPlus = (ImageView) itemView.findViewById(R.id.imgPlus);
        //  addcart = (Button) itemView.findViewById(R.id.addcart);

    }
}