package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("UserId")
    @Expose
    public Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getShowDashboard() {
        return isShowDashboard;
    }

    public void setShowDashboard(Boolean showDashboard) {
        isShowDashboard = showDashboard;
    }

    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Mobile")
    @Expose
    public String mobile;
    @SerializedName("Email")
    @Expose
    public String email;

    @SerializedName("IsShow_Dashboard")
    @Expose
    public Boolean isShowDashboard;
}
