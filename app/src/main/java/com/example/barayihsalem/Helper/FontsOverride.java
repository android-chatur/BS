package com.example.barayihsalem.Helper;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        //   replaceFont(staticTypefaceFieldName, regular);

        replaceFont("DEFAULT", regular);
        replaceFont("MONOSPACE", regular);
        replaceFont("SANS_SERIF", regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field StaticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            StaticField.setAccessible(true);
            StaticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void setDefaultFont(Activity activity,
                                      String string, String fontNormal) {
        // TODO Auto-generated method stub
        final Typeface regular = Typeface.createFromAsset(activity.getAssets(),
                fontNormal);
        replaceFont("DEFAULT", regular);
        replaceFont("MONOSPACE", regular);
        replaceFont("SANS_SERIF", regular);
    }


}