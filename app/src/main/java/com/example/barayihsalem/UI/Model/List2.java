package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List2 {
    @SerializedName("Ordering")
    @Expose
    public Integer ordering;
    @SerializedName("Sr")
    @Expose
    public Integer sr;
    @SerializedName("Comm_SrNo")
    @Expose
    public String commSrNo;
    @SerializedName("Comm_Name")
    @Expose
    public String commName;
    @SerializedName("Comm_Color")
    @Expose
    public String commColor;

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
    }

    public String getCommSrNo() {
        return commSrNo;
    }

    public void setCommSrNo(String commSrNo) {
        this.commSrNo = commSrNo;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getCommColor() {
        return commColor;
    }

    public void setCommColor(String commColor) {
        this.commColor = commColor;
    }

    public String getCommBackgroundColor() {
        return commBackgroundColor;
    }

    public void setCommBackgroundColor(String commBackgroundColor) {
        this.commBackgroundColor = commBackgroundColor;
    }

    public String getLogoColor() {
        return logoColor;
    }

    public void setLogoColor(String logoColor) {
        this.logoColor = logoColor;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    @SerializedName("Comm_Background_Color")
    @Expose
    public String commBackgroundColor;
    @SerializedName("Logo_Color")
    @Expose
    public String logoColor;
    @SerializedName("Txt_Color")
    @Expose
    public String txtColor;
    @SerializedName("XCode")
    @Expose
    public String xCode;
    @SerializedName("XName")
    @Expose
    public String xName;
    @SerializedName("Description")
    @Expose
    public Object description;
    @SerializedName("Image")
    @Expose
    public Object image;

}
