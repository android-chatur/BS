package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Model.LoginPojo;
import com.example.barayihsalem.UI.Model.SocialPojo;
import com.example.barayihsalem.UI.Model.UserLoginpojo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.snackbar.Snackbar;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.util.Random;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.Helper.AppController.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    //CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 007;
    public static boolean login_guest;
    ViewPager viewPager;
    Typeface font;
    SignInButton signInButton;
    ImageView img_change_lang, logoside;
    String User_Email, Password;
    TextView txtlable, emal_txt, txtlable2, txtForgot, pass_txt, text_sigup, munici_corp, supported, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    Button btnLogin;
    RelativeLayout abcd;
    APIInterface apiInterface;
    GoogleSignInClient mGoogleSignInClient;
    String ipaddress;
    String Uniqueids;
    EditText editText_email_phone, edit_pswd;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ProgressDialog progressDialog;
    TwitterLoginButton twitterLoginButton;
    GoogleApiClient getmGoogleApiClient;
    String name = "";
    String lname = "";
    String twitter_email = "";
    String twitter_id = "";
    String userName_email;
    String Socialid;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Twitter.initialize(this);
        Twitter.initialize(getApplicationContext());

        TwitterConfig config = new TwitterConfig.Builder(getApplicationContext())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("ivzAqVUG7q6I8h9R6HHtsL2Vt", "1ccNlroop2fD6mB2PecBdVHQCPMHpXyqmhvyLYh4oiJ6It5yEY"))
                .debug(false)
                .build();
        Twitter.initialize(config);

        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            setContentView(R.layout.activity_login);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setContentView(R.layout.activity_login_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;


        ipaddress = UUID.randomUUID().toString();
        Uniqueids = uniquepwd();

        if (sessionManager.GetIpAddress() != null) {
            if (sessionManager.GetIpAddress().equals("") || sessionManager.GetIpAddress().equals("null")) {
                sessionManager.SetIpAddress(ipaddress);
            }
        } else {
            sessionManager.SetIpAddress(ipaddress);
        }

        if (sessionManager.GetUniqueId() != null) {
            if (sessionManager.GetUniqueId().equals("") || sessionManager.GetUniqueId().equals("null")) {
                sessionManager.SetUniqueId(Uniqueids);
            }
        } else {
            sessionManager.SetUniqueId(Uniqueids);
        }


        findid();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        getmGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, 1, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        //twiiter code
        twitterLoginButton.setCallback(new com.twitter.sdk.android.core.Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();

                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                Call<User> call = twitterApiClient.getAccountService().verifyCredentials(true, false, true);
                call.enqueue(new com.twitter.sdk.android.core.Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        User user = result.data;
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        name = "";
                        name = "";
                        Log.d("user.email", user.email);
                        Log.d("user.id", String.valueOf(user.id));
                        Log.d("screenName", user.screenName);
                        twitter_id = String.valueOf(user.id);
                        //  name=user.name;
                        String fullname = user.name;
                        if (fullname.contains(" ")) {
                            //  fullname.split(" ");
                            String[] temp = fullname.split("\\s+");
                            name = temp[0];
                            lname = temp[1];
                            // String finalName =  fName+" "+fLastName;

                        } else {
                            name = user.name;
                            lname = "-";
                        }
                        twitter_email = user.email;


                        twitter_email = user.email;
                        //  getSocialLogin_twi();

                        //   userDetailsLabel.setText("User Id : " + user.id + "\nUser Name : " + user.name + "\nEmail Id : " + user.email + "\nScreen Name : " + user.screenName);

                        //     if (isNetworkConnected()) {

                        //    new SaveUserRegistration_Facebook1().execute();
                        //  } else {
                           /* Snackbar snackbar = Snackbar
                                    .make(abcd, "No internet connection!", Snackbar.LENGTH_LONG)
                                    .setAction("RETRY", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            // Changing message text color
                            snackbar.setActionTextColor(Color.RED);

                            // Changing action button text color
                            View sbView = snackbar.getView();
                            // TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                            textView.setTextColor(Color.YELLOW);
                            snackbar.show();
                        }*/

                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(getApplicationContext(), "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
                //fetchTwitterEmail(twitterSession);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_LONG).show();
            }
        });


    }



   /* GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //callbackManager.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                handleGPlusSignInResult(result);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onPause() {
        super.onPause();
        getmGoogleApiClient.stopAutoManage(LoginActivity.this);
        getmGoogleApiClient.disconnect();
    }

    private void handleGPlusSignInResult(GoogleSignInResult result) {
        Log.d(TwitterCore.TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //  name = acct.getDisplayName();


            name = acct.getDisplayName();

            /*if (fullname.contains(" ")) {
                //  fullname.split(" ");
                String[] temp = fullname.split("\\s+");
                name = temp[0];
                lname = temp[1];
                Toast.makeText(appController, name+" "+lname, Toast.LENGTH_SHORT).show();
                // String finalName =  fName+" "+fLastName;

            } else {
                name = acct.getDisplayName();
                lname = "-";
            }
*/

            String personName11 = acct.getGivenName();

            // String personPhotoUrl = acct.getPhotoUrl().toString();
            userName_email = acct.getEmail();
            Socialid = acct.getId();

            String familyName = acct.getFamilyName();


            if (isNetworkConnected()) {

                showProgress();
                String mobile_ = "00000";
                UserLoginFromFacebook("google", mobile_);

                //  new SaveUserRegistration_Facebook1().execute();
            } else {
                Snackbar snackbar = Snackbar
                        .make(abcd, "No internet connection!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();
                // TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }


        }
    }

    private void UserLoginFromFacebook(String google, String mobile_) {
        SocialPojo socialPojo = new SocialPojo();


        socialPojo.setSocialId(Socialid);
        socialPojo.setSocialId_Description(google);
        socialPojo.setEmail(userName_email);
        socialPojo.setName(name);
        socialPojo.setMobile(mobile_);
        socialPojo.setPassword(sessionManager.GetUniqueId());
        socialPojo.setConfirmPassword(sessionManager.GetUniqueId());
        socialPojo.setIpAddress(sessionManager.GetIpAddress());
        socialPojo.setSource("Android");


        Call<SocialPojo> call2 = apiInterface.UserLoginFromFacebook(socialPojo);
        call2.enqueue(new Callback<SocialPojo>() {
            @Override
            public void onResponse(Call<SocialPojo> call, Response<SocialPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        UserLogin_Social(Socialid);

                        /*sessionManager.createLoginSession(String.valueOf(response.body().userDetails.getUserId()), response.body().userDetails.getName(), response.body().userDetails.getMobile(), response.body().userDetails.getEmail(), Password);

                        appPreferences.saveuserid(String.valueOf(response.body().userDetails.getUserId()));
                        appPreferences.save_Fname(response.body().userDetails.getName());
                        appPreferences.save_mob(response.body().userDetails.getMobile());
                        appPreferences.save_email(response.body().userDetails.getEmail());
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        boolean islogin = response.body().userDetails.getShowDashboard();


                        if (islogin == true) {
                            appPreferences.saveHomepage(true);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            appPreferences.saveHomepage(false);
                            startActivity(new Intent(LoginActivity.this, WellcomefamilyPage.class));
                            finish();
                        }
*/
                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("4")) {
//
                       // Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        UserLogin_Social(Socialid);

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }


                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void UserLogin_Social(String socialid) {
        UserLoginpojo userLoginpojo = new UserLoginpojo();


        userLoginpojo.setSocialId(socialid);


        Call<UserLoginpojo> call2 = apiInterface.UserLogin_Social(userLoginpojo);
        call2.enqueue(new Callback<UserLoginpojo>() {
            @Override
            public void onResponse(Call<UserLoginpojo> call, Response<UserLoginpojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {



                        sessionManager.createLoginSession(String.valueOf(response.body().userDetails.getUserId()), response.body().userDetails.getName(), response.body().userDetails.getMobile(), response.body().userDetails.getEmail(), Password);

                        appPreferences.saveuserid(String.valueOf(response.body().userDetails.getUserId()));
                        appPreferences.save_Fname(response.body().userDetails.getName());
                        appPreferences.save_mob(response.body().userDetails.getMobile());
                        appPreferences.save_email(response.body().userDetails.getEmail());
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        boolean islogin = response.body().userDetails.getShowDashboard();


                        if (islogin == true) {
                            appPreferences.saveHomepage(true);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            appPreferences.saveHomepage(false);
                            startActivity(new Intent(LoginActivity.this, WellcomefamilyPage.class));
                            finish();
                        }
                    }



                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
//
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }


                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginpojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }


    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(getmGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }


    public void BtnLoginWithTwitterClicked(View view) {
        twitterLoginButton.performClick();

    }

    public void BtnLoginWithGooglerClicked(View view) {
        signIn();

    }


    private void findid() {
        // Set the dimensions of the sign-in button.
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        img_change_lang = findViewById(R.id.img_change_lang);
        twitterLoginButton = findViewById(R.id.twitterLoginButton);
        logoside = findViewById(R.id.logoside);
        text_sigup = findViewById(R.id.text_sigup);
        text_sigup.setTypeface(typeRegular);
        txtlable = findViewById(R.id.txtlable);
        txtlable2 = findViewById(R.id.txtlable2);
        txtlable.setTypeface(typeRegular);
        txtlable2.setTypeface(typeRegular);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setTypeface(typeRegular);
        emal_txt = findViewById(R.id.emal_txt);
        abcd = findViewById(R.id.abcd);
        emal_txt.setTypeface(normal);
        editText_email_phone = findViewById(R.id.editText_email_phone);
        editText_email_phone.setTypeface(typeRegular);
        edit_pswd = findViewById(R.id.edit_pswd);
        edit_pswd.setTypeface(typeRegular);
        text_sigup = findViewById(R.id.text_sigup);
        txtForgot = findViewById(R.id.txtForgot);
        txtForgot.setTypeface(typeRegular);
        pass_txt = findViewById(R.id.pass_txt);
        pass_txt.setTypeface(typeRegular);
        text_sigup.setTypeface(typeRegular);
        String name;
        String surName;
        if (appPreferences.getCulturemode().equals("1")) {
            name = getColoredSpanned("Not a member?", "#9A090606");
            surName = getColoredSpanned("Sign Up", "#03a9f4");
        } else {
            name = getColoredSpanned("لست عضوًا؟ ", "#9A090606");
            surName = getColoredSpanned(" اشترك", "#03a9f4");
        }


        text_sigup.setText(Html.fromHtml(name + " " + surName));
        text_sigup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
        img_change_lang.setOnClickListener(this);
        logoside.setOnClickListener(this);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }


    public String uniquepwd() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }

    public String uniqueid() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_sigup:
                startActivity(new Intent(LoginActivity.this, Signup_Activity.class));
                finish();
                break;

            case R.id.btnLogin:
                if (LoginActivity.this.isNetworkConnected()) {
                    if (validate()) {
                        showProgress();
                        UserLogin();
                    }
                } else {
                    Snackbar snackbar = Snackbar
                            .make(abcd, "No internet connection!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    // TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
                }
                break;

            case R.id.txtForgot:
                startActivity(new Intent(LoginActivity.this, Forgot_Password.class));
                finish();
                break;

            case R.id.img_change_lang:
                changeLanguage();
                break;
            case R.id.logoside:
                appPreferences.save_DrawerBack("login");
                startActivity(new Intent(LoginActivity.this, DraweActivity.class));
                finish();
                break;

        }
    }

    private void UserLogin() {
        LoginPojo loginPojo = new LoginPojo();


        loginPojo.setEmail(User_Email);
        loginPojo.setPassword(Password);


        Call<LoginPojo> call2 = apiInterface.Save_UserLogin(loginPojo);
        call2.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
                        sessionManager.createLoginSession(String.valueOf(response.body().userDetails.getUserId()), response.body().userDetails.getName(), response.body().userDetails.getMobile(), response.body().userDetails.getEmail(), Password);

                        appPreferences.saveuserid(String.valueOf(response.body().userDetails.getUserId()));
                        appPreferences.save_Fname(response.body().userDetails.getName());
                        appPreferences.save_mob(response.body().userDetails.getMobile());
                        appPreferences.save_email(response.body().userDetails.getEmail());
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        boolean islogin = response.body().userDetails.getShowDashboard();


                        if (islogin == true) {
                            appPreferences.saveHomepage(true);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            appPreferences.saveHomepage(false);
                            startActivity(new Intent(LoginActivity.this, WellcomefamilyPage.class));
                            finish();
                        }

                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
//
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }


                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    private boolean validate() {

        User_Email = editText_email_phone.getText().toString();
        Password = edit_pswd.getText().toString();
        //  DOB = tx_Date_of_Birth.getText().toString();


        if (TextUtils.isEmpty(editText_email_phone.getText().toString())) {
            editText_email_phone.setError("Enter Your Register E-mail");
            editText_email_phone.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email_phone.getText().toString())) {
            editText_email_phone.setError("Invalid Email Address");
            editText_email_phone.requestFocus();
            return false;


        }

        if (TextUtils.isEmpty(edit_pswd.getText().toString())) {
            edit_pswd.setError("Please Enter Password");
            edit_pswd.requestFocus();
            return false;
        }
        if (Password.length() < 5) {
            edit_pswd.setError("Please Enter valid Password");
            edit_pswd.requestFocus();
            return false;
        }
        return true;
    }


    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    private void changeLanguage() {
        if (appController.isLangArebic()) {
            appController.changeLanguage(false);
            startActivity(getIntent());
            finish();
        } else {
            appController.changeLanguage(true);


            startActivity(getIntent());
            finish();
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}