package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color {

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getxCode() {
        return xCode;
    }

    public void setxCode(String xCode) {
        this.xCode = xCode;
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public Boolean getFade() {
        return isFade;
    }

    public void setFade(Boolean fade) {
        isFade = fade;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @SerializedName("Ordering")
    @Expose
    private Integer ordering;
    @SerializedName("XCode")
    @Expose
    private String xCode;
    @SerializedName("XName")
    @Expose
    private String xName;
    @SerializedName("IsFade")
    @Expose
    private Boolean isFade;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;

}
