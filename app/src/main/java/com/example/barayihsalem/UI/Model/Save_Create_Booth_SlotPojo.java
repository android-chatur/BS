package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Save_Create_Booth_SlotPojo {

    String
            Event_SrNo;
    String UserId;
    String Corpcentreby;

    public String getEvent_SrNo() {
        return Event_SrNo;
    }

    public void setEvent_SrNo(String event_SrNo) {
        Event_SrNo = event_SrNo;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCorpcentreby() {
        return Corpcentreby;
    }

    public void setCorpcentreby(String corpcentreby) {
        Corpcentreby = corpcentreby;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public List getEvents_Booth() {
        return Events_Booth;
    }

    public void setEvents_Booth(List events_Booth) {
        Events_Booth = events_Booth;
    }

    public List getEvents_BoothTrx() {
        return Events_BoothTrx;
    }

    public void setEvents_BoothTrx(List events_BoothTrx) {
        Events_BoothTrx = events_BoothTrx;
    }

    String IpAddress;
    String Command;
    List Events_Booth,
            Events_BoothTrx;




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
    @SerializedName("TrackId")
    @Expose
    private Object trackId;

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

    public Object getTrackId() {
        return trackId;
    }

    public void setTrackId(Object trackId) {
        this.trackId = trackId;
    }
}
