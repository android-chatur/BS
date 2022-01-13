package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialPojo {
    public String getSocialId() {
        return SocialId;
    }

    public void setSocialId(String socialId) {
        SocialId = socialId;
    }

    public String getSocialId_Description() {
        return SocialId_Description;
    }

    public void setSocialId_Description(String socialId_Description) {
        SocialId_Description = socialId_Description;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
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

    String
            SocialId,
    SocialId_Description,
            Name,
    Mobile,
            Email,
    Password,
            ConfirmPassword,
    Source,
            IpAddress;

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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("RedirectUrl")
    @Expose
    public String redirectUrl;
    @SerializedName("Attribute1")
    @Expose
    public Object attribute1;
    @SerializedName("UserDetails")
    @Expose
    public UserDetails userDetails;

    @SerializedName("ResponseCode")
    @Expose
    public String responseCode;
}
