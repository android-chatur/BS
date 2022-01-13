package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {


    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getMemColor() {
        return memColor;
    }

    public void setMemColor(String memColor) {
        this.memColor = memColor;
    }

    public String getMemBackgroundColor() {
        return memBackgroundColor;
    }

    public void setMemBackgroundColor(String memBackgroundColor) {
        this.memBackgroundColor = memBackgroundColor;
    }

    @SerializedName("SrNo")
    @Expose
    public String srNo;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("VideoURL")
    @Expose
    public String videoURL;
    @SerializedName("Mem_Color")
    @Expose
    public String memColor;

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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("Mem_Background_Color")
    @Expose
    public String memBackgroundColor;


    @SerializedName("Ordering")
    @Expose
    public Integer ordering;
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
    public String image;

}
