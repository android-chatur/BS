package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
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

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.Helper.MyApplication;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Model.RegistrationPojo;
import com.example.barayihsalem.UI.Model.SocialPojo;
import com.example.barayihsalem.UI.Model.UserLoginpojo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.snackbar.Snackbar;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barayihsalem.Helper.AppController.TAG;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    AppController appController;
    AppPreferences appPreferences;
    ProgressDialog progressDialog;

    SessionManager sessionManager;
    String Name, Mobile, Email, Password;
    EditText editText_name, editText_mob, editText_email, editText_password;
    TextView desinrt, name_txt, num_txt, email_txt, passwod_txt, txtlable2;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    Button btnLogin;
    RelativeLayout abcd;
    APIInterface apiInterface;
    TwitterLoginButton twitterLoginButton;
    SignInButton signInButton;
    GoogleApiClient getmGoogleApiClient;

    //CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 007;

    String name = "";
    String lname = "";
    String twitter_email = "";
    String twitter_id = "";
    String userName_email;
    String Socialid;
    ImageView logoside;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        Twitter.initialize(getApplicationContext());
        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

      /*  if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white)); //status bar or the time bar at the top
        }
*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.activity_signup_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_signup_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;
        find();
        apiInterface = APIClient.getClient().create(APIInterface.class);
//google

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
                String  mobile_="00000";
                UserLoginFromFacebook("google",mobile_);

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
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        boolean islogin = response.body().userDetails.getShowDashboard();


                        if (islogin == true) {
                            appPreferences.saveHomepage(true);
                            startActivity(new Intent(appController, HomeActivity.class));
                            finish();
                        } else {
                            appPreferences.saveHomepage(false);
                            startActivity(new Intent(appController, WellcomefamilyPage.class));
                            finish();
                        }
                    }



                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
//
                        Toast.makeText(appController, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }


                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(appController,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(appController, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(appController, "Unknown Error", Toast.LENGTH_SHORT).show();
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



    public void BtnLoginWithTwitterClicked(View view) {
        twitterLoginButton.performClick();

    }

    public void BtnLoginWithGooglerClicked(View view) {
        signIn();

    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(getmGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void find() {
        signInButton = findViewById(R.id.sign_in_button);
        twitterLoginButton = findViewById(R.id.twitterLoginButton);
        twitterLoginButton = findViewById(R.id.twitterLoginButton);
        abcd = findViewById(R.id.abcd);
        logoside = findViewById(R.id.logoside);
        num_txt = findViewById(R.id.num_txt);
        email_txt = findViewById(R.id.email_txt);
        editText_password = findViewById(R.id.editText_password);
        txtlable2 = findViewById(R.id.txtlable2);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_mob = findViewById(R.id.editText_mob);
        editText_name = findViewById(R.id.editText_name);
        desinrt = findViewById(R.id.desinrt);
        name_txt = findViewById(R.id.name_txt);
        btnLogin = findViewById(R.id.btnLogin);
        logoside.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        desinrt.setTypeface(typeHeader);
        name_txt.setTypeface(normal);
        num_txt.setTypeface(normal);
        email_txt.setTypeface(normal);
        editText_password.setTypeface(normal);
        editText_name.setTypeface(typeRegular);
        editText_mob.setTypeface(typeRegular);
        editText_email.setTypeface(typeRegular);
        editText_password.setTypeface(typeRegular);
        btnLogin.setTypeface(typeRegular);
        txtlable2.setTypeface(normal);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Signup_Activity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
             /*   startActivity(new Intent(Signup_Activity.this,LoginActivity.class));
                finish();*/

                if (Signup_Activity.this.isNetworkConnected()) {

                    if (validate()) {
                        showProgress();
                        SaveUserRegistration();
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

            case R.id.logoside:
                onBackPressed();
                break;
        }
    }

    private void SaveUserRegistration() {
        RegistrationPojo sinup_detail = new RegistrationPojo();


        sinup_detail.setName(Name);
        sinup_detail.setMobile(Mobile);
        sinup_detail.setEmail(Email);
        sinup_detail.setPassword(Password);
        sinup_detail.setConfirmPassword(Password);
        sinup_detail.setSource("Android");
        sinup_detail.setIpAddress(sessionManager.GetIpAddress());

        Call<RegistrationPojo> call2 = apiInterface.Save_UserRegistration(sinup_detail);
        call2.enqueue(new Callback<RegistrationPojo>() {
            @Override
            public void onResponse(Call<RegistrationPojo> call, Response<RegistrationPojo> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {
//
                        Toast.makeText(Signup_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Signup_Activity.this, LoginActivity.class));
                        finish();
                        //   TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }

                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(Signup_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        //   TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }
                    if (response.body().getResponseCode().equalsIgnoreCase("-4")) {
//
                        Toast.makeText(Signup_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }


                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Signup_Activity.this,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(Signup_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(Signup_Activity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RegistrationPojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(Signup_Activity.this);
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

        Name = editText_name.getText().toString();
        Mobile = editText_mob.getText().toString();
        Email = editText_email.getText().toString();
        Password = editText_password.getText().toString();
        //  DOB = tx_Date_of_Birth.getText().toString();


        if (TextUtils.isEmpty(editText_name.getText().toString())) {

            editText_name.setError("Please Enter Name");
            editText_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editText_mob.getText().toString())) {

            editText_mob.setError("Please Enter Mobile Number");
            editText_mob.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(editText_email.getText().toString())) {
            editText_email.setError("Enter Your  E-mail");
            editText_email.requestFocus();
            return false;
        } else if (!MyApplication.isEmailValid(editText_email.getText().toString())) {
            editText_email.setError("Invalid Email Address");
            editText_email.requestFocus();
            return false;


        }

        if (TextUtils.isEmpty(editText_password.getText().toString())) {

            editText_password.setError("Please Enter Password");
            editText_password.requestFocus();
            return false;
        }
        if (editText_password.getText().toString().length() < 5) {
            editText_password.setError("Please Enter valid Password");
            editText_password.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}