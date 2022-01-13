package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreDetails {


    public String getStoreSrNo() {
        return storeSrNo;
    }

    public void setStoreSrNo(String storeSrNo) {
        this.storeSrNo = storeSrNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getStoreMinOrder() {
        return storeMinOrder;
    }

    public void setStoreMinOrder(Double storeMinOrder) {
        this.storeMinOrder = storeMinOrder;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getStoreDeliveryCharge() {
        return storeDeliveryCharge;
    }

    public void setStoreDeliveryCharge(Double storeDeliveryCharge) {
        this.storeDeliveryCharge = storeDeliveryCharge;
    }

    public Integer getStoreDeliveryTime() {
        return storeDeliveryTime;
    }

    public void setStoreDeliveryTime(Integer storeDeliveryTime) {
        this.storeDeliveryTime = storeDeliveryTime;
    }

    public String getTimeInWords() {
        return timeInWords;
    }

    public void setTimeInWords(String timeInWords) {
        this.timeInWords = timeInWords;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Double getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Double ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getStoreCountry() {
        return storeCountry;
    }

    public void setStoreCountry(String storeCountry) {
        this.storeCountry = storeCountry;
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    @SerializedName("Store_SrNo")
    @Expose
    private String storeSrNo;
    @SerializedName("Store_Name")
    @Expose
    private String storeName;
    @SerializedName("Store_MinOrder")
    @Expose
    private Double storeMinOrder;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Store_Delivery_Charge")
    @Expose
    private Double storeDeliveryCharge;
    @SerializedName("Store_Delivery_Time")
    @Expose
    private Integer storeDeliveryTime;
    @SerializedName("Time_In_Words")
    @Expose
    private String timeInWords;
    @SerializedName("Store_Logo")
    @Expose
    private String storeLogo;
    @SerializedName("Rating_Star")
    @Expose
    private Double ratingStar;
    @SerializedName("Ratings")
    @Expose
    private Integer ratings;
    @SerializedName("Store_Address")
    @Expose
    private String storeAddress;
    @SerializedName("Store_Area")
    @Expose
    private String storeArea;
    @SerializedName("Store_Country")
    @Expose
    private String storeCountry;
    @SerializedName("Store_Mobile")
    @Expose
    private String storeMobile;
}
