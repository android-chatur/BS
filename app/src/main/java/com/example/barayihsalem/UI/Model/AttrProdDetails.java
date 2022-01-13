package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttrProdDetails {


    @SerializedName("Stock")
    @Expose
    private Integer stock;
    @SerializedName("OldPrice")
    @Expose
    private Double oldPrice;
    @SerializedName("NewPrice")
    @Expose
    private Double newPrice;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @SerializedName("Image1")
    @Expose
    private String image1;
    @SerializedName("Image2")
    @Expose
    private String image2;
    @SerializedName("Image3")
    @Expose
    private String image3;

}
