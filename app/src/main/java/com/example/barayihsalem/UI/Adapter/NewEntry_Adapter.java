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
import com.example.barayihsalem.UI.Model.MyViewHolder_NewUser;
import com.example.barayihsalem.UI.Model.Row;
import com.example.barayihsalem.UI.Model.Save_Req_Initial_Approval_pojo;
import com.example.barayihsalem.UI.View.OnItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEntry_Adapter extends RecyclerView.Adapter<MyViewHolder_NewUser> {
    Context context;
    AppPreferences appPreferences;
    AppController appController;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    boolean abc = false;
    APIInterface apiInterface;

    Typeface typeSemibold, typeRegular, typeHeader, heding, normal, typebold, typeLight;
    ArrayList<Row> newUser_list = new ArrayList<>();
    private OnItemClickListener.OnClickCallback onClickCallback_Benefi;

    public NewEntry_Adapter(FragmentActivity exchage_list, ArrayList<Row> newUser_list) {
        this.context = exchage_list;
        this.newUser_list = newUser_list;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public MyViewHolder_NewUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appPreferences = new AppPreferences(context);
        sessionManager = new SessionManager(context);
        appController = (AppController) context.getApplicationContext();
        typeSemibold = appController.typeSemibold;
        typeRegular = appController.typeNormal;
        typeHeader = appController.typeNavFont;
        normal = appController.typeNormal;
        heding = appController.heding;
        typebold = appController.typebold;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // infalte the item Layout
        View v;
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newentry_list, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newentry_list_ar, parent, false);

        }

        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder_NewUser vh = new MyViewHolder_NewUser(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_NewUser holder, int position) {
        holder.description.setTypeface(typeRegular);
        holder.btn_Accept.setTypeface(typeRegular);
        holder.btn_reject.setTypeface(typeRegular);
        holder.vspart_name.setTypeface(typebold);

        holder.vspart_name.setText(newUser_list.get(position).getHeadline());
        holder.description.setText(newUser_list.get(position).getDescription());
        holder.btn_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abc = true;
                showProgress();
                Save_Req_Initial_Approval(newUser_list.get(position).getSrNo());

            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abc = false;

                showProgress();
                Save_Req_Initial_Approval(newUser_list.get(position).getSrNo());

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

    private void Save_Req_Initial_Approval(String srNo) {
        Save_Req_Initial_Approval_pojo save_req_initial_approval_pojo = new Save_Req_Initial_Approval_pojo();
        save_req_initial_approval_pojo.setSrNo(srNo);

        save_req_initial_approval_pojo.setUserId(appPreferences.getuserid());
        save_req_initial_approval_pojo.setMem_SrNo(appPreferences.get_membership_srno());
        save_req_initial_approval_pojo.setSource("Android");
        save_req_initial_approval_pojo.setApprove(abc);
        save_req_initial_approval_pojo.setCorpcentreby(appPreferences.get_company_id());
        save_req_initial_approval_pojo.setIpAddress(sessionManager.GetIpAddress());
        save_req_initial_approval_pojo.setCommand("Save");


        Call<Save_Req_Initial_Approval_pojo> call2 = apiInterface.Save_Req_Initial_Approval(save_req_initial_approval_pojo);
        call2.enqueue(new Callback<Save_Req_Initial_Approval_pojo>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<Save_Req_Initial_Approval_pojo> call, Response<Save_Req_Initial_Approval_pojo> response) {
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
            public void onFailure(Call<Save_Req_Initial_Approval_pojo> call, Throwable t) {
                call.cancel();
                hideProgress();
            }
        });


    }


    @Override
    public int getItemCount() {
        return newUser_list.size();
    }


}
