package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Row;

import java.util.ArrayList;

public class Nationality_Adapter extends ArrayAdapter<String> {

    public Resources res;
    AppPreferences appPreferences;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    private Context activity;
    ArrayList<Row> Nationality_list = new ArrayList<Row>();

    //   private CountryModel tempValues = null;
    private LayoutInflater inflater;

    /*************  CustomAdapter Constructor *****************/
    public Nationality_Adapter(
            Context activitySpinner,
            int textViewResourceId,
            ArrayList objects,
            Resources resLocal
    ) {
        super(activitySpinner, textViewResourceId, objects);

        /* ********* Take passed values **********/
        activity = activitySpinner;
        Nationality_list = objects;
        res = resLocal;

        /* **********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private View getCustomView(int position, View convertView, ViewGroup parent) {

        /* ********* Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row;
        appPreferences = new AppPreferences(getContext());

        appController = (AppController) getContext().getApplicationContext();
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) activity).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            row = inflater.inflate(R.layout.countryspinner_country_new, parent, false);
        } else {
            ((Activity) activity).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


            row = inflater.inflate(R.layout.countryspinner_country_new, parent, false);
        }


        /* row = inflater.inflate(R.layout.countryspinner_country_new, parent, false);*/

        /* **** Get each Model object from Arraylist ********/
       /* tempValues = null;
        tempValues = (CountryModel) data.get(position);*/
        Typeface face = null;
        TextView label = row.findViewById(R.id.countrytxt);
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;

        label.setTypeface(typeHeader);
        label.setText(Nationality_list.get(position).xName);



      /*  Glide.with(activity)
                .load("http://sarfli.com/Upload/Flag/" + ((CountryModel) data.get(position)).getImageurl())
                //   .load("http://admin.aslambiryanihidayatcateres.in/Upload/ItemCard/1/" + images.get(position).getImage())
                .centerCrop()
                .placeholder(R.mipmap.icon_defa)
                .transform(new RoundedCorners(2))
                .into(countryimage);*/

        return row;
    }
}
