package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Get_Membership_DashboardPojo {

    @SerializedName("Ads_Details")
    @Expose
    public java.util.List<AdsDetail> adsDetails = null;

    public List<AdsDetail> getAdsDetails() {
        return adsDetails;
    }

    public void setAdsDetails(List<AdsDetail> adsDetails) {
        this.adsDetails = adsDetails;
    }

    public List<Common> getCommonList() {
        return commonList;
    }

    public void setCommonList(List<Common> commonList) {
        this.commonList = commonList;
    }

    public List<com.example.barayihsalem.UI.Model.List> getList() {
        return list;
    }

    public void setList(List<com.example.barayihsalem.UI.Model.List> list) {
        this.list = list;
    }

    public List<List2> getList2() {
        return list2;
    }

    public void setList2(List<List2> list2) {
        this.list2 = list2;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @SerializedName("Common_List")
    @Expose
    public java.util.List<Common> commonList = null;
    @SerializedName("List")
    @Expose
    public java.util.List<com.example.barayihsalem.UI.Model.List> list = null;
    @SerializedName("List2")
    @Expose
    public java.util.List<List2> list2 = null;
    @SerializedName("ResponseCode")
    @Expose
    public String responseCode;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("RedirectUrl")
    @Expose
    public String redirectUrl;
    @SerializedName("Attribute1")
    @Expose
    public String attribute1;

}
