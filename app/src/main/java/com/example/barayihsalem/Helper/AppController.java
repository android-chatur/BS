package com.example.barayihsalem.Helper;


import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.barayihsalem.R;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Locale;

public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();
    private static AppController mInstance;
    public Typeface typeNormal, typeRegular,typebold, typeSemibold, typeRev, typeNavFont, typeLight,heding;
    public String font, lang1;
    public boolean first_time = false;
    AppPreferences appPreferences;
    private boolean langArebic;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Global globalclass;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        globalclass = new Global(this);
        appPreferences = new AppPreferences(this);
        changeLanguage(isLangArebic());
        initParse();
        //  MobileAds.initialize(this, getString(R.string.ad_id_interstitial));

	/*	font=globalclass.fontNormal(getLang());
		lang1=getLang()+" "+getLang_Number();
		typeNormal = Typeface.createFromAsset(getAssets(), globalclass.fontNormal(getLang()));
		typeRegular = Typeface.createFromAsset(getAssets(), globalclass.fontRegular(getLang()));
		typeSemibold = Typeface.createFromAsset(getAssets(), globalclass.fontMedium(getLang()));
		typeNavFont = Typeface.createFromAsset(getAssets(), globalclass.NavbaFont(getLang()));
		typeLight = Typeface.createFromAsset(getAssets(), globalclass.LightFont(getLang()));
		typeRev = Typeface.createFromAsset(getAssets(), globalclass.fontRev(getLang()));

		FontsOverride.setDefaultFont(this, "DEFAULT", globalclass.fontNormal(getLang()));
		FontsOverride.setDefaultFont(this, "MONOSPACE", globalclass.fontNormal(getLang()))*/
    }

    public boolean isLangArebic() {
        langArebic = SharedPreferencesManager.getBoolean(this, "lang", false);
        return langArebic;
    }

    public void setLangArebic(boolean langArebic) {
        //  SharedPreferencesManager.writeString(getApplicationContext(), Constants.PREFS_LANGUAGE, "ar");
        this.langArebic = langArebic;
        changeLanguage(langArebic);

    }

    private void initParse() {

/*
		GCM ID  :  473976825494

		API KEY : AIzaSyBq0zXkoNNkfDYooLU4h5qqHcmt2I04E_A*/

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.PARSE_APP_ID))
                //  .server("http://162.13.158.45:1337/ewallts/")
                .build()
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        //	ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        /////////////////////////////////////////////////////////

    }

    public String getLanguage() {
        SharedPreferences pref = getSharedPreferences("locale", 0);
        String lang = pref.getString("lang", "en");

        return lang;

    }

    public String getLang() {
        if (isLangArebic()) {
            return "ar";
        } else {
            return "en";
        }
    }

    public String getLang_Number() {
        if (isLangArebic()) {
            return "15";
        } else {
            return "16";
        }
    }

    public void changeLanguage(Boolean lang) {
        if (lang) {
            appPreferences.saveCulturemode("2");
            SharedPreferencesManager.writeString(getApplicationContext(), "lang_new", "ar");
            SharedPreferencesManager.writeBoolean(getApplicationContext(), "lang", true);

            SharedPreferences.Editor editor = getSharedPreferences("locale", 0).edit();
            editor.putString("lang", "ar");
            editor.commit();
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, null);

        } else {
            appPreferences.saveCulturemode("1");
            SharedPreferencesManager.writeString(getApplicationContext(), "lang_new", "en");
            SharedPreferencesManager.writeBoolean(getApplicationContext(), "lang", false);

            SharedPreferences.Editor editor = getSharedPreferences("locale", 0).edit();
            editor.putString("lang", "en");
            editor.commit();
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, null);
        }


        font = globalclass.fontNormal(getLang());
        lang1 = getLang() + " " + getLang_Number();
        heding = Typeface.createFromAsset(getAssets(), globalclass.heding1(getLang()));
        typeNormal = Typeface.createFromAsset(getAssets(), globalclass.fontNormal(getLang()));
        typeRegular = Typeface.createFromAsset(getAssets(), globalclass.fontRegular(getLang()));
        typebold = Typeface.createFromAsset(getAssets(), globalclass.fontSetter(getLang()));
        typeSemibold = Typeface.createFromAsset(getAssets(), globalclass.fontMedium(getLang()));
        typeNavFont = Typeface.createFromAsset(getAssets(), globalclass.NavbaFont(getLang()));
        typeLight = Typeface.createFromAsset(getAssets(), globalclass.LightFont(getLang()));
        typeRev = Typeface.createFromAsset(getAssets(), globalclass.fontRev(getLang()));

        FontsOverride.setDefaultFont(this, "ROBO", globalclass.fontNormal(getLang()));
        FontsOverride.setDefaultFont(this, "MONOSPACE", globalclass.fontNormal(getLang()));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLanguage(SharedPreferencesManager.getBoolean(this, "lang", false));

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
