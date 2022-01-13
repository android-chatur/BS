package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Events_PlannerPojo {

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getComp_Name() {
        return Comp_Name;
    }

    public void setComp_Name(String comp_Name) {
        Comp_Name = comp_Name;
    }

    public String getComp_License() {
        return Comp_License;
    }

    public void setComp_License(String comp_License) {
        Comp_License = comp_License;
    }

    public String getComp_License_Base64_String() {
        return Comp_License_Base64_String;
    }

    public void setComp_License_Base64_String(String comp_License_Base64_String) {
        Comp_License_Base64_String = comp_License_Base64_String;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


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

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

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
    private String trackId;


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

    String
            UserId,
    Comp_Name,
            Comp_License,
    Comp_License_Base64_String,
            Name,
    Mobile,
            Email,

    Corpcentreby,
            IpAddress,
    Command;

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    boolean  IsActive,
            IsDelete;






}
