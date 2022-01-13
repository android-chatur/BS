package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListZone {
    public String getBoothType() {
        return boothType;
    }

    public void setBoothType(String boothType) {
        this.boothType = boothType;
    }

    public String getBoothTypeName() {
        return boothTypeName;
    }

    public void setBoothTypeName(String boothTypeName) {
        this.boothTypeName = boothTypeName;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @SerializedName("BoothType")
    @Expose
    private String boothType;
    @SerializedName("BoothType_Name")
    @Expose
    private String boothTypeName;
    @SerializedName("Zone")
    @Expose
    private Zone zone;


}
