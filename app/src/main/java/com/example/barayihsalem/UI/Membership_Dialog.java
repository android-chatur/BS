package com.example.barayihsalem.UI;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;

import static com.example.barayihsalem.UI.WellcomefamilyPage.selectcommunity;

public class Membership_Dialog extends Dialog implements View.OnClickListener {
    AppPreferences appPreferences;
    APIInterface apiInterface;
    SessionManager sessionManager;
    TextView message, no, yes;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ProgressDialog progressDialog;
    private Object Activity;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Membership_Dialog(Context context) {
        super(context);
        appPreferences = new AppPreferences(context);
        appPreferences = new AppPreferences(context);
        sessionManager = new SessionManager(context);

        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        if (appPreferences.getCulturemode().equals("1")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            setContentView(R.layout.dialog);


        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            setContentView(R.layout.dialog_ar);


        }
        apiInterface = APIClient.getClient().create(APIInterface.class);

        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        message = findViewById(R.id.message);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);
        message.setTypeface(heding);
        no.setTypeface(heding);
        yes.setTypeface(heding);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.no:
                selectcommunity=true;
                Intent intent1 = new Intent(getContext(), WellcomefamilyPage.class);
                getContext().startActivity(intent1);

                break;
            case R.id.yes:
                Intent intent = new Intent(getContext(), WellcomefamilyPage.class);
                getContext().startActivity(intent);
                /* ((Activity))).finish();*/
            /*    startActivity(new Intent(getContext(), WellcomefamilyPage.class));
                finish();*/
                break;
        }
    }


}
