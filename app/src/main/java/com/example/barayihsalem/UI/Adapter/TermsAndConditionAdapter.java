package com.example.barayihsalem.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.barayihsalem.Helper.AppPreferences;
import com.example.barayihsalem.R;
import com.example.barayihsalem.UI.Model.TermsAndConditionDTO;

import java.util.List;

public class TermsAndConditionAdapter extends RecyclerView.Adapter<TermsAndConditionAdapter.ViewHolder> {
    AppPreferences appPreferences;
    private List<TermsAndConditionDTO> list;
    private Context context;
    // private OnItemClickListener.OnClickCallback onClickCallback;


    //   public TermsAndConditionAdapter(Context context, List<TestDTO> list, OnItemClickListener.OnClickCallback onClickCallback) {
    public TermsAndConditionAdapter(Context context, List<TermsAndConditionDTO> list) {
        this.context = context;
        this.list = list;
        //  this.onClickCallback = onClickCallback;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        appPreferences = new AppPreferences(context);
        if (appPreferences.getCulturemode().equals("1")) {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terms_and_condition, parent, false);

        } else {
            ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terms_and_condition, parent, false);

        }
        ViewHolder holder = new ViewHolder(v);
        v.setTag(holder);
        return holder;
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
       /* TermsAndConditionDTO dto = list.get(position);
        holder.tvName.setText(dto.getName());
        String plainText = Html.fromHtml(dto.getDiscription()).toString();
        holder.tvDescription.setText(plainText);*/
        //holder.llDate.setOnClickListener(new OnItemClickListener(position, onClickCallback, "ITEM"));


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        //  return list.size();
        return list.size();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItem;
        TextView tvName, tvDescription;

        public ViewHolder(View v) {
            super(v);
            llItem = v.findViewById(R.id.ll_item);
            tvName = v.findViewById(R.id.tv_name);
            tvDescription = v.findViewById(R.id.tv_description);
        }
    }


}


