package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordPojo {

    String Email;

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

    public Object getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(Object attribute1) {
        this.attribute1 = attribute1;
    }

    public Object getTrackId() {
        return trackId;
    }

    public void setTrackId(Object trackId) {
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
    private Object attribute1;
    @SerializedName("TrackId")
    @Expose
    private Object trackId;
}
