package com.example.barayihsalem;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String KEY_UserId = "user_id";
    public static final String userID = "userID";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String KEY_Country = "Country";
    public static final String KEY_Email = "email";
    public static final String KEY_Password = "password";
    public static final String KEY_Language = "Language";
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String UniqueId = "UniqueId";
    private static final String KEY_IPADDRESS = "ipaddress";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String HOME_COUNTRY_ID = "home_country_id";
    private static final String Posi = "posi";
    private static final String XCODE_Value = "xcode_value";
    private static final String HOME_COUNTRY_Nmae = "home_country_name";
    private static final String SETCURRENCY = "SetCurrency";
    private static final String SetTwitter_username = "SetTwitter_username";
    private static final String Image1 = "Image1";
    private static final String Image2 = "Image2";
    private static final String Image3 = "Image3";
    private static final String Image4 = "Image4";
    private static final String Set_product_result = "Set_product_result";
    private static final String HOME_COUNTRY_NAME = "home_country_name";
    private static final String DelSrN0 = "delsrn0";
    private static final String SETSUBTOTAL = "SetSubtotal";
    private static final String GRANTOTAL = "grantotal";
    private static final String CURRENCY = "currency";
    SharedPreferences pref, pref_lang;
    // Editor for Shared preferences
    SharedPreferences.Editor editor, editor_lang;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public void createLoginSession(String userID, String firstName,  String mobile,String email, String password) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(userID, userID);

        editor.putString(firstName, firstName);
        editor.putString(mobile, mobile);
        //  editor.putString(KEY_Country, country);

        editor.putString(KEY_Email, email);
        editor.putString(KEY_Password, password);

        // commit changes
        editor.commit();
    }
    public void SetUniqueId(String f) {
        editor.putString(UniqueId, f);
        editor.commit();
    }
    public String GetUniqueId() {
        return pref.getString(UniqueId, null);
    }
    public void SetIpAddress(String f) {
        editor.putString(KEY_IPADDRESS, f);
        editor.commit();
    }
    public String GetIpAddress() {
        return pref.getString(KEY_IPADDRESS, null);
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences

        editor.putBoolean(IS_LOGIN, false);
        // editor.clear();
        editor.commit();

    }
}
