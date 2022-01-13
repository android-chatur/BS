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
import com.example.barayihsalem.UI.Model.Save_Req_Initial_Approval_pojo;
import com.example.barayihsalem.UI.Model.Save_Req_Support;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEntry_Support_Adapter extends RecyclerView.Adapter<NewEntry_Support_Adapter.MyViewHolder_procedBene> {
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

    public NewEntry_Support_Adapter(FragmentActivity exchage_list, ArrayList<Row> newUser_list) {
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

        confimed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abc = true;
                showProgress();
                Save_Req_Support(newUser_list.get(position).getSrNo());

            }
        });



    }

    private void Save_Req_Support(String srNo) {
        Save_Req_Support save_req_support = new Save_Req_Support();
        save_req_support.setSrNo(srNo);

        save_req_support.setUserId(appPreferences.getuserid());
        save_req_support.setMem_SrNo(appPreferences.get_membership_srno());
        save_req_support.setSource("Android");
        save_req_support.setConfirmed(abc);
        save_req_support.setCorpcentreby(appPreferences.get_company_id());
        save_req_support.setIpAddress(sessionManager.GetIpAddress());
        save_req_support.setCommand("Save");


        Call<Save_Req_Support> call2 = apiInterface.Save_Req_Support(save_req_support);
        call2.enqueue(new Callback<Save_Req_Support>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Save_Req_Support> call, Response<Save_Req_Support> response) {
                Log.i(getClass().getName(), "response: " + response.body());
                hideProgress();
                if (response.isSuccessful()) {

                    if (response.body().getResponseCode().equalsIgnoreCase("2")) {

                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }


                    if (response.body().getResponseCode().equalsIgnoreCase("-2")) {
//
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // TastyToast.makeText(Signup_Activity.this, response.body().getMessage(), TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                    }
                } else {
                    switch (response.code()) {
                        case 400:
                            // Toast.makeText(Signup_Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(context,
                                    "then Server side validation error...Please try again later!!!", Toast.LENGTH_SHORT).show();

                            ////      TastyToast.makeText(Signup_Activity.this, " then Server side validation error...Please try again later!!!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        case 500:
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

                            // TastyToast.makeText(Signup_Activity.this, "Something went wrong", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                            break;
                        default:
                            Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Save_Req_Support> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }

    private void showProgress() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
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
