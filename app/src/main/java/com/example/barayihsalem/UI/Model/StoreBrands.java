package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreBrands {

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("XCode")
    @Expose
    private String xCode;
    @SerializedName("XName")
    @Expose
    private String xName;
    @SerializedName("Ordering")
    @Expose
    private Integer ordering;

}
