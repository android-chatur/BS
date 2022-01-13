package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventList {
    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getEventXCode() {
        return eventXCode;
    }

    public void setEventXCode(String eventXCode) {
        this.eventXCode = eventXCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventSDate() {
        return eventSDate;
    }

    public void setEventSDate(String eventSDate) {
        this.eventSDate = eventSDate;
    }

    public String getEventEDate() {
        return eventEDate;
    }

    public void setEventEDate(String eventEDate) {
        this.eventEDate = eventEDate;
    }

    @SerializedName("Ordering")
    @Expose
    private Integer ordering;
    @SerializedName("Event_XCode")
    @Expose
    private String eventXCode;
    @SerializedName("Event_Name")
    @Expose
    private String eventName;
    @SerializedName("Event_SDate")
    @Expose
    private String eventSDate;
    @SerializedName("Event_EDate")
    @Expose
    private String eventEDate;
}
