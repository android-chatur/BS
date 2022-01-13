package com.example.barayihsalem.UI.View;

import android.view.View;

public class OnItemListenerSubPosition implements View.OnClickListener {
    private int position = 0,subPosition = 0;
    private OnClickCallback onClickCallback;
    private String type = "";

    public OnItemListenerSubPosition(int position, int subPosition, OnClickCallback onClickCallback, String type) {
        this.subPosition = subPosition;
        this.position = position;
        this.onClickCallback = onClickCallback;
        this.type = type;
    }

    @Override
    public void onClick(View view) {
        onClickCallback.onClicked(view, position,subPosition, type);
    }

    public interface OnClickCallback {
        void onClicked(View view, int position, int subPosition, String type);
    }
}