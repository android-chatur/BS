package com.example.barayihsalem.UI.View;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonViewRegular extends Button {
    public ButtonViewRegular(Context context) {
        super(context);
        setCustomFont(context);
    }

    public ButtonViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public ButtonViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }


    public void setCustomFont(Context context) {
        Typeface tf = null;
        try {

            tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-SemiBold.ttf");

            setTypeface(tf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
