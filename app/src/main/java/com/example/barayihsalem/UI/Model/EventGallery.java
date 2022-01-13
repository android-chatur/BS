package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventGallery {

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
    }

    public String getEventXCode() {
        return eventXCode;
    }

    public void setEventXCode(String eventXCode) {
        this.eventXCode = eventXCode;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @SerializedName("Sr")
    @Expose
    private Integer sr;
    @SerializedName("Event_XCode")
    @Expose
    private String eventXCode;
    @SerializedName("Image1")
    @Expose
    private String image1;
}
