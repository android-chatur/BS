package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Get_Store_Details_Pojo {
    String
            Value;
    String Value1;
    String Value2;

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
    }

    String UserId;
    String CultureId;



    @SerializedName("Store_Details")
    @Expose
    private StoreDetails storeDetails;
    @SerializedName("Store_Tag")
    @Expose
    private StoreTag storeTag;
    @SerializedName("Store_Featured_ItemList")
    @Expose
    private java.util.List<StoreFeaturedItem> storeFeaturedItemList = null;
    @SerializedName("Store_MenuList")
    @Expose
    private java.util.List<StoreMenu> storeMenuList = null;
    @SerializedName("Store_Time_Info")
    @Expose
    private java.util.List<StoreTimeInfo> storeTimeInfo = null;
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("RedirectUrl")
    @Expose
    private String redirectUrl;
    @SerializedName("Attribute1")
    @Expose
    private String attribute1;

    public StoreDetails getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(StoreDetails storeDetails) {
        this.storeDetails = storeDetails;
    }

    public StoreTag getStoreTag() {
        return storeTag;
    }

    public void setStoreTag(StoreTag storeTag) {
        this.storeTag = storeTag;
    }

    public java.util.List<StoreFeaturedItem> getStoreFeaturedItemList() {
        return storeFeaturedItemList;
    }

    public void setStoreFeaturedItemList(java.util.List<StoreFeaturedItem> storeFeaturedItemList) {
        this.storeFeaturedItemList = storeFeaturedItemList;
    }

    public java.util.List<StoreMenu> getStoreMenuList() {
        return storeMenuList;
    }

    public void setStoreMenuList(java.util.List<StoreMenu> storeMenuList) {
        this.storeMenuList = storeMenuList;
    }

    public java.util.List<StoreTimeInfo> getStoreTimeInfo() {
        return storeTimeInfo;
    }

    public void setStoreTimeInfo(java.util.List<StoreTimeInfo> storeTimeInfo) {
        this.storeTimeInfo = storeTimeInfo;
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




    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCultureId() {
        return CultureId;
    }

    public void setCultureId(String cultureId) {
        CultureId = cultureId;
    }

    public List<StoreBrands> getStoreBrandsList() {
        return storeBrandsList;
    }

    public void setStoreBrandsList(List<StoreBrands> storeBrandsList) {
        this.storeBrandsList = storeBrandsList;
    }

    public String getCorpcentreBy() {
        return CorpcentreBy;
    }

    public void setCorpcentreBy(String corpcentreBy) {
        CorpcentreBy = corpcentreBy;
    }

    String CorpcentreBy;


    @SerializedName("Store_Brands_List")
    @Expose
    private java.util.List<StoreBrands> storeBrandsList = null;



}
