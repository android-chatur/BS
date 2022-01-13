package com.example.barayihsalem.UI.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.Pattern;
import com.example.barayihsalem.UI.Model.Style;

import java.util.ArrayList;

public class Style_Adapter extends ArrayAdapter<String> {

    public Resources res;
    AppPreferences appPreferences;
    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    private Context activity;
    ArrayList<Style> Nationality_list = new ArrayList<Style>();

    //   private CountryModel tempValues = null;
    private LayoutInflater inflater;

    /*************  CustomAdapter Constructor *****************/
    public Style_Adapter(
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

        appPreferences = new AppPreferences(activity);

        appController = (AppController) activity.getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        appPreferences = new AppPreferences(activity);

        if (appPreferences.getCulturemode().equals("1")) {
            convertView = inflater.inflate(R.layout.countryspinner_country_new, parent, false);
        } else {
            convertView = inflater.inflate(R.layout.countryspinner_country_new, parent, false);
        }


        Typeface face = null;
        TextView label = convertView.findViewById(R.id.countrytxt);
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;

        if (Nationality_list.get(position).getFade().equals(true)){
            label.setTextColor(activity.getResources().getColor(R.color.vispart_color));
        }else {
            label.setTextColor(activity.getResources().getColor(R.color.black));

        }


        label.setTypeface(typeHeader);
        label.setText(Nationality_list.get(position).getxName());


        return convertView;

    }
}