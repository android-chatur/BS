package com.example.barayihsalem.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.Adapter.EventGallary_Adapter;
import com.example.barayihsalem.UI.Adapter.Membeship_Adapter;
import com.example.barayihsalem.UI.Adapter.SocialAdapter;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.VisionspinnerPojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.example.barayihsalem.UI.Comming_Events.eventback;
import static com.example.barayihsalem.UI.WellcomefamilyPage.CONNECTION_TIMEOUT;
import static com.example.barayihsalem.UI.WellcomefamilyPage.READ_TIMEOUT;

public class Booth_Event_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView aboutbs, about, aboutqw, txtlable1, er, benefit_ofevent, dxt_galary, social_media, oprated;
    AppController appController;
    AppPreferences appPreferences;
    SessionManager sessionManager;
    ArrayList<Row> Social_list = new ArrayList<Row>();

    AlertDialog alertDialog;
    TextView benefit_subheding, aboutbqwers, yes;
    String Event_Name, Event_Image, About, Benefits, EventType, Image1, Image2;
    ImageView logoside, down_arrow_benefit, up_arrow_bene;
    ImageView down_arrow_gallary, up_arrow_gallary, aboutus_recyclerview_gallery, ti11, ti12;
    ImageView down_arrow_sm, up_arrow_sm, image1, cross;
    Button proceed, procee1d;
    RecyclerView rw_gallary, rw_social;
    EventGallary_Adapter eventGallary_adapter;
    RelativeLayout abcd;
    LinearLayout lin_benefit, lin_bene, lin_gala, lin_galary, lin_soci, lin_social;
    EditText editText_work, editText_title, editText_job;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;

   /* OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onClicked(View view, int position, String type) {

          *//*  Toast.makeText(appController, "hiiii", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(Social_list.get(position).getUrl()));
            startActivity(i);*//*




        }

    };
*/



   /* OnItemClickListener.OnClickCallback onClickCall = new OnItemClickListener.OnClickCallback() {
        @Override
        public void onClicked(View view, int position, String type) {

        }

    };
*/


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void Showimage() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.image_list, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image, viewGroup, false);

        }

        cross = dialogView.findViewById(R.id.cross);
        image1 = dialogView.findViewById(R.id.image1);

        Glide.with(getApplicationContext())
                .load(getResources().getString(R.string.Imageurl_eventapproval) + Image1)

                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }


                })
                .transition(withCrossFade())
                .apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(image1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appController = (AppController) getApplicationContext();
        appPreferences = new AppPreferences(this);
        sessionManager = new SessionManager(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.vispart_color)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.vispart_color)); //status bar or the time bar at the top
        }
        //  setContentView(R.layout.activity_signup);
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


            setContentView(R.layout.activity_event_);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.activity_event_ar);


        }
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        typeLight = appController.typeLight;

        findid();


        if (Booth_Event_Activity.this.isNetworkConnected()) {
            new Get_Event_Details_By_SrNo().execute();
            new Get_Event_Details_Social_List_By_SrNo().execute();
            new Get_Event_Details_Booth_Available_By_SrNo().execute();


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

    private boolean isNetworkConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onBackPressed() {
        if (eventback == true) {
            startActivity(new Intent(Booth_Event_Activity.this, Comming_Events.class));
            finish();
        } else {
            startActivity(new Intent(Booth_Event_Activity.this, Booth_Activity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.procee1d:
                startActivity(new Intent(Booth_Event_Activity.this, Booth_Booking_Form.class));
                finish();
                break;

            case R.id.logoside:
                onBackPressed();
                break;

            case R.id.down_arrow_benefit:
                down_arrow_benefit.setVisibility(View.GONE);
                up_arrow_bene.setVisibility(View.VISIBLE);
                lin_benefit.setVisibility(View.VISIBLE);

                break;
            case R.id.lin_bene:
                down_arrow_benefit.setVisibility(View.GONE);
                up_arrow_bene.setVisibility(View.VISIBLE);
                lin_benefit.setVisibility(View.VISIBLE);

                break;

            case R.id.up_arrow_bene:
                up_arrow_bene.setVisibility(View.GONE);
                lin_benefit.setVisibility(View.GONE);
                down_arrow_benefit.setVisibility(View.VISIBLE);
                break;

            case R.id.down_arrow_gallary:
                down_arrow_gallary.setVisibility(View.GONE);
                lin_galary.setVisibility(View.VISIBLE);
                up_arrow_gallary.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_gala:
                down_arrow_gallary.setVisibility(View.GONE);
                lin_galary.setVisibility(View.VISIBLE);
                up_arrow_gallary.setVisibility(View.VISIBLE);
                break;


            case R.id.up_arrow_gallary:
                up_arrow_gallary.setVisibility(View.GONE);
                lin_galary.setVisibility(View.GONE);
                down_arrow_gallary.setVisibility(View.VISIBLE);
                break;

            case R.id.down_arrow_sm:
                down_arrow_sm.setVisibility(View.GONE);
                lin_soci.setVisibility(View.VISIBLE);
                lin_social.setVisibility(View.VISIBLE);
                up_arrow_sm.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_soci:
                down_arrow_gallary.setVisibility(View.GONE);
                lin_social.setVisibility(View.VISIBLE);
                up_arrow_gallary.setVisibility(View.VISIBLE);
                break;


            case R.id.up_arrow_sm:
                up_arrow_sm.setVisibility(View.GONE);
                lin_social.setVisibility(View.GONE);
                down_arrow_sm.setVisibility(View.VISIBLE);
                break;

        }
    }


    private void findid() {
        abcd = findViewById(R.id.abcd);
        aboutbqwers = findViewById(R.id.aboutbqwers);
        benefit_subheding = findViewById(R.id.benefit_subheding);
        lin_soci = findViewById(R.id.lin_soci);
        lin_social = findViewById(R.id.lin_social);
        lin_social.setVisibility(View.GONE);
        logoside = findViewById(R.id.logoside);
        rw_gallary = findViewById(R.id.rw_gallary);
        rw_social = findViewById(R.id.rw_social);
        aboutbs = findViewById(R.id.aboutbs);
        about = findViewById(R.id.about);
        lin_bene = findViewById(R.id.lin_bene);
        lin_gala = findViewById(R.id.lin_gala);
        lin_galary = findViewById(R.id.lin_galary);
        lin_galary.setVisibility(View.GONE);
        proceed = findViewById(R.id.proceed);
        procee1d = findViewById(R.id.procee1d);
        aboutqw = findViewById(R.id.aboutqw);
        txtlable1 = findViewById(R.id.txtlable1);
        benefit_ofevent = findViewById(R.id.benefit_ofevent);
        up_arrow_gallary = findViewById(R.id.up_arrow_gallary);
        aboutus_recyclerview_gallery = findViewById(R.id.aboutus_recyclerview_gallery);
        ti12 = findViewById(R.id.ti12);
        ti11 = findViewById(R.id.ti11);
        dxt_galary = findViewById(R.id.dxt_galary);
        social_media = findViewById(R.id.social_media);
        down_arrow_benefit = findViewById(R.id.down_arrow_benefit);
        down_arrow_gallary = findViewById(R.id.down_arrow_gallary);
        up_arrow_bene = findViewById(R.id.up_arrow_bene);
        lin_benefit = findViewById(R.id.lin_benefit);
        down_arrow_sm = findViewById(R.id.down_arrow_sm);
        up_arrow_sm = findViewById(R.id.up_arrow_sm);
        lin_benefit.setVisibility(View.GONE);
        up_arrow_bene.setVisibility(View.GONE);
        er = findViewById(R.id.er);
        aboutbs.setTypeface(typebold);
        dxt_galary.setTypeface(typebold);
        social_media.setTypeface(typebold);
        aboutbqwers.setTypeface(typebold);
        about.setTypeface(heding);
        txtlable1.setTypeface(heding);
        er.setTypeface(heding);
        benefit_subheding.setTypeface(typeRegular);
        proceed.setTypeface(typeRegular);
        procee1d.setTypeface(typeRegular);
        aboutqw.setTypeface(typeRegular);
        benefit_ofevent.setTypeface(typebold);
        logoside.setOnClickListener(this);
        procee1d.setOnClickListener(this);
        up_arrow_bene.setOnClickListener(this);
        down_arrow_benefit.setOnClickListener(this);
        lin_soci.setOnClickListener(this);
        down_arrow_gallary.setOnClickListener(this);
        up_arrow_gallary.setOnClickListener(this);
        lin_bene.setOnClickListener(this);
        lin_gala.setOnClickListener(this);
        down_arrow_sm.setOnClickListener(this);
        up_arrow_sm.setOnClickListener(this);

    }

    private void Showimage2() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView;

        //then we will inflate the custom alert dialog xml that we created
        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            dialogView = LayoutInflater.from(this).inflate(R.layout.image_list, viewGroup, false);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_image, viewGroup, false);

        }

        cross = dialogView.findViewById(R.id.cross);
        image1 = dialogView.findViewById(R.id.image1);

        Glide.with(getApplicationContext())
                .load(getResources().getString(R.string.Imageurl_eventapproval) + Image2)

                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }


                })
                .transition(withCrossFade())
                .apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(image1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.OurAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private class Get_Event_Details_By_SrNo extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    progressDialog = new ProgressDialog(Book_Location_Events.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(getResources().getString(R.string.GetMainurl));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Event_Details_By_SrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                bf.write(query);
                bf.flush();
                bf.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exceptin";
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String response) {
            Gson gson = new GsonBuilder().create();
            Log.d("Event_Detail", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);

                        Event_Name = collegeData.getString("Event_Name");
                        Event_Image = collegeData.getString("Event_Image");
                        About = collegeData.getString("About");
                        Benefits = collegeData.getString("Benefits");
                        EventType = collegeData.getString("EventType");
                        Image1 = collegeData.getString("Image1");
                        Image2 = collegeData.getString("Image2");

                        benefit_subheding.setText(Benefits);
                        aboutqw.setText(About);
                        proceed.setText(EventType);
                        aboutbs.setText(Event_Name);
                        Glide.with(getApplicationContext())
                                .load(getResources().getString(R.string.Imageurl_eventapproval) + Event_Image)

                                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }


                                })
                                .transition(withCrossFade())
                                .apply(new RequestOptions().transform(new RoundedCorners(30)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(aboutus_recyclerview_gallery);


                        Glide.with(getApplicationContext())
                                .load(getResources().getString(R.string.Imageurl_eventapproval) + Image2)

                                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }


                                })
                                .transition(withCrossFade())
                                .apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(ti12);


                        Glide.with(getApplicationContext())
                                .load(getResources().getString(R.string.Imageurl_eventapproval) + Image1)

                                //  https://www.malakprophecy.com/Upload/SubCat/59/"Pass Image Name"


                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }


                                })
                                .transition(withCrossFade())
                                .apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.back_image).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(ti11);
                        ti11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Showimage();
                            }
                        });


                        ti12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Showimage2();
                            }
                        });




                       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
                        rw_gallary.setLayoutManager(gridLayoutManager);
                        eventGallary_adapter = new EventGallary_Adapter(this, onClickCall);
                        rw_gallary.setAdapter(eventGallary_adapter);*/

                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }


        }
    }

    private class Get_Event_Details_Social_List_By_SrNo extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    progressDialog = new ProgressDialog(Book_Location_Events.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(getResources().getString(R.string.GetMainurl));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Event_Details_Social_List_By_SrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                bf.write(query);
                bf.flush();
                bf.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exceptin";
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String response) {
            Gson gson = new GsonBuilder().create();
            Log.d("Get_Event_Details_Social", response);
            //  progressDialog.d ismiss();

            VisionspinnerPojo bsNewsPojo = new Gson().fromJson(response, VisionspinnerPojo.class);
            if (bsNewsPojo.success.equals(1)) {

                Social_list.addAll(bsNewsPojo.row);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                rw_social.setLayoutManager(gridLayoutManager);
              SocialAdapter membeship_adapter = new SocialAdapter(Booth_Event_Activity.this, Social_list);
                rw_social.setAdapter(membeship_adapter);
            }else {
                Toast.makeText(appController, bsNewsPojo.message, Toast.LENGTH_SHORT).show();

            }

        }
    }

    private class Get_Event_Details_Booth_Available_By_SrNo extends AsyncTask<String, String, String> {
        //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    progressDialog = new ProgressDialog(Book_Location_Events.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(getResources().getString(R.string.GetMainurl));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("StrQuery", "Sp_Index_Mst_App'Get_Event_Details_Booth_Available_By_SrNo','" + appPreferences.get_ComingEventSRNO() + "','','','','','','" + appPreferences.getuserid() + "','" + appPreferences.getCulturemode() + "','" + appPreferences.get_company_id() + "'");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                bf.write(query);
                bf.flush();
                bf.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exceptin";
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String response) {
            Gson gson = new GsonBuilder().create();
            Log.d("Get_Event_Details_Booth_Available_By_SrNo", response);
            //  progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String emailresponse = jsonObject.getString("success");
                if (emailresponse.equals("0")) {

                } else {

                    JSONArray result = jsonObject.getJSONArray("row");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collegeData = result.getJSONObject(i);


                        String Booth_Available = collegeData.getString("Booth_Available");

                        aboutbqwers.setText(Booth_Available);
                        if (Booth_Available.equals("0")) {
                            procee1d.setVisibility(View.GONE);
                        } else {
                            procee1d.setVisibility(View.VISIBLE);
                        }


                    }
                }
            } catch (JSONException e) {
                Log.d("exception", String.valueOf(e));

            }


        }
    }


}