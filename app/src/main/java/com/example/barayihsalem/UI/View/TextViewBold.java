package com.example.barayihsalem.UI.View;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBold extends TextView {


    public TextViewBold(Context context) {
        super(context);
        setCustomFont(context);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }


    public void setCustomFont(Context context) {
        Typeface tf = null;
        try {

            tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Bold.ttf");


            setTypeface(tf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

