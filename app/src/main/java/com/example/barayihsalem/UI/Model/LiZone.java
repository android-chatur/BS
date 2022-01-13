package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiZone {
    @SerializedName("Ordering")
    @Expose
    private Integer ordering;
    @SerializedName("EventBoothTrx_SrNo")
    @Expose
    private String eventBoothTrxSrNo;

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getEventBoothTrxSrNo() {
        return eventBoothTrxSrNo;
    }

    public void setEventBoothTrxSrNo(String eventBoothTrxSrNo) {
        this.eventBoothTrxSrNo = eventBoothTrxSrNo;
    }

    public Integer getEventBoothTrxSr() {
        return eventBoothTrxSr;
    }

    public void setEventBoothTrxSr(Integer eventBoothTrxSr) {
        this.eventBoothTrxSr = eventBoothTrxSr;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Boolean getBook() {
        return isBook;
    }

    public void setBook(Boolean book) {
        isBook = book;
    }
    boolean isSelected;
    @SerializedName("EventBoothTrx_Sr")
    @Expose
    private Integer eventBoothTrxSr;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Commission")
    @Expose
    private Double commission;
    @SerializedName("TotalPrice")
    @Expose
    private Double totalPrice;
    @SerializedName("IsBook")
    @Expose
    private Boolean isBook;

}
