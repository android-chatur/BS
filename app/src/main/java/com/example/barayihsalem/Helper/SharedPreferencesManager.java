package com.example.barayihsalem.Helper;





import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


public final class SharedPreferencesManager {

    private static final String SHARED_PREFS_NAME = "hyaat";
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesManager() {
    }


    public static boolean writeString(@NonNull Context context, String key, String value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putString(key, value).apply();
        return true;
    }


    public static boolean writeInt(@NonNull Context context, String key, int value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putInt(key, value).apply();
        return true;
    }


    public static boolean writeBoolean(@NonNull Context context, String key, boolean value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putBoolean(key, value).apply();
        return true;
    }


    public static boolean writeLong(@NonNull Context context, String key, long value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putLong(key, value).apply();
        return true;
    }


    public static boolean writeFloat(@NonNull Context context, String key, float value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putFloat(key, value).apply();
        return true;
    }

    public static String getString(@NonNull Context context, String key, String defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    public static int getInt(@NonNull Context context, String key, int defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static long getLong(@NonNull Context context, String key, long defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getLong(key, defaultValue);
    }


    public static float getFloat(@NonNull Context context, String key, float defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static boolean getBoolean(@NonNull Context context, String key, boolean defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static SharedPreferences getSharedPreferences(@NonNull Context context) {

        if (context == null) {
            return null;
        }

        if (sharedPreferences == null) {

//            if (Constants.IS_DEBUG)
            {
                sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
            }
//            else {
//                sharedPreferences = new SecurePreferences(context, BuildConfig.APPLICATION_ID + "!sp0rtk1x#b0mb4y#r0b1n@!", SHARED_PREFS_NAME);
//            }
        }
        return sharedPreferences;
    }

    /**
     * Gets shared preferences editor.
     *
     * @param context the context
     * @return the shared preferences editor
     */
    public static SharedPreferences.Editor getSharedPreferencesEditor(@NonNull Context context) {

        if (context == null) {
            return null;
        }
        return getSharedPreferences(context).edit();
    }

}
