package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.APIInterface;
import com.example.barayihsalem.Helper.AppController;
import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.SessionManager;
import com.example.barayihsalem.UI.APIClient;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Save_Req_Support;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recomded_Support_Adapter extends RecyclerView.Adapter<Recomded_Support_Adapter.MyViewHolder_procedBene> {
    Context context;
    AppPreferences appPreferences;
    ImageView next;
    APIInterface apiInterface;

    SessionManager sessionManager;
    TextView description, vspart_name;
    Button confimed;
    LinearLayout lin_membackground;
    ProgressDialog progressDialog;
    boolean abc = false;

    AppController appController;
    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<String> exchange_list = new ArrayList<>();
    ArrayList<Row> newUser_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public Recomded_Support_Adapter(FragmentActivity exchage_list, ArrayList<Row> newUser_list) {
        this.context = exchage_list;
        this.newUser_list = newUser_list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder_procedBene onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        sessionManager = new SessionManager(context);
        appController = (AppController) context.getApplicationContext();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newentry_spuuort_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newentry_spuuort_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_procedBene vh = new MyViewHolder_procedBene(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_procedBene holder, int position) {
        vspart_name.setText(newUser_list.get(position).getHeadline());
        description.setText(newUser_list.get(position).getDescription());
        confimed.setText(newUser_list.get(position).getIsConfirmedStatus());




    }





    @Override
    public int getItemCount() {
        return newUser_list.size();
    }

    public class MyViewHolder_procedBene extends RecyclerView.ViewHolder {
        public MyViewHolder_procedBene(@NonNull View itemView) {
            super(itemView);
            typeSemibold = appController.typeSemibold;
            typeRegular = appController.typeNormal;
            typeHeader = appController.typeNavFont;
            normal = appController.typeNormal;
            heding = appController.heding;
            typebold = appController.typebold;
            typeLight = appController.typeLight;
            vspart_name = itemView.findViewById(R.id.vspart_name);
            description = itemView.findViewById(R.id.description);
            confimed = itemView.findViewById(R.id.confimed);

            description.setTypeface(typeRegular);
            confimed.setTypeface(typeRegular);
            vspart_name.setTypeface(typeHeader);


            //  lin_membackground = itemView.findViewById(R.id.lin_membackground);
          /*  productname.setTypeface(heding);
            price.setTypeface(heding);
            countnolable.setTypeface(typeRegular);*/



          /*  bene_bank_name = itemView.findViewById(R.id.bene_bank_name);
            benefi_name = itemView.findViewById(R.id.benefi_name);

*/
           /* benefi_name.setTypeface(typeSemibold);

            bene_bank_name.setTypeface(typeLight);*/

        }
    }


}
