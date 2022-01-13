package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seek_Job_Pojo {
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
    String
            UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getSeek_SrNo() {
        return Seek_SrNo;
    }

    public void setSeek_SrNo(String seek_SrNo) {
        Seek_SrNo = seek_SrNo;
    }

    public String getCV_Name() {
        return CV_Name;
    }

    public void setCV_Name(String CV_Name) {
        this.CV_Name = CV_Name;
    }

    public String getCV_Base64_String() {
        return CV_Base64_String;
    }

    public void setCV_Base64_String(String CV_Base64_String) {
        this.CV_Base64_String = CV_Base64_String;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getCorpcentreby() {
        return Corpcentreby;
    }

    public void setCorpcentreby(String corpcentreby) {
        Corpcentreby = corpcentreby;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    String Seek_SrNo;
    String CV_Name;
    String CV_Base64_String;
    String Source;
    String IpAddress;
    String Corpcentreby;
    String Command;
}
