package com.example.barayihsalem.UI.Model;

public class ZonePojo {
    private String text;
    private boolean isSelected = false;

    public ZonePojo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
