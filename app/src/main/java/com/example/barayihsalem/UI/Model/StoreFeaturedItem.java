package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreFeaturedItem {
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
    @SerializedName("Image1")
    @Expose
    private String image1;
    @SerializedName("OldPrice")
    @Expose
    private Double oldPrice;
    @SerializedName("NewPrice")
    @Expose
    private Double newPrice;
    @SerializedName("Currency")
    @Expose
    private String currency;
}
